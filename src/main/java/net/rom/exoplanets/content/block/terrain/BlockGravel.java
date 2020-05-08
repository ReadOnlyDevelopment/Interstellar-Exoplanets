package net.rom.exoplanets.content.block.terrain;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockGravel extends BlockFalling implements ISortableBlock {
	public BlockGravel() {
		super();
		this.setHardness(0.6F);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);

	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.FLINT;
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}
}
