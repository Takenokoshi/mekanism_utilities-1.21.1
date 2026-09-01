package com.takenokoshi.mekut.recipe.building;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.jerry.mekextras.common.resource.ExtraResource;
import com.jerry.mekextras.common.resource.ore.ExtraOreType;
import com.takenokoshi.mekut.core.MekUtConstants;
import com.takenokoshi.mekut.recipe.builder.GreenHouseRecipeBuilder;
import com.takenokoshi.mekut.recipe.output.MekUtChanceOutput;
import com.takenokoshi.mekut.registries.MekUtBlocks;

import appeng.core.AppEng;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.resource.ore.OreType;
import mekanism.common.tags.MekanismTags;
import mekanism.generators.common.registries.GeneratorsFluids;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class MeteorCollectorRecipes {
    public static void build(RecipeOutput output) {
        GreenHouseRecipeBuilder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(MekanismTags.Items.DUSTS_DIAMOND, 1),
                        IngredientCreatorAccess.item().from(Items.STONE, 1),
                        IngredientCreatorAccess.fluid().fromHolder(GeneratorsFluids.FUSION_FUEL, 1_000_000),
                        2000)
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DIAMOND_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_DIAMOND_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.EMERALD_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_EMERALD_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.LAPIS_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_LAPIS_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(MekanismBlocks.ORES.get(OreType.FLUORITE).stone(), 1),
                        0.3d))
                .addOutput(new MekUtChanceOutput(
                        new ItemStack(MekanismBlocks.ORES.get(OreType.FLUORITE).deepslate(), 1), 0.3d))
                .build(output, MekUtConstants.rl("meteor_collector/gems"));
        GreenHouseRecipeBuilder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(Tags.Items.DUSTS_REDSTONE, 1),
                        IngredientCreatorAccess.item().from(Items.STONE, 1),
                        IngredientCreatorAccess.fluid().fromHolder(GeneratorsFluids.FUSION_FUEL, 1_000_000),
                        2000)
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.REDSTONE_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_REDSTONE_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.COAL_ORE, 1), 0.9d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_COAL_ORE, 1), 0.9d))
                .build(output, MekUtConstants.rl("meteor_collector/redstone"));
        GreenHouseRecipeBuilder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(
                                MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.IRON), 1),
                        IngredientCreatorAccess.item().from(Items.STONE, 1),
                        IngredientCreatorAccess.fluid().fromHolder(GeneratorsFluids.FUSION_FUEL, 1_000_000),
                        2000)
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.IRON_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_IRON_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.COPPER_ORE, 1), 0.6d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_COPPER_ORE, 1), 0.6d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.GOLD_ORE, 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(Items.DEEPSLATE_GOLD_ORE, 1), 0.3d))
                .build(output, MekUtConstants.rl("meteor_collector/vanilla_metal"));
        GreenHouseRecipeBuilder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(
                                MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.OSMIUM),
                                1),
                        IngredientCreatorAccess.item().from(Items.STONE, 1),
                        IngredientCreatorAccess.fluid().fromHolder(GeneratorsFluids.FUSION_FUEL, 1_000_000),
                        2000)
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.OSMIUM).stone(), 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.OSMIUM).deepslate(), 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.TIN).stone(), 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.TIN).deepslate(), 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.LEAD).stone(), 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.LEAD).deepslate(), 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.URANIUM).stone(), 1), 0.3d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.URANIUM).deepslate(), 1), 0.3d))
                .build(output, MekUtConstants.rl("meteor_collector/mekanism_metal"));
        GreenHouseRecipeBuilder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(
                                ExtraItems.PROCESSED_RESOURCES.get(ResourceType.DUST, ExtraResource.NAQUADAH),
                                1),
                        IngredientCreatorAccess.item().from(Items.DEEPSLATE, 1),
                        IngredientCreatorAccess.fluid().fromHolder(GeneratorsFluids.FUSION_FUEL, 1_000_000),
                        2000)
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        MekanismBlocks.ORES.get(OreType.URANIUM).deepslate(), 1), 0.6d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        ExtraBlocks.ORES.get(ExtraOreType.NAQUADAH).stone(), 1), 0.6d))
                .addCondition(new ModLoadedCondition(MekanismExtras.MOD_ID))
                .build(output, MekUtConstants.rl("meteor_collector/naquadah"));
        GreenHouseRecipeBuilder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(
                                Tags.Items.DUSTS_GLOWSTONE,
                                1),
                        IngredientCreatorAccess.item().from(Items.NETHERRACK, 1),
                        IngredientCreatorAccess.fluid().fromHolder(GeneratorsFluids.FUSION_FUEL, 1_000_000),
                        2000)
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        Items.NETHER_QUARTZ_ORE, 1), 0.6d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        Items.NETHER_GOLD_ORE, 1), 0.6d))
                .addOutput(new MekUtChanceOutput(new ItemStack(MekUtBlocks.NETHERITE_ORE, 1), 0.001d))
                .build(output, MekUtConstants.rl("meteor_collector/nether"));
        GreenHouseRecipeBuilder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(
                                AEItems.SKY_DUST,
                                1),
                        IngredientCreatorAccess.item().from(AEBlocks.QUARTZ_BLOCK, 1),
                        IngredientCreatorAccess.fluid().fromHolder(GeneratorsFluids.FUSION_FUEL, 1_000),
                        100)
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        AEItems.SKY_DUST, 64), 1.0d))
                .addOutput(new MekUtChanceOutput(new ItemStack(
                        AEBlocks.FLAWLESS_BUDDING_QUARTZ, 1), 0.00001d))
                .addCondition(new ModLoadedCondition(AppEng.MOD_ID))
                .build(output, MekUtConstants.rl("meteor_collector/sky_stone"));
    }
}
