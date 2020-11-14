package net.romvoid95.space.astrogeneration.biome.preset;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class WE_Plains extends WE_Biome {
	
	private static String biomeName;
	
	protected WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();

	public WE_Plains() {
		super(new BiomeProperties(biomeName), new int[] { 0x00FF00, 0xEEDD44, 0x00FF00 });

		biomeMinValueOnMap = getMinMapValue();
		biomeMaxValueOnMap = getMaxMapValue();
		biomePersistence = getPersistance();
		biomeNumberOfOctaves = getOctaveCount();
		biomeScaleX = getScaleX();
		biomeScaleY = getScaleY();
		biomeSurfaceHeight = getSurfaceHeight();
		biomeInterpolateQuality = getInterpolateNum();

		// -//
		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome(World world, Random rand, int x, int z) {

	}

	/**
	 * @return the minMapValue
	 */
	abstract double getMinMapValue();

	/**
	 * @return the maxMapValue
	 */
	abstract double getMaxMapValue();

	/**
	 * @return the persistance
	 */
	abstract double getPersistance();

	/**
	 * @return the octaveCount
	 */
	abstract int getOctaveCount();

	/**
	 * @return the scaleX
	 */
	abstract double getScaleX();

	/**
	 * @return the scaleY
	 */
	abstract double getScaleY();

	/**
	 * @return the surfaceHeight
	 */
	abstract int getSurfaceHeight();

	/**
	 * @return the interpolateNum
	 */
	abstract int getInterpolateNum();
}
