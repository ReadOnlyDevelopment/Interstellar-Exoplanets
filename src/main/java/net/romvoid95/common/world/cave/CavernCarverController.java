package net.romvoid95.common.world.cave;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import net.minecraftforge.common.BiomeDictionary;

import net.romvoid95.common.world.cave.carver.CarverNoiseRange;
import net.romvoid95.common.world.cave.carver.cavern.CavernBuilder;
import net.romvoid95.common.world.cave.carver.cavern.CavernSettings;
import net.romvoid95.common.world.cave.enums.CavernType;
import net.romvoid95.common.world.cave.enums.RegionSize;
import net.romvoid95.common.world.cave.noise.*;
import net.romvoid95.common.world.cave.util.CaveUtils;
import net.romvoid95.core.ExoplanetsMod;

public class CavernCarverController {
    private World world;
    private FastNoise cavernRegionController;
    private List<CarverNoiseRange> noiseRanges = new ArrayList<>();

    // Vars from config
    private boolean isDebugViewEnabled;
    private boolean isOverrideSurfaceDetectionEnabled;
    private boolean isFloodedUndergroundEnabled;

    // Equality checking functions used for closing off flooded caves
    private static Predicate<Biome> isOcean = b -> BiomeDictionary.hasType(b, BiomeDictionary.Type.OCEAN);
    private static Predicate<Biome> isNotOcean = b -> !isOcean.test(b);

    public CavernCarverController(World worldIn, ConfigHolder config) {
        this.world = worldIn;
        this.isDebugViewEnabled = config.debugVisualizer.get();
        this.isOverrideSurfaceDetectionEnabled = config.overrideSurfaceDetection.get();
        this.isFloodedUndergroundEnabled = config.enableFloodedUnderground.get();

        // Configure cavern region controller, which determines what type of cavern should be carved in any given region
        float cavernRegionSize = calcCavernRegionSize(config.cavernRegionSize.get(), config.cavernRegionCustomSize.get());
        this.cavernRegionController = new FastNoise();
        this.cavernRegionController.SetSeed((int)worldIn.getSeed() + 333);
        this.cavernRegionController.SetFrequency(cavernRegionSize);

        // Initialize all carvers using config options
        List<CavernBuilder> carvers = new ArrayList<>();
        carvers.add(new CavernSettings(worldIn)
            .ofTypeFromConfig(CavernType.LIQUID, config)
            .debugVisualizerBlock(Blocks.REDSTONE_BLOCK.getDefaultState())
            .build()
        );
        carvers.add(new CavernSettings(worldIn)
            .ofTypeFromConfig(CavernType.FLOORED, config)
            .debugVisualizerBlock(Blocks.GOLD_BLOCK.getDefaultState())
            .build()
        );

        float spawnChance = config.cavernSpawnChance.get() / 100f;
        int totalPriority = carvers.stream().map(CavernBuilder::getPriority).reduce(0, Integer::sum);

        ExoplanetsMod.logger.debug("CAVERN INFORMATION");
        ExoplanetsMod.logger.debug("--> SPAWN CHANCE SET TO: " + spawnChance);
        ExoplanetsMod.logger.debug("--> TOTAL PRIORITY: " + totalPriority);

        carvers.removeIf(carver -> carver.getPriority() == 0);
        float totalDeadzonePercent = 1 - spawnChance;
        float deadzonePercent = carvers.size() > 1
                ? totalDeadzonePercent / (carvers.size() - 1)
                : totalDeadzonePercent;

        ExoplanetsMod.logger.debug("--> DEADZONE PERCENT: " + deadzonePercent + "(" + totalDeadzonePercent + " TOTAL)");

        float currNoise = -1f;

        for (CavernBuilder carver : carvers) {
            ExoplanetsMod.logger.debug("--> CARVER");
            float rangeCDFPercent = (float)carver.getPriority() / totalPriority * spawnChance;
            float topNoise = NoiseUtils.simplexNoiseOffsetByPercent(currNoise, rangeCDFPercent);
            CarverNoiseRange range = new CarverNoiseRange(currNoise, topNoise, carver);
            noiseRanges.add(range);

            // Offset currNoise for deadzone region
            currNoise = NoiseUtils.simplexNoiseOffsetByPercent(topNoise, deadzonePercent);

            ExoplanetsMod.logger.debug("    --> RANGE PERCENT LENGTH WANTED: " + rangeCDFPercent);
            ExoplanetsMod.logger.debug("    --> RANGE FOUND: " + range);
        }
    }

    public void carveChunk(ChunkPrimer primer, int chunkX, int chunkZ, int[][] surfaceAltitudes, IBlockState[][] liquidBlocks) {
        // Prevent unnecessary computation if caverns are disabled
        if (noiseRanges.size() == 0) {
            return;
        }

        boolean flooded = false;
        float smoothAmpFactor = 1;

        for (int subX = 0; subX < 16 / CaveConstant.SUB_CHUNK_SIZE; subX++) {
            for (int subZ = 0; subZ < 16 / CaveConstant.SUB_CHUNK_SIZE; subZ++) {
                int startX = subX * CaveConstant.SUB_CHUNK_SIZE;
                int startZ = subZ * CaveConstant.SUB_CHUNK_SIZE;
                int endX = startX + CaveConstant.SUB_CHUNK_SIZE - 1;
                int endZ = startZ + CaveConstant.SUB_CHUNK_SIZE - 1;
                BlockPos startPos = new BlockPos(chunkX * 16 + startX, 1, chunkZ * 16 + startZ);
                BlockPos endPos = new BlockPos(chunkX * 16 + endX, 1, chunkZ * 16 + endZ);

                noiseRanges.forEach(range -> range.setNoiseCube(null));

                // Get max height in subchunk. This is needed for calculating the noise cube
                int maxHeight = 0;
                if (!isOverrideSurfaceDetectionEnabled) { // Only necessary if we aren't overriding surface detection
                    for (int x = startX; x < endX; x++) {
                        for (int z = startZ; z < endZ; z++) {
                            maxHeight = Math.max(maxHeight, surfaceAltitudes[x][z]);
                        }
                    }
                    for (CarverNoiseRange range : noiseRanges) {
                        CavernBuilder carver = (CavernBuilder) range.getCarver();
                        maxHeight = Math.max(maxHeight, carver.getTopY());
                    }
                }

                for (int offsetX = 0; offsetX < CaveConstant.SUB_CHUNK_SIZE; offsetX++) {
                    for (int offsetZ = 0; offsetZ < CaveConstant.SUB_CHUNK_SIZE; offsetZ++) {
                        int localX = startX + offsetX;
                        int localZ = startZ + offsetZ;
                        BlockPos colPos = new BlockPos(chunkX * 16 + localX, 1, chunkZ * 16 + localZ);

                        if (isFloodedUndergroundEnabled && !isDebugViewEnabled) {
                            flooded = BiomeDictionary.hasType(world.getBiome(colPos), BiomeDictionary.Type.OCEAN);
                            smoothAmpFactor = CaveUtils.biomeDistanceFactor(world, colPos, 2, flooded ? isNotOcean : isOcean);
                            if (smoothAmpFactor <= 0) { // Wall between flooded and normal caves.
                                continue; // Continue to prevent unnecessary noise calculation
                            }
                        }

                        int surfaceAltitude = surfaceAltitudes[localX][localZ];
                        IBlockState liquidBlock = liquidBlocks[localX][localZ];

                        // Get noise values used to determine cavern region
                        float cavernRegionNoise = cavernRegionController.GetNoise(colPos.getX(), colPos.getZ());

                        // Carve cavern using matching carver
                        for (CarverNoiseRange range : noiseRanges) {
                            if (!range.contains(cavernRegionNoise)) {
                                continue;
                            }
                            CavernBuilder carver = (CavernBuilder)range.getCarver();
                            int bottomY = carver.getBottomY();
                            int topY = isDebugViewEnabled ? carver.getTopY() : Math.min(surfaceAltitude, carver.getTopY());
                            if (isOverrideSurfaceDetectionEnabled) {
                                topY = carver.getTopY();
                                maxHeight = carver.getTopY();
                            }
                            float smoothAmp = range.getSmoothAmp(cavernRegionNoise) * smoothAmpFactor;
                            if (range.getNoiseCube() == null) {
                                range.setNoiseCube(carver.getNoiseGen().interpolateNoiseCube(startPos, endPos, bottomY, maxHeight));
                            }
                            NoiseColumn noiseColumn = range.getNoiseCube().get(offsetX).get(offsetZ);
                            carver.carveColumn(primer, colPos, topY, smoothAmp, noiseColumn, liquidBlock, flooded);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * @return frequency value for cavern region controller
     */
    private float calcCavernRegionSize(RegionSize cavernRegionSize, float cavernRegionCustomSize) {
        switch (cavernRegionSize) {
            case Small:
                return .01f;
            case Large:
                return .005f;
            case ExtraLarge:
                return .001f;
            case Custom:
                return cavernRegionCustomSize;
            default: // Medium
                return .007f;
        }
    }
}
