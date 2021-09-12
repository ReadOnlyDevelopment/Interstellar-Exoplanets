package com.readonlydev.space.wolf1061.d.gen;

import com.readonlydev.api.world.ExoBiomes;
import com.readonlydev.space.generation.BiomeDecoratorExoBase;

import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkProviderWolf1061D extends ChunkProviderBedrock {
	
	private final BiomeDecoratorWolf1061D biomeDecorator = new BiomeDecoratorWolf1061D();

	public ChunkProviderWolf1061D (World par1World, long seed, boolean mapFeaturesEnabled) {
		super(par1World, seed, mapFeaturesEnabled);
	}

	@Override
	protected BiomeDecoratorExoBase getBiomeGenerator() {
		return this.biomeDecorator;
	}

	@Override
	protected Biome[] getBiomesForGeneration() {
		return new Biome[] { ExoBiomes.WOLF1061D_ATMOSPHERE };
	}

	@Override
	protected int getSeaLevel() {
		return 64;
	}

	@Override
	protected BlockMetaPair getGrassBlock() {
		return new BlockMetaPair(Blocks.AIR, (byte) 1);
	}

	@Override
	protected BlockMetaPair getDirtBlock() {
		return new BlockMetaPair(Blocks.AIR, (byte) 1);
	}

	@Override
	protected BlockMetaPair getStoneBlock() {
		return new BlockMetaPair(Blocks.AIR, (byte) 1);
	}

	@Override
	public double getHeightModifier() {
		return 12;
	}

	@Override
	public double getSmallFeatureHeightModifier() {
		return 26;
	}

	@Override
	public double getMountainHeightModifier() {
		return 95;
	}

	@Override
	public double getValleyHeightModifier() {
		return 50;
	}

	@Override
	public void onChunkProvide(int cX, int cZ, ChunkPrimer primer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPopulate(int cX, int cZ) {
		// TODO Auto-generated method stub
		
	}

}
