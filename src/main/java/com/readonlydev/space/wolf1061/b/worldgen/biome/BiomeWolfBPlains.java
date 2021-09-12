/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.readonlydev.space.wolf1061.b.worldgen.biome;

import java.util.Random;

import com.readonlydev.lib.world.biome.DataBuilder;
import com.readonlydev.lib.world.biome.ExoplanetBiome;
import com.readonlydev.space.wolf1061.Wolf1061Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeWolfBPlains extends ExoplanetBiome {

	public BiomeWolfBPlains () {
        super(new DataBuilder("Wolf1061 B Plains")
        		.temp(0.8F)
        		.baseHeight(0.1F)
        		.heightVariation(0.025F)
        		.build());
		this.topBlock    = Wolf1061Blocks.Wolf1061B.WOLFB_STONE.getDefaultState();
		this.fillerBlock = Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState();
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
				} else if (iblockstate2.getBlock() == Wolf1061Blocks.Wolf1061B.WOLFB_STONE) {
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
