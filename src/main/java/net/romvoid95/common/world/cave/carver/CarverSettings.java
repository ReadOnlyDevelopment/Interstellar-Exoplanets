package net.romvoid95.common.world.cave.carver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

import lombok.*;
import net.romvoid95.common.world.cave.noise.NoiseSettings;

@Builder(builderMethodName = "make", buildMethodName = "create")
@Data
public class CarverSettings {

	@NonNull
	private World			world;
	@Builder.Default
	private NoiseSettings	noiseSettings	= NoiseSettings.createDefault();
	private long			seed;
	private int				priority;
	private boolean			isFastNoise;
	private int				numGens;
	private float			yCompression;
	private float			xzCompression;
	private float			noiseThreshold;
	private int				liquidAltitude;
	private boolean			replaceFloatingGravel;
	private IBlockState		debugBlock;
	private boolean			enableDebugVisualizer;

}
