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
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.romvoid95.space.astrogeneration.biome.BiomeData.DataValues;
import net.romvoid95.space.wolf1061.Wolf1061Blocks;
import net.romvoid95.space.wolf1061.b.worldgen.WolfBBiomes;
import net.romvoid95.space.yzceti.YzCetiBlocks;

public class BiomeGenWolfBHills extends WolfBBiomes {

	public IBlockState subTopBlock;
	public IBlockState lowerTopBlocks;
	public IBlockState topBlock;
	public IBlockState fillerBlock;

	public BiomeGenWolfBHills () {
        super(new DataValues("Wolf1061 B Hills")
        		.temperature(0.2F)
        		.baseHeight(2.1F)
        		.heightVariation(0.45F)
        		.finalzie());
		this.topBlock = YzCetiBlocks.D.YZD_STONE.getDefaultState();
		this.subTopBlock = YzCetiBlocks.D.YZD_MNT2.getDefaultState();
		this.lowerTopBlocks = YzCetiBlocks.D.YZD_SEDIMENTARYROCK.getDefaultState();
		this.fillerBlock = YzCetiBlocks.D.YZD_MNT1.getDefaultState();
	}

	@Override
	public void generateBiomeSurface(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate3 = this.subTopBlock;
		IBlockState iblockstate4 = this.lowerTopBlocks;
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
				} else if (iblockstate2.getBlock() == Wolf1061Blocks.Wolf1061B.WOLFB_STONE) {
					if (j == -1) {
						if ((j1 >= (63 - 4)) && (j1 <= (63 + 1))) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
							iblockstate3 = this.subTopBlock;
							iblockstate4 = this.lowerTopBlocks;
						}

						j = k;

						if (j1 >= (65 + 18)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
							iblockstate1 = iblockstate;
							iblockstate3 = iblockstate;
							iblockstate4 = iblockstate;
						} else if (j1 >= (65 + 12)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate3);
							iblockstate1 = iblockstate3;
							iblockstate = iblockstate3;
							iblockstate4 = iblockstate3;
						} else if (j1 >= (65 + 4)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate4);
							iblockstate1 = iblockstate4;
							iblockstate = iblockstate4;
							iblockstate3 = iblockstate4;
						} else if (j1 < (63 - 7 - k)) {
							iblockstate = null;
							iblockstate3 = null;
							iblockstate1 = this.subTopBlock;
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
