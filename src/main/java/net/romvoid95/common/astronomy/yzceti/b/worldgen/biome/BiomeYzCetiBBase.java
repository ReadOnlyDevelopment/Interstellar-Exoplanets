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

package net.romvoid95.common.astronomy.yzceti.b.worldgen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.romvoid95.common.astronomy.yzceti.b.worldgen.BiomeDecoratorYzCetiB;
import net.romvoid95.common.world.biome.BiomeSpace;
import net.romvoid95.common.world.helpers.EnumBiomeType;
import net.romvoid95.core.ExoBlock;
import net.romvoid95.core.initialization.Planets;

public class BiomeYzCetiBBase extends BiomeSpace {

	protected static final IBlockState STONE   = ExoBlock.YZB_METAMORPHIC;
	protected static final IBlockState AIR     = Blocks.AIR.getDefaultState();
	protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
	protected static final IBlockState GRAVEL  = ExoBlock.YZB_IGNEOUS;
	protected static final IBlockState DIRT    = ExoBlock.YZB_METAMORPHIC;
	protected static final IBlockState ICE     = Blocks.PACKED_ICE.getDefaultState();
	protected static final IBlockState WATER   = Blocks.WATER.getDefaultState();

	protected static final int SEA_LEVEL       = 63;
	protected static final int SEA_FLOOR_LEVEL = 42;

	public BiomeYzCetiBBase(String singleName, BiomeProperties props) {
		super(singleName, props);
		this.setTempCategory(TempCategory.COLD);
		this.clearAllSpawning();
		this.setPlanetForBiome(Planets.yzcetib);
	}

	public final void generateYzCetiBTerrain (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		//float biomeHeight = this.getBiomeHeight();
		IBlockState topState  = this.topBlock;
		IBlockState fillState = this.fillerBlock;
		int         j         = -1;
		int         k         = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int         l         = x & 15;
		int         i1        = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 == 0) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			}
			else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
				if (this.getBiomeType() == EnumBiomeType.OCEAN) {
					if ((j1 < SEA_LEVEL) && (j1 > SEA_FLOOR_LEVEL)) {
						chunkPrimerIn.setBlockState(i1, j1, l, WATER);
					}
					else if (j1 < SEA_FLOOR_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, STONE);
					}
					else if (j1 == SEA_FLOOR_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, DIRT);
					}
					else if (j1 >= SEA_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, AIR);
					}
				}
				else {
					if (iblockstate2.getMaterial() == Material.AIR) {
						j = -1;
					}
					else if (iblockstate2.getBlock() == ExoBlock.YZB_METAMORPHIC) {
						if (j == -1) {
							if (k <= 0) {
								topState  = AIR;
								fillState = STONE;
							}
							else if (j1 >= i - 4 && j1 <= i + 1) {
								topState  = this.topBlock;
								fillState = this.fillerBlock;
							}

							if (j1 < i && (topState == null || topState.getMaterial() == Material.AIR)) {
								topState = ICE;
							}

							j = k;

							if (j1 >= i - 1) {
								chunkPrimerIn.setBlockState(i1, j1, l, topState);
							}
							else if (j1 < i - 7 - k) {
								topState  = AIR;
								fillState = STONE;
								chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
							}
							else {
								chunkPrimerIn.setBlockState(i1, j1, l, fillState);
							}
						}
						else if (j > 0) {
							--j;
							chunkPrimerIn.setBlockState(i1, j1, l, fillState);
						}
					}
				}
			}
		}
	}

	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateYzCetiBTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public float getSpawningChance () {
		return 0.1F;
	}

	@Override
	public BiomeDecorator createBiomeDecorator () {
		return new BiomeDecoratorYzCetiB();
	}

	protected BiomeDecoratorYzCetiB getBiomeDecorator () {
		return (BiomeDecoratorYzCetiB) this.decorator;
	}
}
