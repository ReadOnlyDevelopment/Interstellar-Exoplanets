package net.romvoid95.api.world.worldgen.deco;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ExoGenTallGrass extends WorldGenerator {

	private final IBlockState state;
	private final int amount;

	public ExoGenTallGrass(IBlockState state) {
		this(state, 128); // Default vanilla amount
	}

	public ExoGenTallGrass(IBlockState state, int amount) {
		this.state = state;
		this.amount = amount;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		do {
			IBlockState state = worldIn.getBlockState(position);
			if (!state.getBlock().isAir(state, worldIn, position) && !state.getBlock().isLeaves(state, worldIn, position))
				break;
			position = position.down();
		} while (position.getY() > 0);

		// 128 -> amount
		for (int i = 0; i < amount; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			// use our own state
			if (worldIn.isAirBlock(blockpos) && ((BlockBush) state.getBlock()).canBlockStay(worldIn, blockpos, state)) {
				worldIn.setBlockState(blockpos, state, 16 | 2);
			}
		}

		return true;
	}

}
