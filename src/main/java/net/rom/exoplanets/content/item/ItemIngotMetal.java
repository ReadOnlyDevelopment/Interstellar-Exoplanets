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

package net.rom.exoplanets.content.item;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
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
