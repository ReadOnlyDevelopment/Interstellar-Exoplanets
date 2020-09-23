package net.romvoid95.common.lib.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public interface ISpawnablePlant extends IPlantable {
	/**
	 * @param world
	 * @param pos
	 * @param state
	 * @return
	 */
	default boolean canGeneratePlant (World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this);
	}

	/**
	 * @param world
	 * @param pos
	 * @param state
	 * @return
	 */
	static boolean canGenerate (World world, BlockPos pos, IBlockState state) {
		if (state.getBlock() instanceof ISpawnablePlant) {
			return ((ISpawnablePlant) state.getBlock()).canGeneratePlant(world, pos, state);
		}
		return false;
	}
}
