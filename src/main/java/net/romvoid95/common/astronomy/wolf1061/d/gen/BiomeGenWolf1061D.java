package net.romvoid95.common.astronomy.wolf1061.d.gen;

import net.romvoid95.common.astronomy.wolf1061.d.Wolf1061D_Biomes;
import net.romvoid95.common.world.biome.properties.BiomeData.BiomeDataBuilder;

public class BiomeGenWolf1061D extends Wolf1061D_Biomes {

	public BiomeGenWolf1061D (String name) {
		super(new BiomeDataBuilder()
				.biomeName(name)
				.baseHeight(0.0f)
				.heightVariation(0.0f));
	}
}
