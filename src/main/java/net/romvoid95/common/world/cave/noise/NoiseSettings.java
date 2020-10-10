package net.romvoid95.common.world.cave.noise;

import lombok.Builder;
import lombok.Data;

@Builder(buildMethodName = "create")
@Data
public class NoiseSettings {
    private FastNoise.NoiseType   noiseType;
    private FastNoise.FractalType fractalType;
    private int   octaves;
    private float gain;
    private float frequency;

    public static NoiseSettings createDefault() {
		return NoiseSettings.builder()
				.noiseType(FastNoise.NoiseType.SimplexFractal)
				.fractalType(FastNoise.FractalType.FBM)
				.octaves(3)
				.gain(0.5F)
				.frequency(0.01F).create();
    }
}
