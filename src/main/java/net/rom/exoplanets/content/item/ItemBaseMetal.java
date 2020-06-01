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

public abstract class ItemBaseMetal extends ItemBaseExoMetal implements IAddRecipe, ICustomModel {
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
			String name = oreDictPrefix + metal.getMetalName();
			OreDictionary.registerOre(name, stack);

		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + stack.getItemDamage();
	}
}
