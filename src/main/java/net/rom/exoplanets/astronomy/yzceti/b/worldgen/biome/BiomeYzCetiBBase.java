/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.astronomy.yzceti.b.worldgen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzceti.b.worldgen.BiomeDecoratorYzCetiB;
import net.rom.exoplanets.init.InitPlanets;
import net.rom.exoplanets.internal.enums.EnumBiomeType;
import net.rom.exoplanets.internal.world.biome.BiomeSpace;

public class BiomeYzCetiBBase extends BiomeSpace {

	protected static final IBlockState STONE = YzCetiBlocks.CetiB.B_METAMORPHIC.getDefaultState();
	protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
	protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
	protected static final IBlockState GRAVEL = YzCetiBlocks.CetiB.B_IGNEOUS.getDefaultState();
	protected static final IBlockState DIRT = YzCetiBlocks.CetiB.B_METAMORPHIC.getDefaultState();
	protected static final IBlockState ICE = Blocks.PACKED_ICE.getDefaultState();
	protected static final IBlockState WATER = Blocks.WATER.getDefaultState();

	protected static final int SEA_LEVEL = 63;
	protected static final int SEA_FLOOR_LEVEL = 42;

	public BiomeYzCetiBBase(String singleName, BiomeProperties props) {
		super(singleName, props);
		this.setTempCategory(TempCategory.COLD);
		this.clearAllSpawning();
		this.setPlanetForBiome(InitPlanets.yzcetib);
	}

	public final void generateYzCetiBTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		//float biomeHeight = this.getBiomeHeight();
		IBlockState topState = this.topBlock;
		IBlockState fillState = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 == 0) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
				if (this.getBiomeType() == EnumBiomeType.OCEAN) {
					if ((j1 < SEA_LEVEL) && (j1 > SEA_FLOOR_LEVEL)) {
						chunkPrimerIn.setBlockState(i1, j1, l, WATER);
					} else if (j1 < SEA_FLOOR_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, STONE);
					} else if (j1 == SEA_FLOOR_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, DIRT);
					} else if (j1 >= SEA_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, AIR);
					}
				} else {
					if (iblockstate2.getMaterial() == Material.AIR) {
						j = -1;
					} else if (iblockstate2.getBlock() == YzCetiBlocks.CetiB.B_METAMORPHIC) {
						if (j == -1) {
							if (k <= 0) {
								topState = AIR;
								fillState = STONE;
							} else if (j1 >= i - 4 && j1 <= i + 1) {
								topState = this.topBlock;
								fillState = this.fillerBlock;
							}

							if (j1 < i && (topState == null || topState.getMaterial() == Material.AIR)) {
								topState = ICE;
							}

							j = k;

							if (j1 >= i - 1) {
								chunkPrimerIn.setBlockState(i1, j1, l, topState);
							} else if (j1 < i - 7 - k) {
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

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateYzCetiBTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public float getSpawningChance() {
		return 0.1F;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new BiomeDecoratorYzCetiB();
	}

	protected BiomeDecoratorYzCetiB getBiomeDecorator() {
		return (BiomeDecoratorYzCetiB) this.decorator;
	}
}
