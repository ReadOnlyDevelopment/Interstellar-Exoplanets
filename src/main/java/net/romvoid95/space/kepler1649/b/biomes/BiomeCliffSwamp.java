package net.romvoid95.space.kepler1649.b.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.world.worldgen.feature.tree.ExoGenSwampTree;
import net.romvoid95.common.block.terrain.foliage.BlockDoubleFoliage;
import net.romvoid95.core.ExoBlocks;
import net.romvoid95.space.astrogeneration.biome.BiomeData.DataValues;
import net.romvoid95.space.astrogeneration.feature.foliage.WorldGenModFoliage;

/**
 * 
 * Biome for the lower section of the cliff dimension
 *
 */
public class BiomeCliffSwamp extends Kepler1649B_Biomes {

	public BiomeCliffSwamp() {
		super(new DataValues("Kepler1649 B Valley")
        		.temperature(0.2F)
        		.baseHeight(-1.56F)
        		.heightVariation(0.15F)
        		.waterColor(4864285)
        		.finalzie());
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.DIRT.getDefaultState();
		this.decorator.treesPerChunk = 8;
		this.decorator.flowersPerChunk = 1;
		this.decorator.deadBushPerChunk = 1;
		this.decorator.mushroomsPerChunk = 8;
		this.decorator.bigMushroomsPerChunk = 1;
		this.decorator.reedsPerChunk = 10;
		this.decorator.clayPerChunk = 1;
		this.decorator.waterlilyPerChunk = 4;
		this.decorator.sandPatchesPerChunk = 0;
		this.decorator.gravelPatchesPerChunk = 0;
		this.decorator.grassPerChunk = 8;
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		//WorldGenAbstractTree jungleTree = new WorldGenTrees(false, 4 + rand.nextInt(7), log, leaf, true);
		//WorldGenAbstractTree bigJungleTree = new WorldGenMegaJungle(false, 8, 18, log, leaf);
		WorldGenAbstractTree swampTree = new ExoGenSwampTree(true, 8);

		if (rand.nextFloat() > 0.96) {
			return swampTree;
		} else if (rand.nextFloat() > 0.8) {
			if (rand.nextInt() == 2) {
				new WorldGenShrub(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState());
			}
			return new WorldGenShrub(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState());
		} else if (rand.nextFloat() > 0.8) {
			return new WorldGenBigTree(false);
		} else if (rand.nextFloat() > 0.8) {
			return new WorldGenCanopyTree(false);
		}
		return swampTree;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		if (rand.nextInt(3) == 0) {
			return new WorldGenModFoliage(new BlockDoubleFoliage[] { (BlockDoubleFoliage) ExoBlocks.EXO_TALL_PLANT}, 128);
		}
		return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN)
				: new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

		for (int i = 0; i < 7; ++i) {
			int j = rand.nextInt(16) + 8;
			int k = rand.nextInt(16) + 8;
			int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
			DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
		}
		super.decorate(worldIn, rand, pos);
	}
	
	

	@Override
	public void generateTopBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal,
			Block stoneBlock) {
		double d0 = GRASS_COLOR_NOISE.getValue(x * 0.25D, z * 0.25D);

		if (d0 > 0.0D) {
			int i = x & 15;
			int j = z & 15;

			for (int k = 255; k >= 0; --k) {
				if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR) {
					if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER) {
						chunkPrimerIn.setBlockState(j, k, i, WATER);

						if (d0 < 0.12D) {
							chunkPrimerIn.setBlockState(j, k + 1, i, Blocks.WATERLILY.getDefaultState());
						}
					}

					break;
				}
			}
		}

		super.generateTopBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal, stoneBlock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		double d0 = GRASS_COLOR_NOISE.getValue(pos.getX() * 0.0225D, pos.getZ() * 0.0225D);
		return d0 < -0.1D ? 4605755 : 5325610;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return 6975545;
	}
}
