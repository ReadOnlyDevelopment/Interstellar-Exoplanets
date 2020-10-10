package net.romvoid95.common.world.cave.noise;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import lombok.Getter;
import net.romvoid95.common.world.cave.CaveConstant;

public class NoiseGen {
	@Getter
	private long				seed;
	private int					numGenerators;
	private NoiseSettings		noiseSettings;
	private float				yCompression;
	private float				xzCompression;
	private List<INoiseLibrary>	listNoiseGens	= new ArrayList<>();

	public NoiseGen (World world, boolean isFastNoise, NoiseSettings noiseSettings, int numGenerators, float yComp, float xzComp) {
		this.seed = world.getSeed();
		this.noiseSettings = noiseSettings;
		this.numGenerators = numGenerators;
		this.yCompression = yComp;
		this.xzCompression = xzComp;
		initializeNoiseGens(isFastNoise);
	}

	public NoiseColumn genNoiseColumn(BlockPos blockPos, int minHeight, int maxHeight) {
		int x = blockPos.getX();
		int z = blockPos.getZ();
		NoiseColumn noiseColumn = new NoiseColumn();

		for (int y = minHeight; y <= maxHeight; y++) {
			Vector3f f = new Vector3f(x * xzCompression, y * yCompression, z * xzCompression);

			// Create NoiseTuple for this block
			NoiseTuple newTuple = new NoiseTuple();
			for (int i = 0; i < numGenerators; i++)
				newTuple.put(listNoiseGens.get(i).GetNoise(f.x, f.y, f.z));

			noiseColumn.put(y, newTuple);
		}

		return noiseColumn;
	}

	public NoiseColumn interpolateNoiseColumn(BlockPos blockPos, int minHeight, int maxHeight, int subChunkSize) {
		int startY;
		int x = blockPos.getX();
		int z = blockPos.getZ();
		NoiseColumn noiseColumn = new NoiseColumn();

		// Calculate noise for every nth block in the column, using bilinear interpolation for the rest
		for (startY = minHeight; startY <= maxHeight; startY += subChunkSize) {
			int endY = Math.min(startY + subChunkSize - 1, maxHeight);
			Vector3f subChunkStart = new Vector3f(x * xzCompression, startY * yCompression, z * xzCompression);
			Vector3f subChunkEnd = new Vector3f(x * xzCompression, endY * yCompression, z * xzCompression);

			// Create NoiseTuples for subchunk edge blocks
			NoiseTuple startTuple = new NoiseTuple();
			NoiseTuple endTuple = new NoiseTuple();
			for (int i = 0; i < numGenerators; i++) {
				startTuple.put(listNoiseGens.get(i).GetNoise(subChunkStart.x, subChunkStart.y, subChunkStart.z));
				endTuple.put(listNoiseGens.get(i).GetNoise(subChunkEnd.x, subChunkEnd.y, subChunkEnd.z));
			}
			noiseColumn.put(startY, startTuple);
			noiseColumn.put(endY, endTuple);

			// Fill in middle values via bilinear interpolation of edge values
			for (int y = startY + 1; y < endY; y++) {
				float startCoeff, endCoeff;
				if (endY == maxHeight) {
					startCoeff = (float) (endY - startY - y - startY) / (endY - startY);
					endCoeff = (float) (y - startY) / (endY - startY);
				} else {
					startCoeff = CaveConstant.START_COEFFS[y - startY];
					endCoeff = CaveConstant.END_COEFFS[y - startY];
				}
				NoiseTuple newTuple = startTuple.times(startCoeff).plus(endTuple.times(endCoeff));
				noiseColumn.put(y, newTuple);
			}
		}

		return noiseColumn;
	}

	public NoiseCube interpolateNoiseCube(BlockPos startPos, BlockPos endPos, int minHeight, int maxHeight) {
		float startCoeff, endCoeff;
		int startX = startPos.getX();
		int endX = endPos.getX();
		int startZ = startPos.getZ();
		int endZ = endPos.getZ();
		int subChunkSize = endX - startX + 1;

		// Calculate noise tuples for four corner columns
		NoiseColumn noisesX0Z0 = genNoiseColumn(new BlockPos(startX, 1, startZ), minHeight, maxHeight);
		NoiseColumn noisesX0Z1 = genNoiseColumn(new BlockPos(startX, 1, endZ), minHeight, maxHeight);
		NoiseColumn noisesX1Z0 = genNoiseColumn(new BlockPos(endX, 1, startZ), minHeight, maxHeight);
		NoiseColumn noisesX1Z1 = genNoiseColumn(new BlockPos(endX, 1, endZ), minHeight, maxHeight);

		// Initialize cube with 4 corner columns
		NoiseCube cube = new NoiseCube(subChunkSize);
		cube.get(0).set(0, noisesX0Z0);
		cube.get(0).set(subChunkSize - 1, noisesX0Z1);
		cube.get(subChunkSize - 1).set(0, noisesX1Z0);
		cube.get(subChunkSize - 1).set(subChunkSize - 1, noisesX1Z1);

		// Populate edge planes along x axis
		for (int x = 1; x < subChunkSize - 1; x++) {
			startCoeff = CaveConstant.START_COEFFS[x];
			endCoeff = CaveConstant.END_COEFFS[x];

			NoiseColumn xz0 = cube.get(x).get(0);
			for (int y = minHeight; y <= maxHeight; y++) {
				NoiseTuple startTuple = cube.get(0).get(0).get(y);
				NoiseTuple endTuple = cube.get(subChunkSize - 1).get(0).get(y);
				NoiseTuple newTuple = startTuple.times(startCoeff).plus(endTuple.times(endCoeff));
				xz0.put(y, newTuple);
			}

			NoiseColumn xz1 = cube.get(x).get(subChunkSize - 1);
			for (int y = minHeight; y <= maxHeight; y++) {
				NoiseTuple startTuple = cube.get(0).get(subChunkSize - 1).get(y);
				NoiseTuple endTuple = cube.get(subChunkSize - 1).get(subChunkSize - 1).get(y);
				NoiseTuple newTuple = startTuple.times(startCoeff).plus(endTuple.times(endCoeff));
				xz1.put(y, newTuple);
			}
		}

		// Populate rest of cube by interpolating the two edge planes
		for (int x = 0; x < subChunkSize; x++) {
			for (int z = 1; z < subChunkSize - 1; z++) {
				startCoeff = CaveConstant.START_COEFFS[z];
				endCoeff = CaveConstant.END_COEFFS[z];

				NoiseColumn xz = cube.get(x).get(z);

				for (int y = minHeight; y <= maxHeight; y++) {
					NoiseTuple startTuple = cube.get(x).get(0).get(y);
					NoiseTuple endTuple = cube.get(x).get(subChunkSize - 1).get(y);
					NoiseTuple newTuple = startTuple.times(startCoeff).plus(endTuple.times(endCoeff));

					xz.put(y, newTuple);
				}
			}
		}

		return cube;
	}

	private void initializeNoiseGens(boolean isFastNoise) {
		if (isFastNoise) {
			for (int i = 0; i < numGenerators; i++) {
				FastNoise noiseGen = new FastNoise();
				noiseGen.SetSeed((int) (seed) + (1111 * (i + 1)));
				noiseGen.SetFractalType(noiseSettings.getFractalType());
				noiseGen.SetNoiseType(noiseSettings.getNoiseType());
				noiseGen.SetFractalOctaves(noiseSettings.getOctaves());
				noiseGen.SetFractalGain(noiseSettings.getGain());
				noiseGen.SetFrequency(noiseSettings.getFrequency());
				listNoiseGens.add(noiseGen);
			}
		} else {
			for (int i = 0; i < numGenerators; i++) {
				OpenSimplex2S noiseGen = new OpenSimplex2S(seed + (1111 * (i + 1)));
				noiseGen.setGain(noiseSettings.getGain());
				noiseGen.setOctaves(noiseSettings.getOctaves());
				noiseGen.setFrequency(noiseSettings.getFrequency());
				noiseGen.setLacunarity(2.0);
				listNoiseGens.add(noiseGen);
			}
		}
	}
}
