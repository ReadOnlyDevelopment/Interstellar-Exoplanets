package net.rom.exoplanets.astronomy.trappist1.c;

import java.util.List;

import asmodeuscore.core.astronomy.dimension.world.gen.ChunkProviderSpaceLakes;
import asmodeuscore.core.astronomy.dimension.world.gen.MapGenCaves;
import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.api.stellar.world.gen.MapGenExoCave;

public class ChunkProviderTrappist1c extends ChunkProviderSpaceLakes {
	
private List<MapGenBaseMeta> worldGenerators;
    
    //private final MapGenExoCave caveGenerator = new MapGenExoCave(GSBlocks.CERES_BLOCKS, 0, 1, 1);
//    
//    private IBlockState top = GSBlocks.CERES_BLOCKS.getDefaultState().withProperty(CeresBlocks.BASIC_TYPE, CeresBlocks.EnumCeresBlocks.CERES_DUNGEON_TOP);
//    private IBlockState floor = GSBlocks.CERES_BLOCKS.getDefaultState().withProperty(CeresBlocks.BASIC_TYPE, CeresBlocks.EnumCeresBlocks.CERES_DUNGEON_FLOOR);
//    private IBlockState bricks = GSBlocks.DUNGEON_BLOCKS.getDefaultState().withProperty(DungeonBlocks.BASIC_TYPE, DungeonBlocks.EnumDungeonBlocks.CERES_BRICKS);

	public ChunkProviderTrappist1c(World world, long seed, boolean flag) {
		super(world, seed, flag);
		// TODO Auto-generated constructor stub
	}  

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected BiomeDecoratorSpace getBiomeGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Biome[] getBiomesForGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onChunkProvider(int cX, int cZ, ChunkPrimer primer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPopulate(int x, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators() {
		return worldGenerators;
//		List<MapGenBaseMeta> generators = Lists.newArrayList();
//		generators.add(this.caveGenerator);
//		return generators;
	}

	@Override
	public double getHeightModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSmallFeatureHeightModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMountainHeightModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValleyHeightModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWaterLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canGenerateWaterBlock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canGenerateIceBlock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCraterProbability() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected BlockMetaPair getWaterBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BlockMetaPair getGrassBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BlockMetaPair getDirtBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BlockMetaPair getStoneBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean enableBiomeGenBaseBlock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected GenType getGenType() {
		// TODO Auto-generated method stub
		return null;
	}

}
