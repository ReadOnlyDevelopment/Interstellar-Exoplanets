package net.rom.exoplanets.astronomy.trappist1.d.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.internal.world.gen.GenUtility;

public class GenSpaceStation extends WorldGenerator {

	private IBlockState block;

	public GenSpaceStation(Block block) {
		this.block = block.getDefaultState();
	}

	@Override
	public boolean generate (World world, Random rand, BlockPos position) {
		if (GenUtility.checkValidSpawn(world, position, 10) == false)
			return false;
		else {
			ExoplanetsMod.logger.info("Spawning at {}", position.toString());
			generateStructure(world, rand, position);
		}
		return true;
	}

	public boolean generateStructure (World world, Random rand, BlockPos position) {
		int                       size = 20;
		int                       x    = position.getX();
		int                       y    = position.getY();
		int                       z    = position.getZ();
		Iterable<MutableBlockPos> area = BlockPos.getAllInBoxMutable(x, y, z, x + size, y, z - size);

		for (BlockPos pos : area) {
			ExoplanetsMod.logger.info("Position: {}", pos.toString());
			int xx = pos.getX();
			int yy = pos.getY();
			int zz = pos.getZ();
			world.setBlockState(new BlockPos(xx, yy, zz), this.block, 3);
			return true;
		}
		return true;
	}

}
