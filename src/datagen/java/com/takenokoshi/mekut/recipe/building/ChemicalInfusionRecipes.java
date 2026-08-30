package com.takenokoshi.mekut.recipe.building;

import com.takenokoshi.mekut.core.MekUtConstants;
import com.takenokoshi.mekut.registries.MekUtChemicals;

import fixdol.mekanismelements.common.MekanismElements;
import fixdol.mekanismelements.common.registries.MSGases;
import mekanism.api.datagen.recipe.builder.ChemicalChemicalToChemicalRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class ChemicalInfusionRecipes {
    public static void build(RecipeOutput output) {
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.BENZENE, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.HYDROGEN, 3),
                        MekUtChemicals.CYCLOHEXANE.asStack(1L))
                .build(output, MekUtConstants.rl("chemical_infusing/cyclohexane"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.CYCLOHEXANE, 4),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.OXYGEN, 1),
                        MekUtChemicals.KA_OIL.asStack(4L))
                .build(output, MekUtConstants.rl("chemical_infusing/ka_oil"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.KA_OIL, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MSGases.NITRIC_ACID, 1),
                        MekUtChemicals.ADIPIC_ACID.asStack(1L))
                .addCondition(new ModLoadedCondition(MekanismElements.MODID))
                .build(output, MekUtConstants.rl("chemical_infusing/adipic_acid"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.ADIPIC_ACID, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MSGases.AMMONIA, 2),
                        MekUtChemicals.ADIPAMIDE.asStack(1L))
                .addCondition(new ModLoadedCondition(MekanismElements.MODID))
                .build(output, MekUtConstants.rl("chemical_infusing/adipamide"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.ADIPAMIDE, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.HYDROGEN, 6),
                        MekUtChemicals.HEXAMETHYLENE_DIAMINE.asStack(1L))
                .build(output, MekUtConstants.rl("chemical_infusing/hexamethylene_diamine"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.HEXAMETHYLENE_DIAMINE, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.ADIPIC_ACID, 1),
                        MekUtChemicals.POLYAMIDE_FIBER.asStack(1L))
                .build(output, MekUtConstants.rl("chemical_infusing/polyamide_fiber"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.ETHENE, 1L),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.CHLORINE, 1L),
                        MekUtChemicals.I_II_DICHLOROETHANE.asStack(1L))
                .build(output, MekUtConstants.rl("chemical_infusing/1.2-dichloroethane_0"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.HYDROGEN_CHLORIDE, 4L),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.OXYGEN, 1L),
                        MekUtChemicals.HYDROGEN_CHLORIDE_OXIGEN_MIXED_GAS.asStack(5L))
                .build(output, MekUtConstants.rl("chemical_infusing/hydrogen_chloride-oxigen_mixed_gas"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.ETHENE, 2L),
                        IngredientCreatorAccess.chemicalStack()
                                .fromHolder(MekUtChemicals.HYDROGEN_CHLORIDE_OXIGEN_MIXED_GAS, 5L),
                        MekUtChemicals.I_II_DICHLOROETHANE.asStack(2L))
                .build(output, MekUtConstants.rl("chemical_infusing/1.2-dichloroethane_1"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.CARBON, 2L),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.OXYGEN, 1L),
                        MekUtChemicals.CARBON_MONOXIDE.asStack(2L))
                .build(output, MekUtConstants.rl("chemical_infusing/carbon_monoxide"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.CARBON_MONOXIDE, 1L),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.HYDROGEN, 2L),
                        MekUtChemicals.METHANOL.asStack(1L))
                .build(output, MekUtConstants.rl("chemical_infusing/methanol"));
    }
}
