package net.romvoid95.space.astrogeneration.chunkgen;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.romvoid95.space.astrogeneration.biome.BiomeRandomStone;

public abstract class ExoChunkProviderAlt implements IChunkGenerator, IExoChunk {

	private IBlockState stone = Blocks.STONE.getDefaultState();
	private IBlockState oceanBlock = Blocks.WATER.getDefaultState();

	protected final Random rand;
	protected NoiseGeneratorOctaves minLimitPerlinNoise;
	protected NoiseGeneratorOctaves maxLimitPerlinNoise;
	protected NoiseGeneratorOctaves mainPerlinNoise;
	protected NoiseGeneratorPerlin surfaceNoise;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;
	public NoiseGeneratorOctaves forestNoise;
	protected final World world;
	protected final boolean mapFeaturesEnabled;
	protected final double[] heightMap;
	protected final float[] biomeWeights;
	protected double[] depthBuffer = new double[256];
	protected Biome[] biomesForGeneration;
	double[] mainNoiseRegion;
	double[] minLimitRegion;
	double[] maxLimitRegion;
	double[] depthRegion;
	protected MapGenStructure[] structures;

	public ExoChunkProviderAlt(World worldIn, long seed, boolean mapFeaturesEnabledIn, Block stone, Block ocean,
			MapGenStructure[] structures) {
		this(worldIn, seed, mapFeaturesEnabledIn);
		this.stone = stone.getDefaultState();
		this.oceanBlock = ocean.getDefaultState();
		this.structures = structures;
	}

	public ExoChunkProviderAlt(World worldIn, long seed, boolean mapFeaturesEnabledIn) {
		this.world = worldIn;
		this.mapFeaturesEnabled = mapFeaturesEnabledIn;
		this.rand = new Random(seed);
		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
		this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
		this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.heightMap = new double[825];
		this.biomeWeights = new float[25];

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt(i * i + j * j + 0.2F);
				this.biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}

		InitNoiseGensEvent.ContextOverworld ctx = new InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise,
				maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise, forestNoise);
		this.minLimitPerlinNoise = ctx.getLPerlin1();
		this.maxLimitPerlinNoise = ctx.getLPerlin2();
		this.mainPerlinNoise = ctx.getPerlin();
		this.surfaceNoise = ctx.getHeight();
		this.scaleNoise = ctx.getScale();
		this.depthNoise = ctx.getDepth();
		this.forestNoise = ctx.getForest();
	}

	/**
	 * Generates the blocks per chunk
	 * 
	 * @param x
	 * @param z
	 * @param primer
	 */
	public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration,
				x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateHeightmap(x * 4, 0, z * 4);

		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {

					double d1 = this.heightMap[i1 + i2];
					double d2 = this.heightMap[j1 + i2];
					double d3 = this.heightMap[k1 + i2];
					double d4 = this.heightMap[l1 + i2];
					double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2) {

						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2) {

							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								/**
								 * The block that usually gets set to stone
								 */
								if ((lvt_45_1_ += d16) > 0.0D) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, stone);
								}

								/**
								 * The ocean block
								 */
								else if (i2 * 8 + j2 < world.getSeaLevel()) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
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

	/**
	 * Generates the specific blocks related to the biome
	 * 
	 * @param x
	 * @param z
	 * @param primer
	 * @param biomesIn
	 */
	public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
		this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, x * 16, z * 16, 16, 16, 0.0625D, 0.0625D,
				1.0D);

		for (int i = 0; i < 16; ++i) {
			for (int j = 0; j < 16; ++j) {
				Biome biome = biomesIn[j + i * 16];

				if (biome instanceof BiomeRandomStone) {
					((BiomeRandomStone) biome).generateTopBlocks(this.world, this.rand, primer, x * 16 + i, z * 16 + j,
							this.depthBuffer[j + i * 16], this.stone.getBlock());
				} else {
					biome.generateBiomeTerrain(this.world, this.rand, primer, x * 16 + i, z * 16 + j,
							this.depthBuffer[j + i * 16]);
				}
			}
		}
	}

	/**
	 * Generates the chunk at the specified position, from scratch
	 */
	@Override
	public Chunk generateChunk(int x, int z) {
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16,
				16);

		// Place the blocks related to the biome
		this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);

		this.generateCaves(x, z, chunkprimer);

		for (MapGenStructure s : this.structures) {
			s.generate(this.world, x, z, chunkprimer);
		}

		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	protected abstract void generateCaves(int x, int z, ChunkPrimer cp);

	private void generateHeightmap(int chunkX, int chunkY, int chunkZ) {
		this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, chunkX, chunkZ, 5, 5, 200.0F, 200.0F,
				0.5F);
		float f = 684.412F;
		float f1 = 684.412F;
		this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkX, chunkY, chunkZ,
				5, 33, 5, f / 80.0F, f1 / 160.0F, f / 80.0F);
		this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, chunkX, chunkY, chunkZ,
				5, 33, 5, f, f1, f);
		this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, chunkX, chunkY, chunkZ,
				5, 33, 5, f, f1, f);
		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;

				Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];

				for (int j1 = -2; j1 <= 2; ++j1) {
					for (int k1 = -2; k1 <= 2; ++k1) {
						Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
						float f5 = 0.0F + biome1.getBaseHeight() * 1.0F;
						float f6 = 0.0F + biome1.getHeightVariation() * 1.0F;

						float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

						if (biome1.getBaseHeight() > biome.getBaseHeight()) {
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = this.depthRegion[j] / 8000.0D;

				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}

				d7 = d7 * 3.0D - 2.0D;

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
				d8 = d8 + d7 * 0.2D;
				d8 = d8 * 8.5F / 8.0D;
				double d0 = 8.5F + d8 * 4.0D;

				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = (l1 - d0) * 12.0F * 128.0D / 256.0D / d9;

					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}

					double d2 = this.minLimitRegion[i] / 512.0F;
					double d3 = this.maxLimitRegion[i] / 512.0F;
					double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (l1 > 29) {
						double d6 = (l1 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					this.heightMap[i] = d5;
					++i;
				}
			}
		}
	}

	/**
	 * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes,
	 * and dungeons
	 */
	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
		Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
		this.rand.setSeed(this.world.getSeed());
		long k = this.rand.nextLong() / 2L * 2L + 1L;
		long l = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(x * k + z * l ^ this.world.getSeed());

		ChunkPos chunkpos = new ChunkPos(x, z);

		for (MapGenStructure s : this.structures) {
			s.generateStructure(this.world, this.rand, chunkpos);
		}

		this.generateFeatures(blockpos, biome);

		biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
		WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.rand);

		BlockFalling.fallInstantly = false;
	}

	protected abstract void generateFeatures(BlockPos pos, Biome biome);

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		if (!this.mapFeaturesEnabled) {
			return false;
		}

		for (MapGenStructure s : this.structures) {
			if (s.getStructureName().equals(structureName)) {
				return s.isInsideStructure(pos);
			}
		}

		return false;
	}

	@Override
	@Nullable
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		if (!this.mapFeaturesEnabled) {
			return null;
		}

		for (MapGenStructure s : this.structures) {
			if (s.getStructureName().equals(structureName)) {
				return s.getNearestStructurePos(worldIn, position, findUnexplored);
			}
		}

		return null;
	}

	/**
	 * Recreates data about structures intersecting given chunk (used for example by
	 * getPossibleCreatures), without placing any blocks. When called for the first
	 * time before any chunk is generated - also initializes the internal state
	 * needed by getPossibleCreatures.
	 */
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		for (MapGenStructure s : this.structures) {
			s.generate(this.world, x, z, (ChunkPrimer) null);
		}
	}

	/**
	 * Called to generate additional structures after initial worldgen, used by
	 * ocean monuments
	 */
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}
}
