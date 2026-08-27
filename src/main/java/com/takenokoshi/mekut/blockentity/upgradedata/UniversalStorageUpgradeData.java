package com.takenokoshi.mekut.blockentity.upgradedata;

import java.util.List;

import mekanism.api.chemical.IChemicalTank;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.FluidInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.tile.component.ITileComponent;
import mekanism.common.tile.interfaces.IRedstoneControl.RedstoneControl;
import mekanism.common.upgrade.IUpgradeData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public final class UniversalStorageUpgradeData implements IUpgradeData {

    public final boolean redstone;
    public final RedstoneControl controlType;
    public final IChemicalTank chemicalTank;
    public final IEnergyContainer energyContainer;
    public final IExtendedFluidTank fluidTank;
    public final List<IInventorySlot> slots;
    public final ChemicalInventorySlot chemicalInputSlot;
    public final ChemicalInventorySlot chemicalOutputSlot;
    public final EnergyInventorySlot energyInputSlot;
    public final EnergyInventorySlot energyOutputSlot;
    public final FluidInventorySlot fluidInputSlot;
    public final FluidInventorySlot fluidOutputSlot;
    public final OutputInventorySlot fluidReturnSlot;
    public final CompoundTag components;

    public UniversalStorageUpgradeData(HolderLookup.Provider provider, boolean redstone, RedstoneControl controlType, IChemicalTank chemicalTank,
            IEnergyContainer energyContainer, IExtendedFluidTank fluidTank, List<IInventorySlot> slots,
            ChemicalInventorySlot chemicalInputSlot, ChemicalInventorySlot chemicalOutputSlot,
            EnergyInventorySlot energyInputSlot, EnergyInventorySlot energyOutputSlot,
            FluidInventorySlot fluidInputSlot, FluidInventorySlot fluidOutputSlot,
            OutputInventorySlot fluidReturnSlot, List<ITileComponent> components) {
        this.redstone = redstone;
        this.controlType = controlType;
        this.chemicalTank = chemicalTank;
        this.energyContainer = energyContainer;
        this.fluidTank = fluidTank;
        this.slots = slots;
        this.chemicalInputSlot = chemicalInputSlot;
        this.chemicalOutputSlot = chemicalOutputSlot;
        this.energyInputSlot = energyInputSlot;
        this.energyOutputSlot = energyOutputSlot;
        this.fluidInputSlot = fluidInputSlot;
        this.fluidOutputSlot = fluidOutputSlot;
        this.fluidReturnSlot = fluidReturnSlot;
        this.components = new CompoundTag();
        for (ITileComponent component : components) {
            component.write(this.components, provider);
        }
    }

}
