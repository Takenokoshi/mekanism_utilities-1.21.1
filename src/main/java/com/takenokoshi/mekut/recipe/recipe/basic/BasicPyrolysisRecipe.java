package com.takenokoshi.mekut.recipe.recipe.basic;

import com.takenokoshi.mekut.recipe.recipe.prefab.ChemicalToBiChemicalRecipe;
import com.takenokoshi.mekut.registries.MekUtRecipeSerializers;
import com.takenokoshi.mekut.registries.MekUtRecipeTypes;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BasicPyrolysisRecipe extends ChemicalToBiChemicalRecipe {

    public BasicPyrolysisRecipe(
            ChemicalStackIngredient input, ChemicalStack output1, ChemicalStack output2) {
        super(MekUtRecipeTypes.PYROLYSIS.get(), input, output1, output2);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekUtRecipeSerializers.PIROLYSIS.get();
    }

}
