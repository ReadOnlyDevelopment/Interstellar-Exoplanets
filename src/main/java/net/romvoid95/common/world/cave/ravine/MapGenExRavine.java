package net.romvoid95.common.world.cave.ravine;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenRavine;

import net.minecraftforge.common.BiomeDictionary;

import net.romvoid95.common.world.cave.*;
import net.romvoid95.common.world.cave.carver.CarverUtils;
import net.romvoid95.common.world.cave.util.CaveUtils;

public class MapGenExRavine extends MapGenRavine {
	private ConfigHolder config;
	private WaterRegionController waterRegionController;

	IBlockState[][] currChunkLiquidBlocks;
	int currChunkX, currChunkZ;

	@Override
	public void generate(World worldIn, int x, int z, @Nonnull ChunkPrimer primer) {

		if (config == null) { // First call - lazy initialization
			this.initialize(worldIn);
		}

		if (config.enableVanillaRavines.get()) {
			super.generate(worldIn, x, z, primer);
		}
	}

	@Override
	protected void digBlock(ChunkPrimer primer, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
		IBlockState liquidBlockState;
		BlockPos pos = new BlockPos(x + (chunkX * 16), y, z + (chunkZ * 16));

		if ((currChunkLiquidBlocks == null) || (chunkX != currChunkX) || (chunkZ != currChunkZ)) {
			try {
				currChunkLiquidBlocks = waterRegionController.getLiquidBlocksForChunk(chunkX, chunkZ);
				liquidBlockState = currChunkLiquidBlocks[CaveUtils.getLocal(x)][CaveUtils.getLocal(z)];
				currChunkX = chunkX;
				currChunkZ = chunkZ;
			} catch (Exception e) {
				liquidBlockState = Blocks.LAVA.getDefaultState();
			}
		}
		else {
			try {
				liquidBlockState = currChunkLiquidBlocks[CaveUtils.getLocal(x)][CaveUtils.getLocal(z)];
			} catch (Exception e) {
				liquidBlockState = Blocks.LAVA.getDefaultState();
			}
		}

		// Don't dig boundaries between flooded and unflooded openings.
		boolean flooded = config.enableFloodedRavines.get() && BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.OCEAN) && (y < world.getSeaLevel());
		if (flooded) {
			float smoothAmpFactor = CaveUtils.biomeDistanceFactor(world, pos, 2, b -> !BiomeDictionary.hasType(b, BiomeDictionary.Type.OCEAN));
			if (smoothAmpFactor <= .25f) { // Wall between flooded and normal caves.
				return;
			}
		}

		IBlockState airBlockState = flooded ? Blocks.WATER.getDefaultState() : AIR;
		CarverUtils.digBlock(world, primer, pos, airBlockState, liquidBlockState, config.liquidAltitude.get(), config.replaceFloatingGravel.get());
	}

	// Disable built-in water block checks.
	// Without this, ravines in water regions will be sliced up.
	@Override
	protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
		return false;
	}

	private void initialize(World worldIn) {
		this.world = worldIn;

		this.config = ConfigLoader.loadConfigFromFileForDimension(worldIn);
		this.waterRegionController = new WaterRegionController(world, config);
	}
}
