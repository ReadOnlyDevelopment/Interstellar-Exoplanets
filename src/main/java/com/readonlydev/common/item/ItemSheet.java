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

public class ItemSheet extends ItemBaseMetal {

	private final boolean isAlloy;
	
	public ItemSheet(boolean isAlloy) {
		super("sheet", "sheet");
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
			ItemStack sheet = metal.getSheet();
			for (String metalName : metal.getMetalNames()) {
				String mat = "ingot" + metalName;
				recipes.addShapedOre("sheet_" + metalName, sheet, " m ", "mim", " m ", 'm', mat, 'i', "ingotIron");
			}
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
