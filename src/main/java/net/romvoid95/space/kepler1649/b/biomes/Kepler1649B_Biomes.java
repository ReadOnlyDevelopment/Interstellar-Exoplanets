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

package net.romvoid95.space.kepler1649.b.biomes;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.romvoid95.core.initialization.Planets;
import net.romvoid95.space.astrogeneration.biome.BiomeData;
import net.romvoid95.space.astrogeneration.biome.ExoBiome;
import net.romvoid95.space.kepler1649.b.WorldProviderKepler1649B;

public class Kepler1649B_Biomes extends ExoBiome {

	protected IBlockState top; 
	protected IBlockState filler;

	protected Kepler1649B_Biomes(BiomeData biomeData) {
		super(biomeData);
		setPlanetForBiome(Planets.KEPLER1649B);
	}
	
	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		generateTopBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal, WorldProviderKepler1649B.instance().getWorldStoneBlockState());
	}

	public void generateTopBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal,
			IBlockState stoneBlock) {
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
