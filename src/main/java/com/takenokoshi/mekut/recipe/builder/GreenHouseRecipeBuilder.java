package com.takenokoshi.mekut.recipe.builder;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Function5;
import com.takenokoshi.mekut.recipe.output.MekUtChanceOutput;
import com.takenokoshi.mekut.recipe.recipe.basic.BasicMeteorCollectorRecipe;

import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.crafting.Recipe;

public class GreenHouseRecipeBuilder extends MekanismRecipeBuilder<GreenHouseRecipeBuilder> {

    protected final Function5<ItemStackIngredient, ItemStackIngredient, FluidStackIngredient, List<MekUtChanceOutput>, Integer, Recipe<?>> factory;

    protected final ItemStackIngredient cropIngredient, soilIngredient;
    protected final FluidStackIngredient fertilizerInredient;
    protected final List<MekUtChanceOutput> outputs = new ArrayList<>();
    protected final int duration;

    protected GreenHouseRecipeBuilder(
            Function5<ItemStackIngredient, ItemStackIngredient, FluidStackIngredient, List<MekUtChanceOutput>, Integer, Recipe<?>> factory,
            ItemStackIngredient cropIngredient, ItemStackIngredient soilIngredient,
            FluidStackIngredient fertilizerInredient, int duration) {
        this.factory = factory;
        this.cropIngredient = cropIngredient;
        this.soilIngredient = soilIngredient;
        this.fertilizerInredient = fertilizerInredient;
        this.duration = duration;
    }

    public static GreenHouseRecipeBuilder meteorCollector(ItemStackIngredient cropIngredient,
            ItemStackIngredient soilIngredient,
            FluidStackIngredient fertilizerInredient, int duration) {
        return new GreenHouseRecipeBuilder(BasicMeteorCollectorRecipe::new, cropIngredient, soilIngredient,
                fertilizerInredient, duration);
    }

    public GreenHouseRecipeBuilder addOutput(MekUtChanceOutput output) {
        outputs.add(output);
        return this;
    }

    @Override
    protected Recipe<?> asRecipe() {
        return factory.apply(cropIngredient, soilIngredient, fertilizerInredient, List.copyOf(outputs), duration);
    }

}
