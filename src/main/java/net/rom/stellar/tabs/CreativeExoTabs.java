package net.rom.stellar.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.rom.stellar.init.ExoplanetsBlocks;

public class CreativeExoTabs {

	public static final CreativeTabs DECO_CREATIVE_TABS = new ExoTab("Exoplanets Decoration", "terrain.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ExoplanetsBlocks.raid0);
		}
	};

}
