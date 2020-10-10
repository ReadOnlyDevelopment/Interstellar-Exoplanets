package net.romvoid95.common.world.cave.carver;

import lombok.*;
import net.romvoid95.common.world.cave.noise.NoiseCube;
import net.romvoid95.common.world.cave.noise.NoiseUtils;

@ToString
public class CarverNoiseRange {
	private float bottom, top;

	private float smoothBottomCutoff, smoothTopCutoff, percentLength;

	@Getter private ICarver carver;

	@Getter @Setter private NoiseCube noiseCube;

	private static final float SMOOTH_PERCENT = .3f;

	public CarverNoiseRange(float bottom, float top, ICarver carver) {
		this.bottom = bottom;
		this.top = top;
		this.percentLength = (top == 1 ? 1 : NoiseUtils.noiseToCDF(top)) - (bottom == -1 ? 0 : NoiseUtils.noiseToCDF(bottom));
		float smoothRangePercent = percentLength * SMOOTH_PERCENT;
		this.smoothBottomCutoff = NoiseUtils.simplexNoiseOffsetByPercent(bottom, smoothRangePercent);
		this.smoothTopCutoff = NoiseUtils.simplexNoiseNegativeOffsetByPercent(top, smoothRangePercent);
		this.carver = carver;
		this.noiseCube = null;
		
	}

	public boolean contains(float noiseValue) {
		return (bottom <= noiseValue) && (noiseValue < top);
	}

	public float getSmoothAmp(float noiseValue) {
		if ((bottom <= noiseValue) && (noiseValue <= smoothBottomCutoff)) {
			return (noiseValue - bottom) / (smoothBottomCutoff - bottom);
		}
		else if ((smoothTopCutoff <= noiseValue) && (noiseValue < top)) {
			return (noiseValue - top) / (smoothTopCutoff - top);
		}
		return 1;
	}
}
