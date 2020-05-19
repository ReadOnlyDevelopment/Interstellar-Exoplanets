package net.rom.exoplanets.astronomy.trappist1.c.worldegnine;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;

public class Trappist1C_Mountains extends WE_Biome {

	public Trappist1C_Mountains(double min, double max, int height, double per, int octaves) {
		super(new BiomeProperties("trappist1_c_mountains" + height), new int[] {0x55BB44, 0x44FFAA, 0x00FF00});
		biomeMinValueOnMap      =  	   min;
		biomeMaxValueOnMap      =      max;
		biomePersistence        =     per;
		biomeNumberOfOctaves    =        octaves;
		biomeScaleX             =   280.0D;
		biomeScaleY             =     1.7D;
		biomeSurfaceHeight      =       height;
		biomeInterpolateQuality =       25;
		
		//-//
		decorateChunkGen_List.clear();		
		createChunkGen_InXZ_List.clear();
		
		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(TrappistBlocks.TrappistC.T1C_TOP.getDefaultState(), TrappistBlocks.TrappistC.T1C_Dirt_1.getDefaultState(), -256, 0,   -5, -1,  true);
		standardBiomeLayers.add(TrappistBlocks.SharedTerrain.HOT_GROUND_1.getDefaultState(), TrappistBlocks.TrappistC.T1C_Dirt_1.getDefaultState(), -256, 35, -256,  0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2,  0,  0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

}
