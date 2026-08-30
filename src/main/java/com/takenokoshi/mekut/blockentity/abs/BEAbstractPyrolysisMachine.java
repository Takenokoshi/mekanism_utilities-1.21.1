package com.takenokoshi.mekut.blockentity.abs;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.blockentity.base.BEExpScaledRecipeMachine;
import com.takenokoshi.mekaddonlib.blockentity.component.EjectorComponentUtils;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.inputcache.MekALSingleInputRecipeCache;
import com.takenokoshi.mekaddonlib.recipe.lookup.IMekALRecipeTypedLookupHandler;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.inventory.slot.ChemicalFillConvertOrSupplyingSlot;
import com.takenokoshi.mekut.recipe.cached.ChemicalToBiChemicalCachedRecipe;
import com.takenokoshi.mekut.recipe.input.AdvancedChemicalInputHandler;
import com.takenokoshi.mekut.recipe.recipe.prefab.ChemicalToBiChemicalRecipe;
import com.takenokoshi.mekut.recipe_viewer.type.MekUtRecipeViewerRecipeType;
import com.takenokoshi.mekut.registries.MekUtRecipeTypes;

import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.ChemicalSlotInfo;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BEAbstractPyrolysisMachine extends BEExpScaledRecipeMachine<ChemicalToBiChemicalRecipe>
        implements
        IMekALRecipeTypedLookupHandler<ChemicalToBiChemicalRecipe, MekALSingleInputRecipeCache.MekALSingleChemical<ChemicalToBiChemicalRecipe>>,
        IRecipeViewerTypeProvider {

    public static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);

    public static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.CHEMICAL, AttachedSideConfig.LightConfigInfo.TWO_OUTPUT);
        configInfo.put(TransmissionType.ENERGY, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.MACHINE);
        return new AttachedSideConfig(configInfo);
    });

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(long chemicalTankCapacity) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addBasic(3)
                    .addEnergy()
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(chemicalTankCapacity)
                    .addBasic(chemicalTankCapacity)
                    .addBasic(chemicalTankCapacity)
                    .build());
        };
    }

    protected IChemicalTank inputTank, mainOutputTank, secondaryOutputTank;
    protected MachineEnergyContainer<?> energyContainer;
    protected ChemicalFillConvertOrSupplyingSlot inputSlot;
    protected ChemicalInventorySlot mainOutputSlot, secondaryOutputSlot;
    protected EnergyInventorySlot energySlot;

    protected final AdvancedChemicalInputHandler inputHandler;
    protected final IOutputHandler<ChemicalStack> mainOutputHandler, secondaryOutputHandler;

    public BEAbstractPyrolysisMachine(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baselineMaxOperations);
        var chemicalConfig = configComponent.getConfig(TransmissionType.CHEMICAL);
        chemicalConfig.addSlotInfo(DataType.INPUT, new ChemicalSlotInfo(true, false, List.of(inputTank)));
        chemicalConfig.addSlotInfo(DataType.OUTPUT_1, new ChemicalSlotInfo(false, true, List.of(mainOutputTank)));
        chemicalConfig.addSlotInfo(DataType.OUTPUT_2, new ChemicalSlotInfo(false, true, List.of(secondaryOutputTank)));
        chemicalConfig.addSlotInfo(DataType.INPUT_OUTPUT,
                new ChemicalSlotInfo(true, true, List.of(inputTank, mainOutputTank, secondaryOutputTank)));
        chemicalConfig.setDataType(DataType.OUTPUT_1, RelativeSide.LEFT);
        chemicalConfig.setDataType(DataType.OUTPUT_2, RelativeSide.RIGHT);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);
        configComponent.setupItemIOConfig(
                List.of(inputSlot, mainOutputSlot, secondaryOutputSlot),
                List.of(inputSlot, mainOutputSlot, secondaryOutputSlot), energySlot, false);
        ejectorComponent = new TileComponentEjector(this, () -> Long.MAX_VALUE).setOutputData(configComponent,
                TransmissionType.CHEMICAL, TransmissionType.ITEM);
        EjectorComponentUtils.setCanChemicalTankEject(ejectorComponent, (type, tank) -> tank != inputTank);
        this.inputHandler = AdvancedChemicalInputHandler.create(inputTank, RecipeError.NOT_ENOUGH_INPUT);
        this.mainOutputHandler = OutputHelper.getOutputHandler(mainOutputTank, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
        this.secondaryOutputHandler = OutputHelper.getOutputHandler(secondaryOutputTank,
                RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
        this.inputSlot.setSupplyingStackSetter(this.inputHandler::setSuppliedStack);
    }

    @Override
    protected @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(
                inputTank = BasicChemicalTank.inputModern(initChemicalTankCapacity(), this::containsRecipe,
                        recipeCacheListener));
        builder.addTank(
                mainOutputTank = BasicChemicalTank.output(initChemicalTankCapacity(), recipeCacheUnpauseListener));
        builder.addTank(
                secondaryOutputTank = BasicChemicalTank.output(initChemicalTankCapacity(), recipeCacheUnpauseListener));
        return builder.build();
    }

    protected abstract long initChemicalTankCapacity();

    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(energyContainer = MachineEnergyContainer.input(this, recipeCacheUnpauseListener));
        return builder.build();
    }

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(inputSlot = ChemicalFillConvertOrSupplyingSlot.create(inputTank, this::getLevel,
                recipeCacheListener, 50, 56))
                .setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(mainOutputSlot = ChemicalInventorySlot.drain(mainOutputTank,
                listener, 89, 56))
                .setSlotOverlay(SlotOverlay.PLUS);
        builder.addSlot(secondaryOutputSlot = ChemicalInventorySlot.drain(secondaryOutputTank,
                listener, 152, 56))
                .setSlotOverlay(SlotOverlay.PLUS);
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel,
                listener, 152, 14));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        inputSlot.fillTankOrConvert();
        mainOutputSlot.drainTank();
        secondaryOutputSlot.drainTank();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return sendUpdatePacket;
    }

    @Override
    public @NotNull ICachedRecipe<ChemicalToBiChemicalRecipe> createNewCachedRecipe(
            @NotNull ChemicalToBiChemicalRecipe recipe, int cacheIndex) {
        return new ChemicalToBiChemicalCachedRecipe(recipe, recheckAllRecipeErrors, inputHandler, mainOutputHandler,
                secondaryOutputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setOnFinish(this::markForSave)
                .setBaselineMaxOperations(this::getOperationsPerTick);
    }

    protected boolean containsRecipe(ChemicalStack input) {
        return getRecipeType().getInputCache().containsInput(getLevel(), input);
    }

    protected @Nullable ChemicalToBiChemicalRecipe findfirstRecipe(ChemicalStack input) {
        return getRecipeType().getInputCache().findFirstRecipe(getLevel(), input);
    }

    @Override
    public @Nullable ChemicalToBiChemicalRecipe getRecipe(int cacheIndex) {
        return findfirstRecipe(inputHandler.getInput());
    }

    public MachineEnergyContainer<?> getEnergyContainer() {
        return energyContainer;
    }

    public IChemicalTank getInputTank() {
        return inputTank;
    }

    public IChemicalTank getMainOutputTank() {
        return mainOutputTank;
    }

    public IChemicalTank getSecondaryOutputTank() {
        return secondaryOutputTank;
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, ChemicalToBiChemicalRecipe, MekALSingleInputRecipeCache.MekALSingleChemical<ChemicalToBiChemicalRecipe>> getRecipeType() {
        return MekUtRecipeTypes.PYROLYSIS;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<?> recipeViewerType() {
        return MekUtRecipeViewerRecipeType.PYROLYSIS;
    }

}
