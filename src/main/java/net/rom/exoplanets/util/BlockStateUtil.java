package net.rom.exoplanets.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class BlockStateUtil {
	
	public static IBlockState getState(ItemStack stack) {
		Block block = Block.getBlockFromItem(stack.getItem());
		if (block != null && !(block instanceof BlockAir))
			return getState(block, stack.getMetadata());
		return Blocks.AIR.getDefaultState();
	}
	
	@SuppressWarnings("deprecation")
	public static IBlockState getState(Block block, int meta) {
		try {
			return block.getStateFromMeta(meta);
		} catch (Exception e) {
			return block.getDefaultState();
		}
	}

}
