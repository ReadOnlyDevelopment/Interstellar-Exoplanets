package net.romvoid95.api.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.DimensionType;
import net.romvoid95.core.ExoBlock;
import net.romvoid95.api.world.ExoDimensions;

public class OreHelper {

	public static IBlockState getDimensionOreBase (DimensionType dimension) {
		switch (dimension) {
		case OVERWORLD:
			return Blocks.STONE.getDefaultState();
		default:
			Blocks.STONE.getDefaultState();
		}
		if (dimension == ExoDimensions.TRAPPIST_1C) {
			return ExoBlock.TRAP1C_DIRT_1;
		}
		if (dimension == ExoDimensions.TRAPPIST_1D) {
			return ExoBlock.TRAP1D_STONE_2;
		}
		if (dimension == ExoDimensions.TRAPPIST_1E) {
			return ExoBlock.TRAP1E_STONE;
		}
		if (dimension == ExoDimensions.YZCETIB) {
			return ExoBlock.YZB_METAMORPHIC;
		}
		if (dimension == ExoDimensions.YZCETIC) {
			return ExoBlock.YZC_METAMORPHIC;
		}
		if (dimension == ExoDimensions.YZCETID) {
			return ExoBlock.YZD_METAMORPHIC;
		}
		return Blocks.STONE.getDefaultState();

	}
}
