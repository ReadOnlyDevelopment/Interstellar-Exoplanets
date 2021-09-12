package com.readonlydev.space.kepler1649.c;

import com.readonlydev.core.registries.ExoplanetBlocks;
import com.readonlydev.space.generation.chunkgen.ChunkGeneratorSimplex;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkProviderKepler1649C extends ChunkGeneratorSimplex {

	public ChunkProviderKepler1649C(World world, long seed, boolean flag) {
		super(world, seed, flag);
		this.planetGroundVariant1 = ExoplanetBlocks.KEPLERC_VARIANT1.getDefaultState();
		this.planetGroundVariant2 = ExoplanetBlocks.KEPLERC_VARIANT2.getDefaultState();
		this.useSnowWithLayers = false;
	}

	@Override
	public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
	}

	@Override
	protected IBlockState getPlanetIceBlock() {
		return null;
	}

	@Override
	protected IBlockState getPlanetWaterBlock() {
		return Blocks.WATER.getDefaultState();
	}

	@Override
	protected IBlockState getPlanetCobblestoneBlock() {
		return ExoplanetBlocks.KEPLERC_COBBLESTONE.getDefaultState();
	}

	@Override
	protected IBlockState getPlanetGravelBlock() {
		return ExoplanetBlocks.KEPLERC_GRAVEL.getDefaultState();
	}

	@Override
	protected IBlockState getPlanetDirtBlock() {
		return ExoplanetBlocks.KEPLERC_DIRT.getDefaultState();
	}

	@Override
	protected IBlockState getPlanetGrassBlock() {
		return ExoplanetBlocks.KEPLERC_GRASS.getDefaultState();
	}

	@Override
	protected IBlockState getPlanetStoneBlock() {
		return ExoplanetBlocks.KEPLERC_STONE.getDefaultState();
	}

}
