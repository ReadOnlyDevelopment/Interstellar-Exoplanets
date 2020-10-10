package net.romvoid95.common.world.cave.carver.cavern;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import lombok.Getter;
import net.romvoid95.common.world.cave.carver.*;
import net.romvoid95.common.world.cave.enums.CavernType;
import net.romvoid95.common.world.cave.noise.NoiseColumn;
import net.romvoid95.common.world.cave.noise.NoiseGen;
import net.romvoid95.common.world.cave.util.CaveUtils;
import net.romvoid95.core.ExoplanetsMod;

public class CavernBuilder implements ICarver {
	
	@Getter private CarverSettings settings;
	@Getter private NoiseGen noiseGen;
	private World world;

	private CavernType cavernType;
	@Getter private int bottomY, topY;

	public CavernBuilder(final CavernSettings builder) {
		settings = builder.getWorldSettings();
		noiseGen = new NoiseGen(
				settings.getWorld(),
				settings.isFastNoise(),
				settings.getNoiseSettings(),
				settings.getNumGens(),
				settings.getYCompression(),
				settings.getXzCompression()
				);
		world = builder.getWorldSettings().getWorld();
		cavernType = builder.getCavernType();
		bottomY = builder.getBottomY();
		topY = builder.getTopY();
		if (bottomY > topY) {
			ExoplanetsMod.logger.warn("Warning: Min altitude for caverns should not be greater than max altitude.");
			ExoplanetsMod.logger.warn("Using default values...");
			this.bottomY = 1;
			this.topY = 35;
		}
	}

	public void carveColumn(ChunkPrimer primer, BlockPos colPos, int topY, float smoothAmp, NoiseColumn noises, IBlockState liquidBlock, boolean flooded) {
		int localX = CaveUtils.getLocal(colPos.getX());
		int localZ = CaveUtils.getLocal(colPos.getZ());

		IBlockState airBlockState;

		// Validate vars
		if ((localX < 0) || (localX > 15)) {
			return;
		}
		if ((localZ < 0) || (localZ > 15)) {
			return;
		}
		if ((bottomY < 0) || (bottomY > 255)) {
			return;
		}
		if (topY > 255) {
			return;
		}

		// Set altitude at which caverns start closing off on the top
		topY -= 2;
		int topTransitionBoundary = topY - 6;

		// Set altitude at which caverns start closing off on the bottom
		int bottomTransitionBoundary = bottomY + 3;
		if (cavernType == CavernType.FLOORED) { // Close off floored caverns more to create "floors"
			bottomTransitionBoundary = bottomY < settings.getLiquidAltitude() ? settings.getLiquidAltitude() + 8 : bottomY + 7;
		}

		// Validate transition boundaries
		topTransitionBoundary = Math.max(topTransitionBoundary, 1);
		bottomTransitionBoundary = Math.min(bottomTransitionBoundary, 255);

		for (int y = topY; y >= bottomY; y--) {
			if ((y <= settings.getLiquidAltitude()) && (liquidBlock == null)) {
				break;
			}

			List<Double> noiseBlock;
			boolean digBlock = false;

			// Compute a single noise value to represent all the noise values in the NoiseTuple
			float noise = 1;
			noiseBlock = noises.get(y).getNoiseValues();
			for (double n : noiseBlock) {
				noise *= n;
			}

			// Adjust threshold if we're in the transition range to provide smoother transition into ceiling
			float noiseThreshold = settings.getNoiseThreshold();
			if (y >= topTransitionBoundary) {
				noiseThreshold *= (float) (y - topY) / (topTransitionBoundary - topY);
			}

			// Close off caverns at the bottom to hide bedrock and give some walkable area
			if (y < bottomTransitionBoundary) {
				noiseThreshold *= (float) (y - bottomY) / (bottomTransitionBoundary - bottomY);
			}

			// Adjust threshold along region borders to create smooth transition
			if (smoothAmp < 1) {
				noiseThreshold *= smoothAmp;
			}

			// Mark block for removal if the noise passes the threshold check
			if (noise < noiseThreshold) {
				digBlock = true;
			}

			BlockPos blockPos = new BlockPos(localX, y, localZ);
			airBlockState = flooded && (y < world.getSeaLevel()) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();

			// Dig out the block if it passed the threshold check, using the debug visualizer if enabled
			if (settings.isEnableDebugVisualizer()) {
				CarverUtils.debugDigBlock(primer, blockPos, settings.getDebugBlock(), digBlock);
			} else if (digBlock) {
				CarverUtils.digBlock(settings.getWorld(), primer, blockPos, airBlockState, liquidBlock, settings.getLiquidAltitude(), settings.isReplaceFloatingGravel());
			}
		}
	}
	
	@Override
	public int getPriority() {
		return settings.getPriority();
	}
}
