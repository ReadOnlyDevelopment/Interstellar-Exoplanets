package net.romvoid95.common.world.generation;

import java.util.Arrays;
import java.util.Random;

public class NoiseGeneratorExo {
	public interface IIntInterpolater {
		int interpolate(int t, int a, int b);
	}

	public static class IntInterpolateLinear implements IIntInterpolater {
		@Override
		public int interpolate(int t, int a, int b) {
			return a + (t * (b - a) / 255);
		}
	}

	public static class IntInterpolateSmooth implements IIntInterpolater {
		private int[] table = new int[256];

		public IntInterpolateSmooth () {
			double t;
			for (int i = 0; i < 256; i++) {
				t = i / 256.0D;
				this.table[i] = (int) (256.0D * t * t * t * (t * (t * 6.0D - 15.0D) + 10.0D));
			}
		}

		@Override
		public int interpolate(int t, int a, int b) {
			return a + (this.table[t] * (b - a) / 255);
		}
	}

	public static class IntInterpolatePower implements IIntInterpolater {
		private int[] table = new int[256];

		public IntInterpolatePower (double power) {
			double t;
			for (int i = 0; i < 256; i++) {
				t = i / 256.0D;
				this.table[i] = (int) Math.pow(t, power);
			}
		}

		@Override
		public int interpolate(int t, int a, int b) {
			return a + (this.table[t] * (b - a) / 255);
		}
	}

	private int					numOctaves;
	private int					octavesToSkip;
	private int[][]				permutations;
	private int[]				offsetU;
	private int[]				offsetV;
	private int					numX;
	private int					numZ;
	private IIntInterpolater	interpolater;

	public byte[][] noise;

	public NoiseGeneratorExo (Random rand, int numOctaves, int numX, int numZ) {
		if (numOctaves < 1) {
			throw new IllegalArgumentException("Must have at least one octave");
		}
		if (numOctaves > 8) {
			throw new IllegalArgumentException("Cannot have more than 8 octaves");
		}
		this.init(rand, numOctaves, numX, numZ, 0);
	}

	public NoiseGeneratorExo (Random rand, int numOctaves, int numX, int numZ, int octavesToSkip) {
		if (numOctaves < 1) {
			throw new IllegalArgumentException("Must have at least one octave");
		}
		if (octavesToSkip < 0) {
			throw new IllegalArgumentException("octavesToSkip cannot be negative");
		}
		if ((numOctaves + octavesToSkip) > 8) {
			throw new IllegalArgumentException("sum of numOctaves and octavesToSkip cannot be more than 8");
		}
		this.init(rand, numOctaves, numX, numZ, octavesToSkip);
	}

	private void init(Random rand, int numOctaves, int numX, int numZ, int octavesToSkip) {
		this.numOctaves = numOctaves;
		this.octavesToSkip = octavesToSkip;
		this.numX = numX;
		this.numZ = numZ;

		this.interpolater = new IntInterpolateSmooth();
		this.permutations = new int[numOctaves][512];
		this.offsetU = new int[numOctaves];
		this.offsetV = new int[numOctaves];

		for (int i = 0; i < numOctaves; i++) {
			this.generatePermutations(this.permutations[i], rand);
			this.offsetU[i] = rand.nextInt();
			this.offsetV[i] = rand.nextInt();
		}
	}

	public void setInterpolator(IIntInterpolater interpolator) {
		this.interpolater = interpolator;
	}

	public void generateNoise(int startX, int startZ) {
		this.noise = new byte[this.numOctaves][numX * numZ];
		for (int octave = 0; octave < this.numOctaves; octave++) {
			this.populateNoise(octave, startX, startZ);
		}
	}

	public int getWeightedInt(int localX, int localZ, int[] weights) {
		return this.getWeightedInt(localX * numZ + localZ, weights);
	}

	public int getWeightedInt(int index, int[] weights) {
		int total = 0;
		for (int octave = 0; octave < this.numOctaves; octave++) {
			total += weights[octave] * this.noise[octave][index];
		}
		return total;
	}

	private static double[] doubleScalingsPerNumOctave = new double[] { 96.0D, 89.0D, 83.0D, 76.0D, 72.0D, 60.0D, 48.0D,
			38.0D, 30.0D };

	public double getWeightedDouble(int localX, int localZ, double[] weights) {
		return this.getWeightedDouble(localX * numZ + localZ, weights);
	}

	public double getWeightedDouble(int index, double[] weights) {
		double total = 0.0D;
		for (int octave = 0; octave < this.numOctaves; octave++) {
			total += weights[octave] * this.noise[octave][index];
		}
		return total / doubleScalingsPerNumOctave[this.numOctaves];
	}

	private void generatePermutations(int[] permutations, Random rand) {
		for (int i = 0; i < 256; i++) {
			permutations[i] = i;
		}

		for (int i = 0; i < 256; ++i) {
			int j = rand.nextInt(256 - i) + i;
			int k = permutations[i];
			permutations[i] = permutations[j];
			permutations[j] = k;
			permutations[i + 256] = permutations[i];
		}
	}

	private static final int[]	rc2_a	= new int[] { 1, -1, 1, -1, 1, -1, 1, -1, 0, 0, 0, 0, 1, 0, -1, 0 };
	private static final int[]	rc2_b	= new int[] { 0, 0, 0, 0, 1, 1, -1, -1, 1, 1, -1, -1, 0, 1, 0, -1 };

	public final int randomCombineTwo(int seed, int a, int b) {
		int j = seed & 15;
		return rc2_a[j] * a + rc2_b[j] * b;
	}

	private void populateNoise(int octave, int startX, int startZ) {
		int unitsUVperUnitXZ = 1 << (7 - octave - this.octavesToSkip);
		long startU = (((long) startX) << (7 - octave - this.octavesToSkip)) + this.offsetU[octave];
		long startV = (((long) startZ) << (7 - octave - this.octavesToSkip)) + this.offsetV[octave];
		int[] permutations = this.permutations[octave];
		byte[] noise = this.noise[octave];
		int index = 0;
		long u = startU;
		for (int localX = 0; localX < this.numX; ++localX) {
			int intU = (int) ((u >> 8) & 255); // high 8 bits are the int
			int fracU = (int) (u & 255); // low 8 bits are the fraction
			int permU0 = permutations[intU];
			int permU1 = permutations[intU + 1];

			long v = startV;
			for (int localZ = 0; localZ < this.numZ; ++localZ) {
				int intV = (int) ((v >> 8) & 255); // high 8 bits are the int
				int fracV = (int) (v & 255); // low 8 bits are the fraction
				int permV0 = permutations[permU0] + intV;
				int permV1 = permutations[permU1] + intV;
				int val00 = this.randomCombineTwo(permutations[permV0], fracU, fracV);
				int val01 = this.randomCombineTwo(permutations[permV1], fracU - 255, fracV);
				int val10 = this.randomCombineTwo(permutations[permV0 + 1], fracU, fracV - 255);
				int val11 = this.randomCombineTwo(permutations[permV1 + 1], fracU - 255, fracV - 255);

				int val0 = this.interpolater.interpolate(fracU, val00, val01);
				int val1 = this.interpolater.interpolate(fracU, val10, val11);
				int val = this.interpolater.interpolate(fracV, val0, val1);
				val = val >> 1;

				noise[index] = ((val < 127) ? ((val > -127) ? (byte) val : (byte) (-127)) : (byte) 127);

				index++;
				v += unitsUVperUnitXZ;
			}
			u += unitsUVperUnitXZ;
		}

	}

	public void getOctaveDistribution(int octave) {
		int[] counters = new int[64];
		int min = 256;
		int max = -256;
		for (byte b : this.noise[octave]) {
			counters[(b >> 2) + 32]++;
			min = Math.min(min, b);
			max = Math.max(max, b);
		}
		int counterMax = 0;
		for (int i = 0; i < 64; i++) {
			counterMax = Math.max(counterMax, counters[i]);
		}
		double scale = (50.0 / counterMax);
		for (int i = 0; i < 64; i++) {
			char[] line = new char[(int) (counters[i] * scale)];
			Arrays.fill(line, '+');
			int low = (i - 32) * 4;
			System.out.println(
					String.format("%-16s", low + " to " + (low + 3) + ":") + (new String(line)) + " " + counters[i]);
		}
		System.out.println("Min " + min + " Max " + max);
	}

	public void getWeightedDoubleDistribution(double[] weights) {
		int n = this.numX * this.numZ;
		double[] vals = new double[n];
		int bars = 32;
		int[] counters = new int[bars];
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < n; i++) {
			double val = this.getWeightedDouble(i, weights);
			vals[i] = val;
			max = Math.max(max, val);
			min = Math.min(min, val);
		}
		double interval = (max - min) / bars;
		for (int j = 0; j < bars; j++) {
			double barMin = min + (interval * j);
			double barMax = barMin + interval;
			for (int i = 0; i < n; i++) {
				if (vals[i] > barMin && vals[i] <= barMax) {
					counters[j]++;
				}
			}
		}
		int countersMax = 0;
		for (int j = 0; j < bars; j++) {
			countersMax = Math.max(countersMax, counters[j]);
		}
		double scale = (50.0 / countersMax);
		for (int j = 0; j < bars; j++) {
			double barMin = min + (interval * j);
			double barMax = barMin + interval;
			char[] line = new char[(int) (counters[j] * scale)];
			Arrays.fill(line, '+');
			System.out.println(
					String.format("%-50s", barMin + " to " + (barMax) + ":") + (new String(line)) + " " + counters[j]);
		}
	}

	public static void main(String[] args) {
		int numX = 1000;
		int numZ = 400;
		int numOctaves = 4;
		double[] weights = new double[numOctaves];

		for (int i = 0; i < numOctaves; i++) {
			weights[i] = Math.pow(2.0D, i) / (Math.pow(2.0D, numOctaves) - 1.0D);
		}

		NoiseGeneratorExo noise = new NoiseGeneratorExo(new Random(), numOctaves, numX, numZ);
		noise.generateNoise(4, 5);
		noise.getWeightedDoubleDistribution(weights);

	}
}
