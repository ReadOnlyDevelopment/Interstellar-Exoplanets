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

package net.romvoid95.common.astronomy.yzceti.d.worldgen.biomes;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;
import net.romvoid95.common.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.romvoid95.core.States;

public class BiomeGenYzCetiMoltenMantleSea extends YzCetiDBiomes {

	public static IBlockState redRock = Blocks.RED_SANDSTONE.getDefaultState();

	public BiomeGenYzCetiMoltenMantleSea(BiomeProperties properties) {
		super(properties);
		this.topBlock    = States.YZD_SEDIMENTARYROCK;
		this.fillerBlock = States.YZD_IGNEOUS;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}

	@Override
	public List<Biome.SpawnListEntry> getSpawnableList (EnumCreatureType creatureType) {
		return Lists.<Biome.SpawnListEntry> newArrayList();
	}

	@Override
	public void registerTypes (Biome b) {
		BiomeDictionary
				.addTypes(b, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.OCEAN);
	}

	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int                      i                        = worldIn.getSeaLevel();
		IBlockState              iblockstate              = this.topBlock;
		IBlockState              iblockstate1             = this.fillerBlock;
		int                      j                        = -1;
		int                      k                        = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int                      l                        = x & 15;
		int                      i1                       = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			}
			else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				}
				else if (iblockstate2.getBlock() == Blocks.STONE) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate  = AIR;
							iblockstate1 = STONE;
						}
						else if (j1 >= i - 4 && j1 <= i + 1) {
							iblockstate  = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
							if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
								iblockstate = ICE;
							}
							else {
								iblockstate = WATER;
							}
						}

						j = k;

						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						}
						else if (j1 < i - 7 - k) {
							iblockstate  = AIR;
							iblockstate1 = STONE;
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						}
						else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					}
					else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

						if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1) {
							j            = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND
									? RED_SANDSTONE
									: SANDSTONE;
						}

						if (j == 0 && iblockstate == redRock && k > 1) {
							j           = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate = redRock;
						}

						if (j == 0 && iblockstate1 == redRock && k > 1) {
							j            = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = redRock;
						}
					}
				}
			}
		}
	}
}
