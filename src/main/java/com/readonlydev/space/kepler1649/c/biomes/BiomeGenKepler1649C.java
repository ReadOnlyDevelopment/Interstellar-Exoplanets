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

package com.readonlydev.space.kepler1649.c.biomes;

import java.util.Random;

import com.readonlydev.core.registries.ExoplanetBlocks;
import com.readonlydev.space.generation.biome.BiomeData.DataBuilder;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenKepler1649C extends Kepler1649CBiomes {

	public BiomeGenKepler1649C () {
        super(new DataBuilder("Kepler 1649 C Plains")
        		.temp(0.8F)
        		.baseHeight(0.125F)
        		.heightVariation(0.085F)
        		.build());
		this.topBlock = ExoplanetBlocks.YZD_IGNEOUS.getDefaultState();
		this.fillerBlock = ExoplanetBlocks.YZD_LOOSE_SEDIMENT.getDefaultState();
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z,
			double noiseVal) {
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.25D));
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, Blocks.BEDROCK.getDefaultState());
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == ExoplanetBlocks.YZD_SEDIMENTARYROCK) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = null;
							iblockstate1 = ExoplanetBlocks.YZD_SEDIMENTARYROCK.getDefaultState();
						} else if ((j1 >= (63 - 4)) && (j1 <= (63 + 1))) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						j = k;

						if (j1 >= (63 - 1)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < (63 - 7 - k)) {
							iblockstate = null;
							iblockstate1 = ExoplanetBlocks.YZD_SEDIMENTARYROCK.getDefaultState();
							chunkPrimerIn.setBlockState(i1, j1, l, Blocks.GRAVEL.getDefaultState());
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
