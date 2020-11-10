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
package net.romvoid95.space.yzceti.d.worldgen.biomes;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.romvoid95.space.astrogeneration.biome.BiomeData.DataValues;
import net.romvoid95.space.yzceti.YzCetiBlocks;
import net.romvoid95.space.yzceti.d.worldgen.YzCetiDBiomes;

public class BiomeCetiDCliffs extends YzCetiDBiomes {

	protected static final WorldGenBlockBlob	BOULDER_FEATURE	= new WorldGenBlockBlob(YzCetiBlocks.D.YZD_STONE, 5);
	public static IBlockState					rock			= YzCetiBlocks.D.YZD_MNT1.getDefaultState();

	public BiomeCetiDCliffs () {
		super(new DataValues("YzCeti D Cliffs")
        		.temperature(0.5F)
        		.baseHeight(0.5455F)
        		.heightVariation(0.2F)
        		.finalzie());
		this.topBlock = rock;
		this.fillerBlock = YzCetiBlocks.D.YZD_MNT2.getDefaultState();
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		int genChance = rand.nextInt(9);
		if (genChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			BOULDER_FEATURE.generate(worldIn, rand, blockpos);
		}

		super.decorate(worldIn, rand, pos);
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
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
				} else if (iblockstate2.getBlock() == YzCetiBlocks.D.YZD_SEDIMENTARYROCK) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = YzCetiBlocks.D.YZD_SEDIMENTARYROCK.getDefaultState();
						} else if ((j1 >= (i - 4)) && (j1 <= (i + 1))) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						j = k;

						if (j1 >= (i - 1)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < (i - 7 - k)) {
							iblockstate = AIR;
							iblockstate1 = YzCetiBlocks.D.YZD_SEDIMENTARYROCK.getDefaultState();
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

						if ((j == 0) && (iblockstate1.getBlock() == YzCetiBlocks.D.YZD_SEDIMENTARYROCK) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
						}

						if ((j == 0) && (iblockstate == rock) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate = rock;
						}

						if ((j == 0) && (iblockstate1 == rock) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = rock;
						}
					}
				}
			}
		}
	}
}
