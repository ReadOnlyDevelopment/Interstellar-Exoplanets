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

package net.rom.exoplanets.content.item;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.content.EnumAlloy;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.RecipeBuilder;

public class ItemCraftingItem extends ItemBaseMetal {
	private final String craftingItemName;
	private final boolean isAlloy;
	private final boolean isGear;
	private final boolean isPlate;

	public ItemCraftingItem(String type, boolean isAlloy) {
		super(type, type);
		this.craftingItemName = type;
		this.isAlloy = isAlloy;
		this.isGear = "gear".equals(type);
		this.isPlate = "plate".equals(type);
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
				ItemStack gear = metal.getPlate();

				for (String metalName : metal.getMetalNames()) {
					String mat = "ingot" + metalName;
					recipes.addShapedOre("gear_" + metalName, gear, " m ", "mim", " m ", 'm', mat, 'i', "ingotIron");
				}
			}
		}
		if (isPlate) {
			for (IMetal metal : getMetals(this)) {
				ItemStack plate = metal.getPlate();
				for (String metalName : metal.getMetalNames()) {
					String mat = "ingot" + metalName;
					recipes.addShapedOre("plate_" + metalName, plate, " m ", "mim", " m ", 'm', mat, 'i', "ingotIron");
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
