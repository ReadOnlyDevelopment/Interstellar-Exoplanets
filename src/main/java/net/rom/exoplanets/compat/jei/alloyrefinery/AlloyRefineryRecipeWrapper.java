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
import net.rom.exoplanets.api.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.exoplanets.api.recipe.alloyrefinery.AlloyRefineryRecipeObject;

public class AlloyRefineryRecipeWrapper  implements IRecipeWrapper {
    private final AlloyRefineryRecipe recipe;

    public AlloyRefineryRecipeWrapper(@Nonnull AlloyRefineryRecipe recipe) {
        this.recipe = recipe;
    }

    List<AlloyRefineryRecipeObject> getInputObjects() {
        return Arrays.asList(recipe.getInputs());
    }

    private List<ItemStack> getInputs() {
        List<ItemStack> list = Lists.newArrayList();
        for (AlloyRefineryRecipeObject recipeObject : recipe.getInputs()) {
            list.addAll(recipeObject.getPossibleItemStacks());
        }
        return list;
    }

    List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getOutput());
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, this.getInputs());
        ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        FontRenderer fontRender = minecraft.fontRenderer;
        String str = String.format("%.1f XP", recipe.getExperience());
        fontRender.drawStringWithShadow(str, 63, 0, 0xFFFFFF);
        str = (recipe.getCookTime() / 20) + "s";
        fontRender.drawStringWithShadow(str, 66, 28, 0xFFFFFF);
    }
}
