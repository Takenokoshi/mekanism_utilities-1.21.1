package com.takenokoshi.mekut.block.attribute;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.level.block.state.BlockState;

public final class AttributeUniversalStorageUpgradeable implements Attribute {

    private final Supplier<BlockRegistryObject<?, ?>> upgradeBlock;

    public AttributeUniversalStorageUpgradeable(Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        this.upgradeBlock = upgradeBlock;
    }

    @NotNull
    public BlockState upgradeResult(@NotNull BlockState current) {
        return BlockStateHelper.copyStateData(current, upgradeBlock.get());
    }
}
