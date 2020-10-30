package net.romvoid95.space.astrogeneration.feature.foliage;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.romvoid95.common.block.terrain.BlockDoubleFoliage;
import net.romvoid95.common.block.terrain.IDoubleGrass;

public class WorldGenExoDoublePlant extends WorldGenerator {

	private Block block;
	private IDoubleGrass foliageState;

	public WorldGenExoDoublePlant(Block block) {
		this.block = block;
		this.foliageState = (BlockDoubleFoliage) block;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		boolean flag = false;
		for (IBlockState iblockstate = worldIn
				.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position)
						|| iblockstate.getBlock().isLeaves(iblockstate, worldIn, position))
						&& position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
			position = position.down();
		}
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
					rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254)
					&& this.block.canPlaceBlockAt(worldIn, blockpos)) {
				this.foliageState.placeAt(worldIn, blockpos, 2);
				flag = true;
			}
		}

		return flag;
	}
}