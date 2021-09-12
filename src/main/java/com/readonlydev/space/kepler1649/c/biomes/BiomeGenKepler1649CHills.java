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

public class BiomeGenKepler1649CHills extends Kepler1649CBiomes {

	public IBlockState subTopBlock;
	public IBlockState lowerTopBlocks;
	public IBlockState topBlock;
	public IBlockState fillerBlock;

	public BiomeGenKepler1649CHills () {
        super(new DataBuilder("Kepler 1649 C Highlands")
        		.temp(0.2F)
        		.baseHeight(0.95F)
        		.heightVariation(0.55F)
        		.build());
		this.topBlock = ExoplanetBlocks.YZD_STONE.getDefaultState();
		this.subTopBlock = ExoplanetBlocks.YZD_MNT2.getDefaultState();
		this.lowerTopBlocks = ExoplanetBlocks.YZD_SEDIMENTARYROCK.getDefaultState();
		this.fillerBlock = ExoplanetBlocks.YZD_MNT1.getDefaultState();
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate3 = this.subTopBlock;
		IBlockState iblockstate4 = this.lowerTopBlocks;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.25D));
		int l = x & 15;
		int i1 = z & 15;

		for (int primerY = 255; primerY >= 0; --primerY) {
			if (primerY <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, primerY, l, Blocks.BEDROCK.getDefaultState());
			} else {
				IBlockState blockAtPosition = chunkPrimerIn.getBlockState(i1, primerY, l);
				if (blockAtPosition.getMaterial() == Material.AIR) {
					j = -1;
				} else if (blockAtPosition.getBlock() == ExoplanetBlocks.YZD_SEDIMENTARYROCK) {
					if (j == -1) {
						if ((primerY >= (63 - 4)) && (primerY <= (63 + 1))) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
							iblockstate3 = this.subTopBlock;
							iblockstate4 = this.lowerTopBlocks;
						}

						j = k;

						if (primerY >= (65 + 18)) {
							chunkPrimerIn.setBlockState(i1, primerY, l, iblockstate);
							iblockstate1 = iblockstate;
							iblockstate3 = iblockstate;
							iblockstate4 = iblockstate;
						} else if (primerY >= (65 + 12)) {
							chunkPrimerIn.setBlockState(i1, primerY, l, iblockstate3);
							iblockstate1 = iblockstate3;
							iblockstate = iblockstate3;
							iblockstate4 = iblockstate3;
						} else if (primerY >= (65 + 4)) {
							chunkPrimerIn.setBlockState(i1, primerY, l, iblockstate4);
							iblockstate1 = iblockstate4;
							iblockstate = iblockstate4;
							iblockstate3 = iblockstate4;
						} else if (primerY < (63 - 7 - k)) {
							iblockstate = null;
							iblockstate3 = null;
							iblockstate1 = this.subTopBlock;
							chunkPrimerIn.setBlockState(i1, primerY, l, Blocks.GRAVEL.getDefaultState());
						} else {
							chunkPrimerIn.setBlockState(i1, primerY, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, primerY, l, iblockstate1);
					}
				}
			}
		}
	}
}
