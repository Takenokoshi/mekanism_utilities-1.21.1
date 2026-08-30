package com.takenokoshi.mekut.blockentity.machine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractPyrolysisMachine;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEPyrolysisMachine extends BEAbstractPyrolysisMachine {

    public BEPyrolysisMachine(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 1);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 10_000L;
    }

}
