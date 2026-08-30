package com.takenokoshi.mekut.recipe;

import java.util.concurrent.CompletableFuture;

import com.takenokoshi.mekut.core.MekUtConstants;
import com.takenokoshi.mekut.recipe.building.*;
import com.takenokoshi.mekut.registries.MekUtChemicals;
import com.takenokoshi.mekut.registries.MekUtItems;

import fixdol.mekanismelements.common.MekanismElements;
import fixdol.mekanismelements.common.registries.MSItems;
import mekanism.api.datagen.recipe.builder.ChemicalCrystallizerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class MekUtRecipeProvider extends RecipeProvider {

    public MekUtRecipeProvider(PackOutput output, CompletableFuture<Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        AntiprotonicNucleoSynthesizeRecipes.build(output);
        ChemicalConvertionRecipes.build(output);
        ChemicalCutRecipes.build(output);
        ChemicalDissolutionRecipes.build(output);
        ChemicalInfusionRecipes.build(output);
        ChemicalWashingRecipes.build(output);
        CombiningRecipes.build(output);
        CraftingRecipes.build(output, RecipeProvider::has);
        CrystallizingRecipes.build(output);
        EnrichingRecipes.build(output);
        GreenHouseRecipes.build(output);
        IceMakerRecipes.build(output);
        InjectingRecipes.build(output);
        LazerCompressNucleoSynthesizeRecipes.build(output);
        MekReactionRecipes.build(output);
        MetallurgicInfusingRecipes.build(output);
        MaterialProcessRecipes.build(output, RecipeProvider::has);
        PaintingRecipes.build(output);
        PigmentExtractingRecipes.build(output);
        PyrolysisRecipes.build(output);
        RotaryRecipes.build(output);
        SDARecipes.build(output);
        SDRCRecipes.build(output);
        StellarGenesisRecipes.build(output);

        ChemicalCrystallizerRecipeBuilder
                .crystallizing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.POLYAMIDE_FIBER, 200),
                        new ItemStack(Items.STRING, 1))
                .build(output, MekUtConstants.rl("chemical_crystallizing/string"));
        ChemicalCrystallizerRecipeBuilder
                .crystallizing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.POLYPROPYLENE, 200),
                        MekUtItems.NONWOVEN_FABRIC.asStack(1))
                .build(output, MekUtConstants.rl("chemical_crystallizing/nonwoven_fabric"));
        SimpleCookingRecipeBuilder
                .smelting(
                        Ingredient.of(new ItemLike[] { MekUtItems.CALCIUM_HYDROXIDE_DUST }),
                        RecipeCategory.MISC,
                        MSItems.DUST_CALCIUM_OXIDE,
                        0.5F,
                        200)
                .unlockedBy("unlock", has(MekUtItems.CALCIUM_HYDROXIDE_DUST.asItem()))
                .save(
                        output.withConditions(new ICondition[] {
                                new ModLoadedCondition(MekanismElements.MODID),
                        }),
                        MekUtConstants.rl("smelting/calcium_oxide_dust"));
        ItemStackChemicalToItemStackRecipeBuilder
                .compressing(
                        IngredientCreatorAccess.item().from(MekUtItems.NONWOVEN_FABRIC, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.POLYVINYL_CHLORIDE, 200L),
                        new ItemStack(Items.LEATHER, 1), false)
                .build(output, MekUtConstants.rl("compressing/leather"));
    }

}
