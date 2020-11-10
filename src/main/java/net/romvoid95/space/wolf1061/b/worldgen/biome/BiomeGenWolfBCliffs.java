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
package net.romvoid95.space.wolf1061.b.worldgen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.romvoid95.space.astrogeneration.biome.BiomeData.DataValues;
import net.romvoid95.space.wolf1061.Wolf1061Blocks;
import net.romvoid95.space.wolf1061.b.worldgen.WolfBBiomes;
import net.romvoid95.space.yzceti.YzCetiBlocks;

public class BiomeGenWolfBCliffs extends WolfBBiomes {


	public BiomeGenWolfBCliffs () {
        super(new DataValues("Wolf1061 B Cliffs")
        		.temperature(0.5F)
        		.baseHeight(0.5455F)
        		.heightVariation(0.265F)
        		.finalzie()
        		);
		this.fillerBlock = YzCetiBlocks.D.YZD_MNT2.getDefaultState();
	}

	@Override
	public void generateBiomeSurface(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.25D));
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == Wolf1061Blocks.Wolf1061B.WOLFB_STONE) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = Wolf1061Blocks.Wolf1061B.WOLFB_STONE.getDefaultState();
						} else if ((j1 >= (i - 4)) && (j1 <= (i + 1))) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						j = k;

						if (j1 >= (i - 1)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < (i - 7 - k)) {
							iblockstate = AIR;
							iblockstate1 = Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState();
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

						if ((j == 0) && (iblockstate1.getBlock() == Wolf1061Blocks.Wolf1061B.WOLFB_STONE) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
						}

						if ((j == 0) && (iblockstate == Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState()) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate = Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState();
						}

						if ((j == 0) && (iblockstate1 == Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState()) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState();
						}
					}
				}
			}
		}
	}
}
