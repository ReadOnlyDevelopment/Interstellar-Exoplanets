package net.romvoid95.api.world.worldgen.feature.newtreegen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.romvoid95.api.enums.LeafType;
import net.romvoid95.api.enums.WoodType;
import net.romvoid95.common.block.terrain.tree.BlockExoLeaves;
import net.romvoid95.common.block.terrain.tree.BlockExoLog;
import net.romvoid95.core.initialization.ExoBlocks;

public class FlatTopTreeGenerator extends ExoTreeGenerator {

    private final int minTreeHeight = 11;
    private IBlockState stateWood = ExoBlocks.EXO_LOG.getDefaultState().withProperty(BlockExoLog.WOOD_TYPE, WoodType.LIGHT);
    private IBlockState stateLeaves = ExoBlocks.EXO_LEAVES.getDefaultState().withProperty(BlockExoLeaves.VARIANT, LeafType.LIGHT).withProperty(BlockExoLeaves.CHECK_DECAY, false);
    
    public FlatTopTreeGenerator() {
    	this(false);
    }
    
    public FlatTopTreeGenerator(boolean notify) {
    	super(notify);
    }

	@Override
	public boolean generate (World world, Random rand, BlockPos pos) {
		if (!this.isGroundValid(world, pos)) {
			return false;
		}

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		int h  = this.minTreeHeight;
		int bh = h - 6;

		for (int i = 0; i < h; i++) {
			this.placeLogBlock(world, new BlockPos(x, y + i, z), this.stateWood, this.generateFlag);
		}
		genLeaves(world, rand, x, y + h, z);

		int   sh, dir;
		float xd, yd, c;

		for (int a = 6 + rand.nextInt(3); a > -1; a--) {
			sh  = bh + rand.nextInt(4);
			dir = rand.nextInt(360);
			xd  = (float) Math.cos((dir * Math.PI) / 180f) * 2f;
			yd  = (float) Math.sin((dir * Math.PI) / 180f) * 2f;
			c   = 1;

			while (sh < h) {
				this.placeLogBlock(world, new BlockPos(x + (int) (xd * c), y + sh, z
						+ (int) (yd * c)), this.stateWood, this.generateFlag);
				sh++;
				c += 0.5f;
			}
			genLeaves(world, rand, x + (int) (xd * c), y + sh, z + (int) (yd * c));
		}

		return true;
	}

	public void genLeaves (World world, Random rand, int x, int y, int z) {

			int i;
			int j;
			for (i = -2; i <= 2; i++) {
				for (j = -2; j <= 2; j++) {
					if ((Math.abs(i) + Math.abs(j)) < 4) {
						this.placeLeavesBlock(world, new BlockPos(x + i, y + 1, z
								+ j), this.stateLeaves, this.generateFlag);
					}
				}
			}

			for (i = -3; i <= 3; i++) {
				for (j = -3; j <= 3; j++) {
					if ((Math.abs(i) + Math.abs(j)) < 5) {
						this.placeLeavesBlock(world, new BlockPos(x + i, y, z
								+ j), this.stateLeaves, this.generateFlag);
					}
				}
			}


		this.placeLogBlock(world, new BlockPos(x, y, z), this.stateWood, this.generateFlag);
	}

}
