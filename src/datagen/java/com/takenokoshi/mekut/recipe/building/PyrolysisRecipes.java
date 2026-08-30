package com.takenokoshi.mekut.recipe.building;

import com.takenokoshi.mekut.core.MekUtConstants;
import com.takenokoshi.mekut.recipe.builder.ChemicalToBiChemicalRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;

import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;

public class PyrolysisRecipes {

    public static void build(RecipeOutput output) {
        ChemicalToBiChemicalRecipeBuilder
                .pyrolysis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.I_II_DICHLOROETHANE, 1L),
                        MekUtChemicals.CHLOROETHYLENE.asStack(1L),
                        MekanismChemicals.HYDROGEN_CHLORIDE.asStack(1L))
                .build(output, MekUtConstants.rl("pyrolysis/chloroethylene"));
    }
}
