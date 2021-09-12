package com.readonlydev.space.generation.chunkgen;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.readonlydev.space.generation.terrain.ExoCaveGenerator;

import micdoodle8.mods.galacticraft.api.world.ChunkProviderBase;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenLakes;

public abstract class ChunkGeneratorSimplex extends ChunkProviderBase implements IExoChunk {
	private final World world;
	private final Random random;

	private final boolean hasDecoration;
	private WorldGenLakes waterLakeGenerator;
	private WorldGenLakes lavaLakeGenerator;
	private IBlockState planetStoneBlock = this.getPlanetStoneBlock();
	private IBlockState planetGrassBlock = this.getPlanetGrassBlock();
	private IBlockState planetDirtBlock = this.getPlanetDirtBlock();
	private IBlockState planetGravelBlock = this.getPlanetGravelBlock();
	private IBlockState planetCobbleStoneBlock = this.getPlanetCobblestoneBlock();
	private IBlockState planetWaterBlock = this.getPlanetWaterBlock();

	protected IBlockState planetGroundVariant1;
	protected IBlockState planetGroundVariant2;

	@Nullable private IBlockState planetIceBlock = this.getPlanetIceBlock();
	protected boolean hasIce = true;
	protected boolean useSnowWithLayers = true;
	private ExoCaveGenerator caveGenerator = new ExoCaveGenerator();
	public static int maxY = 0;

	public ChunkGeneratorSimplex(World world, long seed, boolean flag) {
		this.world = world;
		this.random = new Random(seed);

		this.waterLakeGenerator = new WorldGenLakes(this.planetWaterBlock.getBlock());
		this.lavaLakeGenerator = new WorldGenLakes(Blocks.LAVA);

		int j = 0;

		world.setSeaLevel(j);
		this.hasDecoration = true;
	}

	@Nullable
	protected abstract IBlockState getPlanetIceBlock();

	protected abstract IBlockState getPlanetWaterBlock();

	protected abstract IBlockState getPlanetCobblestoneBlock();

	protected abstract IBlockState getPlanetGravelBlock();

	protected abstract IBlockState getPlanetDirtBlock();

	protected abstract IBlockState getPlanetGrassBlock();

	protected abstract IBlockState getPlanetStoneBlock();

	public double[][] generateHeightMap(int x, int z) {
		double[][] heightmap = new double[16][16];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				int ax = x * 16 + i;
				int az = z * 16 + j;
				heightmap[i][j] = generateOctave(128, 128, ax, az, this.world.getSeed());
				heightmap[i][j] += generateOctave(64, 64, ax, az, this.world.getSeed());
				heightmap[i][j] += generateOctave(32, 32, ax, az, this.world.getSeed());
				heightmap[i][j] += generateOctave(16, 16, ax, az, this.world.getSeed());
				heightmap[i][j] += generateOctave(8, 8, ax, az, this.world.getSeed());
				heightmap[i][j] += generateOctave(4, 4, ax, az, this.world.getSeed());
				heightmap[i][j] += generateOctave(2, 2, ax, az, this.world.getSeed());
				double d = generateOctave(256, 1, ax, az, this.world.getSeed());
				heightmap[i][j] += d * d * d * 192;
				d = generateOctave(512, 1, ax, az, this.world.getSeed());
				heightmap[i][j] += d * d * d * d * 384;
			}
		}
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				int ax = x * 16 + i;
				int az = z * 16 + j;
				double f = generateOctave(512, 1, ax, az, this.world.getSeed());
				f += generateOctave(256, 0.5, ax, az, this.world.getSeed());
				f += generateOctave(128, 0.25, ax, az, this.world.getSeed());
				f /= 2;

				heightmap[i][j] *= f;
			}
		}
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				//				int ax = x * 16 + i;
				//				int az = z * 16 + j;
				if (heightmap[i][j] < 75) {
					double d = 75 - heightmap[i][j];
					heightmap[i][j] = 75 - (d / 2);
					if (heightmap[i][j] < 70) {
						d = 70 - heightmap[i][j];
						heightmap[i][j] = 70 - (d / 3);
						if (heightmap[i][j] < 64) {
							d = 64 - heightmap[i][j];
							heightmap[i][j] = 64 - (d * 10);
							d = 64 - heightmap[i][j];
							heightmap[i][j] = 64 - (Math.sqrt(d) * 4);
						}
					}
				}
			}
		}
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				//				int ax = x * 16 + i;
				//				int az = z * 16 + j;
				double h = 192;
				for (int f = 0; f < 8; f++) {
					if (heightmap[i][j] > h) {
						double d = (heightmap[i][j] - h) * 0.9D;
						heightmap[i][j] = h + d;
						h += 8;
					}
				}
			}
		}
		return heightmap;
	}

	public double[][] generateFertilityMap(int x, int z) {
		double[][] fertilitymap = new double[16][16];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				int ax = x * 16 + i;
				int az = z * 16 + j;
				fertilitymap[i][j] = generateOctave(256, 1, ax, az, this.world.getSeed());
				fertilitymap[i][j] += generateOctave(128, 0.5, ax, az, this.world.getSeed());
				fertilitymap[i][j] *= 1.25;
				fertilitymap[i][j] -= 0.25;
				if (fertilitymap[i][j] > 1) {
					fertilitymap[i][j] = 1;
				}
			}
		}
		return fertilitymap;
	}

	public double[][] generateTemperatureMap(int x, int z) {
		double[][] fertilitymap = new double[16][16];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				int ax = x * 16 + i;
				int az = z * 16 + j;
				fertilitymap[i][j] = generateOctave(256, 1, ax, az, this.world.getSeed());
				fertilitymap[i][j] += generateOctave(128, 0.5, ax, az, this.world.getSeed());
				fertilitymap[i][j] -= 1;
			}
		}
		return fertilitymap;
	}

	public static double getLocalFertility(double height, double fertility, double temperature) {
		if (height < 64) {
			return 0;
		} else {
			double h = height - 64;
			return fertility - (h / 96D) + (temperature / 4);
		}
	}

	public static double getLocalTemperature(double height, double temperature) {
		if (height < 64) {
			return temperature;
		} else {
			double h = height - 64;
			return temperature - (h / 96D);
		}
	}

	public double generateOctave(int frequency, double amplitude, int x, int z, long seed) {
		x = Math.abs(x + 34630988);
		z = Math.abs(z + 84534737);
		int xmin = x / frequency;
		int xmax = xmin + 1;
		int zmin = z / frequency;
		int zmax = zmin + 1;

		double xminzminh = Math.sin(xmin * xmin * seed * zmin * zmin) * 0.5 + 0.5;
		double xmaxzminh = Math.sin(xmax * xmax * seed * zmin * zmin) * 0.5 + 0.5;
		double xminzmaxh = Math.sin(xmin * xmin * seed * zmax * zmax) * 0.5 + 0.5;
		double xmaxzmaxh = Math.sin(xmax * xmax * seed * zmax * zmax) * 0.5 + 0.5;

		double xweight = (double) (Math.floorMod(x, frequency)) / (double) (frequency);
		double zweight = (double) (Math.floorMod(z, frequency)) / (double) (frequency);

		double xdiffzmin = xmaxzminh - xminzminh;
		double xhpzmin = xdiffzmin * xweight;
		double xhzmin = xhpzmin + xminzminh;

		double xdiffzmax = xmaxzmaxh - xminzmaxh;
		double xhpzmax = xdiffzmax * xweight;
		double xhzmax = xhpzmax + xminzmaxh;

		double zdiff = xhzmax - xhzmin;
		double zhp = zdiff * zweight;
		double zh = zhp * amplitude + xhzmin * amplitude;
		return zh;
	}

	/**
	 * Generates the chunk at the specified position, from scratch
	 */
	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();

		double[][] heightmap;
		double[][] fertilitymap;
		double[][] temperaturemap;

		heightmap = generateHeightMap(x, z);
		fertilitymap = generateFertilityMap(x, z);
		temperaturemap = generateTemperatureMap(x, z);

		Random random = new Random();
		random.setSeed((x + 3985735358L) * (z + 407340697436L) * this.world.getSeed());
		for (int j = 0; j < 16; ++j) {
			for (int k = 0; k < 16; ++k) {
				if ((int) (heightmap[j][k]) > maxY) {
					maxY = (int) (heightmap[j][k]);
				}
				for (int i = 0; i < 64; ++i) {
					if (i < heightmap[j][k]) {
						chunkprimer.setBlockState(j, i, k, this.planetStoneBlock);
					} else {
						if (i == 68) {
							if (temperaturemap[j][k] < -0.5) {
								if(!this.hasIce) {
									chunkprimer.setBlockState(j, i, k, this.planetWaterBlock);
								} else {
									chunkprimer.setBlockState(j, i, k, this.planetIceBlock);
								}
							} else if (temperaturemap[j][k] >= -0.5 && temperaturemap[j][k] < 0) {
								double v = (temperaturemap[j][k] + 0.5) * 2;
								if (random.nextDouble() > v) {
									if(!this.hasIce) {
										chunkprimer.setBlockState(j, i, k, this.planetWaterBlock);
									} else {
										chunkprimer.setBlockState(j, i, k, this.planetIceBlock);
									}
								} else {
									chunkprimer.setBlockState(j, i, k, this.planetWaterBlock);
								}
							} else {
								chunkprimer.setBlockState(j, i, k, this.planetWaterBlock);
							}
						} else {
							chunkprimer.setBlockState(j, i, k, this.planetWaterBlock);
						}
					}
				}
				for (int i = 64; i < heightmap[j][k]; ++i) {
					chunkprimer.setBlockState(j, i, k, this.planetStoneBlock);
				}
				if (heightmap[j][k] < 64) {
					chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGravelBlock);
					chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetGravelBlock);
					chunkprimer.setBlockState(j, (int) heightmap[j][k] - 2, k, this.planetGravelBlock);
					chunkprimer.setBlockState(j, (int) heightmap[j][k] - 3, k, this.planetGravelBlock);
				} else {
					int height = (int) heightmap[j][k];
					double fertility = getLocalFertility(height, fertilitymap[j][k], temperaturemap[j][k]);
					if (fertility < -0.5) {
						if (random.nextDouble() > 0.5) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k,
									this.planetCobbleStoneBlock);
						}
						if (random.nextDouble() > 0.5) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k,
									this.planetCobbleStoneBlock);
						}
					} else if (fertility >= -0.5 && fertility < 0) {
						double v = (fertility + 0.5) * 2;
						if (random.nextDouble() > v) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k,
									this.planetCobbleStoneBlock);
						}
					} else if (fertility >= 0 && fertility < 0.1) {
						double v = (fertility) * 10;
						if (random.nextDouble() < v) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGroundVariant1);
						}
					} else if (fertility >= 0.1 && fertility < 0.2) {
						double v = (fertility - 0.1) * 10;
						if (random.nextDouble() > v) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGroundVariant2);
						} else {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetDirtBlock);
						}
					} else if (fertility >= 0.2 && fertility < 0.4) {
						double v = (fertility - 0.2) * 5;
						if (random.nextDouble() > v) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetDirtBlock);
						} else {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGrassBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
						}
					} else if (fertility >= 0.4 && fertility < 0.6) {
						double v = (fertility - 0.4) * 5;
						if (random.nextDouble() > v) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGrassBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
						} else {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGrassBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 2, k, this.planetDirtBlock);
						}
					} else if (fertility >= 0.6 && fertility < 0.8) {
						double v = (fertility - 0.6) * 5;
						if (random.nextDouble() > v) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGrassBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 2, k, this.planetDirtBlock);
						} else {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGroundVariant2);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 2, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 3, k, this.planetDirtBlock);
						}
					} else if (fertility >= 0.8 && fertility < 1) {
						double v = (fertility - 0.8) * 5;
						if (random.nextDouble() > v) {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGroundVariant2);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 2, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 3, k, this.planetDirtBlock);
						} else {
							chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGroundVariant2);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 2, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 3, k, this.planetDirtBlock);
							chunkprimer.setBlockState(j, (int) heightmap[j][k] - 4, k, this.planetDirtBlock);
						}
					} else {
						chunkprimer.setBlockState(j, (int) heightmap[j][k], k, this.planetGroundVariant2);
						chunkprimer.setBlockState(j, (int) heightmap[j][k] - 1, k, this.planetDirtBlock);
						chunkprimer.setBlockState(j, (int) heightmap[j][k] - 2, k, this.planetDirtBlock);
						chunkprimer.setBlockState(j, (int) heightmap[j][k] - 3, k, this.planetDirtBlock);
						chunkprimer.setBlockState(j, (int) heightmap[j][k] - 4, k, this.planetDirtBlock);
					}
					double temperature = getLocalTemperature(heightmap[j][k], temperaturemap[j][k]);
					if(this.useSnowWithLayers) {
						if (temperature < -1.5) {
							if (random.nextBoolean()) {
								chunkprimer.setBlockState(j, (int) heightmap[j][k], k, Blocks.SNOW.getDefaultState());
							} else {
								chunkprimer.setBlockState(j, (int) heightmap[j][k], k, Blocks.PACKED_ICE.getDefaultState());
							}
							int h = random.nextInt(4) + 5;
							chunkprimer.setBlockState(j, (int) heightmap[j][k] + 1, k,
									Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, h));
						} else if (temperature >= -1.5 && temperature < -1) {
							double v = (temperature + 1.5) * 2;
							if (random.nextDouble() > v) {
								if (random.nextBoolean()) {
									chunkprimer.setBlockState(j, (int) heightmap[j][k], k, Blocks.SNOW.getDefaultState());
								} else {
									chunkprimer.setBlockState(j, (int) heightmap[j][k], k,
											Blocks.PACKED_ICE.getDefaultState());
								}
							}
							int h = random.nextInt(4) + 5;
							chunkprimer.setBlockState(j, (int) heightmap[j][k] + 1, k,
									Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, h));
						} else if (temperature >= -1 && temperature < -0.5) {
							double v = 8 - (temperature + 1D) * 16D;
							if (v < 0) {
								v = 0;
							}
							int h;
							if (v > 4) {
								h = random.nextInt(4) + 1 + (int) (v - 4);
							} else {
								h = random.nextInt((int) (v + 1)) + 1;
							}
							if (h > 8) {
								h = 8;
							}
							chunkprimer.setBlockState(j, (int) heightmap[j][k] + 1, k,
									Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, h));
						} else if (temperature >= -0.5 && temperature < 0) {
							double v = (temperature + 0.5) * 2;
							if (random.nextDouble() > v) {
								chunkprimer.setBlockState(j, (int) heightmap[j][k] + 1, k,
										Blocks.SNOW_LAYER.getDefaultState());
							}
						}
					}
				}

				if (random.nextInt(5) > 3) {
					chunkprimer.setBlockState(j, 4, k, Blocks.BEDROCK.getDefaultState());
				}
				if (random.nextInt(5) > 2) {
					chunkprimer.setBlockState(j, 3, k, Blocks.BEDROCK.getDefaultState());
				}
				if (random.nextInt(5) > 1) {
					chunkprimer.setBlockState(j, 2, k, Blocks.BEDROCK.getDefaultState());
				}
				if (random.nextInt(5) > 0) {
					chunkprimer.setBlockState(j, 1, k, Blocks.BEDROCK.getDefaultState());
				}
				chunkprimer.setBlockState(j, 0, k, Blocks.BEDROCK.getDefaultState());
			}

			//this.ravineGenerator.generate(this.world, x, z, chunkprimer);
			this.caveGenerator.generate(this.world, x, z, chunkprimer);
		}

		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		Biome[] abiome = this.world.getBiomeProvider().getBiomes((Biome[]) null, x * 16, z * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();

		for (int l = 0; l < abyte.length; ++l) {
			abyte[l] = (byte) Biome.getIdForBiome(abiome[l]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	/**
	 * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes,
	 * and dungeons
	 */
	@Override
	public void populate(int x, int z) {
		net.minecraft.block.BlockFalling.fallInstantly = true;
		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
		Biome biome = this.world.getBiome(new BlockPos(i + 16, 0, j + 16));
		boolean flag = false;
		this.random.setSeed(this.world.getSeed());
		long k = this.random.nextLong() / 2L * 2L + 1L;
		long l = this.random.nextLong() / 2L * 2L + 1L;
		this.random.setSeed(x * k + z * l ^ this.world.getSeed());
		//		ChunkPos chunkpos = new ChunkPos(x, z);

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.random, x, z, flag);


		if (this.waterLakeGenerator != null && !flag && this.random.nextInt(4) == 0) {
			this.waterLakeGenerator.generate(this.world, this.random,
					blockpos.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
		}

		if (this.lavaLakeGenerator != null && !flag && this.random.nextInt(8) == 0) {
			BlockPos blockpos1 = blockpos.add(this.random.nextInt(16) + 8,
					this.random.nextInt(this.random.nextInt(248) + 8), this.random.nextInt(16) + 8);

			if (blockpos1.getY() < this.world.getSeaLevel() || this.random.nextInt(10) == 0) {
				this.lavaLakeGenerator.generate(this.world, this.random, blockpos1);
			}
		}

		if (this.hasDecoration) {
			biome.decorate(this.world, this.random, blockpos);
		}

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.random, x, z, flag);
		net.minecraft.block.BlockFalling.fallInstantly = false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}


}
