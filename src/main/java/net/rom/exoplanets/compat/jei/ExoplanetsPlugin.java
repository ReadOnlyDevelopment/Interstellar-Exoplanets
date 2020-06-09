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

package net.rom.exoplanets.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.api.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.exoplanets.client.gui.GuiAlloyRefinery;
import net.rom.exoplanets.compat.jei.alloyrefinery.AlloyRefineryRecipeCategory;
import net.rom.exoplanets.compat.jei.alloyrefinery.AlloyRefineryRecipeMaker;
import net.rom.exoplanets.compat.jei.alloyrefinery.AlloyRefineryRecipeWrapper;
import net.rom.exoplanets.init.ExoBlocks;

@JEIPlugin
public class ExoplanetsPlugin implements IModPlugin {
	
    @Override
    public void register(IModRegistry reg) {
    	
    	reg.handleRecipes(AlloyRefineryRecipe.class, AlloyRefineryRecipeWrapper::new, AlloyRefineryRecipeCategory.UID);
    	reg.addRecipes(AlloyRefineryRecipeMaker.getRecipes(), AlloyRefineryRecipeCategory.UID);
    	
    	reg.addRecipeClickArea(GuiAlloyRefinery.class, 80, 34, 25, 16, AlloyRefineryRecipeCategory.UID);
    	
        ItemStack metalFurnace = new ItemStack(ExoBlocks.metalFurnace);
        ItemStack alloySmelter = new ItemStack(ExoBlocks.alloyRefinery);
        
        reg.addRecipeCatalyst(metalFurnace, VanillaRecipeCategoryUid.FUEL, VanillaRecipeCategoryUid.SMELTING);
        reg.addRecipeCatalyst(alloySmelter, AlloyRefineryRecipeCategory.UID);
        
        // Description pages
        String descPrefix = "jei.exoplanets.desc.";
        reg.addIngredientInfo(metalFurnace, ItemStack.class, descPrefix + "metal_furnace");
        reg.addIngredientInfo(alloySmelter, ItemStack.class, descPrefix + "alloy_refinery");
        
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
    	 IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
    	 registry.addRecipeCategories(new AlloyRefineryRecipeCategory(guiHelper));
    }

}
