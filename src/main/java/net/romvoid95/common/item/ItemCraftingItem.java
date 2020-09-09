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

package net.romvoid95.common.item;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.romvoid95.api.crafting.RecipeBuilder;
import net.romvoid95.common.lib.EnumAlloy;
import net.romvoid95.common.lib.EnumMetal;
import net.romvoid95.common.lib.interfaces.IMetal;

@SuppressWarnings("unused")
public class ItemCraftingItem extends ItemBaseMetal {

	private final String craftingItemName;
	private final boolean isAlloy;
	private final boolean isGear;
	private final boolean isSheet;

	public ItemCraftingItem(String type, boolean isAlloy) {
		super(type, type);
		this.craftingItemName = type;
		this.isAlloy = isAlloy;
		this.isGear = "gear".equals(type);
		this.isSheet = "sheet".equals(type);
	}

	@Override
	public List<IMetal> getMetals(Item item) {
		List<IMetal> metals = Lists.newArrayList();

		if (isAlloy) {
			// Alloys
			metals.addAll(Arrays.asList(EnumAlloy.values()));
		} else {
			// Basic metals (including vanilla)
			metals.addAll(Arrays.asList(EnumMetal.values()));
			return metals;
		}

		return metals;
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		if (isGear) {
			for (IMetal metal : getMetals(this)) {
				ItemStack gear = metal.getSheet();

				for (String metalName : metal.getMetalNames()) {
					String mat = "ingot" + metalName;
					recipes.addShapedOre("gear_" + metalName, gear, " m ", "mim", " m ", 'm', mat, 'i', "ingotIron");
				}
			}
		}
		if (isSheet) {
			for (IMetal metal : getMetals(this)) {
				ItemStack sheet = metal.getSheet();
				for (String metalName : metal.getMetalNames()) {
					String mat = "ingot" + metalName;
					recipes.addShapedOre("sheet_" + metalName, sheet, " m ", "mim", " m ", 'm', mat, 'i', "ingotIron");
				}
			}
		}
	}

	@Override
	public void addOreDict() {
		super.addOreDict();

		if (!isAlloy)
			OreDictionary.registerOre(oreDictPrefix + "Aluminum", new ItemStack(this, 1, EnumMetal.ALUMINIUM.meta));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for (IMetal metal : getMetals(this))
			if (metal.getMeta() == stack.getItemDamage())
				return super.getUnlocalizedName().replaceFirst("metal|alloy", "") + metal.getName();
		return super.getUnlocalizedName(stack);
	}
}
