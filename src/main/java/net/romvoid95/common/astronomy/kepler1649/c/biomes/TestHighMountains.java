package net.romvoid95.common.astronomy.kepler1649.c.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.romvoid95.core.ExoBlock;

public class TestHighMountains extends WE_Biome {

	public TestHighMountains(double min, double max) {
		super(new BiomeProperties("test_high_mountains"), new int[] {
			0x00CC00, 0xFFFFFF, 0x00CC00
		});

		biomeMinValueOnMap      = min;
		biomeMaxValueOnMap      = max;
		biomePersistence        = 1.8D;
		biomeNumberOfOctaves    = 2;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 4;
		biomeInterpolateQuality = 1;

		//-//
		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(ExoBlock.TRAP1E_GRASS, ExoBlock.TRAP1E_STONE, -256, 0, -256, 0, false);
		standardBiomeLayers.add(ExoBlock.TRAP1E_DIRT, ExoBlock.TRAP1E_GRASS, -256, 0, -256, 0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}
}
