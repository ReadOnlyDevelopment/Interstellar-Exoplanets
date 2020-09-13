package net.romvoid95.common.astronomy.trappist1.d.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.romvoid95.core.States;

public class BiomeOceananic extends WE_Biome {

	public BiomeOceananic(double min, double max) {
		super(new BiomeProperties("trappist1d_deepocean"), new int[] { 0x89AC76, 0x116644, 0x985cff });

		biomeMinValueOnMap      = min;
		biomeMaxValueOnMap      = max;
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
		standardBiomeLayers.add(States.TRAP1D_OCEANFLOOR, 50, 0, 31, 0, true);
		standardBiomeLayers.add(States.TRAP1D_DIAMOND, 30, 0, 2, 0, true);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}
}
