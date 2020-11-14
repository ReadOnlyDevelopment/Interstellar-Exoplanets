package net.romvoid95.space.kepler1649.b;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.romvoid95.space.astrogeneration.chunkgen.ExoChunkProviderExoplanet;
import net.romvoid95.space.kepler1649.KeplerBlocks;

public class ChunkProviderKepler1649B extends ExoChunkProviderExoplanet {
	
	private static ChunkProviderKepler1649B instance;

	public ChunkProviderKepler1649B(World worldIn, long seed, boolean mapFeaturesEnabledIn) {
		super(worldIn, seed, mapFeaturesEnabledIn);
		this.waterBlock = Blocks.AIR.getDefaultState();
		this.stoneBlock = KeplerBlocks.Kepler1649B.KEPLERB_STONE.getDefaultState();
		instance = this;
	}
	
	public static ChunkProviderKepler1649B instance() {
		return instance;
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void decoratePlanet(World world, Random rand, int x, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onChunkProvide(int cX, int cZ, ChunkPrimer primer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getCraterProbability() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onPopulate(int cX, int cZ) {
		// TODO Auto-generated method stub
		
	}

}
