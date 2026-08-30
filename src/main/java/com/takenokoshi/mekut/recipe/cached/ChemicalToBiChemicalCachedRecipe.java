package com.takenokoshi.mekut.recipe.cached;

import java.util.function.BooleanSupplier;

import com.takenokoshi.mekut.recipe.recipe.prefab.ChemicalToBiChemicalRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;

public class ChemicalToBiChemicalCachedRecipe extends BasicCachedRecipe<ChemicalToBiChemicalRecipe> {

    private final IInputHandler<ChemicalStack> inputHandler;
    private final IOutputHandler<ChemicalStack> mainOutputHandler, secondaryOutputHandler;
    private ChemicalStack recipeInput = ChemicalStack.EMPTY;

    public ChemicalToBiChemicalCachedRecipe(ChemicalToBiChemicalRecipe recipe, BooleanSupplier recheckAllErrors,
            IInputHandler<ChemicalStack> inputHandler, IOutputHandler<ChemicalStack> mainOutputHandler,
            IOutputHandler<ChemicalStack> secondaryOutputHandler) {
        super(recipe, recheckAllErrors);
        this.inputHandler = inputHandler;
        this.mainOutputHandler = mainOutputHandler;
        this.secondaryOutputHandler = secondaryOutputHandler;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        recipeInput = inputHandler.getRecipeInput(recipe.input);
        if (recipeInput.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        inputHandler.calculateOperationsCanSupport(tracker, recipeInput);
        mainOutputHandler.calculateOperationsCanSupport(tracker, recipe.output1);
        secondaryOutputHandler.calculateOperationsCanSupport(tracker, recipe.output2);
    }

    @Override
    public boolean isInputValid() {
        return recipe.input.test(inputHandler.getInput());
    }

    @Override
    protected void finishProcessing(int operations) {
        if (recipeInput.isEmpty()) {
            return;
        }
        inputHandler.use(recipeInput, operations);
        mainOutputHandler.handleOutput(recipe.output1, operations);
        secondaryOutputHandler.handleOutput(recipe.output2, operations);
    }

}
