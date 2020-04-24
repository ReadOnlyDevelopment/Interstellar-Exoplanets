package net.rom.exoplanets.item;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.api.blocks.IMetalOre;
import net.rom.exoplanets.Exoplanets;
import net.rom.exoplanets.internal.IAddRecipe;
import net.rom.exoplanets.internal.client.ICustomModel;

public abstract class ItemBaseMetal extends Item implements IAddRecipe, ICustomModel {

	String modelName;
	String oreDictPrefix;

	ItemBaseMetal(String modelName, String oreDictPrefix) {
		this.modelName = modelName;
		this.oreDictPrefix = oreDictPrefix;
		setHasSubtypes(true);
	}

	public abstract List<IMetalOre> getMetals(Item item);

	public List<ItemStack> getSubItems(Item item) {
		List<ItemStack> list = Lists.newArrayList();
		for (IMetalOre metal : getMetals(item))
			list.add(new ItemStack(item, 1, metal.getMeta()));
		return list;
	}

	@Override
	public void registerModels() {
		String prefix = Exoplanets.MODID + ":" + modelName;
		for (IMetalOre metal : getMetals(this)) {
			ModelResourceLocation model = new ModelResourceLocation(prefix + metal.getName(), "inventory");
			ModelLoader.setCustomModelResourceLocation(this, metal.getMeta(), model);
		}
	}

	@Override
	public void addOreDict() {
		for (IMetalOre metal : getMetals(this)) {
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
