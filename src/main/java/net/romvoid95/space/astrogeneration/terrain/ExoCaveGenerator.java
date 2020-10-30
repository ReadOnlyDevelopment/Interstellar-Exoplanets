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
package net.romvoid95.space.astrogeneration.terrain;

import com.google.common.base.MoreObjects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.fluids.IFluidBlock;

public class ExoCaveGenerator extends MapGenBase {
	int numLogChunks = 500;
	long[] genTime = new long[numLogChunks];
	int currentTimeIndex = 0;
	double sum = 0;

	private CaveGenUtility worleyF1divF3 = new CaveGenUtility();
	private FastNoise displacementNoisePerlin = new FastNoise();

	private static IBlockState lava;
	private static final IBlockState AIR = Blocks.AIR.getDefaultState();
	private static int maxCaveHeight = 128;
	private static int minCaveHeight = 5;
	private static float noiseCutoff = -0.18F;
	private static float warpAmplifier = 8.0F;
	private static float easeInDepth = 15F;
	private static float yCompression = 2.0F;
	private static float xzCompression = 1.0F;
	private static float surfaceCutoff = -0.081F;
	private static int lavaDepth = 8;
	private static boolean additionalWaterChecks = true;
	private static int HAS_CAVES_FLAG = 129;

	public ExoCaveGenerator() {
		worleyF1divF3.SetFrequency(0.016f);

		displacementNoisePerlin.SetNoiseType(FastNoise.NoiseType.Perlin);
		displacementNoisePerlin.SetFrequency(0.05f);

	}

	@Override
	public void generate(World worldIn, int x, int z, ChunkPrimer primer) {

		this.world = worldIn;

		boolean logTime = false; // TODO turn off
		long start = 0;
		if (logTime) {
			start = System.nanoTime();
		}

		this.world = worldIn;
		this.generateExoCaves(worldIn, x, z, primer);

		if (logTime) {
			genTime[currentTimeIndex] = System.nanoTime() - start;// System.currentTimeMillis() - start;
			sum += genTime[currentTimeIndex];
			currentTimeIndex++;
			if (currentTimeIndex == genTime.length) {
				System.out.printf("%d chunk average: %.2f ms per chunk\n", numLogChunks,
						sum / ((float) numLogChunks * 1000000));
				sum = 0;
				currentTimeIndex = 0;
			}
		}
	}

	protected void generateExoCaves(World worldIn, int chunkX, int chunkZ, ChunkPrimer chunkPrimerIn) {
		int chunkMaxHeight = getMaxSurfaceHeight(chunkPrimerIn);
		int seaLevel = worldIn.getSeaLevel();
		float[][][] samples = sampleNoise(chunkX, chunkZ, chunkMaxHeight + 1);
		float oneQuarter = 0.25F;
		float oneHalf = 0.5F;
		Biome currentBiome;
		BlockPos realPos;
		// float cutoffAdjuster = 0F; //TODO one day, perlin adjustments to cutoff

		// each chunk divided into 4 subchunks along X axis
		for (int x = 0; x < 4; x++) {
			// each chunk divided into 4 subchunks along Z axis
			for (int z = 0; z < 4; z++) {
				int depth = 0;

				// don't bother checking all the other logic if there's nothing to dig in this
				// column
				if (samples[x][HAS_CAVES_FLAG][z] == 0 && samples[x + 1][HAS_CAVES_FLAG][z] == 0
						&& samples[x][HAS_CAVES_FLAG][z + 1] == 0 && samples[x + 1][HAS_CAVES_FLAG][z + 1] == 0)
					continue;

				// each chunk divided into 128 subchunks along Y axis. Need lots of y sample
				// points to not break things
				for (int y = (maxCaveHeight / 2) - 1; y >= 0; y--) {
					// grab the 8 sample points needed from the noise values
					float x0y0z0 = samples[x][y][z];
					float x0y0z1 = samples[x][y][z + 1];
					float x1y0z0 = samples[x + 1][y][z];
					float x1y0z1 = samples[x + 1][y][z + 1];
					float x0y1z0 = samples[x][y + 1][z];
					float x0y1z1 = samples[x][y + 1][z + 1];
					float x1y1z0 = samples[x + 1][y + 1][z];
					float x1y1z1 = samples[x + 1][y + 1][z + 1];

					// how much to increment noise along y value
					// linear interpolation from start y and end y
					float noiseStepY00 = (x0y1z0 - x0y0z0) * -oneHalf;
					float noiseStepY01 = (x0y1z1 - x0y0z1) * -oneHalf;
					float noiseStepY10 = (x1y1z0 - x1y0z0) * -oneHalf;
					float noiseStepY11 = (x1y1z1 - x1y0z1) * -oneHalf;

					// noise values of 4 corners at y=0
					float noiseStartX0 = x0y0z0;
					float noiseStartX1 = x0y0z1;
					float noiseEndX0 = x1y0z0;
					float noiseEndX1 = x1y0z1;

					// loop through 2 blocks of the Y subchunk
					for (int suby = 1; suby >= 0; suby--) {
						int localY = suby + y * 2;
						float noiseStartZ = noiseStartX0;
						float noiseEndZ = noiseStartX1;

						// how much to increment X values, linear interpolation
						float noiseStepX0 = (noiseEndX0 - noiseStartX0) * oneQuarter;
						float noiseStepX1 = (noiseEndX1 - noiseStartX1) * oneQuarter;

						// loop through 4 blocks of the X subchunk
						for (int subx = 0; subx < 4; subx++) {
							int localX = subx + x * 4;
							int realX = localX + chunkX * 16;

							// how much to increment Z values, linear interpolation
							float noiseStepZ = (noiseEndZ - noiseStartZ) * oneQuarter;

							// Y and X already interpolated, just need to interpolate final 4 Z block to get
							// final noise value
							float noiseVal = noiseStartZ;

							// loop through 4 blocks of the Z subchunk
							for (int subz = 0; subz < 4; subz++) {
								int localZ = subz + z * 4;
								int realZ = localZ + chunkZ * 16;
								realPos = new BlockPos(realX, localY, realZ);
								currentBiome = null;

								if (depth == 0) {
									// only checks depth once per 4x4 subchunk
									if (subx == 0 && subz == 0) {
										IBlockState currentBlock = chunkPrimerIn.getBlockState(localX, localY, localZ);
										currentBiome = world.provider.getBiomeProvider().getBiome(realPos,
												Biomes.PLAINS);// world.getBiome(realPos);

										// use isDigable to skip leaves/wood getting counted as surface
										if (canReplaceBlock(currentBlock, AIR) || isBiomeBlock(chunkPrimerIn, realX,
												realZ, currentBlock, currentBiome)) {
											depth++;
										}
									} else {
										continue;
									}
								} else if (subx == 0 && subz == 0) {
									// already hit surface, simply increment depth counter
									depth++;
								}

								float adjustedNoiseCutoff = noiseCutoff;// + cutoffAdjuster;
								if (depth < easeInDepth) {
									// higher threshold at surface, normal threshold below easeInDepth
									adjustedNoiseCutoff = (float) MathHelper.clampedLerp(noiseCutoff, surfaceCutoff,
											(easeInDepth - (float) depth) / easeInDepth);

								}

								// increase cutoff as we get closer to the minCaveHeight so it's not all flat
								// floors
								if (localY < (minCaveHeight + 5)) {
									adjustedNoiseCutoff += ((minCaveHeight + 5) - localY) * 0.05;
								}

								if (noiseVal > adjustedNoiseCutoff) {
									IBlockState aboveBlock = (IBlockState) MoreObjects.firstNonNull(
											chunkPrimerIn.getBlockState(localX, localY + 1, localZ),
											Blocks.AIR.getDefaultState());
									if (!isFluidBlock(aboveBlock) || localY <= lavaDepth) {
										// if we are in the easeInDepth range or near sea level or subH2O is installed,
										// do some extra checks for water before digging
										if ((depth < easeInDepth || localY > (seaLevel - 8) || additionalWaterChecks)
												&& localY > lavaDepth) {
											if (localX < 15)
												if (isFluidBlock(
														chunkPrimerIn.getBlockState(localX + 1, localY, localZ)))
													continue;
											if (localX > 0)
												if (isFluidBlock(
														chunkPrimerIn.getBlockState(localX - 1, localY, localZ)))
													continue;
											if (localZ < 15)
												if (isFluidBlock(
														chunkPrimerIn.getBlockState(localX, localY, localZ + 1)))
													continue;
											if (localZ > 0)
												if (isFluidBlock(
														chunkPrimerIn.getBlockState(localX, localY, localZ - 1)))
													continue;
										}
										IBlockState currentBlock = chunkPrimerIn.getBlockState(localX, localY, localZ);
										if (currentBiome == null)
											currentBiome = world.provider.getBiomeProvider().getBiome(realPos,
													Biomes.PLAINS);// world.getBiome(realPos);

										boolean foundTopBlock = isTopBlock(currentBlock, currentBiome);
										digBlock(chunkPrimerIn, localX, localY, localZ, chunkX, chunkZ, foundTopBlock,
												currentBlock, aboveBlock, currentBiome);
									}
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

	public float[][][] sampleNoise(int chunkX, int chunkZ, int maxSurfaceHeight) {
		int originalMaxHeight = 128;
		float[][][] noiseSamples = new float[5][130][5];
		float noise;
		for (int x = 0; x < 5; x++) {
			int realX = x * 4 + chunkX * 16;
			for (int z = 0; z < 5; z++) {
				int realZ = z * 4 + chunkZ * 16;

				int columnHasCaveFlag = 0;

				// loop from top down for y values so we can adjust noise above current y later
				// on
				for (int y = 128; y >= 0; y--) {
					float realY = y * 2;
					if (realY > maxSurfaceHeight || realY > maxCaveHeight || realY < minCaveHeight) {
						// if outside of valid cave range set noise value below normal minimum of -1.0
						noiseSamples[x][y][z] = -1.1F;
					} else {
						// Experiment making the cave system more chaotic the more you descend
						/// TODO might be too dramatic down at lava level
						float dispAmp = (float) (warpAmplifier
								* ((originalMaxHeight - y) / (originalMaxHeight * 0.85)));

						float xDisp = 0f;
						float yDisp = 0f;
						float zDisp = 0f;

						xDisp = displacementNoisePerlin.GetNoise(realX, realZ) * dispAmp;
						yDisp = displacementNoisePerlin.GetNoise(realX, realZ + 67.0f) * dispAmp;
						zDisp = displacementNoisePerlin.GetNoise(realX, realZ + 149.0f) * dispAmp;

						// doubling the y frequency to get some more caves
						noise = worleyF1divF3.SingleCellular3Edge(realX * xzCompression + xDisp,
								realY * yCompression + yDisp, realZ * xzCompression + zDisp);
						noiseSamples[x][y][z] = noise;

						if (noise > noiseCutoff) {
							columnHasCaveFlag = 1;
							// if noise is below cutoff, adjust values of neighbors
							// helps prevent caves fracturing during interpolation

							if (x > 0)
								noiseSamples[x - 1][y][z] = (noise * 0.2f) + (noiseSamples[x - 1][y][z] * 0.8f);
							if (z > 0)
								noiseSamples[x][y][z - 1] = (noise * 0.2f) + (noiseSamples[x][y][z - 1] * 0.8f);

							// more heavily adjust y above 'air block' noise values to give players more
							// headroom
							if (y < 128) {
								float noiseAbove = noiseSamples[x][y + 1][z];
								if (noise > noiseAbove)
									noiseSamples[x][y + 1][z] = (noise * 0.8F) + (noiseAbove * 0.2F);
								if (y < 127) {
									float noiseTwoAbove = noiseSamples[x][y + 2][z];
									if (noise > noiseTwoAbove)
										noiseSamples[x][y + 2][z] = (noise * 0.35F) + (noiseTwoAbove * 0.65F);
								}
							}

						}
					}
				}
				noiseSamples[x][HAS_CAVES_FLAG][z] = columnHasCaveFlag; // used to skip cave digging logic when we know
																		// there is nothing to dig out
			}
		}
		return noiseSamples;
	}

	private int getSurfaceHeight(ChunkPrimer chunkPrimerIn, int localX, int localZ) {
		// Using a recursive binary search to find the surface
		return recursiveBinarySurfaceSearch(chunkPrimerIn, localX, localZ, 255, 0);
	}

	// Recursive binary search, this search always converges on the surface in 8 in
	// cycles for the range 255 >= y >= 0
	private int recursiveBinarySurfaceSearch(ChunkPrimer chunkPrimer, int localX, int localZ, int searchTop,
			int searchBottom) {
		int top = searchTop;
		if (searchTop > searchBottom) {
			int searchMid = (searchBottom + searchTop) / 2;
			if (canReplaceBlock(chunkPrimer.getBlockState(localX, searchMid, localZ), AIR)) {
				top = recursiveBinarySurfaceSearch(chunkPrimer, localX, localZ, searchTop, searchMid + 1);
			} else {
				top = recursiveBinarySurfaceSearch(chunkPrimer, localX, localZ, searchMid, searchBottom);
			}
		}
		return top;
	}

	// tests 6 points in hexagon pattern get max height of chunk
	private int getMaxSurfaceHeight(ChunkPrimer primer) {
		int max = 0;
		int[][] testcords = { { 2, 6 }, { 3, 11 }, { 7, 2 }, { 9, 13 }, { 12, 4 }, { 13, 9 } };

		for (int n = 0; n < testcords.length; n++) {

			int testmax = getSurfaceHeight(primer, testcords[n][0], testcords[n][1]);
			if (testmax > max) {
				max = testmax;
				if (max > maxCaveHeight)
					return max;
			}

		}
		return max;
	}

	private boolean isBiomeBlock(ChunkPrimer primer, int realX, int realZ, IBlockState state, Biome biome) {
		return state == biome.topBlock || state == biome.fillerBlock;
	}

	private boolean isFluidBlock(IBlockState state) {
		Block blocky = state.getBlock();
		return blocky instanceof BlockLiquid || blocky instanceof IFluidBlock;
	}

	private boolean isTopBlock(IBlockState state, Biome biome) {
		return state == biome.topBlock;
	}

	protected boolean canReplaceBlock(IBlockState state, IBlockState stateUp) {
		return (additionalWaterChecks && state.getMaterial() == Material.ROCK) || state.getMaterial() == Material.GROUND;
	}

	/**
	 * Digs out the current block, default implementation removes stone, filler, and
	 * top block Sets the block to lava if y is less then 10, and air other wise. If
	 * setting to air, it also checks to see if we've broken the surface and if so
	 * tries to make the floor the biome's top block
	 *
	 * @param data     Block data array
	 * @param index    Pre-calculated index into block data
	 * @param x        local X position
	 * @param y        local Y position
	 * @param z        local Z position
	 * @param chunkX   Chunk X position
	 * @param chunkZ   Chunk Y position
	 * @param foundTop True if we've encountered the biome's top block. Ideally if
	 *                 we've broken the surface.
	 */
	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop,
			IBlockState state, IBlockState up, Biome biome) {
		IBlockState top = biome.topBlock;
		IBlockState filler = biome.fillerBlock;

		if (this.canReplaceBlock(state, up) || state.getBlock() == top.getBlock()
				|| state.getBlock() == filler.getBlock()) {
			if (y <= lavaDepth) {
				data.setBlockState(x, y, z, lava);
			} else {
				data.setBlockState(x, y, z, AIR);

				if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock()) {
					data.setBlockState(x, y - 1, z, top);
				}
			}
		}
	}
}
