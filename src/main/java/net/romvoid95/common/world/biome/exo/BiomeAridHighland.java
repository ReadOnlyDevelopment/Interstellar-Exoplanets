package net.romvoid95.common.world.biome.exo;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenFossils;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.romvoid95.common.world.biome.BiomeSpace;

public class BiomeAridHighland extends BiomeSpace implements WorldGenConstants {

	public static BiomeProperties properties = new BiomeProperties("Arid Highland");

	public BiomeAridHighland() {
		super(properties);
		this.topBlock                   = SAND;
		this.fillerBlock                = SAND;
		this.decorator.treesPerChunk    = 1;
		this.decorator.deadBushPerChunk = 2;
		this.decorator.reedsPerChunk    = 50;
		this.decorator.cactiPerChunk    = 10;
		properties.setTemperature(Biomes.DESERT.getDefaultTemperature());
		properties.setRainfall(Biomes.DESERT.getRainfall());
		properties.setRainDisabled();
		properties.setBaseHeight(1.3F);
		properties.setHeightVariation(0.4F);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature (Random rand) {
		if (rand.nextInt(3) == 0) {
			return TREE_FEATURE;
		}
		return ACACIA_TREE_FEATURE;
	}

	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (noiseVal > 1D) {
			this.topBlock    = GRASS;
			this.fillerBlock = SAND;
		}
		else {
			this.topBlock    = SAND;
			this.fillerBlock = SAND;
		}

		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public int getSkyColorByTemp (float currentTemperature) {
		return Biomes.DESERT.getSkyColorByTemp(currentTemperature);
	}

	@Override
	public int getModdedBiomeGrassColor (int original) {
		return super.getModdedBiomeGrassColor(0xFFBACD78);
	}

	@Override
	public int getModdedBiomeFoliageColor (int original) {
		return super.getModdedBiomeFoliageColor(0xFF80A02E);
	}

	@Override
	public void decorate (World worldIn, Random rand, BlockPos pos) {

		if (TerrainGen.decorate(worldIn, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.DESERT_WELL))
			if (rand.nextInt(1000) == 0) {
				int      i        = rand.nextInt(16) + 8;
				int      j        = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
				(new WorldGenDesertWells()).generate(worldIn, rand, blockpos);
			}

		if (TerrainGen.decorate(worldIn, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.FOSSIL))
			if (rand.nextInt(64) == 0) {
				(new WorldGenFossils()).generate(worldIn, rand, pos);
			}
		if (TerrainGen.decorate(worldIn, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.TREE)) {
			if (rand.nextInt(5) == 0) {
				int      k6       = rand.nextInt(16) + 8;
				int      l        = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				OAK_SHRUB_FEATURE.generate(worldIn, rand, blockpos);
			}
		}

		super.decorate(worldIn, rand, pos);
	}
}
