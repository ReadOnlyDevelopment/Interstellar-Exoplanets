package net.rom.stellar.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.rom.stellar.Exoplanets;

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
