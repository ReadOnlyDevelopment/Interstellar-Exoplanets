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

package com.readonlydev.space.generation.chunkgen;

import java.util.List;
import java.util.Random;

import com.readonlydev.space.generation.terrain.ExoCaveGenerator;

import micdoodle8.mods.galacticraft.api.world.ChunkProviderBase;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public abstract class ExoChunkProvider extends ChunkProviderBase {
	protected Random rand;
	protected World worldObj;
	private double[] depthBuffer= new double[256];
	private Biome[] biomesForGeneration;

	private NoiseModule noiseGenSmooth1;

	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;
	public NoiseGeneratorOctaves forestNoise;
	private double[] terrainCalcs;
	private float[] biomeWeights;
	double[] mainNoiseRegion;
	double[] minLimitRegion;
	double[] maxLimitRegion;
	double[] depthRegion;

	protected IBlockState stoneBlock = this.getStoneBlock();
	protected IBlockState waterBlock;

	private ExoCaveGenerator caveGenerator = new ExoCaveGenerator();

	protected int seaLevel = 53;
	protected boolean seaIceLayer = false;

	public static double CHUNK_HEIGHT = 20.0D;

	public ExoChunkProvider(World world, long seed, boolean flag) {
		super();
		this.worldObj = world;
		this.rand = new Random(seed);

		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
		this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
		this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGenSmooth1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
		this.terrainCalcs = new double[825];
		this.biomeWeights = new float[25];

		for (int j = -2; j <= 2; j++) {
			for (int k = -2; k <= 2; k++) {
				float f = 10.0F / MathHelper.sqrt((j * j) + (k * k) + 0.2F);
				this.biomeWeights[j + 2 + ((k + 2) * 5)] = f;
			}
		}

		NoiseGenerator[] noiseGens = { minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise, forestNoise };
		this.minLimitPerlinNoise = (NoiseGeneratorOctaves) noiseGens[0];
		this.maxLimitPerlinNoise = (NoiseGeneratorOctaves) noiseGens[1];
		this.mainPerlinNoise = (NoiseGeneratorOctaves) noiseGens[2];
		this.surfaceNoise = (NoiseGeneratorPerlin) noiseGens[3];
		this.scaleNoise = (NoiseGeneratorOctaves) noiseGens[4];
		this.depthNoise = (NoiseGeneratorOctaves) noiseGens[5];
		this.forestNoise = (NoiseGeneratorOctaves) noiseGens[6];
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.rand.setSeed((chunkX * 341873128712L) + (chunkZ * 132897987541L));
		ChunkPrimer chunkprimer = new ChunkPrimer();

		this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);

		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		this.replaceBlocksForBiome(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);

		this.caveGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);

		this.onChunkProvide(chunkX, chunkZ, chunkprimer);

		Chunk chunk = new Chunk(this.worldObj, chunkprimer, chunkX, chunkZ);
		byte[] abyte = chunk.getBiomeArray();
		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, (chunkX * 4) - 2, (chunkZ * 4) - 2, 10, 10);
		this.generateHeightMap(chunkX * 4, 0, chunkZ * 4);
		this.noiseGenSmooth1.setFrequency(0.015F);

		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {
					double d0 = 0.125D;
					double d1 = this.terrainCalcs[i1 + i2];
					double d2 = this.terrainCalcs[j1 + i2];
					double d3 = this.terrainCalcs[k1 + i2];
					double d4 = this.terrainCalcs[l1 + i2];
					double d5 = (this.terrainCalcs[i1 + i2 + 1] - d1) * d0;
					double d6 = (this.terrainCalcs[j1 + i2 + 1] - d2) * d0;
					double d7 = (this.terrainCalcs[k1 + i2 + 1] - d3) * d0;
					double d8 = (this.terrainCalcs[l1 + i2 + 1] - d4) * d0;

					for (int j2 = 0; j2 < 8; ++j2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int k2 = 0; k2 < 4; ++k2) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								int x = (i * 4) + k2;
								int y = (i2 * 8) + j2;
								int z = (l * 4) + l2;



								Biome biome = worldObj.getBiome(new BlockPos(x, y, z));

								double biomeHeight = biome.getBaseHeight() * 1.0D;
								if (biomeHeight > 0.0D) {
									CHUNK_HEIGHT = biomeHeight;
								}


								CHUNK_HEIGHT += biome.getBaseHeight();

								if ((lvt_45_1_ += d16) > (this.noiseGenSmooth1.getNoise((chunkX * 16) + ((i * 4) + k2), (chunkZ * 16) + ((l * 4) + l2)) * 20.0)) {
									primer.setBlockState(x, y, z, stoneBlock);
								} else if (y < seaLevel) {
									primer.setBlockState(x, y, z, waterBlock);
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	private void generateHeightMap(int chunkX, int chunkY, int chunkZ) {
		this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, chunkX, chunkZ, 5, 5, 2000.0, 2000.0, 0.5);
		float f = 684.412F;
		float f1 = 684.412F;
		this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkX, chunkY, chunkZ, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
		this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, chunkX, chunkY, chunkZ, 5, 33, 5, f, f1, f);
		this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, chunkX, chunkY, chunkZ, 5, 33, 5, f, f1, f);
		chunkZ = 0;
		chunkX = 0;
		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				int i1 = 2;
				Biome biomegenbase = this.biomesForGeneration[k + 2 + ((l + 2) * 10)];

				for (int j1 = -i1; j1 <= i1; ++j1) {
					for (int k1 = -i1; k1 <= i1; ++k1) {
						Biome biomegenbase1 = this.biomesForGeneration[k + j1 + 2 + ((l + k1 + 2) * 10)];
						float f5 = 0.0F + (biomegenbase1.getBaseHeight() * 1.0F);
						float f6 = 0.0F + (biomegenbase1.getHeightVariation() * 1.0F);
						float f7 = this.biomeWeights[j1 + 2 + ((k1 + 2) * 5)] / (f5 + 2.0F);

						if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight()) {
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = (f2 * 0.9F) + 0.1F;
				f3 = ((f3 * 4.0F) - 1.0F) / 8.0F;
				double d7 = this.depthRegion[j] / 4000.0D;

				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}

				d7 = (d7 * 3.0D) - 2.0D;

				if (d7 < 0.0D) {
					d7 = d7 / 2.0D;

					if (d7 < -1.0D) {
						d7 = -1.0D;
					}

					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				} else {
					if (d7 > 1.0D) {
						d7 = 1.0D;
					}

					d7 = d7 / 8.0D;
				}

				++j;
				double d8 = f3;
				double d9 = f2;
				d8 = d8 + (d7 * 0.2D);
				d8 = (d8 * 8.5F) / 8.0D;
				double d0 = 8.5F + (d8 * 4.0D);

				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = ((l1 - d0) * 12.0F * 128.0D) / 256.0D / d9;

					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}

					double d2 = this.minLimitRegion[i] / 512.0F;
					double d3 = this.maxLimitRegion[i] / 512.0F;
					double d4 = ((this.mainNoiseRegion[i] / 10.0D) + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (l1 > 29) {
						double d6 = (l1 - 29) / 3.0F;
						d5 = (d5 * (1.0D - d6)) + (-10.0D * d6);
					}

					this.terrainCalcs[i] = d5;
					++i;
				}
			}
		}
	}

	/**
	 * Replaces the stone that was placed in with blocks that match the biome
	 */
	public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer chunk, Biome[] biomeGen) {
		this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, chunkX * 16, chunkZ * 16, 16, 16, 0.0625D, 0.0625D, 1.0D);

		for (int z = 0; z < 16; z++) {
			for (int x = 0; x < 16; x++) {
				Biome biomegenbase = biomeGen[x + (z * 16)];
				biomegenbase.genTerrainBlocks(this.worldObj, this.rand, chunk, (chunkX * 16) + z, (chunkZ * 16) + x, this.depthBuffer[x + (z * 16)]);
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

	protected abstract IBlockState getStoneBlock();

	@Override
	public abstract void recreateStructures(Chunk chunk, int x, int z);

	protected abstract void decoratePlanet(World world, Random rand, int x, int z);

	protected abstract void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z);

	protected abstract void onChunkProvide(int cX, int cZ, ChunkPrimer primer);

	protected abstract int getCraterProbability();

	public abstract void onPopulate(int cX, int cZ);
}
