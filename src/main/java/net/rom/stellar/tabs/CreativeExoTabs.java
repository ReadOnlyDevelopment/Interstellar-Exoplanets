package net.rom.stellar.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.rom.stellar.init.ExoplanetsBlocks;

public class CreativeExoTabs {

	public static final CreativeTabs TERRAIN = new ExoTab("terrain", "") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ExoplanetsBlocks.tiles);
		}
	};

}
