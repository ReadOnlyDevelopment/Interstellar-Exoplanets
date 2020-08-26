/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom95.api.crafting.recipe.alloyrefinery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public class AlloyRefineryRecipe {
    public static final List<AlloyRefineryRecipe> ALL_RECIPES = new ArrayList<>();
    private static final Set<AlloyRefineryRecipeObject> ALL_INGREDIENTS = new HashSet<>();

    private static final int MAX_INPUTS = 4;

    private final AlloyRefineryRecipeObject[] inputs;
    private final ItemStack output;
    private final int cookTime;
    private final float experience;

    private AlloyRefineryRecipe(ItemStack output, int cookTime, float experience, Object... inputs) {
        this.inputs = AlloyRefineryRecipeObject.getFromObjectArray(inputs);
        this.output = output;
        this.cookTime = cookTime;
        this.experience = experience;
    }

    /**
     * Creates a new AlloyRefineryRecipe and adds it to allRecipes.
     *
     * @param output The resulting stack.
     * @param inputs All inputs. Can be Strings, ItemStacks, or AlloyRefineryRecipeObjects. Stack
     *               size is considered. Length should be greater than 0, less than 5.
     */
    public static void addRecipe(ItemStack output, int cookTime, float experience, Object... inputs) {
        AlloyRefineryRecipe newRecipe = new AlloyRefineryRecipe(output, cookTime, experience, inputs);
        Collections.addAll(ALL_INGREDIENTS, newRecipe.getInputs());
        ALL_RECIPES.add(newRecipe);
    }

    /**
     * Gets the first recipe that matches for the given inventory.
     *
     * @param inputList The alloy smelter inventory
     * @return The first matching recipe, or null if none match.
     */
    @Nullable
    public static AlloyRefineryRecipe getMatchingRecipe(List<ItemStack> inputList) {
        for (AlloyRefineryRecipe recipe : ALL_RECIPES) {
            if (recipe.matches(inputList)) {
                return recipe;
            }
        }
        return null;
    }

    @Nullable
    public static AlloyRefineryRecipe getRecipeByOutput(ItemStack stack) {
        for (AlloyRefineryRecipe recipe : ALL_RECIPES) {
            if (recipe.getOutput().isItemEqual(stack)) {
                return recipe;
            }
        }
        return null;
    }

    public static boolean isValidIngredient(ItemStack stack) {
        if (stack.isEmpty()) return false;
        ItemStack copy = stack.copy();
        copy.setCount(64);
        for (AlloyRefineryRecipeObject recipeObject : ALL_INGREDIENTS) {
            if (recipeObject.matches(copy)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given recipes matches the inventory.
     *
     * @param inputList The alloy smelter inventory
     * @return True if the recipe is a match (ie, will smelt), false otherwise.
     */
    public boolean matches(List<ItemStack> inputList) {
        // No inputs or outputs?
        if (inputs.length == 0 || output.isEmpty()) return false;

        // Check that inputs match recipe. Order does not matter.
        boolean[] matches = new boolean[MAX_INPUTS];
        int i;
        for (ItemStack inputStack : inputList) {
            for (i = 0; i < inputs.length; ++i) {
                if (inputs[i] == null) {
                    // Inputs shouldn't be null, but that is acceptable.
                    matches[i] = true;
                } else if (inputs[i].matches(inputStack)) {
                    // Correct item.
                    matches[i] = true;
                }
            }
        }
        for (i = inputs.length; i < MAX_INPUTS; ++i) {
            matches[i] = true;
        }

        // All ingredients match up (or are null)?
        for (i = 0; i < MAX_INPUTS; ++i) {
            if (!matches[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the number of ticks to smelt this recipe.
     *
     * @return Time to smelt in ticks
     */
    public int getCookTime() {
        return cookTime;
    }

    /**
     * Gets the experience awarded for cooking this recipe.
     *
     * @return Experience awarded for smelting
     */
    public float getExperience() {
        return experience;
    }

    /**
     * Gets a copy of the input stacks for the recipe.
     *
     * @return Copy of the inputs
     */
    public AlloyRefineryRecipeObject[] getInputs() {

        return inputs.clone();
    }

    /**
     * Gets a copy of the output stack for the recipe.
     *
     * @return Copy of the output stack
     */
    public ItemStack getOutput() {

        return output.copy();
    }
}
