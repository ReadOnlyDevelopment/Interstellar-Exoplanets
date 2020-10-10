package net.romvoid95.common.world.cave.carver.cave;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

import lombok.Getter;
import net.romvoid95.common.world.cave.ConfigHolder;
import net.romvoid95.common.world.cave.carver.CarverSettings;
import net.romvoid95.common.world.cave.enums.CaveType;
import net.romvoid95.common.world.cave.noise.FastNoise;

public class CaveSettings {

	@Getter private CarverSettings worldSettings;
	@Getter private int surfaceCutoff, bottomY, topY;

	@Getter private boolean enableYAdjust;
	@Getter private float yAdjustF1, yAdjustF2;

	public CaveSettings(World world) {
		this.worldSettings = CarverSettings.make().world(world).create();
	}

	public CaveBuilder build() {
		return new CaveBuilder(this);
	}

	/**
	 * Helps build a CaveCarver from a ConfigHolder based on its CaveType
	 * @param caveType the CaveType of this CaveCarver
	 * @param config the config
	 */
	public CaveSettings ofTypeFromConfig(CaveType caveType, ConfigHolder config) {
		this.worldSettings.setLiquidAltitude(config.liquidAltitude.get());
		this.worldSettings.setReplaceFloatingGravel(config.replaceFloatingGravel.get());
		this.worldSettings.setEnableDebugVisualizer(config.debugVisualizer.get());
		this.worldSettings.getNoiseSettings().setFractalType(FastNoise.FractalType.RigidMulti);
		switch (caveType) {
			case CUBIC:
				this.worldSettings.setFastNoise(true);
				this.worldSettings.setNoiseThreshold(config.cubicCaveNoiseThreshold.get());
				this.worldSettings.getNoiseSettings().setNoiseType(config.cubicCaveNoiseType.get());
				this.worldSettings.getNoiseSettings().setOctaves(config.cubicCaveFractalOctaves.get());
				this.worldSettings.getNoiseSettings().setGain(config.cubicCaveFractalGain.get());
				this.worldSettings.getNoiseSettings().setFrequency(config.cubicCaveFractalFrequency.get());
				this.worldSettings.setNumGens(config.cubicCaveNumGenerators.get());
				this.worldSettings.setXzCompression(config.cubicCaveXZCompression.get());
				this.worldSettings.setYCompression(config.cubicCaveYCompression.get());
				this.worldSettings.setPriority(config.cubicCavePriority.get());
				this.surfaceCutoff = config.cubicCaveSurfaceCutoffDepth.get();
				this.bottomY = config.cubicCaveBottom.get();
				this.topY = config.cubicCaveTop.get();
				this.enableYAdjust = config.cubicCaveEnableVerticalAdjustment.get();
				this.yAdjustF1 = config.cubicCaveYAdjustF1.get();
				this.yAdjustF2 = config.cubicCaveYAdjustF2.get();
				break;
			case SIMPLEX:
				this.worldSettings.setFastNoise(false);
				this.worldSettings.setNoiseThreshold(config.simplexCaveNoiseThreshold.get());
				this.worldSettings.getNoiseSettings().setNoiseType(config.simplexCaveNoiseType.get());
				this.worldSettings.getNoiseSettings().setOctaves(config.simplexCaveFractalOctaves.get());
				this.worldSettings.getNoiseSettings().setGain(config.simplexCaveFractalGain.get());
				this.worldSettings.getNoiseSettings().setFrequency(config.simplexCaveFractalFrequency.get());
				this.worldSettings.setNumGens(config.simplexCaveNumGenerators.get());
				this.worldSettings.setXzCompression(config.simplexCaveXZCompression.get());
				this.worldSettings.setYCompression(config.simplexCaveYCompression.get());
				this.worldSettings.setPriority(config.simplexCavePriority.get());
				this.surfaceCutoff = config.simplexCaveSurfaceCutoffDepth.get();
				this.bottomY = config.simplexCaveBottom.get();
				this.topY = config.simplexCaveTop.get();
				this.enableYAdjust = config.simplexCaveEnableVerticalAdjustment.get();
				this.yAdjustF1 = config.simplexCaveYAdjustF1.get();
				this.yAdjustF2 = config.simplexCaveYAdjustF2.get();
				break;
		}
		return this;
	}

	/**
	 * @param noiseType The type of noise this carver will use
	 */
	public CaveSettings noiseType(FastNoise.NoiseType noiseType) {
		worldSettings.getNoiseSettings().setNoiseType(noiseType);
		return this;
	}

	/**
	 * @param fractalOctaves Number of fractal octaves to use in ridged multifractal noise generation
	 */
	public CaveSettings fractalOctaves(int fractalOctaves) {
		worldSettings.getNoiseSettings().setOctaves(fractalOctaves);
		return this;
	}

	/**
	 * @param fractalGain Amount of gain to use in ridged multifractal noise generation
	 */
	public CaveSettings fractalGain(float fractalGain) {
		worldSettings.getNoiseSettings().setGain(fractalGain);
		return this;
	}

	/**
	 * @param fractalFreq Frequency to use in ridged multifractal noise generation
	 */
	public CaveSettings fractalFrequency(float fractalFreq) {
		worldSettings.getNoiseSettings().setFrequency(fractalFreq);
		return this;
	}

	/**
	 * @param numGens Number of noise values to calculate for a given block
	 */
	public CaveSettings numberOfGenerators(int numGens) {
		worldSettings.setNumGens(numGens);
		return this;
	}

	/**
	 * @param yCompression Vertical cave gen compression. Use 1.0 for default generation
	 */
	public CaveSettings verticalCompression(float yCompression) {
		worldSettings.setYCompression(yCompression);
		return this;
	}

	/**
	 * @param xzCompression Horizontal cave gen compression. Use 1.0 for default generation
	 */
	public CaveSettings horizontalCompression(float xzCompression) {
		worldSettings.setXzCompression(xzCompression);
		return this;
	}

	/**
	 * @param surfaceCutoff Cave surface cutoff depth
	 */
	public CaveSettings surfaceCutoff(int surfaceCutoff) {
		this.surfaceCutoff = surfaceCutoff;
		return this;
	}

	/**
	 * @param bottomY Cave bottom y-coordinate
	 */
	public CaveSettings bottomY(int bottomY) {
		this.bottomY = bottomY;
		return this;
	}

	/**
	 * @param topY Cave top y-coordinate
	 */
	public CaveSettings topY(int topY) {
		this.topY = topY;
		return this;
	}

	/**
	 * @param yAdjustF1 Adjustment value for the block immediately above. Must be between 0 and 1.0
	 */
	public CaveSettings verticalAdjuster1(float yAdjustF1) {
		this.yAdjustF1 = yAdjustF1;
		return this;
	}

	/**
	 * @param yAdjustF2 Adjustment value for the block two blocks above. Must be between 0 and 1.0
	 */
	public CaveSettings verticalAdjuster2(float yAdjustF2) {
		this.yAdjustF2 = yAdjustF2;
		return this;
	}

	/**
	 * @param enableYAdjust Whether or not to adjust/increase the height of caves.
	 */
	public CaveSettings enableVerticalAdjustment(boolean enableYAdjust) {
		this.enableYAdjust = enableYAdjust;
		return this;
	}

	/**
	 * @param noiseThreshold Noise threshold to determine whether or not a given block will be dug out
	 */
	public CaveSettings noiseThreshold(float noiseThreshold) {
		worldSettings.setNoiseThreshold(noiseThreshold);
		return this;
	}

	/**
	 * @param vBlock Block used for this cave type in the debug visualizer
	 */
	public CaveSettings debugVisualizerBlock(IBlockState vBlock) {
		worldSettings.setDebugBlock(vBlock);
		return this;
	}

	/**
	 * @param liquidAltitude altitude at and below which air is replaced with liquid
	 */
	public CaveSettings liquidAltitude(int liquidAltitude) {
		worldSettings.setLiquidAltitude(liquidAltitude);
		return this;
	}

	/**
	 * Enable the debug visualizer
	 */
	public CaveSettings enableDebugVisualizer(boolean enableDebugVisualizer) {
		worldSettings.setEnableDebugVisualizer(enableDebugVisualizer);
		return this;
	}
}

