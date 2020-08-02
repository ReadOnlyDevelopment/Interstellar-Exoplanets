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

package net.rom.exoplanets.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.rom.api.crafting.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.exoplanets.content.EnumAlloy;

public class ExoRecipes {

	public static void alloySmelterRecipes () {

		AlloyRefineryRecipe.addRecipe(EnumAlloy.BRONZE.getIngot(), 200, 1.2f, "ingotCopper*3", "ingotTin*1");

		AlloyRefineryRecipe.addRecipe(EnumAlloy.BRONZE.getIngot(), 200, 1.2f, "ingotCopper*3", "ingotTin*1");

		AlloyRefineryRecipe.addRecipe(EnumAlloy.BRASS.getIngot(), 200, 1.0f, "ingotCopper*3", "ingotZinc*1");

		ItemStack coal = new ItemStack(Items.COAL, 2);
		AlloyRefineryRecipe.addRecipe(EnumAlloy.STEEL.getIngot(), 800, 2.5f, "ingotIron*1", coal);

	}

}
