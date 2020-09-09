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

package net.romvoid95.common.integration.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;
import net.romvoid95.api.crafting.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.romvoid95.client.gui.block.GuiAlloyRefinery;
import net.romvoid95.common.integration.jei.alloyrefinery.AlloyRefineryRecipeCategory;
import net.romvoid95.common.integration.jei.alloyrefinery.AlloyRefineryRecipeMaker;
import net.romvoid95.common.integration.jei.alloyrefinery.AlloyRefineryRecipeWrapper;
import net.romvoid95.core.initialization.ExoBlocks;

// TODO: Auto-generated Javadoc
/**
 * The Class ExoplanetsPlugin.
 */
@JEIPlugin
public class ExoplanetsPlugin implements IModPlugin {

	/**
	 * Register.
	 *
	 * @param reg the reg
	 */
	@Override
	public void register(IModRegistry reg) {

		doItemBlacklist(reg.getJeiHelpers().getIngredientBlacklist());

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

	/**
	 * Register categories.
	 *
	 * @param registry the registry
	 */
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new AlloyRefineryRecipeCategory(guiHelper));
	}

	/**
	 * Do item blacklist.
	 *
	 * @param list the list
	 */
	private void doItemBlacklist(IIngredientBlacklist list) {

		list.addIngredientToBlacklist(new ItemStack(ExoBlocks.alarm_light));
		list.addIngredientToBlacklist(new ItemStack(ExoBlocks.cellar_lamp));
		list.addIngredientToBlacklist(new ItemStack(ExoBlocks.inset_lamp));
		list.addIngredientToBlacklist(new ItemStack(ExoBlocks.wall_lamp));

	}

}
