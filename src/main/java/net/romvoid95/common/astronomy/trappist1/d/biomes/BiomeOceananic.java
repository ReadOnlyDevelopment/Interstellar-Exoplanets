package net.romvoid95.common.astronomy.trappist1.d.biomes;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.romvoid95.core.States;

public class BiomeOceananic extends WE_Biome {

	public BiomeOceananic() {
		super(new BiomeProperties("trappist1d_deepocean"), new int[] { 0x89AC76, 0x116644, 0x985cff });

		biomeMinValueOnMap      = -0.4D;
		biomeMaxValueOnMap      = 0.0D;
		biomePersistence        = 2.4D;
		biomeNumberOfOctaves    = 4;
		biomeScaleX             = 480.0D;
		biomeScaleY             = 2.7D;
		biomeSurfaceHeight      = 35;
		biomeInterpolateQuality = 65;
		biomeTemerature         = 0.0F;

		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(States.TRAP1D_OCEANFLOOR, 53, 0, 31, 0, true);
		standardBiomeLayers.add(States.TRAP1D_DIAMOND, 30, 0, 2, 0, true);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

}
