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

package net.romvoid95.common.world.chunk;

import java.util.*;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import net.romvoid95.common.world.cave.MapGeneExCaves;
import net.romvoid95.common.world.cave.ravine.MapGenExRavine;
import net.romvoid95.common.world.generation.NoiseGeneratorExo;
import net.romvoid95.common.world.generation.TerrainSettings;
import net.romvoid95.common.world.mapgen.MapGenBaseMeta;

public abstract class ExoChunkProviderAdvSpace extends ExoChunkProviderBase {

	protected Random				rand;
	protected World					worldObj;
	private Biome[]					biomesForGeneration;
	private NoiseGeneratorOctaves	xyzNoiseGenA;
	private NoiseGeneratorOctaves	xyzNoiseGenB;
	private NoiseGeneratorOctaves	xyzBalanceNoiseGen;
	private NoiseGeneratorPerlin	stoneNoiseGen;
	public NoiseGeneratorExo		byteNoiseGen;
	private double[]				xyzBalanceNoiseArray;
	private double[]				xyzNoiseArrayA;
	private double[]				xyzNoiseArrayB;
	private double[]				stoneNoiseArray;
	private final double[]			noiseArray;

	private MapGeneExCaves	caveGenerator	= new MapGeneExCaves();
	private MapGenExRavine	ravineGenerator	= new MapGenExRavine();

	private List<MapGenBaseMeta>		worldGenerators;
	private Map<Biome, TerrainSettings>	biomeTerrainSettings;

	protected IBlockState	stoneBlock;
	protected IBlockState	waterBlock;

	protected int seaLevel = 53;

	public ExoChunkProviderAdvSpace (World world, long seed, boolean flag) {
		super();
		this.worldObj = world;
		this.rand = new Random(seed);

		this.xyzNoiseGenA = new NoiseGeneratorOctaves(this.rand, 16);
		this.xyzNoiseGenB = new NoiseGeneratorOctaves(this.rand, 16);
		this.xyzBalanceNoiseGen = new NoiseGeneratorOctaves(this.rand, 8);
		this.stoneNoiseGen = new NoiseGeneratorPerlin(this.rand, 4);
		this.byteNoiseGen = new NoiseGeneratorExo(this.rand, 6, 5, 5); // 6 octaves, 5x5 xz grid
		this.stoneNoiseArray = new double[256];
		this.noiseArray = new double[825];

		this.biomeTerrainSettings = new HashMap<>();
		for (Biome biome : Lists.newArrayList(Biome.REGISTRY.iterator())) {
			if (biome == null) {
				continue;
			}
			this.biomeTerrainSettings.put(biome, TerrainSettings.forVanillaBiome(biome));
		}
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setChunkAirStoneWater(chunkX, chunkZ, chunkprimer);

		if (this.worldGenerators == null) {
			this.worldGenerators = this.getWorldGenerators();
		}

		for (MapGenBaseMeta generator : this.worldGenerators) {
			generator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
		}

		this.ravineGenerator.generate(worldObj, chunkX, chunkZ, chunkprimer);
		this.caveGenerator.generate(worldObj, chunkX, chunkZ, chunkprimer);

		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16,
				chunkZ * 16, 16, 16);
		this.replaceBlocksForBiome(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);

		this.onChunkProvide(chunkX, chunkZ, chunkprimer);

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, chunkX, chunkZ);
        byte[] chunkBiomes = chunk.getBiomeArray();
        for (int k = 0; k < chunkBiomes.length; ++k)
        {
            chunkBiomes[k] = (byte)Biome.getIdForBiome(biomesForGeneration[k]);
        }
        chunk.generateSkylightMap();
		return chunk;
	}

	private static float[]	radialFalloff5x5		= new float[25];
	private static float[]	radialStrongFalloff5x5	= new float[25];
	static {
		for (int j = -2; j <= 2; ++j) {
			for (int k = -2; k <= 2; ++k) {
				radialFalloff5x5[j + 2 + (k + 2) * 5] = 0.06476162171F / MathHelper.sqrt(j * j + k * k + 0.2F);
				radialStrongFalloff5x5[j + 2 + (k + 2) * 5] = 0.076160519601F / (j * j + k * k + 0.2F);
			}
		}
	}

	private TerrainSettings getWeightedTerrainSettings(int localX, int localZ, Biome[] biomes) {

		Biome centerBiome = biomes[localX + 2 + (localZ + 2) * 10];
		if (centerBiome == Biomes.RIVER || centerBiome == Biomes.FROZEN_RIVER) {
			return this.biomeTerrainSettings.get(centerBiome);
		}

		// Otherwise, get weighted average of properties from this and surrounding biomes
		TerrainSettings settings = new TerrainSettings();
		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float weight = radialFalloff5x5[i + 2 + (j + 2) * 5];
				TerrainSettings biomeSettings = this.biomeTerrainSettings.get(
						biomes[localX + i + 2 + (localZ + j + 2) * 10]);

				if (biomeSettings != null) {
					settings.avgHeight += weight * biomeSettings.avgHeight;
					settings.variationAbove += weight * biomeSettings.variationAbove;
					settings.variationBelow += weight * biomeSettings.variationBelow;
					settings.minHeight += weight * biomeSettings.minHeight;
					settings.maxHeight += weight * biomeSettings.maxHeight;
					settings.sidewaysNoiseAmount += weight * biomeSettings.sidewaysNoiseAmount;
					for (int k = 0; k < settings.octaveWeights.length; k++) {
						settings.octaveWeights[k] += weight * biomeSettings.octaveWeights[k];
					}
				}
			}
		}

		return settings;
	}

	public void setChunkAirStoneWater(int chunkX, int chunkZ, ChunkPrimer primer) {

		this.populateNoiseArray(chunkX, chunkZ);

		double oneEighth = 0.125D;
		double oneQuarter = 0.25D;

		for (int ix = 0; ix < 4; ++ix) {
			int k_x0 = ix * 5;
			int k_x1 = (ix + 1) * 5;
			for (int iz = 0; iz < 4; ++iz) {
				int k_x0z0 = (k_x0 + iz) * 33;
				int k_x0z1 = (k_x0 + iz + 1) * 33;
				int k_x1z0 = (k_x1 + iz) * 33;
				int k_x1z1 = (k_x1 + iz + 1) * 33;
				for (int iy = 0; iy < 32; ++iy) {
					double n_x0y0z0 = this.noiseArray[k_x0z0 + iy];
					double n_x0y0z1 = this.noiseArray[k_x0z1 + iy];
					double n_x1y0z0 = this.noiseArray[k_x1z0 + iy];
					double n_x1y0z1 = this.noiseArray[k_x1z1 + iy];
					double n_x0y1z0 = this.noiseArray[k_x0z0 + iy + 1];
					double n_x0y1z1 = this.noiseArray[k_x0z1 + iy + 1];
					double n_x1y1z0 = this.noiseArray[k_x1z0 + iy + 1];
					double n_x1y1z1 = this.noiseArray[k_x1z1 + iy + 1];

					double noiseStepY00 = (n_x0y1z0 - n_x0y0z0) * oneEighth;
					double noiseStepY01 = (n_x0y1z1 - n_x0y0z1) * oneEighth;
					double noiseStepY10 = (n_x1y1z0 - n_x1y0z0) * oneEighth;
					double noiseStepY11 = (n_x1y1z1 - n_x1y0z1) * oneEighth;

					double noiseStartX0 = n_x0y0z0;
					double noiseStartX1 = n_x0y0z1;
					double noiseEndX0 = n_x1y0z0;
					double noiseEndX1 = n_x1y0z1;

					for (int jy = 0; jy < 8; ++jy) {

						double noiseStartZ = noiseStartX0;
						double noiseEndZ = noiseStartX1;
						double noiseStepX0 = (noiseEndX0 - noiseStartX0) * oneQuarter;
						double noiseStepX1 = (noiseEndX1 - noiseStartX1) * oneQuarter;

						for (int jx = 0; jx < 4; ++jx) {
							double noiseStepZ = (noiseEndZ - noiseStartZ) * oneQuarter;
							double noiseVal = noiseStartZ;

							for (int jz = 0; jz < 4; ++jz) {
								if (noiseVal > 0.0D) {
									primer.setBlockState(ix * 4 + jx, iy * 8 + jy, iz * 4 + jz, this.stoneBlock);
								} else if (iy * 8 + jy < this.seaLevel) {
									primer.setBlockState(ix * 4 + jx, iy * 8 + jy, iz * 4 + jz, this.waterBlock);
								}
								noiseVal += noiseStepZ;
							}

							noiseStartZ += noiseStepX0;
							noiseEndZ += noiseStepX1;
						}

						noiseStartX0 += noiseStepY00;
						noiseStartX1 += noiseStepY01;
						noiseEndX0 += noiseStepY10;
						noiseEndX1 += noiseStepY11;
					}
				}
			}
		}
	}

	private void populateNoiseArray(int chunkX, int chunkZ) {
		Biome[] biomes = this.worldObj.getBiomeProvider().getBiomesForGeneration(null, chunkX * 4 - 2, chunkZ * 4 - 2,
				10, 10);
		float coordinateScale = 684.412F;
		float heightScale = 684.412F;
		double upperLimitScale = 512.0F;
		double lowerLimitScale = 512.0F;
		float mainNoiseScaleX = 80.0F;
		float mainNoiseScaleY = 160.0F;
		float mainNoiseScaleZ = 80.0F;
		int subchunkX = chunkX * 4;
		int subchunkY = 0;
		int subchunkZ = chunkZ * 4;
		this.byteNoiseGen.generateNoise(subchunkX, subchunkZ);
		this.xyzBalanceNoiseArray = this.xyzBalanceNoiseGen.generateNoiseOctaves(this.xyzBalanceNoiseArray, subchunkX,
				subchunkY, subchunkZ, 5, 33, 5, coordinateScale / mainNoiseScaleX, heightScale / mainNoiseScaleY,
				coordinateScale / mainNoiseScaleZ);
		this.xyzNoiseArrayA = this.xyzNoiseGenA.generateNoiseOctaves(this.xyzNoiseArrayA, subchunkX, subchunkY,
				subchunkZ, 5, 33, 5, coordinateScale, heightScale, coordinateScale);
		this.xyzNoiseArrayB = this.xyzNoiseGenB.generateNoiseOctaves(this.xyzNoiseArrayB, subchunkX, subchunkY,
				subchunkZ, 5, 33, 5, coordinateScale, heightScale, coordinateScale);
		int xyzCounter = 0;
		int xzCounter = 0;
		for (int ix = 0; ix < 5; ++ix) {
			for (int iz = 0; iz < 5; ++iz) {
				TerrainSettings settings = this.getWeightedTerrainSettings(ix, iz, biomes);
				double xzNoiseVal = this.byteNoiseGen.getWeightedDouble(xzCounter, settings.octaveWeights);
				double xzAmplitude = 1.0F * (xzNoiseVal < 0 ? settings.variationBelow : settings.variationAbove)
						* (1 - settings.sidewaysNoiseAmount);
				double xyzAmplitude = 1.0F * (xzNoiseVal < 0 ? settings.variationBelow : settings.variationAbove)
						* (settings.sidewaysNoiseAmount);
				double baseLevel = settings.avgHeight + (xzNoiseVal * xzAmplitude);

				for (int iy = 0; iy < 33; ++iy) {
					int y = iy * 8;

					if (y < settings.minHeight) {
						this.noiseArray[xyzCounter] = settings.minHeight - y;
					} else if (y > settings.maxHeight) {
						this.noiseArray[xyzCounter] = settings.maxHeight - y;
					} else {
						double xyzNoiseA = this.xyzNoiseArrayA[xyzCounter] / lowerLimitScale;
						double xyzNoiseB = this.xyzNoiseArrayB[xyzCounter] / upperLimitScale;
						double balance = (this.xyzBalanceNoiseArray[xyzCounter] / 10.0D + 1.0D) / 2.0D;
						double xyzNoiseValue = MathHelper.clamp(xyzNoiseA, xyzNoiseB, balance) / 50.0D;
						double depth = baseLevel - y + (xyzAmplitude * xyzNoiseValue);
						if (iy > 29) {
							double closeToTopOfChunkFactor = (iy - 29) / 3.0F;
							depth = depth * (1.0D - closeToTopOfChunkFactor) + -10.0D * closeToTopOfChunkFactor;
						}
						this.noiseArray[xyzCounter] = depth;
					}
					++xyzCounter;
				}
				xzCounter++;
			}
		}
	}

	public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomes) {
		double d0 = 0.03125D;
		this.stoneNoiseArray = this.stoneNoiseGen.getRegion(this.stoneNoiseArray, chunkX * 16, chunkZ * 16, 16, 16,
				d0 * 2.0D, d0 * 2.0D, 1.0D);

		for (int localX = 0; localX < 16; ++localX) {
			for (int localZ = 0; localZ < 16; ++localZ) {
				Biome biome = biomes[localZ + localX * 16];
				biome.genTerrainBlocks(this.worldObj, this.rand, primer, chunkX * 16 + localX, chunkZ * 16 + localZ,
						this.stoneNoiseArray[localZ + localX * 16]);
			}
		}
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		BlockFalling.fallInstantly = true;
		int x = chunkX * 16;
		int z = chunkZ * 16;
		BlockPos pos = new BlockPos(x, 0, z);
		Biome biome = this.worldObj.getBiome(pos.add(16, 0, 16));
		this.rand.setSeed(this.worldObj.getSeed());
		long k = ((this.rand.nextLong() / 2L) * 2L) + 1L;
		long l = ((this.rand.nextLong() / 2L) * 2L) + 1L;
		this.rand.setSeed(((chunkX * k) + (chunkZ * l)) ^ this.worldObj.getSeed());
		ChunkPos chunkpos = new ChunkPos(chunkX, chunkZ);
		this.populate(pos, chunkpos, biome, chunkX, chunkZ, x, z);
		this.decoratePlanet(this.worldObj, this.rand, x, z);
		this.onPopulate(x, z);

		BlockFalling.fallInstantly = false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biomegenbase = this.worldObj.getBiome(pos);
		return biomegenbase.getSpawnableList(creatureType);
	}

	@Override
	public abstract void recreateStructures(Chunk chunk, int x, int z);

	protected abstract void decoratePlanet(World world, Random rand, int x, int z);

	protected abstract List<MapGenBaseMeta> getWorldGenerators();

	protected abstract void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x,
			int z);

	protected abstract void onChunkProvide(int cX, int cZ, ChunkPrimer primer);

	protected abstract int getCraterProbability();

	public abstract void onPopulate(int cX, int cZ);
}
