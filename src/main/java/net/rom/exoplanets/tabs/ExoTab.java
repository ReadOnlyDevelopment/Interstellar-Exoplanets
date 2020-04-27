package net.rom.exoplanets.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ExoTab extends CreativeTabs {

	public String backgroundName = "";

	public ExoTab(String label, String backgroundName)
	{
		super(label);
		setBackgroundImageName(backgroundName);
	}

	@Override
	public boolean hasSearchBar()
	{
		return true;
	}

	@Override
	public ItemStack getTabIconItem() {
		return null;
	}


}
