package net.rom95.common.astronomy.trappist1.d.biomes;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;

public class BiomeOceananic extends WE_Biome {

	public BiomeOceananic() {
		super(new BiomeProperties("trappist1d_deepocean"), new int[] { 0x89AC76, 0x116644, 0x985cff });

		biomeMinValueOnMap      = -5.0;
		biomeMaxValueOnMap      = 5.0;
		biomePersistence        = 1.4D;
		biomeNumberOfOctaves    = 4;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 35;
		biomeInterpolateQuality = 65;
		biomeTemerature         = 0.0F;

		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(Blocks.DIAMOND_ORE.getDefaultState(), 53, 0, 31, 0, true);
		standardBiomeLayers.add(Blocks.DIAMOND_BLOCK.getDefaultState(), 30, 0, 2, 0, true);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

}
