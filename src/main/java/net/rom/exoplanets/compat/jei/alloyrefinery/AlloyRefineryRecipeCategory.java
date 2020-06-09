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

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.api.recipe.alloyrefinery.AlloyRefineryRecipeObject;
import net.rom.exoplanets.content.block.machine.TileAlloyRefinery;

@SuppressWarnings("rawtypes")
public class AlloyRefineryRecipeCategory implements IRecipeCategory {
    public static final String UID = ExoInfo.RESOURCE_PREFIX + "alloy_refinery";

    private final IDrawable background;
    private final IDrawableAnimated flame;
    private final IDrawableAnimated arrow;
    private final String localizedName = ExoplanetsMod.translate.translate("jei.exoplanets.recipe.alloy_refinery");

    public AlloyRefineryRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation backgroundLocation = new ResourceLocation(ExoInfo.RESOURCE_PREFIX + "textures/gui/jei/alloyrefinery.png");

        background = guiHelper.createDrawable(backgroundLocation, 0, 0, 120, 40);

        ResourceLocation furnaceLocation = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
        IDrawableStatic flameDrawable = guiHelper.createDrawable(furnaceLocation, 176, 0, 14, 14);
        flame = guiHelper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

        IDrawableStatic arrowDrawable = guiHelper.createDrawable(furnaceLocation, 176, 14, 24, 17);
        this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        flame.draw(minecraft, 2, 4);
        arrow.draw(minecraft, 62, 10);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 25, 0);
        recipeLayout.getItemStacks().init(1, true, 43, 0);
        recipeLayout.getItemStacks().init(2, true, 25, 18);
        recipeLayout.getItemStacks().init(3, true, 43, 18);
        recipeLayout.getItemStacks().init(TileAlloyRefinery.SLOT_FUEL, true, 0, 15);
        recipeLayout.getItemStacks().init(TileAlloyRefinery.SLOT_OUTPUT, false, 98, 10);

        if (recipeWrapper instanceof AlloyRefineryRecipeWrapper) {
            AlloyRefineryRecipeWrapper wrapper = (AlloyRefineryRecipeWrapper) recipeWrapper;
            // Set inputs
            for (int i = 0; i < wrapper.getInputObjects().size(); ++i) {
                Object obj = wrapper.getInputObjects().get(i);
                AlloyRefineryRecipeObject recipeObject = (AlloyRefineryRecipeObject) obj;
                recipeLayout.getItemStacks().set(i, recipeObject.getPossibleItemStacks());
            }
            // Set output
            recipeLayout.getItemStacks().set(TileAlloyRefinery.SLOT_OUTPUT, wrapper.getOutputs());
        }
    }

    @Override
    public String getModName() {
        return ExoInfo.NAME;
    }
}
