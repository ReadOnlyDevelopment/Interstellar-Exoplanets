package com.readonlydev.space.kepler1649.b.biomes;

import java.util.Random;

import com.readonlydev.lib.world.biome.DataBuilder;
import com.readonlydev.space.kepler1649.KeplerBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * The biome for the tall part of the cliff dimension
 *
 */
public class BiomeKepler1649BCliffPlateau extends BiomeKepler1649B {

	public BiomeKepler1649BCliffPlateau() {
        super(new DataBuilder("Kepler1649 B Cliffs")
        		.temp(0.5F)
        		.baseHeight(7.05F)
        		.heightVariation(0.215F)
        		.build());
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = KeplerBlocks.Kepler1649B.KEPLERB_STONE.getDefaultState();
		this.decorator.treesPerChunk = 1;
		this.decorator.grassPerChunk = 3;
		this.decorator.flowersPerChunk = -999;
		this.decorator.deadBushPerChunk = 4;
		this.decorator.sandPatchesPerChunk = 0;
		this.decorator.gravelPatchesPerChunk = 0;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		double d0 = GRASS_COLOR_NOISE.getValue(pos.getX() * 0.0225D, pos.getZ() * 0.0225D);
		return d0 < -0.1D ? 4671303 : 4665927;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return 4671303;
	}

	@Override
	public void generateSurface(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				}
				/**
				 * The line below is the block that needs to be match to the custom stone in
				 * order to make the block replace correctly
				 */
				else if (iblockstate2.getBlock() == stoneBlock.getBlock()) {
					if (j1 < 243) {
						iblockstate = AIR;
					}
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = stoneBlock;
						} else if (j1 >= i - 4 && j1 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
							if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
								iblockstate = ICE;
							} else {
								iblockstate = WATER;
							}
						}

						j = k;

						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < i - 7 - k) {
							iblockstate = AIR;
							iblockstate1 = stoneBlock;
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
					}
				}
			}
		}
	}
}
