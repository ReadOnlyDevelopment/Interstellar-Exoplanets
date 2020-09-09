package net.romvoid95.common.world.biome.exo;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeForest;

public class BiomeForestedHills extends BiomeForest implements WorldGenConstants {

    public BiomeForestedHills(BiomeForest.Type type, String name) {
        super(type, new BiomeProperties(name).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()).setBaseHeight(Biomes.EXTREME_HILLS.getBaseHeight()).setHeightVariation(Biomes.EXTREME_HILLS.getHeightVariation()));
    }
}
