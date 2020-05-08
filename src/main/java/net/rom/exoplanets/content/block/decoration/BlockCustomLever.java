package net.rom.exoplanets.content.block.decoration;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.BlockLever;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockCustomLever extends BlockLever implements ISortableBlock {
	public BlockCustomLever() {
		setHardness(2F);
		setResistance(5F);
		this.setHarvestLevel("axe", 2);
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.TRANSMITTER;
	}
}
