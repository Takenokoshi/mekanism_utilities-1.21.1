package com.takenokoshi.mekut.blockentity.interfaces.machine;

import com.takenokoshi.mekaddonlib.blockentity.interfaces.IWarningSupporter;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.blockentity.interfaces.IScaledProgressProvider;

import mekanism.api.chemical.IChemicalTank;

public interface IMekUtChemicalToChemicalMachine
        extends IHasMachineEnergyContainer, IWarningSupporter, IRecipeViewerTypeProvider, IScaledProgressProvider {
    IChemicalTank getInputTank();

    IChemicalTank getOutputTank();
}
