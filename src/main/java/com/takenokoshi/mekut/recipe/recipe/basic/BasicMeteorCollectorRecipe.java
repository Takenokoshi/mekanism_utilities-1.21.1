package com.takenokoshi.mekut.recipe.recipe.basic;

import java.util.List;

import com.takenokoshi.mekut.recipe.output.MekUtChanceOutput;
import com.takenokoshi.mekut.recipe.recipe.prefab.GreenHouseRecipe;
import com.takenokoshi.mekut.registries.MekUtRecipeSerializers;
import com.takenokoshi.mekut.registries.MekUtRecipeTypes;

import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BasicMeteorCollectorRecipe extends GreenHouseRecipe {

    public BasicMeteorCollectorRecipe(
            ItemStackIngredient cropIngredient, ItemStackIngredient soilIngredient,
            FluidStackIngredient fertilizerInredient, List<MekUtChanceOutput> outputs, int duration) {
        super(MekUtRecipeTypes.METEOR_COLLECTOR.get(), cropIngredient, soilIngredient, fertilizerInredient, outputs,
                duration);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekUtRecipeSerializers.METEOR_COLLECTOR.get();
    }

}
