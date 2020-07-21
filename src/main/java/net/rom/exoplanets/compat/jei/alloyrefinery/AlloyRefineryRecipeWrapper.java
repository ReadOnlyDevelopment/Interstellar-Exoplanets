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

package net.rom.exoplanets.compat.jei.alloyrefinery;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipeObject;

// TODO: Auto-generated Javadoc
/**
 * The Class AlloyRefineryRecipeWrapper.
 */
public class AlloyRefineryRecipeWrapper  implements IRecipeWrapper {
    
    /** The recipe. */
    private final AlloyRefineryRecipe recipe;

    /**
     * Instantiates a new alloy refinery recipe wrapper.
     *
     * @param recipe the recipe
     */
    public AlloyRefineryRecipeWrapper(@Nonnull AlloyRefineryRecipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Gets the input objects.
     *
     * @return the input objects
     */
    List<AlloyRefineryRecipeObject> getInputObjects() {
        return Arrays.asList(recipe.getInputs());
    }

    /**
     * Gets the inputs.
     *
     * @return the inputs
     */
    private List<ItemStack> getInputs() {
        List<ItemStack> list = Lists.newArrayList();
        for (AlloyRefineryRecipeObject recipeObject : recipe.getInputs()) {
            list.addAll(recipeObject.getPossibleItemStacks());
        }
        return list;
    }

    /**
     * Gets the outputs.
     *
     * @return the outputs
     */
    List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getOutput());
    }

    /**
     * Gets the ingredients.
     *
     * @param ingredients the ingredients
     * @return the ingredients
     */
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, this.getInputs());
        ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
    }

    /**
     * Draw info.
     *
     * @param minecraft the minecraft
     * @param recipeWidth the recipe width
     * @param recipeHeight the recipe height
     * @param mouseX the mouse X
     * @param mouseY the mouse Y
     */
    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        FontRenderer fontRender = minecraft.fontRenderer;
        String str = String.format("%.1f XP", recipe.getExperience());
        fontRender.drawStringWithShadow(str, 63, 0, 0xFFFFFF);
        str = (recipe.getCookTime() / 20) + "s";
        fontRender.drawStringWithShadow(str, 66, 28, 0xFFFFFF);
    }
}
