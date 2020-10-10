package net.romvoid95.common.world.features;

import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.romvoid95.common.world.helpers.GenUtility;
import net.romvoid95.core.ExoplanetsMod;

public class GenFeatureDome extends WorldGenerator {

	private IBlockState state;
	private int size;
	private int yOffset;
	private boolean showDebugInfo;

	public GenFeatureDome (boolean showDebugInfo, IBlockState state, int size, int yOffset) {
		super();
		this.state = state;
		this.size = size;
		this.yOffset = yOffset;
		this.showDebugInfo = showDebugInfo;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		if (GenUtility.checkValidSpawn(world, position, this.size) == false) {
			return false;
		} else {
			if (this.showDebugInfo) {
				ExoplanetsMod.logger.debug("Spawning Dome at (x, y, z)" + position.toString());
			}
			generateStructure(world, rand, position.down(this.yOffset));
		}
		return true;
	}

	private boolean generateStructure(World world, Random rand, BlockPos down) {
		for (Entry<BlockPos, IBlockState> temp : GenUtility.generateDome(this.state, this.size, down).entrySet()) {
			world.setBlockState(temp.getKey(), temp.getValue(), 3);
		}
		return true;
	}
}
