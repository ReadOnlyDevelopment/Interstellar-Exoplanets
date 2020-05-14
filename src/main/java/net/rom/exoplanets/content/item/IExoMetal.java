package net.rom.exoplanets.content.item;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IExoMetal {
	
	List<ItemStack> getSubItems(Item item);

}
