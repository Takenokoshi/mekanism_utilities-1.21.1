package com.takenokoshi.mekut.recipe.building;

import com.takenokoshi.mekut.core.MekUtConstants;
import com.takenokoshi.mekut.registries.MekUtChemicals;

import mekanism.api.datagen.recipe.builder.PressurizedReactionRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IChemicalStackIngredientCreator;
import mekanism.api.recipes.ingredients.creator.IFluidStackIngredientCreator;
import mekanism.api.recipes.ingredients.creator.IItemStackIngredientCreator;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismFluids;
import mekanism.common.registries.MekanismItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class MekReactionRecipes {

    public static void build(RecipeOutput output) {
        IItemStackIngredientCreator creatorI = IngredientCreatorAccess.item();
        IFluidStackIngredientCreator creatorF = IngredientCreatorAccess.fluid();
        IChemicalStackIngredientCreator creatorC = IngredientCreatorAccess.chemicalStack();
        PressurizedReactionRecipeBuilder
                .reaction(
                        creatorI.from(MekanismItems.SUBSTRATE, 1),
                        creatorF.fromHolder(MekanismFluids.OXYGEN, 20),
                        creatorC.fromHolder(MekUtChemicals.CHLOROETHYLENE, 1000L),
                        100,
                        MekUtChemicals.POLYVINYL_CHLORIDE.asStack(1000L))
                .build(output, MekUtConstants.rl("pressurized_reaction/polyvinyl_chloride"));
        PressurizedReactionRecipeBuilder
                .reaction(
                        creatorI.from(MekanismItems.SUBSTRATE, 1),
                        creatorF.fromHolder(MekanismFluids.OXYGEN, 20),
                        creatorC.fromHolder(MekUtChemicals.PROPYLENE, 1000L),
                        100,
                        MekUtChemicals.POLYPROPYLENE.asStack(1000L))
                .build(output, MekUtConstants.rl("pressurized_reaction/polypropylene"));
        PressurizedReactionRecipeBuilder
                .reaction(
                        creatorI.from(Items.GLOWSTONE_DUST, 1),
                        creatorF.from(Tags.Fluids.LAVA, 20),
                        creatorC.fromHolder(MekUtChemicals.METHANOL, 1000L),
                        100,
                        new ItemStack(Items.GLOWSTONE_DUST, 1),
                        MekUtChemicals.PROPYLENE.asStack(200L))
                .build(output, MekUtConstants.rl("pressurized_reaction/propylene"));
    }
}
