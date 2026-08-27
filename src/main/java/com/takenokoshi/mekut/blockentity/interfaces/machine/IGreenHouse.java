package com.takenokoshi.mekut.blockentity.interfaces.machine;

import com.takenokoshi.mekaddonlib.blockentity.interfaces.IHasGuiSizeOffset;
import com.takenokoshi.mekaddonlib.blockentity.interfaces.IWarningSupporter;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.blockentity.interfaces.IScaledProgressProvider;

import mekanism.api.fluid.IExtendedFluidTank;

public interface IGreenHouse extends IHasMachineEnergyContainer,
        IHasGuiSizeOffset, IWarningSupporter, IScaledProgressProvider,IRecipeViewerTypeProvider {

    public IExtendedFluidTank getFertilizerTank();

}
