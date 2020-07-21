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
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipeObject;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.content.block.machine.TileAlloyRefinery;

// TODO: Auto-generated Javadoc
/**
 * The Class AlloyRefineryRecipeCategory.
 */
@SuppressWarnings("rawtypes")
public class AlloyRefineryRecipeCategory implements IRecipeCategory {
    
    /** The Constant UID. */
    public static final String UID = ExoInfo.RESOURCE_PREFIX + "alloy_refinery";

    /** The background. */
    private final IDrawable background;
    
    /** The flame. */
    private final IDrawableAnimated flame;
    
    /** The arrow. */
    private final IDrawableAnimated arrow;
    
    /** The localized name. */
    private final String localizedName = ExoplanetsMod.translate.translate("jei.exoplanets.recipe.alloy_refinery");

    /**
     * Instantiates a new alloy refinery recipe category.
     *
     * @param guiHelper the gui helper
     */
    public AlloyRefineryRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation backgroundLocation = new ResourceLocation(ExoInfo.RESOURCE_PREFIX + "textures/gui/jei/alloyrefinery.png");

        background = guiHelper.createDrawable(backgroundLocation, 0, 0, 120, 40);

        ResourceLocation furnaceLocation = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
        IDrawableStatic flameDrawable = guiHelper.createDrawable(furnaceLocation, 176, 0, 14, 14);
        flame = guiHelper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

        IDrawableStatic arrowDrawable = guiHelper.createDrawable(furnaceLocation, 176, 14, 24, 17);
        this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    /**
     * Draw extras.
     *
     * @param minecraft the minecraft
     */
    @Override
    public void drawExtras(Minecraft minecraft) {
        flame.draw(minecraft, 2, 4);
        arrow.draw(minecraft, 62, 10);
    }

    /**
     * Gets the background.
     *
     * @return the background
     */
    @Override
    public IDrawable getBackground() {
        return background;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    @Override
    public String getTitle() {
        return localizedName;
    }

    /**
     * Gets the uid.
     *
     * @return the uid
     */
    @Override
    public String getUid() {
        return UID;
    }

    /**
     * Sets the recipe.
     *
     * @param recipeLayout the recipe layout
     * @param recipeWrapper the recipe wrapper
     * @param ingredients the ingredients
     */
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

    /**
     * Gets the mod name.
     *
     * @return the mod name
     */
    @Override
    public String getModName() {
        return ExoInfo.NAME;
    }
}
