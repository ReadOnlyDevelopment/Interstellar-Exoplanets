package net.romvoid95.api.world.worldgen.feature.newtreegen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockSettable {
	
	void setBlockAndNotify(World world, BlockPos pos, IBlockState state);

}
