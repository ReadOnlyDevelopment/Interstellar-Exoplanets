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
