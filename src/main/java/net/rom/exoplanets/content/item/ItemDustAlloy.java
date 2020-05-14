package net.rom.exoplanets.content.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.content.EnumAlloy;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.util.CreativeExoTabs;

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
