package net.romvoid95.api.world.worldgen.terrain;

import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.romvoid95.ExoplanetsMod;
import net.romvoid95.space.astrogeneration.util.GenUtility;

public class ExoGenMountain extends WorldGenerator {
	
	private IBlockState state;
	private int size;
	private int yOffset;
	private boolean showDebugInfo;
	
	public ExoGenMountain(boolean showDebugInfo, IBlockState state, int size, int yOffset) {
		super();
		this.state = state;
		this.size = size;
		this.yOffset = yOffset;
		this.showDebugInfo = showDebugInfo;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {

			if (this.showDebugInfo)
				ExoplanetsMod.logger.debug("Generating Mountain at (x, y, z)" + position.toString());
			generateStructure(worldIn, rand, position.down(this.yOffset));
		
		return true;
	}

	public boolean generateStructure(World world, Random rand, BlockPos position) {
		for (Entry<BlockPos, IBlockState> temp : GenUtility.generateSphere(this.state, this.size, position).entrySet()) {
			world.setBlockState(temp.getKey(), temp.getValue(), 3);
		}
		return true;

	}
}
