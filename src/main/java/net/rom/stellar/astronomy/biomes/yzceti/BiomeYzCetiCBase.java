package net.rom.stellar.astronomy.biomes.yzceti;

import java.util.Random;

import micdoodle8.mods.galacticraft.planets.venus.VenusBlocks;
import micdoodle8.mods.galacticraft.planets.venus.blocks.BlockBasicVenus;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.stellar.astronomy.biomes.BiomeSpace;
import net.rom.stellar.astronomy.biomes.yzceti.decorators.BiomeDecoratorYzCetiC;
import net.rom.stellar.astronomy.chunkproviders.ChunkProviderYzCetiC;
import net.rom.stellar.init.ExoPlanets;
import net.rom.stellar.init.ExoplanetsBlocks;

public abstract class BiomeYzCetiCBase extends BiomeSpace {

	protected static final IBlockState STONE = ExoplanetsBlocks.yzc_sedimentary.getDefaultState();
	protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
	protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
	protected static final IBlockState CONSTRUCT = ExoplanetsBlocks.tiles.getDefaultState();
	protected static final IBlockState DIRT = ExoplanetsBlocks.dirt.getDefaultState();
	protected static final IBlockState ICE = Blocks.ICE.getDefaultState();
	protected static final IBlockState WATER = Blocks.WATER.getDefaultState();

	protected static final int SEA_LEVEL = ChunkProviderYzCetiC.SEA_LEVEL;
	protected static final int SEA_FLOOR_LEVEL = 36;

	public BiomeDecoratorYzCetiC biomeDecor = this.getBiomeDecorator();

	public BiomeYzCetiCBase(String singleName, BiomeProperties props) {
		super(singleName, props);
		this.setTempCategory(TempCategory.MEDIUM);
		this.setStoneBlock(ExoplanetsBlocks.yzc_metamorphic);
		this.setPlanetForBiome(ExoPlanets.yzcetic);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new BiomeDecoratorYzCetiC();
	}

	protected BiomeDecoratorYzCetiC getBiomeDecorator() {
		return (BiomeDecoratorYzCetiC) this.decorator;
	}

	public final void generateYzCetiCTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z,
			double noiseVal) {
		int i = worldIn.getSeaLevel();
		IBlockState topBlock = this.topBlock;
		IBlockState fillerBlock = this.fillerBlock;
		IBlockState stoneBlock = VenusBlocks.venusBlock.getDefaultState().withProperty(BlockBasicVenus.BASIC_TYPE_VENUS,
				BlockBasicVenus.EnumBlockBasicVenus.ROCK_HARD);
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = z & 15;
		int i1 = x & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, Blocks.BEDROCK.getDefaultState());
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == VenusBlocks.venusBlock) {
					if (j == -1) {
						if (k <= 0) {
							topBlock = null;
							fillerBlock = stoneBlock;
						} else if (j1 >= i - 4 && j1 <= i + 1) {
							topBlock = this.topBlock;
							fillerBlock = this.fillerBlock;
						}

						j = k;

						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, topBlock);
						} else if (j1 < i - 7 - k) {
							topBlock = null;
							fillerBlock = stoneBlock;
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
					}
				}
			}
		}
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateYzCetiCTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}
}