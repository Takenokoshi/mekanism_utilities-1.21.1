package com.takenokoshi.mekut.blockentity.machine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractGreenHouse;
import com.takenokoshi.mekut.recipe.inputcache.MekUtTripleInputRecipeCache.ItemItemFluid;
import com.takenokoshi.mekut.recipe.output.BasicChanceOutputHandler;
import com.takenokoshi.mekut.recipe.output.SimpleChanceOutputHandler;
import com.takenokoshi.mekut.recipe.recipe.prefab.GreenHouseRecipe;
import com.takenokoshi.mekut.recipe_viewer.type.MekUtRecipeViewerRecipeType;
import com.takenokoshi.mekut.registries.MekUtRecipeTypes;

import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEMeteorCollector extends BEAbstractGreenHouse {

    public BEMeteorCollector(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 1);
    }

    @Override
    protected BasicChanceOutputHandler initOutputHandler(IInventorySlot slot, RecipeError notEnoughSpaceError) {
        return SimpleChanceOutputHandler.create(slot, notEnoughSpaceError);
    }

    @Override
    protected int initFluidTankCapacity() {
        return 1_000_000;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_024;
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, GreenHouseRecipe, ItemItemFluid<GreenHouseRecipe>> getRecipeType() {
        return MekUtRecipeTypes.METEOR_COLLECTOR;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<?> recipeViewerType() {
        return MekUtRecipeViewerRecipeType.METEOR_COLLECTOR;
    }
    
}
