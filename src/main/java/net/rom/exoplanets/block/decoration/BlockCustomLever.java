package net.rom.exoplanets.block.decoration;

import net.minecraft.block.BlockLever;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockCustomLever extends BlockLever {
	public BlockCustomLever() {
		setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
		setHardness(2F);
		setResistance(5F);
		this.setHarvestLevel("axe", 2);
	}
}
