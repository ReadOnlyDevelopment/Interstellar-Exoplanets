package net.rom.exoplanets.compat.jei.alloyrefinery;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipe;

public class AlloyRefineryRecipeMaker {
    @Nonnull
    public static List<AlloyRefineryRecipeWrapper> getRecipes() {
        List<AlloyRefineryRecipeWrapper> recipes = new ArrayList<>();
        for (AlloyRefineryRecipe smelterRecipe : AlloyRefineryRecipe.ALL_RECIPES) {
            recipes.add(new AlloyRefineryRecipeWrapper(smelterRecipe));
        }
        return recipes;
    }
}
