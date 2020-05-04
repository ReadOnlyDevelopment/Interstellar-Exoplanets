package net.rom.exoplanets.block;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.internal.block.BlockBase;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockGeneral extends BlockBase implements ISortableBlock {

	public BlockGeneral(Material materialIn) {
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
		return EnumSortCategoryBlock.GENERAL;
	}

}
