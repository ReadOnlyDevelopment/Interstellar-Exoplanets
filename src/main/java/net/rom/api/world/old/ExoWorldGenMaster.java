package net.rom.api.world.old;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class ExoWorldGenMaster extends WorldGenerator {

	protected void setBlock(World world, BlockPos pos, Block block) {
		world.setBlockState(pos, block.getDefaultState());
	}

	protected void setBlock(World world, BlockPos pos, IBlockState blockState) {
		world.setBlockState(pos, blockState);
	}

	protected void setBlock(World world, BlockPos pos, int x, int y, int z, Block block) {
		this.setBlock(world, pos.add(x, y, z), block);
	}

	protected Block getBlock(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock();
	}
	
}