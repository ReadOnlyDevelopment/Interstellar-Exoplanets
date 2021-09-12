package com.readonlydev.space.kepler1649.b.biomes;

import java.util.Random;

import com.readonlydev.core.registries.ExoplanetBlocks;
import com.readonlydev.lib.world.biome.DataBuilder;
import com.readonlydev.lib.world.biome.ExoplanetBiome;
import com.readonlydev.lib.world.biome.DataBuilder.BiomeData;
import com.readonlydev.space.kepler1649.KeplerBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeKepler1649B extends ExoplanetBiome {
	
	protected final IBlockState stoneBlock = ExoplanetBlocks.KEPLERB_STONE.getDefaultState();
	
	public BiomeKepler1649B(BiomeData biomeData) {
		super(biomeData);
	}

	public BiomeKepler1649B() {
		this(new DataBuilder("Kepler1649 B")
        		.temp(0.25F)
        		.baseHeight(0.256F)
        		.heightVariation(0.015F)
        		.build());
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = KeplerBlocks.Kepler1649B.KEPLERB_STONE.getDefaultState();
	}

	@Override
	public void generateSurface(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		IBlockState topState = this.topBlock;
		IBlockState fillState = this.fillerBlock;
		int j = -1;
		int k = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.45D));
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 == 0) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == this.fillerBlock) {
					if (j == -1) {
						if (k <= 0) {
							topState = AIR;
							fillState = STONE;
						} else if ((j1 >= (i - 4)) && (j1 <= (i + 1))) {
							topState = this.topBlock;
							fillState = this.fillerBlock;
						}

						if ((j1 < i) && ((topState == null) || (topState.getMaterial() == Material.AIR))) {
							topState = ICE;
						}

						j = k;

						if (j1 >= (i - 1)) {
							chunkPrimerIn.setBlockState(i1, j1, l, topState);
						} else if (j1 < (i - 7 - k)) {
							topState = AIR;
							fillState = STONE;
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, fillState);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, fillState);
					}
				}
			}
		}
	}
}
