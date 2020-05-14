package net.rom.exoplanets.content.block;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.internal.block.BlockBaseHorizontal;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockFloor extends BlockBaseHorizontal implements ISortableBlock {
	
	public BlockFloor(Material materialIn) {
		super(materialIn);
		setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	}
	
	public ItemStack getStack(int count) {
		return new ItemStack(this, count);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this);
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}
}
