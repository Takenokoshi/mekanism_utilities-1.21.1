package com.takenokoshi.mekut.recipe.builder;

import com.mojang.datafixers.util.Function3;
import com.takenokoshi.mekut.recipe.recipe.basic.BasicPyrolysisRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import net.minecraft.world.item.crafting.Recipe;

public class ChemicalToBiChemicalRecipeBuilder extends MekanismRecipeBuilder<ChemicalToBiChemicalRecipeBuilder> {

    protected final Function3<ChemicalStackIngredient, ChemicalStack, ChemicalStack, Recipe<?>> factory;
    protected final ChemicalStackIngredient input;
    protected final ChemicalStack mainOutput;
    protected final ChemicalStack secondaryOutput;

    protected ChemicalToBiChemicalRecipeBuilder(
            Function3<ChemicalStackIngredient, ChemicalStack, ChemicalStack, Recipe<?>> factory,
            ChemicalStackIngredient input, ChemicalStack mainOutput, ChemicalStack secondaryOutput) {
        this.factory = factory;
        this.input = input;
        this.mainOutput = mainOutput;
        this.secondaryOutput = secondaryOutput;
    }

    public static ChemicalToBiChemicalRecipeBuilder pyrolysis(ChemicalStackIngredient input, ChemicalStack mainOutput,
            ChemicalStack secondaryOutput) {
        return new ChemicalToBiChemicalRecipeBuilder(BasicPyrolysisRecipe::new, input, mainOutput, secondaryOutput);
    }

    @Override
    protected Recipe<?> asRecipe() {
        return factory.apply(input, mainOutput, secondaryOutput);
    }

}
