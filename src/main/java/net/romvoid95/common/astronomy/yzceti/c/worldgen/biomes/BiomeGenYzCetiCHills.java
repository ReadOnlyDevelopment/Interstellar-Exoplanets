package net.romvoid95.common.astronomy.yzceti.c.worldgen.biomes;

import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;
import net.romvoid95.common.astronomy.yzceti.c.worldgen.YzCetiCBiomes;
import net.romvoid95.common.world.biome.properties.BiomeData;

public class BiomeGenYzCetiCHills extends YzCetiCBiomes {

	public BiomeGenYzCetiCHills(String name, float height, float variation) {
		super(new BiomeData.BiomeDataBuilder()
				.biomeName(name)
				.rainfall(0F)
				.rainEnabled(false)
				.baseHeight(height)
				.heightVariation(variation));
		this.topBlock = YzCetiBlocks.C.YZC_METAMORPHIC.getDefaultState();
		this.fillerBlock = YzCetiBlocks.C.YZC_IGNEOUS.getDefaultState();
	}
}
