package com.takenokoshi.mekut.item;

import com.takenokoshi.mekut.block.attribute.AttributeUniversalStorage;
import com.takenokoshi.mekut.block.attribute.AttributeUniversalStorageUpgradeable;
import com.takenokoshi.mekut.blockentity.misc.BEUniversalStorage;
import com.takenokoshi.mekut.blockentity.upgradedata.UniversalStorageUpgradeData;
import com.takenokoshi.mekut.tier.UniversalStorageTier;

import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ItemUniversalStorageTierInstaller extends Item {

    private final UniversalStorageTier fromTier;
    private final UniversalStorageTier toTier;

    public ItemUniversalStorageTierInstaller(Properties properties, UniversalStorageTier fromTier,
            UniversalStorageTier toTier) {
        super(properties);
        this.fromTier = fromTier;
        this.toTier = toTier;
    }

    public UniversalStorageTier getFromTier() {
        return fromTier;
    }

    public UniversalStorageTier getToTier() {
        return toTier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (player == null) {
            return InteractionResult.PASS;
        }
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Holder<Block> block = state.getBlockHolder();
        AttributeUniversalStorage attributeUniversalStorage = Attribute.get(block, AttributeUniversalStorage.class);
        AttributeUniversalStorageUpgradeable attributeUniversalStorageUpgradeable = Attribute.get(block,
                AttributeUniversalStorageUpgradeable.class);
        if (attributeUniversalStorage == null
                || attributeUniversalStorageUpgradeable == null
                || attributeUniversalStorage.tier != fromTier) {
            return InteractionResult.PASS;
        }
        BlockState upgradeState = attributeUniversalStorageUpgradeable.upgradeResult(state);
        BlockEntity tile = WorldUtils.getTileEntity(world, pos);
        if (!(tile instanceof BEUniversalStorage universalStorage)) {
            return InteractionResult.PASS;
        }
        if (!universalStorage.playersUsing.isEmpty()) {
            return InteractionResult.FAIL;
        }
        UniversalStorageUpgradeData upgradeData = universalStorage.getUpgradeData(world.registryAccess());
        if (!world.setBlockAndUpdate(pos, upgradeState)) {
            // Something went wrong, bail rather than trying to
            Mekanism.logger.warn("Error upgrading block at position: {} in {}.", pos, world.dimension().location());
            return InteractionResult.FAIL;
        }
        TileEntityMekanism upgradedTile = WorldUtils.getTileEntity(TileEntityMekanism.class, world, pos);
        upgradedTile.parseUpgradeData(world.registryAccess(), upgradeData);
        upgradedTile.resyncMasterToBounding();
        upgradedTile.sendUpdatePacket();
        upgradedTile.setChanged();
        upgradedTile.invalidateCapabilitiesFull();
        if (!player.isCreative()) {
            context.getItemInHand().shrink(1);
        }
        return InteractionResult.CONSUME;
    }

}
