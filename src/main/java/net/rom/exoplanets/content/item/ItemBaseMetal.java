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

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.inerf.IAddRecipe;
import net.rom.exoplanets.internal.inerf.ICustomModel;
import net.rom.exoplanets.util.CreativeExoTabs;

public abstract class ItemBaseMetal extends ItemBaseExo implements IAddRecipe, ICustomModel {
	String modelName;
	String oreDictPrefix;

	ItemBaseMetal(String modelName, String oreDictPrefix) {
		this.modelName = modelName;
		this.oreDictPrefix = oreDictPrefix;
		setHasSubtypes(true);
		setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
	}

	public abstract List<IMetal> getMetals(Item item);

	@Override
	public List<ItemStack> getSubItems(Item item) {
		List<ItemStack> list = Lists.newArrayList();
		for (IMetal metal : getMetals(item))
			list.add(new ItemStack(item, 1, metal.getMeta()));
		return list;
	}

	@Override
	public void registerModels() {
		String prefix = ExoInfo.MODID + ":" + modelName;
		for (IMetal metal : getMetals(this)) {
			ModelResourceLocation model = new ModelResourceLocation(prefix + metal.getName(), "inventory");
			ModelLoader.setCustomModelResourceLocation(this, metal.getMeta(), model);

		}
	}

	@Override
	public void addOreDict() {
		for (IMetal metal : getMetals(this)) {
			ItemStack stack = new ItemStack(this, 1, metal.getMeta());
			String    name  = oreDictPrefix + metal.getMetalName();
			OreDictionary.registerOre(name, stack);

		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + stack.getItemDamage();
	}
}
