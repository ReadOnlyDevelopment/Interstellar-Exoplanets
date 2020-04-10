package net.rom.stellar.astronomy.biomes.yzceti;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.stellar.astronomy.biomes.BiomeSpace;
import net.rom.stellar.astronomy.biomes.yzceti.decorators.BiomeDecoratorYzCetiC;
import net.rom.stellar.astronomy.enums.EnumBiomeType;
import net.rom.stellar.init.PlanetsRegister;
import net.rom.stellar.init.BlocksRegister;

public abstract class BiomeYzCetiCBase extends BiomeSpace {

	protected static final IBlockState STONE = BlocksRegister.yzc_sedimentary.getDefaultState();
	protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
	protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
	protected static final IBlockState CONSTRUCT = BlocksRegister.tiles.getDefaultState();
	protected static final IBlockState DIRT = BlocksRegister.dirt.getDefaultState();
	protected static final IBlockState ICE = Blocks.ICE.getDefaultState();
	protected static final IBlockState WATER = Blocks.WATER.getDefaultState();

	protected static final int SEA_LEVEL = 63;
	protected static final int SEA_FLOOR_LEVEL = 36;

	public BiomeDecoratorYzCetiC biomeDecor = this.getBiomeDecorator();

	public BiomeYzCetiCBase(String singleName, BiomeProperties props) {
		super(singleName, props);
		this.setTempCategory(TempCategory.MEDIUM);
		this.setStoneBlock(BlocksRegister.yzc_metamorphic);
		this.setPlanetForBiome(PlanetsRegister.yzcetic);
	}

	public final void generateYzCetiCTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z,
			double noiseVal) {
		int i = worldIn.getSeaLevel();
		float biomeHeight = this.getBiomeHeight();
		IBlockState topState = this.topBlock;
		IBlockState fillState = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		
		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 == 0) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
				if (this.getBiomeType() == EnumBiomeType.OCEAN) {
					if ((j1 < SEA_LEVEL) && (j1 > SEA_FLOOR_LEVEL)) {
						chunkPrimerIn.setBlockState(i1, j1, l, WATER);
					} else if (j1 < SEA_FLOOR_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, STONE);
					} else if (j1 == SEA_FLOOR_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, DIRT);
					} else if (j1 >= SEA_LEVEL) {
						chunkPrimerIn.setBlockState(i1, j1, l, AIR);
					}
				} else {
					if (iblockstate2.getMaterial() == Material.AIR) {
						j = -1;
					} else if (iblockstate2.getBlock() == BlocksRegister.yzc_metamorphic) {
						if (j == -1) {
							if (k <= 0) {
								topState = AIR;
								fillState = STONE;
							} else if (j1 >= i - 4 && j1 <= i + 1) {
								topState = this.topBlock;
								fillState = this.fillerBlock;
							}
							
							if (j1 < i && (topState == null || topState.getMaterial() == Material.AIR)) {
								topState = ICE;
							}
							
							j = k;
							
							if (j1 >= i - 1) {
								chunkPrimerIn.setBlockState(i1, j1, l, topState);
							} else if (j1 < i - 7 - k) {
								topState = AIR;
								fillState = STONE;
								chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
							} else {
								chunkPrimerIn.setBlockState(i1, j1, l, fillState);
							}
						} else if (j > 0) {
							--j;
							chunkPrimerIn.setBlockState(i1, j1, l, fillState);
						}
					}
				}
			}
		}
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateYzCetiCTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}
	
	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new BiomeDecoratorYzCetiC();
	}
	
	@Override
	public float getSpawningChance() {
		return 0.1F;
	}

	protected BiomeDecoratorYzCetiC getBiomeDecorator() {
		return (BiomeDecoratorYzCetiC) this.decorator;
	}
}