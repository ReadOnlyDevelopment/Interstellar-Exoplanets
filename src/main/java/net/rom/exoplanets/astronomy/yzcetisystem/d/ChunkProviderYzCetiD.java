package net.rom.exoplanets.astronomy.yzcetisystem.d;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.api.world.chunk.ExoChunkProviderMultiSpace;
import net.rom.api.world.gen.MapGenBaseMeta;
import net.rom.api.world.gen.MapGenExoCaveGen;
import net.rom.api.world.gen.MapGenExoRavinGen;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;

public class ChunkProviderYzCetiD extends ExoChunkProviderMultiSpace {
	
	private final MapGenExoCaveGen caveGenerator = new MapGenExoCaveGen(YzCetiBlocks.CetiD.D_IGNEOUS, 0, 1, 2);
	private final MapGenExoRavinGen ravineGenerator = new MapGenExoRavinGen();

	public ChunkProviderYzCetiD(World world, long seed, boolean flag) {
		super(world, seed, flag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {
	}

	@Override
	protected void decoratePlanet(World world, Random rand, int x, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators() {
		List<MapGenBaseMeta> generators = Lists.newArrayList();
		generators.add(this.caveGenerator);
		return generators;
	}

	@Override
	protected void onChunkProvide(int cX, int cZ, ChunkPrimer primer) {
		this.ravineGenerator.generate(this.worldObj, cX, cZ, primer);
		
	}

	@Override
	protected int getCraterProbability() {
		return 500;
	}

	@Override
	public void onPopulate(int cX, int cZ) {		
	}


}