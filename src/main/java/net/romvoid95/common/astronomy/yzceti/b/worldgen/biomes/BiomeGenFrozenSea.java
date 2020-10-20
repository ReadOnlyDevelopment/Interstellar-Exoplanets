package net.romvoid95.common.astronomy.yzceti.b.worldgen.biomes;

import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;
import net.romvoid95.common.astronomy.yzceti.b.worldgen.YzCetiBBiomes;
import net.romvoid95.common.world.biome.properties.BiomeData;

public class BiomeGenFrozenSea extends YzCetiBBiomes {

	public BiomeGenFrozenSea (String name, float height, float variation) {
		super(new BiomeData.BiomeDataBuilder()
				.biomeName(name)
				.rainfall(0F)
				.rainEnabled(false)
				.baseHeight(height)
				.heightVariation(variation));
		this.topBlock = YzCetiBlocks.B.YZB_METAMORPHIC.getDefaultState();
		this.fillerBlock = YzCetiBlocks.B.YZB_IGNEOUS.getDefaultState();
	}
}
