/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.rom95.common.integration.jei.alloyrefinery;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.rom95.api.crafting.recipe.alloyrefinery.AlloyRefineryRecipe;

// TODO: Auto-generated Javadoc
/**
 * The Class AlloyRefineryRecipeMaker.
 */
public class AlloyRefineryRecipeMaker {
    
    /**
     * Gets the recipes.
     *
     * @return the recipes
     */
    @Nonnull
    public static List<AlloyRefineryRecipeWrapper> getRecipes() {
        List<AlloyRefineryRecipeWrapper> recipes = new ArrayList<>();
        for (AlloyRefineryRecipe smelterRecipe : AlloyRefineryRecipe.ALL_RECIPES) {
            recipes.add(new AlloyRefineryRecipeWrapper(smelterRecipe));
        }
        return recipes;
    }
}
