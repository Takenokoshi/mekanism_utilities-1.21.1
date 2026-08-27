package com.takenokoshi.mekut.blockentity.misc;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.blockentity.interfaces.IHasGuiSizeOffset;
import com.takenokoshi.mekaddonlib.blockentity.interfaces.IMultiPageMachine;
import com.takenokoshi.mekaddonlib.inventory.slot.PadeIndexedBasicInventorySlot;
import com.takenokoshi.mekut.block.attribute.AttributeUniversalStorage;
import com.takenokoshi.mekut.blockentity.upgradedata.UniversalStorageUpgradeData;
import com.takenokoshi.mekut.tier.UniversalStorageTier;

import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.energy.EnergyContainersBuilder;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.fluid.FluidTankHelper;
import mekanism.common.capabilities.holder.fluid.IFluidTankHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.FluidInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.ITileComponent;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.prefab.TileEntityConfigurableMachine;
import mekanism.common.upgrade.IUpgradeData;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEUniversalStorage extends TileEntityConfigurableMachine implements IHasGuiSizeOffset, IMultiPageMachine {

    public static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.MACHINE);
        configInfo.put(TransmissionType.CHEMICAL, AttachedSideConfig.LightConfigInfo.OUT_EJECT);
        configInfo.put(TransmissionType.ENERGY, AttachedSideConfig.LightConfigInfo.OUT_EJECT);
        configInfo.put(TransmissionType.FLUID, AttachedSideConfig.LightConfigInfo.OUT_EJECT);
        return new AttachedSideConfig(configInfo);
    });

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(UniversalStorageTier tier) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addBasic(tier.invSlots + 7)
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(tier.chemicalTankCapacity)
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                    .addBasic(tier.fluidTankCapacity)
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.ENERGY, () -> EnergyContainersBuilder.builder()
                    .addBasic(() -> Long.MAX_VALUE, () -> tier.energyCapacity)
                    .build());
        };
    }

    private UniversalStorageTier tier;
    private int totalPages;

    private IChemicalTank chemicalTank;
    private IEnergyContainer energyContainer;
    private IExtendedFluidTank fluidTank;

    private PadeIndexedBasicInventorySlot[] slots;
    private ChemicalInventorySlot chemicalInputSlot;
    private ChemicalInventorySlot chemicalOutputSlot;
    private EnergyInventorySlot energyInputSlot;
    private EnergyInventorySlot energyOutputSlot;
    private FluidInventorySlot fluidInputSlot;
    private FluidInventorySlot fluidOutputSlot;
    private OutputInventorySlot fluidReturnSlot;

    public BEUniversalStorage(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        configComponent.setupItemIOConfig(List.<IInventorySlot>of(slots), List.<IInventorySlot>of(slots),
                energyInputSlot, false);
        configComponent.setupIOConfig(TransmissionType.CHEMICAL, chemicalTank, RelativeSide.RIGHT);
        configComponent.setupIOConfig(TransmissionType.ENERGY, energyContainer, RelativeSide.RIGHT);
        configComponent.setupIOConfig(TransmissionType.FLUID, fluidTank, RelativeSide.RIGHT);
        ejectorComponent = new TileComponentEjector(this,
                () -> Long.MAX_VALUE,
                () -> 0x7fffffff,
                () -> Long.MAX_VALUE)
                .setOutputData(configComponent, TransmissionType.ITEM, TransmissionType.FLUID,
                        TransmissionType.CHEMICAL, TransmissionType.ENERGY);
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = Attribute.get(getBlockHolder(), AttributeUniversalStorage.class).tier;
        totalPages = (tier.invSlots + 53) / 54;
    }

    @Override
    public @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.createAllValid(tier.chemicalTankCapacity, listener));
        return builder.build();
    }

    @Override
    protected @Nullable IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(energyContainer = BasicEnergyContainer.create(tier.energyCapacity, listener));
        return builder.build();
    }

    @Override
    protected @Nullable IFluidTankHolder getInitialFluidTanks(IContentsListener listener) {
        FluidTankHelper builder = FluidTankHelper.forSideWithConfig(this);
        builder.addTank(fluidTank = BasicFluidTank.create(tier.fluidTankCapacity, listener));
        return builder.build();
    }

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        slots = new PadeIndexedBasicInventorySlot[tier.invSlots];
        for (int i = 0; i < slots.length; i++) {
            builder.addSlot(slots[i] = new PadeIndexedBasicInventorySlot(
                    stack -> true,
                    stack -> true,
                    stack -> true,
                    listener, 18 * (i % 9) + 62, 18 * (i % 54 / 9) + 17, i / 54, tier.invSlotCapacity));
        }
        builder.addSlot(chemicalInputSlot = ChemicalInventorySlot.fill(chemicalTank, listener, 8, 35))
                .setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(chemicalOutputSlot = ChemicalInventorySlot.drain(chemicalTank, listener, 8, 53))
                .setSlotOverlay(SlotOverlay.PLUS);
        builder.addSlot(energyInputSlot = EnergyInventorySlot.fill(energyContainer, listener, 260, 11))
                .setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(energyOutputSlot = EnergyInventorySlot.drain(energyContainer, listener, 260, 29))
                .setSlotOverlay(SlotOverlay.PLUS);
        builder.addSlot(fluidInputSlot = FluidInventorySlot.fill(fluidTank, listener, 260, 55))
                .setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(fluidOutputSlot = FluidInventorySlot.drain(fluidTank, listener, 260, 73))
                .setSlotOverlay(SlotOverlay.PLUS);
        builder.addSlot(fluidReturnSlot = OutputInventorySlot.at(listener, 260, 107));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        chemicalInputSlot.fillTank();
        chemicalOutputSlot.drainTank();
        fluidInputSlot.fillTank(fluidReturnSlot);
        fluidOutputSlot.drainTank(fluidReturnSlot);
        energyInputSlot.fillContainer();
        energyOutputSlot.drainContainer();
        return sendUpdatePacket;
    }

    @Override
    public int getExtraWidth() {
        return 108;
    }

    @Override
    public int getExtraHeight() {
        return 54;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    public IChemicalTank getChemicalTank() {
        return chemicalTank;
    }

    public IExtendedFluidTank getFluidTank() {
        return fluidTank;
    }

    public IEnergyContainer getEnergyContainer() {
        return energyContainer;
    }

    @Override
    public UniversalStorageUpgradeData getUpgradeData(Provider provider) {
        return new UniversalStorageUpgradeData(provider, redstone, getControlType(), chemicalTank, energyContainer,
                fluidTank, List.<IInventorySlot>of(slots), chemicalInputSlot, chemicalOutputSlot, energyInputSlot,
                energyOutputSlot, fluidInputSlot, fluidOutputSlot, fluidReturnSlot, getComponents());
    }

    @Override
    public void parseUpgradeData(Provider provider, @NotNull IUpgradeData upgradeData) {
        if (upgradeData instanceof UniversalStorageUpgradeData data) {
            redstone = data.redstone;
            setControlType(data.controlType);
            chemicalTank.setStack(data.chemicalTank.getStack());
            energyContainer.setEnergy(data.energyContainer.getEnergy());
            fluidTank.setStack(data.fluidTank.getFluid());
            for (int i = 0; i < data.slots.size(); i++) {
                slots[i].deserializeNBT(provider, data.slots.get(i).serializeNBT(provider));
            }
            chemicalInputSlot.deserializeNBT(provider, data.chemicalInputSlot.serializeNBT(provider));
            chemicalOutputSlot.deserializeNBT(provider, data.chemicalOutputSlot.serializeNBT(provider));
            energyInputSlot.deserializeNBT(provider, data.energyInputSlot.serializeNBT(provider));
            energyOutputSlot.deserializeNBT(provider, data.energyOutputSlot.serializeNBT(provider));
            fluidInputSlot.deserializeNBT(provider, data.fluidInputSlot.serializeNBT(provider));
            fluidOutputSlot.deserializeNBT(provider, data.fluidOutputSlot.serializeNBT(provider));
            fluidReturnSlot.deserializeNBT(provider, data.fluidReturnSlot.serializeNBT(provider));
            for (ITileComponent component : getComponents()) {
                component.read(data.components, provider);
            }
        } else {
            super.parseUpgradeData(provider, upgradeData);
        }
    }

}
