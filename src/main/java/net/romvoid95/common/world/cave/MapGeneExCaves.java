package net.romvoid95.common.world.cave;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

import net.romvoid95.common.world.cave.bedrock.FlattenBedrock;
import net.romvoid95.common.world.cave.util.CaveUtils;

public class MapGeneExCaves extends MapGenCaves {

	// Region Controllers
	public WaterRegionController waterRegionController;
	private CaveCarverController caveCarverController;
	private CavernCarverController cavernCarverController;

	// Config holder for options specific to this carver
	public ConfigHolder config;

	/**
	 * This overrides the vanilla cave generation, which is
	 * ordinarily performed by the MapGenCaves class.
	 * This function is called for every new chunk that is generated in a world.
	 * @param worldIn The Minecraft world
	 * @param chunkX The chunk's x-coordinate (on the chunk grid, not the block grid)
	 * @param chunkZ The chunk's z-coordinate (on the chunk grid, not the block grid)
	 * @param primer The chunk's ChunkPrimer
	 */
	@Override
	public void generate(World worldIn, int chunkX, int chunkZ, @Nonnull ChunkPrimer primer) {

		if (world == null) { // First call - lazy initialization of all controllers and config
			this.initialize(worldIn);
		}

		// Flatten bedrock, if enabled
		if (config.flattenBedrock.get()) {
			FlattenBedrock.flattenBedrock(primer, config.bedrockWidth.get());
		}

		// Determine surface altitudes in this chunk
		int[][] surfaceAltitudes = new int[16][16];
		for (int subX = 0; subX < (16 / CaveConstant.SUB_CHUNK_SIZE); subX++) {
			for (int subZ = 0; subZ < (16 / CaveConstant.SUB_CHUNK_SIZE); subZ++) {
				int startX = subX * CaveConstant.SUB_CHUNK_SIZE;
				int startZ = subZ * CaveConstant.SUB_CHUNK_SIZE;
				for (int offsetX = 0; offsetX < CaveConstant.SUB_CHUNK_SIZE; offsetX++) {
					for (int offsetZ = 0; offsetZ < CaveConstant.SUB_CHUNK_SIZE; offsetZ++) {
						int surfaceHeight;
						if (config.overrideSurfaceDetection.get()) {
							surfaceHeight = 1; // Don't waste time calculating surface height if it's going to be overridden anyway
						}
						else {
							surfaceHeight = CaveUtils.getSurfaceAltitudeForColumn(primer, startX + offsetX, startZ + offsetZ);
						}
						surfaceAltitudes[startX + offsetX][startZ + offsetZ] = surfaceHeight;
					}
				}
			}
		}

		// Determine liquid blocks for this chunk
		IBlockState[][] liquidBlocks = waterRegionController.getLiquidBlocksForChunk(chunkX, chunkZ);

		// Carve chunk
		caveCarverController.carveChunk(primer, chunkX, chunkZ, surfaceAltitudes, liquidBlocks);
		cavernCarverController.carveChunk(primer, chunkX, chunkZ, surfaceAltitudes, liquidBlocks);
	}

	/**
	 * Initialize carvers and controllers for this dimension.
	 * @param worldIn The minecraft world
	 */
	private void initialize(World worldIn) {
		// Extract world information
		this.world = worldIn;
		// Load config for this dimension
		this.config = ConfigLoader.loadConfigFromFileForDimension(worldIn);

		// Initialize controllers
		this.waterRegionController = new WaterRegionController(world, config);
		this.caveCarverController = new CaveCarverController(world, config);
		this.cavernCarverController = new CavernCarverController(worldIn, config);
	}
}
