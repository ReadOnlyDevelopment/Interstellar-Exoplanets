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

import com.readonlydev.space.generation.noise.NewGradient;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.world.ChunkProviderBase;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.world.gen.EnumCraterSize;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public abstract class ChunkGeneratorSpace extends ChunkProviderBase {
	protected final Random rand;

	private final NewGradient noiseGen1;
	private final NewGradient noiseGen2;
	private final NewGradient noiseGen3;
	private final NewGradient noiseGen4;
	private final NewGradient noiseGen5;
	private final NewGradient noiseGen6;
	private final NewGradient noiseGen7;

	protected final World world;

	private Biome[] biomesForGeneration = this.getBiomesForGeneration();

	private final double TERRAIN_HEIGHT_MOD = this.getHeightModifier();
	private final double SMALL_FEATURE_HEIGHT_MOD = this.getSmallFeatureHeightModifier();
	private final double MOUNTAIN_HEIGHT_MOD = this.getMountainHeightModifier();
	private final double VALLEY_HEIGHT_MOD = this.getValleyHeightModifier();
	private final int CRATER_PROB = this.getCraterProbability();

	// DO NOT CHANGE
	private final int MID_HEIGHT = this.getSeaLevel();
	private static final int CHUNK_SIZE_X = 16;
	private static final int CHUNK_SIZE_Y = 256;
	private static final int CHUNK_SIZE_Z = 16;
	private static final double MAIN_FEATURE_FILTER_MOD = 4;
	private static final double LARGE_FEATURE_FILTER_MOD = 8;
	private static final double SMALL_FEATURE_FILTER_MOD = 8;

	public ChunkGeneratorSpace(World par1World, long seed, boolean mapFeaturesEnabled)
	{
		this.world = par1World;
		this.rand = new Random(seed);

		this.noiseGen1 = new NewGradient(this.rand.nextLong(), 6, 0.25F);
		this.noiseGen2 = new NewGradient(this.rand.nextLong(), 6, 0.25F);
		this.noiseGen3 = new NewGradient(this.rand.nextLong(), 4, 0.25F);
		this.noiseGen4 = new NewGradient(this.rand.nextLong(), 2, 0.25F);
		this.noiseGen5 = new NewGradient(this.rand.nextLong(), 1, 0.25F);
		this.noiseGen6 = new NewGradient(this.rand.nextLong(), 1, 0.25F);
		this.noiseGen7 = new NewGradient(this.rand.nextLong(), 1, 0.25F);
	}

	public void generateTerrain(int chunkX, int chunkZ, ChunkPrimer primer)
	{
		this.noiseGen1.setFrequency(0.015F);
		this.noiseGen2.setFrequency(0.01F);
		this.noiseGen3.setFrequency(0.01F);
		this.noiseGen4.setFrequency(0.01F);
		this.noiseGen5.setFrequency(0.01F);
		this.noiseGen6.setFrequency(0.001F);
		this.noiseGen7.setFrequency(0.005F);

		for (int x = 0; x < ChunkGeneratorSpace.CHUNK_SIZE_X; x++)
		{
			for (int z = 0; z < ChunkGeneratorSpace.CHUNK_SIZE_Z; z++)
			{
				final double baseHeight = this.noiseGen1.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * this.TERRAIN_HEIGHT_MOD;
				final double smallHillHeight = this.noiseGen2.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * this.SMALL_FEATURE_HEIGHT_MOD;
				double mountainHeight = Math.abs(this.noiseGen3.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
				double valleyHeight = Math.abs(this.noiseGen4.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
				final double featureFilter = this.noiseGen5.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkGeneratorSpace.MAIN_FEATURE_FILTER_MOD;
				final double largeFilter = this.noiseGen6.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkGeneratorSpace.LARGE_FEATURE_FILTER_MOD;
				final double smallFilter = this.noiseGen7.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkGeneratorSpace.SMALL_FEATURE_FILTER_MOD - 0.5;
				mountainHeight = this.lerp(smallHillHeight, mountainHeight * this.MOUNTAIN_HEIGHT_MOD, this.fade(this.clamp(mountainHeight * 2, 0, 1)));
				valleyHeight = this.lerp(smallHillHeight, valleyHeight * this.VALLEY_HEIGHT_MOD - this.VALLEY_HEIGHT_MOD + 9, this.fade(this.clamp((valleyHeight + 2) * 4, 0, 1)));

				double yDev = this.lerp(valleyHeight, mountainHeight, this.fade(largeFilter));
				yDev = this.lerp(smallHillHeight, yDev, smallFilter);
				yDev = this.lerp(baseHeight, yDev, featureFilter);

				for (int y = 0; y < ChunkGeneratorSpace.CHUNK_SIZE_Y; y++)
				{
					if (y < this.MID_HEIGHT + yDev)
					{
						primer.setBlockState(x, y, z, this.getStoneBlock().getDefaultState());
					}
				}
			}
		}
	}

	private double lerp(double d1, double d2, double t)
	{
		if (t < 0.0)
		{
			return d1;
		}
		else if (t > 1.0)
		{
			return d2;
		}
		else
		{
			return d1 + (d2 - d1) * t;
		}
	}

	private double fade(double n)
	{
		return n * n * n * (n * (n * 6 - 15) + 10);
	}

	private double clamp(double x, double min, double max)
	{
		if (x < min)
		{
			return min;
		}
		if (x > max)
		{
			return max;
		}
		return x;
	}

	public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn)
	{
		final int var5 = 20;
		final float var6 = 0.03125F;
		this.noiseGen4.setFrequency(var6 * 2);
		for (int var8 = 0; var8 < 16; ++var8)
		{
			for (int var9 = 0; var9 < 16; ++var9)
			{
				final int var12 = (int) (this.noiseGen4.getNoise(x * 16 + var8, z * 16 + var9) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var13 = -1;
				Block var14 = this.getGrassBlock();
				Block var15 = this.getDirtBlock();

				for (int var16 = ChunkGeneratorSpace.CHUNK_SIZE_Y - 1; var16 >= 0; --var16)
				{
					//final int index = this.getIndex(var8, var16, var9);

					if (var16 <= 0 + this.rand.nextInt(5))
					{
						primer.setBlockState(var8, var16, var9, Blocks.BEDROCK.getDefaultState());
					}
					else
					{
						Block var18 = primer.getBlockState(var8, var16, var9).getBlock();

						if (Blocks.AIR == var18)
						{
							var13 = -1;
						}
						else if (var18 == this.getStoneBlock())
						{

							if (var13 == -1)
							{
								if (var12 <= 0)
								{
									var14 = Blocks.AIR;
									var15 = this.getStoneBlock();
								}
								else if (var16 >= var5 - -16 && var16 <= var5 + 1)
								{
									var14 = this.getGrassBlock();
									var14 = this.getDirtBlock();
								}

								var13 = var12;

								if (var16 >= var5 - 1)
								{
									primer.setBlockState(var8, var16, var9, var14.getDefaultState());
								}
								else
								{
									primer.setBlockState(var8, var16, var9, var15.getDefaultState());
								}
							}
							else if (var13 > 0)
							{
								--var13;
								primer.setBlockState(var8, var16, var9, var15.getDefaultState());
							}
						}
					}
				}
			}
		}
	}

	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer primer = new ChunkPrimer();
		try {
			this.rand.setSeed(x * 341873128712L + z * 132897987541L);
			this.generateTerrain(x, z, primer);
			this.createCraters(x, z, primer);
			this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
			this.replaceBiomeBlocks(x, z, primer, this.biomesForGeneration);

			this.onChunkProvide(x, z, primer);
		}
		catch (Exception e)
		{
			GCLog.severe("Error caught in planetary worldgen at coords " + x + "," + z + ".  If the next 2 lines are showing an Add-On mod name, please report to that mod's author!");
			e.printStackTrace();
		}

		final Chunk var4 = new Chunk(this.world, primer, x, z);
		final byte[] var5 = var4.getBiomeArray();

		for (int var6 = 0; var6 < var5.length; ++var6)
		{
			var5[var6] = (byte) Biome.getIdForBiome(this.biomesForGeneration[var6]);
		}

		var4.generateSkylightMap();
		return var4;
	}

	public void createCraters(int chunkX, int chunkZ, ChunkPrimer primer)
	{
		this.noiseGen5.setFrequency(0.015F);
		for (int cx = chunkX - 2; cx <= chunkX + 2; cx++)
		{
			for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++)
			{
				for (int x = 0; x < ChunkGeneratorSpace.CHUNK_SIZE_X; x++)
				{
					for (int z = 0; z < ChunkGeneratorSpace.CHUNK_SIZE_Z; z++)
					{
						if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseGen5.getNoise(cx * 16 + x, cz * 16 + z) / this.CRATER_PROB)
						{
							final Random random = new Random(cx * 16 + x + (cz * 16 + z) * 5000);
							final EnumCraterSize cSize = EnumCraterSize.sizeArray[random.nextInt(EnumCraterSize.sizeArray.length)];
							final int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE + 15;
							this.makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, primer);
						}
					}
				}
			}
		}
	}

	public void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, ChunkPrimer primer)
	{
		for (int x = 0; x < ChunkGeneratorSpace.CHUNK_SIZE_X; x++)
		{
			for (int z = 0; z < ChunkGeneratorSpace.CHUNK_SIZE_Z; z++)
			{
				double xDev = craterX - (chunkX + x);
				double zDev = craterZ - (chunkZ + z);
				if (xDev * xDev + zDev * zDev < size * size)
				{
					xDev /= size;
					zDev /= size;
					final double sqrtY = xDev * xDev + zDev * zDev;
					double yDev = sqrtY * sqrtY * 6;
					yDev = 5 - yDev;
					int helper = 0;
					for (int y = 127; y > 0; y--)
					{
						if (Blocks.AIR != primer.getBlockState(x, y, z).getBlock() && helper <= yDev)
						{
							primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
							helper++;
						}

						if (helper > yDev)
						{
							break;
						}
					}
				}
			}
		}
	}

	//    private int getIndex(int x, int y, int z)
	//    {
	//        return (x * 16 + z) * 256 + y;
	//    }

	private double randFromPoint(int x, int z)
	{
		int n;
		n = x + z * 57;
		n = n << 13 ^ n;
		return 1.0 - (n * (n * n * 15731 + 789221) + 1376312589 & 0x7fffffff) / 1073741824.0;
	}

	public void decoratePlanet(World par1World, Random par2Random, int par3, int par4)
	{
		this.getBiomeGenerator().decorate(par1World, par2Random, par3, par4);
	}

	@Override
	public void populate(int x, int z)
	{
		BlockFalling.fallInstantly = true;
		int var4 = x * 16;
		int var5 = z * 16;
		this.world.getBiome(new BlockPos(var4 + 16, 0, var5 + 16));
		this.rand.setSeed(this.world.getSeed());
		final long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		final long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(x * var7 + z * var9 ^ this.world.getSeed());
		this.decoratePlanet(this.world, this.rand, var4, var5);
		this.onPopulate(x, z);

		BlockFalling.fallInstantly = false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		Biome biomegenbase = this.world.getBiome(pos);
		return biomegenbase.getSpawnableList(creatureType);
	}

	/**
	 * Do not return null
	 *
	 * @return The biome generator for this world, handles ore, flower, etc
	 * generation. See GCBiomeDecoratorBase.
	 */
	protected abstract BiomeDecoratorSpace getBiomeGenerator();

	/**
	 * Do not return null, have at least one biome for generation
	 *
	 * @return Biome instance for generation
	 */
	protected abstract Biome[] getBiomesForGeneration();

	/**
	 * @return The average terrain level. Default is 64.
	 */
	protected abstract int getSeaLevel();

	/**
	 * The grass block to be generated. Doesn't have to be grass of course.
	 *
	 * @return BlockMetaPair instance containing ID and metadata for grass
	 * block.
	 */
	protected abstract Block getGrassBlock();

	/**
	 * The dirt block to be generated. Doesn't have to be dirt of course.
	 *
	 * @return BlockMetaPair instance containing ID and metadata for dirt block.
	 */
	protected abstract Block getDirtBlock();

	/**
	 * The stone block to be generated. Doesn't have to be stone of course.
	 *
	 * @return BlockMetaPair instance containing ID and metadata for stone
	 * block.
	 */
	protected abstract Block getStoneBlock();

	/**
	 * @return Base height modifier
	 */
	public abstract double getHeightModifier();

	/**
	 * @return Height modifier for small hills
	 */
	public abstract double getSmallFeatureHeightModifier();

	/**
	 * @return Height modifier for mountains
	 */
	public abstract double getMountainHeightModifier();

	/**
	 * @return Height modifier for valleys
	 */
	public abstract double getValleyHeightModifier();

	/**
	 * @return Probability that craters will be generated
	 */
	public abstract int getCraterProbability();

	public abstract void onChunkProvide(int cX, int cZ, ChunkPrimer primer);

	public abstract void onPopulate(int cX, int cZ);
}
