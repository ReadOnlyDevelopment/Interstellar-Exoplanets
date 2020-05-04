package net.rom.exoplanets.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.item.EnumIngots;

public class CreativeExoTabs {

	public static final CreativeTabs DECO_CREATIVE_TABS = new ExoTab("decoration", "decoration.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ExoBlocks.stand_console);
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return false;
		}
	};
	
	public static final CreativeTabs TERRAIN_CREATIVE_TABS = new ExoTab("terrain", "terrain.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(YzCetiBlocks.CetiB.B_METAMORPHIC);
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return false;
		}
	};
	
	public static final CreativeTabs ITEMS_CREATIVE_TABS = new ExoTab("items", "decoration.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(EnumIngots.RUTILE.getItem());
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return false;
		}
	};

}
