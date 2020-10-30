package net.romvoid95.common.block.terrain;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IDoubleGrass {
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos);
	
	public void placeAt(World worldIn, BlockPos lowerPos, int flags);

}
