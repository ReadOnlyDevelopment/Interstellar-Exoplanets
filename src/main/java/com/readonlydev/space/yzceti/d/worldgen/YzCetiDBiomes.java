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

package com.readonlydev.space.yzceti.d.worldgen;

import java.util.Random;

import com.readonlydev.core.Planets;
import com.readonlydev.space.generation.biome.BiomeData;
import com.readonlydev.space.generation.biome.ExoBiome;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class YzCetiDBiomes extends ExoBiome {

	protected YzCetiDBiomes(BiomeData biomeData) {
		super(biomeData);
		setPlanetForBiome(Planets.YZCETID);
	}

	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int seaLevel = worldIn.getSeaLevel();
		IBlockState topState = this.topBlock;
		IBlockState fillState = this.fillerBlock;
		int j = -1;
		int noise = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.45D));
		int chunkX = x & 15;
		int chunkZ = z & 15;

		for (int primerY = 255; primerY >= 0; --primerY) {
			if (primerY <= rand.nextInt(3)) {
				chunkPrimerIn.setBlockState(chunkZ, primerY, chunkX, BEDROCK);
			} else {
				IBlockState blockAtPosition = chunkPrimerIn.getBlockState(chunkZ, primerY, chunkX);

				if (blockAtPosition.getMaterial() == Material.AIR) {
					j = -1;
				} else if (blockAtPosition.getBlock() == fillState) {
					if (j == -1) {
						if (noise <= 0) {
							topState = AIR;
							fillState = STONE;
						} else if ((primerY >= (seaLevel - 4)) && (primerY <= (seaLevel + 1))) {
							topState = this.topBlock;
							fillState = this.fillerBlock;
						}

						if ((primerY < seaLevel) && ((topState == null) || (topState.getMaterial() == Material.AIR))) {
							topState = ICE;
						}

						j = noise;

						if (primerY >= (seaLevel - 1)) {
							chunkPrimerIn.setBlockState(chunkZ, primerY, chunkX, topState);
						} else if (primerY < (seaLevel - 7 - noise)) {
							topState = AIR;
							fillState = STONE;
							chunkPrimerIn.setBlockState(chunkZ, primerY, chunkX, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(chunkZ, primerY, chunkX, fillState);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(chunkZ, primerY, chunkX, fillState);
					}
				}
			}
		}
	}
}																	