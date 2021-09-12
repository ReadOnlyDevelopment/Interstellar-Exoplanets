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

public class ItemExoDust extends ItemBaseMetal {

	private final boolean isAlloy;

	public ItemExoDust(boolean isAlloy) {
		super("dust", "dust");
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
		if(!isAlloy) {
	        for (IMetal metal : getMetals(this)) {
	            ItemStack dust = metal.getDust();
	            ItemStack ingot = metal.getIngot();
	                recipes.addSmelting(dust, ingot, 0.6f);
	        }
		}
		if(isAlloy) {
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
			for (IMetal metal : getMetals(this)) {
				ItemStack dust = metal.getDust();
				ItemStack ingot = metal.getIngot();
					recipes.addSmelting(dust, ingot, 0.6f);
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
