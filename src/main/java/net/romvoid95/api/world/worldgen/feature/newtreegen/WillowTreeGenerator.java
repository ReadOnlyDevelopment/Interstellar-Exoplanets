package net.romvoid95.api.world.worldgen.feature.newtreegen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.romvoid95.api.enums.LeafType;
import net.romvoid95.api.enums.WoodType;
import net.romvoid95.common.block.terrain.tree.BlockExoLeaves;
import net.romvoid95.common.block.terrain.tree.BlockExoLog;
import net.romvoid95.core.ExoBlocks;

public class WillowTreeGenerator extends ExoTreeGenerator {

    private IBlockState stateWood = ExoBlocks.EXO_LOG.getDefaultState().withProperty(BlockExoLog.WOOD_TYPE, WoodType.LIGHT);
    private IBlockState stateLeaves = ExoBlocks.EXO_LEAVES.getDefaultState().withProperty(BlockExoLeaves.VARIANT, LeafType.LIGHT).withProperty(BlockExoLeaves.CHECK_DECAY, false);
    private boolean noLeaves = false;
    private int generateFlag = 2;
    
    public WillowTreeGenerator() {
    	this(false);
    }
    
    public WillowTreeGenerator(boolean notify) {
    	super(notify);
    }

	@Override
	public boolean generate (World world, Random rand, BlockPos pos) {

		if (!this.isGroundValid(world, pos, true)) {
			return false;
		}

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		int height       = 13;
		int leaveheight  = 5;
		int branches     = 6;
		int branchLenght = 6;

		for (int i = 0; i < height; i++) {
			this.placeLogBlock(world, new BlockPos(x, y + i, z), this.stateWood, this.generateFlag);
		}
		createLeavesAroundBranch(world, rand, x, y + height, z, 3, 2);
		createTrunk(world, rand, x, y, z);

		int     dir = rand.nextInt((int) (360f / branches));
		float   xd, yd, hd, c;
		boolean m;
		for (int b = 0; b < branches; b++) {
			c    = 0;
			hd   = height - rand.nextFloat() * leaveheight - 2f;
			dir += (int) (360f / branches);
			xd   = (float) Math.cos(dir * Math.PI / 180f);
			yd   = (float) Math.sin(dir * Math.PI / 180f);
			m    = false;

			while (c < branchLenght) {
				if (c > branchLenght / 2 && !m) {
					m = true;
					createLeavesAroundBranch(world, rand, x + (int) (c * xd), y + (int) hd, z + (int) (c * yd), 2, 1);
				}
				c++;
				hd += 0.5f;

				this.placeLogBlock(world, new BlockPos(x + (int) (c * xd), y + (int) hd, z
						+ (int) (c * yd)), this.stateWood, this.generateFlag);
			}
			createLeavesAroundBranch(world, rand, x + (int) (c * xd), y + (int) hd, z + (int) (c * yd), 2, 1);
		}

		return true;
	}

	private void createLeavesAroundBranch (World world, Random rand, int x, int y, int z, int s, int c) {

		int l;
		int t = (int) Math.pow(s, 2);
		for (int i = -s; i <= s; i++) {
			for (int j = -s; j <= s; j++) {
				for (int k = -s; k <= s; k++) {
					l = i * i + j * j + k * k;
					if (l <= t) {
						if ((l < t - c || rand.nextBoolean())) {
							if (!this.noLeaves) {

								this.placeLeavesBlock(world, new BlockPos(x + i, y + j, z
										+ k), this.stateLeaves, this.generateFlag);
								if (j < -(s - 2) && rand.nextInt(3) != 0) {
									createVine(world, rand, x + i, y + j, z + k);
								}
							}
						}
					}
				}
			}
		}
	}

	private void createVine (World world, Random rand, int x, int y, int z) {

		int r = rand.nextInt(3) + 5;
		for (int i = -1; i > -r; i--) {
			this.placeLeavesBlock(world, new BlockPos(x, y + i, z), this.stateLeaves, this.generateFlag);
		}
	}

	private void createTrunk (World world, Random rand, int x, int y, int z) {

		int[] pos = new int[] { 0, 0, 1, 0, 0, 1, -1, 0, 0, -1 };
		int   sh;

		for (int t = 0; t < 5; t++) {
			sh = rand.nextInt(3) + y;
			while (sh > y - 3) {
				this.placeLogBlock(world, new BlockPos(x + pos[t * 2], sh, z
						+ pos[t * 2 + 1]), this.stateWood, this.generateFlag);
				sh--;
			}
		}
	}

	@Override
	protected boolean isGroundValid (World world, BlockPos trunkPos, boolean sandAllowed) {

		int         x     = trunkPos.getX();
		int         y     = trunkPos.getY();
		int         z     = trunkPos.getZ();
		IBlockState cb;
		BlockPos    posTemp;
		boolean     earth = false;
		boolean     water = false;

		for (int c1 = -2; c1 <= 2; c1++) {
			for (int c3 = -2; c3 <= 2; c3++) {
				for (int c2 = -1; c2 <= 1; c2++) {
					posTemp = new BlockPos(x + c1, y + c2, z + c3);
					cb      = world.getBlockState(posTemp);
					if (this.valid(cb)) {
						earth = true;
					}
					else if (cb == Blocks.WATER.getDefaultState()) {
						water = true;
					}
				}
			}
		}

		if ((!earth || !water)) {
			return false;
		}

		return true;
	}

}
