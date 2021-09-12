package com.readonlydev.common.item;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.readonlydev.common.lib.EnumAlloy;
import com.readonlydev.common.lib.EnumMetal;
import com.readonlydev.common.lib.IMetal;
import com.readonlydev.lib.recipe.RecipeBuilder;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemExoIngot extends ItemBaseMetal {

	private final boolean isAlloy;

	public ItemExoIngot(boolean isAlloy) {
		super("ingot", "ingot");
		this.isAlloy = isAlloy;
	}

	@Override
	public List<IMetal> getMetals(Item item) {
		List<IMetal> metals = Lists.newArrayList();

		if (isAlloy) {
			metals.addAll(Arrays.asList(EnumAlloy.values()));
		} else {
			metals.addAll(Arrays.asList(EnumMetal.values()));
		}

		return metals;
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		for (IMetal metal : getMetals(this)) {
			recipes.addCompression("block_" + metal.getMetalName(), metal.getIngot(), metal.getBlock(), 9);
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for (IMetal metal : getMetals(this))
			if (metal.getMeta() == stack.getItemDamage())
				return super.getUnlocalizedName().replaceFirst("metal_|alloy_", "") + "_" + metal.getName();
		return super.getUnlocalizedName(stack);
	}
}
