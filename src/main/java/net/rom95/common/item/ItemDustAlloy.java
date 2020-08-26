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

package net.rom95.common.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.rom95.api.crafting.RecipeBuilder;
import net.rom95.client.CreativeExoTabs;
import net.rom95.common.lib.EnumAlloy;
import net.rom95.common.lib.IMetal;

public class ItemDustAlloy extends ItemBaseMetal {

	public ItemDustAlloy() {
		super("dust", "dust");
		setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
	}

	@Override
	public List<IMetal> getMetals(Item item) {
		return Arrays.asList(EnumAlloy.values());
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		// Crafting alloy dust from other dust
		String copper = "dustCopper";
		String tin = "dustTin";
		String zinc = "dustZinc";
		String iron = "dustIron";
		String coal = "dustCoal";

		ItemStack bronze = EnumAlloy.BRONZE.getDust();
		ItemStack brass = EnumAlloy.BRASS.getDust();
		ItemStack steel = EnumAlloy.STEEL.getDust();

		recipes.addShapelessOre("bronze_dust", bronze, copper, copper, copper, tin);

		recipes.addShapelessOre("brass_dust", brass, copper, copper, copper, zinc);

		recipes.addShapelessOre("steel_dust", steel, iron, coal, coal, coal);

		// Smelting dust to ingots
		for (EnumAlloy metal : EnumAlloy.values()) {
			ItemStack dust = metal.getDust();
			ItemStack ingot = metal.getIngot();
				recipes.addSmelting(dust, ingot, 0.6f);
		}
	}
}
