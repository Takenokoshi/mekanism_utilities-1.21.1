package com.takenokoshi.mekut.recipe.recipe.prefab;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.vanilla_input.SingleChemicalRecipeInput;
import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class ChemicalToBiChemicalRecipe extends MekanismRecipe<SingleChemicalRecipeInput>
        implements Predicate<ChemicalStack> {

    private final RecipeType<? extends ChemicalToBiChemicalRecipe> recipeType;
    public final ChemicalStackIngredient input;
    public final ChemicalStack output1;
    public final ChemicalStack output2;

    protected ChemicalToBiChemicalRecipe(RecipeType<? extends ChemicalToBiChemicalRecipe> recipeType,
            ChemicalStackIngredient input, ChemicalStack output1, ChemicalStack output2) {
        this.recipeType = recipeType;
        this.input = input;
        this.output1 = output1;
        this.output2 = output2;
    }

    public List<Chemical> getInputChemicals() {
        return this.input.ingredient().getChemicalHolders().stream().map(Holder::value).toList();
    }

    @Override
    public RecipeType<? extends ChemicalToBiChemicalRecipe> getType() {
        return recipeType;
    }

    @Override
    public boolean test(ChemicalStack t) {
        return this.input.test(t);
    }

    public ChemicalStackIngredient getInput() {
        return this.input;
    }

    public List<ChemicalStack> getOutputDefinition1() {
        return Collections.singletonList(this.output1);
    }

    public List<ChemicalStack> getOutputDefinition2() {
        return Collections.singletonList(this.output2);
    }

    public ChemicalStack getOutput1Raw() {
        return output1;
    }

    public ChemicalStack getOutput2Raw() {
        return output2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o != null && o.getClass() == this.getClass()) {
            ChemicalToBiChemicalRecipe other = (ChemicalToBiChemicalRecipe) o;
            return this.input.equals(other.input)
                    && this.output1.equals(other.output1)
                    && this.output2.equals(other.output2);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.input.hashCode();
        result += result * 31 + this.output1.hashCode();
        result += result * 31 + this.output2.hashCode();
        return result;
    }

    @Override
    public boolean isIncomplete() {
        return this.input.hasNoMatchingInstances();
    }

    @Override
    public boolean matches(SingleChemicalRecipeInput input, Level arg1) {
        return !this.isIncomplete() && this.test(input.chemical());
    }
}
