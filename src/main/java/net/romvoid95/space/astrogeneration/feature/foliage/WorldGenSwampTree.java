package net.romvoid95.space.astrogeneration.feature.foliage;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.romvoid95.space.astrogeneration.util.GenRandom;

public class WorldGenSwampTree extends WorldGenAbstractTree {
	private IBlockState LOG;
	private IBlockState FULL_LOG;
	private IBlockState LEAVES;
	private static final float hangingLeafChance = 0.3f;
	private static final float upperLeafChance = 0.9f;

	public WorldGenSwampTree(boolean notify, Block[] blocks) {
		super(notify);
		this.LOG = blocks[0].getDefaultState();
		this.FULL_LOG = blocks[1].getDefaultState();
		this.LEAVES = blocks[2].getDefaultState();
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int firstTreeHeight = GenRandom.range(2, 6);
		int additionalTrees = GenRandom.range(0, 3);
		int maxLeafWidth = additionalTrees > 0 ? 3 : 2; // Wider leaves for taller trees
		int heightCheck = 4;

		// Check to make sure the tree position is valid
		for (int y = position.getY() + 1; y <= position.getY() + firstTreeHeight + additionalTrees * heightCheck; y++) {
			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			for (int x = position.getX() - maxLeafWidth; x <= position.getX() + maxLeafWidth; ++x) {
				for (int z = position.getZ() - maxLeafWidth; z <= position.getZ() + maxLeafWidth; ++z) {
					if (y >= 0 && y < 256) {
						if (!this.isReplaceable(worldIn, pos.setPos(x, y, z))) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}

		BlockPos down = position.down();
		IBlockState state = worldIn.getBlockState(down);
		boolean isSoil = state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.WATER;

		// Make sure there is soil
		if (isSoil) {
			state.getBlock().onPlantGrow(state, worldIn, down, position);

			// Generate the first tree
			generateTreeSegment(maxLeafWidth, worldIn, rand, position, firstTreeHeight);

			// Increase the position to align with the next segment of the tree
			BlockPos newPos = position.add(new BlockPos(0, firstTreeHeight, 0));

			// Generate the additional trees on top of the first tree
			for (int i = 0; i < additionalTrees; i++) {
				// Random height
				int additionalHeight = GenRandom.range(2, 3);

				generateTreeSegment(maxLeafWidth, worldIn, rand, newPos, additionalHeight);

				// Increase the position to align with the next segment of the tree
				newPos = newPos.add(new BlockPos(0, additionalHeight, 0));
			}

			// Generate the roots underneath (if there is no grass underneath)
			if (state.getBlock() == Blocks.WATER) {
				this.generateRoots(worldIn, down);
			}
			return true;
		} else {
			return false;
		}
	}

	private void generateRoots(World world, BlockPos pos) {
		int rootLengthX = GenRandom.range(1, 5);
		int rootLengthZ = GenRandom.range(1, 5);

		// Generate the two logs across
		for (int x = -rootLengthX; x <= rootLengthX; x++) {
			this.setBlockAndNotifyAdequately(world, pos.add(new BlockPos(x, 0, 0)), FULL_LOG);
		}

		for (int z = -rootLengthZ; z <= rootLengthZ; z++) {
			this.setBlockAndNotifyAdequately(world, pos.add(new BlockPos(0, 0, z)), FULL_LOG);
		}

		// Generate the four logs downwards until there is dirt or something
		this.generateDownRoots(world, pos.add(new BlockPos(-rootLengthX, 0, 0)));
		this.generateDownRoots(world, pos.add(new BlockPos(rootLengthX, 0, 0)));
		this.generateDownRoots(world, pos.add(new BlockPos(0, 0, rootLengthZ)));
		this.generateDownRoots(world, pos.add(new BlockPos(0, 0, -rootLengthZ)));
	}

	// Generate roots down 5 blocks
	private void generateDownRoots(World world, BlockPos pos) {
		for (int y = -1; y > -5; y--) {
			this.setBlockAndNotifyAdequately(world, pos.add(new BlockPos(0, y, 0)), LOG);
		}
	}

	/**
	 * 
	 * The height of the tree segment to generate The height of the leaves to
	 * generate (e.g. a leaf height of 3 will generate leaves from relative height
	 * down to relative height - 3)
	 * 
	 * @param maxLeafWidth
	 * @param worldIn
	 * @param rand
	 * @param position
	 */
	private void generateTreeSegment(int maxLeafWidth, World worldIn, Random rand, BlockPos position,
			int additonalHeight) {
		// Generate the trunk
		for (int y = 0; y < additonalHeight; y++) {
			this.setBlockAndNotifyAdequately(worldIn, position.add(new BlockPos(0, y, 0)), LOG);
		}

		// Generate the leaves
		for (int x = -maxLeafWidth; x <= maxLeafWidth; ++x) {
			for (int z = -maxLeafWidth; z <= +maxLeafWidth; ++z) {
				int mattDistance = Math.abs(x) + Math.abs(z);
				BlockPos blockpos = new BlockPos(x, 0, z).add(position).add(new BlockPos(0, additonalHeight, 0));

				// Place the main leaf bunch
				if (mattDistance <= maxLeafWidth) {
					placeLeaf(blockpos, worldIn);
				}

				// Place a smaller leaf bunch one block above
				if (mattDistance <= maxLeafWidth - 1 && worldIn.rand.nextFloat() < this.upperLeafChance) {
					placeLeaf(blockpos.up(), worldIn);
				}

				// Place hanging leaves
				if (mattDistance == maxLeafWidth && worldIn.rand.nextFloat() < this.hangingLeafChance) {
					placeLeaf(blockpos.down(), worldIn);
				}
			}
		}
	}

	private void placeLeaf(BlockPos pos, World world) {
		IBlockState state = world.getBlockState(pos);

		if (state.getBlock().canBeReplacedByLeaves(state, world, pos)) {
			this.setBlockAndNotifyAdequately(world, pos, LEAVES);
		}
	}
}
