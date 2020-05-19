package net.rom.exoplanets.astronomy.trappist1.c.chunk;

import java.util.List;

import com.google.common.collect.Lists;

import asmodeuscore.core.astronomy.dimension.world.gen.ChunkProviderSpaceLakes;
import asmodeuscore.core.astronomy.dimension.world.gen.MapGenCaves;
import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;
import net.rom.exoplanets.astronomy.trappist1.c.BiomeDecoratorTrappist1C;

public class ChunkProviderTrappist1C extends ChunkProviderSpaceLakes {

	private final MapGenCaves caveGenerator = new MapGenCaves(TrappistBlocks.SharedTerrain.HOT_GROUND_1, 0, 0, 0);

	public ChunkProviderTrappist1C(World world, long seed, boolean flag) {
		super(world, seed, flag);
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub

	}

	@Override
	protected BiomeDecoratorSpace getBiomeGenerator() {
		return new BiomeDecoratorTrappist1C();
	}

	@Override
	protected Biome[] getBiomesForGeneration() {
		return new Biome[] { BiomeAdaptive.biomeDefault };
	}

	@Override
	public void onChunkProvider(int cX, int cZ, ChunkPrimer primer) {

	}

	@Override
	public void onPopulate(int x, int z) {

	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators() {
		List<MapGenBaseMeta> generators = Lists.newArrayList();
		generators.add(this.caveGenerator);
		return generators;
	}

	@Override
	public double getHeightModifier() {
		return 15;
	}

	@Override
	public double getSmallFeatureHeightModifier() {
		return 1;
	}

	@Override
	public double getMountainHeightModifier() {
		return 0;
	}

	@Override
	public double getValleyHeightModifier() {
		return 0;
	}

	@Override
	public int getWaterLevel() {
		return 80;
	}

	@Override
	public boolean canGenerateWaterBlock() {
		return false;
	}

	@Override
	public boolean canGenerateIceBlock() {
		return false;
	}

	@Override
	public int getCraterProbability() {
		return 0;
	}

	@Override
	protected BlockMetaPair getGrassBlock() {
		return null;
	}

	@Override
	protected BlockMetaPair getDirtBlock() {
		return null;
	}

	@Override
	protected BlockMetaPair getStoneBlock() {
		return new BlockMetaPair(TrappistBlocks.SharedTerrain.HOT_GROUND_1, (byte) 0);
	}

	@Override
	protected boolean enableBiomeGenBaseBlock() {
		return false;
	}

	@Override
	protected GenType getGenType() {
		return GenType.VANILLA;
	}

	@Override
	protected BlockMetaPair getWaterBlock() {
		return null;
	}

}
