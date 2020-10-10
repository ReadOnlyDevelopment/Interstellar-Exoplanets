package net.romvoid95.common.world.cave.carver.cave;

import java.util.*;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import lombok.Getter;
import net.romvoid95.common.world.cave.carver.*;
import net.romvoid95.common.world.cave.noise.*;
import net.romvoid95.common.world.cave.util.CaveUtils;
import net.romvoid95.core.ExoplanetsMod;

public class CaveBuilder implements ICarver {
	@Getter private CarverSettings	settings;
	@Getter private NoiseGen		noiseGen;
	private World			world;

	private int		surfaceCutoff;
	private int		bottomY;
	private int		topY;
	private boolean	enableYAdjust;
	private float	yAdjustF1;
	private float	yAdjustF2;

	public CaveBuilder (final CaveSettings builder) {
		settings = builder.getWorldSettings();
		noiseGen = new NoiseGen(settings.getWorld(), settings.isFastNoise(), settings.getNoiseSettings(), settings.getNumGens(), settings.getYCompression(), settings.getXzCompression());
		world = builder.getWorldSettings().getWorld();
		surfaceCutoff = builder.getSurfaceCutoff();
		bottomY = builder.getBottomY();
		topY = builder.getTopY();
		enableYAdjust = builder.isEnableYAdjust();
		yAdjustF1 = builder.getYAdjustF1();
		yAdjustF2 = builder.getYAdjustF2();
		if (bottomY > topY) {
			ExoplanetsMod.logger.warn("Warning: Min altitude for caves should not be greater than max altitude.");
			ExoplanetsMod.logger.warn("Using default values...");
			this.bottomY = 1;
			this.topY = 80;
		}
	}

	public void carveColumn(ChunkPrimer primer, BlockPos colPos, int topY, NoiseColumn noises, IBlockState liquidBlock,
			boolean flooded) {
		int localX = CaveUtils.getLocal(colPos.getX());
		int localZ = CaveUtils.getLocal(colPos.getZ());

		IBlockState airBlockState;

		if ((localX < 0) || (localX > 15)) {
			return;
		}
		if ((localZ < 0) || (localZ > 15)) {
			return;
		}
		if ((bottomY < 0) || (bottomY > 255)) {
			return;
		}
		if ((topY < 0) || (topY > 255)) {
			return;
		}

		int transitionBoundary = topY - surfaceCutoff;

		if (transitionBoundary < 1) {
			transitionBoundary = 1;
		}

		Map<Integer, Float> thresholds = generateThresholds(topY, bottomY, transitionBoundary);

		if (this.enableYAdjust) {
			preprocessCaveNoiseCol(noises, topY, bottomY, thresholds, settings.getNumGens());
		}

		for (int y = topY; y >= bottomY; y--) {
			if ((y <= settings.getLiquidAltitude()) && (liquidBlock == null)) {
				break;
			}

			List<Double> noiseBlock = noises.get(y).getNoiseValues();
			boolean digBlock = true;

			for (double noise : noiseBlock) {
				if (noise < thresholds.get(y)) {
					digBlock = false;
					break;
				}
			}

			airBlockState = flooded && (y < world.getSeaLevel()) ? Blocks.WATER.getDefaultState()
					: Blocks.AIR.getDefaultState();
			BlockPos blockPos = new BlockPos(localX, y, localZ);

			if (settings.isEnableDebugVisualizer()) {
				CarverUtils.debugDigBlock(primer, blockPos, settings.getDebugBlock(), digBlock);
			} else if (digBlock) {
				CarverUtils.digBlock(settings.getWorld(), primer, blockPos, airBlockState, liquidBlock,
						settings.getLiquidAltitude(), settings.isReplaceFloatingGravel());
			}
		}
	}

	private void preprocessCaveNoiseCol(NoiseColumn noises, int topY, int bottomY, Map<Integer, Float> thresholds,
			int numGens) {
		for (int realY = topY; realY >= bottomY; realY--) {
			NoiseTuple noiseBlock = noises.get(realY);
			float threshold = thresholds.get(realY);

			boolean valid = true;
			for (double noise : noiseBlock.getNoiseValues()) {
				if (noise < threshold) {
					valid = false;
					break;
				}
			}
			if (valid) {
				float f1 = yAdjustF1;
				float f2 = yAdjustF2;
				if (realY < topY) {
					NoiseTuple tupleAbove = noises.get(realY + 1);
					for (int i = 0; i < numGens; i++) {
						tupleAbove.set(i, ((1 - f1) * tupleAbove.get(i)) + (f1 * noiseBlock.get(i)));
					}
				}
				if (realY < (topY - 1)) {
					NoiseTuple tupleTwoAbove = noises.get(realY + 2);
					for (int i = 0; i < numGens; i++) {
						tupleTwoAbove.set(i, ((1 - f2) * tupleTwoAbove.get(i)) + (f2 * noiseBlock.get(i)));
					}
				}
			}
		}
	}

	private Map<Integer, Float> generateThresholds(int topY, int bottomY, int transitionBoundary) {
		Map<Integer, Float> thresholds = new HashMap<>();
		for (int realY = bottomY; realY <= topY; realY++) {
			float noiseThreshold = settings.getNoiseThreshold();
			if (realY >= transitionBoundary) {
				noiseThreshold *= (1 + (.3f * ((float) (realY - transitionBoundary) / (topY - transitionBoundary))));
			}
			thresholds.put(realY, noiseThreshold);
		}

		return thresholds;
	}

	@Override
	public int getPriority() {
		return settings.getPriority();
	}

	public int getBottomY() {
		return this.bottomY;
	}

	@Override
	public int getTopY() {
		return this.topY;
	}
}