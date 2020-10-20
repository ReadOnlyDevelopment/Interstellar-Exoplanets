package net.romvoid95.common.astronomy.wolf1061.b;

import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

import net.romvoid95.common.world.chunk.ExoChunkProvider;
import net.romvoid95.common.world.mapgen.MapGenBaseMeta;

public class ChunkProviderWolfB extends ExoChunkProvider {

	public ChunkProviderWolfB (World world, long seed, boolean flag) {
		super(world, seed, flag);
		// TODO Auto-generated constructor stub
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
	protected List<MapGenBaseMeta> getWorldGenerators() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

}
