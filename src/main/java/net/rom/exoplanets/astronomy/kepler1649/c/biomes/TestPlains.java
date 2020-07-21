package net.rom.exoplanets.astronomy.kepler1649.c.biomes;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.rom.exoplanets.astronomy.kepler1649.KeplerBlocks;

public class TestPlains extends WE_Biome {

	public TestPlains(double min, double max) {
		super(new BiomeProperties("test_plains"), new int[] { 0x00CC00, 0xFFFFFF, 0x00CC00 });

		biomeMinValueOnMap = min;
		biomeMaxValueOnMap = max;
		biomePersistence = 1.5D;
		biomeNumberOfOctaves = 4;
		biomeScaleX = 280.0D;
		biomeScaleY = 1.7D;
		biomeSurfaceHeight = 80;
		biomeInterpolateQuality = 30;

		// -//
		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(KeplerBlocks.Kepler1649C.kepler_surface.getDefaultState(),
				KeplerBlocks.Kepler1649C.kepler_stone.getDefaultState(), -256, 0, -5, -1, true);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

}
