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
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.content.EnumByproduct;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ItemIngotMetal extends ItemBaseMetal {

	public ItemIngotMetal() {
		super("ingot", "ingot");
		setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
	}

	@Override
	public List<IMetal> getMetals(Item item) {
		List<IMetal> metals = Lists.newArrayList();
		metals.addAll(Arrays.asList(EnumMetal.values()));
    	return metals;
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		for (EnumMetal metal : EnumMetal.values()) {

			// Ingots <--> Blocks
			recipes.addCompression("block_" + metal.getMetalName(), metal.getIngot(), metal.getBlock(), 9);
			// Nuggets <--> Ingots
			recipes.addCompression("ingot_" + metal.getMetalName(), metal.getNugget(), metal.getIngot(), 9);

		}
	}

	@Override
	public void addOreDict() {
		super.addOreDict();
		OreDictionary.registerOre("ingotAluminum", EnumMetal.ALUMINIUM.getIngot());

	}
}
