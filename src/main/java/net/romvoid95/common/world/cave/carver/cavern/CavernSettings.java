package net.romvoid95.common.world.cave.carver.cavern;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

import lombok.Getter;
import net.romvoid95.common.world.cave.ConfigHolder;
import net.romvoid95.common.world.cave.carver.CarverSettings;
import net.romvoid95.common.world.cave.enums.CavernType;
import net.romvoid95.common.world.cave.noise.FastNoise;

public class CavernSettings {

	@Getter
	private CarverSettings	worldSettings;
	private CavernType		cavernType;
	private int				bottomY;
	private int				topY;

	public CavernSettings (World world) {
		this.worldSettings = CarverSettings.make().world(world).create();
	}

	public CavernBuilder build() {
		return new CavernBuilder(this);
	}

	/**
	 * Helps build a CavernCarver from a ConfigHolder based on its CavernType
	 * 
	 * @param cavernType the CavernType of this CavernCarver
	 * @param config     the config
	 */
	public CavernSettings ofTypeFromConfig(CavernType cavernType, ConfigHolder config) {
		this.worldSettings.setLiquidAltitude(config.liquidAltitude.get());
		this.worldSettings.setReplaceFloatingGravel(config.replaceFloatingGravel.get());
		this.worldSettings.getNoiseSettings().setFractalType(FastNoise.FractalType.RigidMulti);
		this.worldSettings.setEnableDebugVisualizer(config.debugVisualizer.get());
		this.worldSettings.setFastNoise(true);
		this.cavernType = cavernType;
		switch (cavernType) {
			case LIQUID:
				this.worldSettings.setNoiseThreshold(config.liquidCavernNoiseThreshold.get());
				this.worldSettings.getNoiseSettings().setNoiseType(config.liquidCavernNoiseType.get());
				this.worldSettings.getNoiseSettings().setOctaves(config.liquidCavernFractalOctaves.get());
				this.worldSettings.getNoiseSettings().setGain(config.liquidCavernFractalGain.get());
				this.worldSettings.getNoiseSettings().setFrequency(config.liquidCavernFractalFrequency.get());
				this.worldSettings.setNumGens(config.liquidCavernNumGenerators.get());
				this.worldSettings.setYCompression(config.liquidCavernYCompression.get());
				this.worldSettings.setXzCompression(config.liquidCavernXZCompression.get());
				this.worldSettings.setPriority(config.liquidCavernPriority.get());
				this.bottomY = config.liquidCavernBottom.get();
				this.topY = config.liquidCavernTop.get();
				break;
			case FLOORED:
				this.worldSettings.setNoiseThreshold(config.flooredCavernNoiseThreshold.get());
				this.worldSettings.getNoiseSettings().setNoiseType(config.flooredCavernNoiseType.get());
				this.worldSettings.getNoiseSettings().setOctaves(config.flooredCavernFractalOctaves.get());
				this.worldSettings.getNoiseSettings().setGain(config.flooredCavernFractalGain.get());
				this.worldSettings.getNoiseSettings().setFrequency(config.flooredCavernFractalFrequency.get());
				this.worldSettings.setNumGens(config.flooredCavernNumGenerators.get());
				this.worldSettings.setYCompression(config.flooredCavernYCompression.get());
				this.worldSettings.setXzCompression(config.flooredCavernXZCompression.get());
				this.worldSettings.setPriority(config.flooredCavernPriority.get());
				this.bottomY = config.flooredCavernBottom.get();
				this.topY = config.flooredCavernTop.get();
				break;
		}
		return this;
	}

	/**
	 * @param noiseType The type of noise this carver will use
	 */
	public CavernSettings noiseType(FastNoise.NoiseType noiseType) {
		worldSettings.getNoiseSettings().setNoiseType(noiseType);
		return this;
	}

	/**
	 * @param fractalOctaves Number of fractal octaves to use in ridged multifractal noise generation
	 */
	public CavernSettings fractalOctaves(int fractalOctaves) {
		worldSettings.getNoiseSettings().setOctaves(fractalOctaves);
		return this;
	}

	/**
	 * @param fractalGain Amount of gain to use in ridged multifractal noise generation
	 */
	public CavernSettings fractalGain(float fractalGain) {
		worldSettings.getNoiseSettings().setGain(fractalGain);
		return this;
	}

	/**
	 * @param fractalFreq Frequency to use in ridged multifractal noise generation
	 */
	public CavernSettings fractalFrequency(float fractalFreq) {
		worldSettings.getNoiseSettings().setFrequency(fractalFreq);
		return this;
	}

	/**
	 * @param numGens Number of noise values to calculate for a given block
	 */
	public CavernSettings numberOfGenerators(int numGens) {
		worldSettings.setNumGens(numGens);
		return this;
	}

	/**
	 * @param yCompression Vertical cave gen compression. Use 1.0 for default generation
	 */
	public CavernSettings verticalCompression(float yCompression) {
		worldSettings.setYCompression(yCompression);
		return this;
	}

	/**
	 * @param xzCompression Horizontal cave gen compression. Use 1.0 for default generation
	 */
	public CavernSettings horizontalCompression(float xzCompression) {
		worldSettings.setXzCompression(xzCompression);
		return this;
	}

	/**
	 * @param noiseThreshold Noise threshold to determine whether or not a given block will be dug out
	 */
	public CavernSettings noiseThreshold(float noiseThreshold) {
		worldSettings.setNoiseThreshold(noiseThreshold);
		return this;
	}

	/**
	 * @param vBlock Block used for this cave type in the debug visualizer
	 */
	public CavernSettings debugVisualizerBlock(IBlockState vBlock) {
		worldSettings.setDebugBlock(vBlock);
		return this;
	}

	/**
	 * @param liquidAltitude altitude at and below which air is replaced with liquid
	 */
	public CavernSettings liquidAltitude(int liquidAltitude) {
		worldSettings.setLiquidAltitude(liquidAltitude);
		return this;
	}

	/**
	 * Enable the debug visualizer
	 */
	public CavernSettings enableDebugVisualizer(boolean enableDebugVisualizer) {
		worldSettings.setEnableDebugVisualizer(enableDebugVisualizer);
		return this;
	}

	/**
	 * Set cavern type
	 */
	public CavernSettings cavernType(CavernType cavernType) {
		this.cavernType = cavernType;
		return this;
	}

	/**
	 * Set cavern bottom Y coordinate
	 */
	public CavernSettings bottomY(int bottomY) {
		this.bottomY = bottomY;
		return this;
	}

	/**
	 * Set cavern top Y coordinate
	 */
	public CavernSettings topY(int topY) {
		this.topY = topY;
		return this;
	}

	public CavernType getCavernType() {
		return cavernType;
	}

	public int getBottomY() {
		return bottomY;
	}

	public int getTopY() {
		return topY;
	}
}
