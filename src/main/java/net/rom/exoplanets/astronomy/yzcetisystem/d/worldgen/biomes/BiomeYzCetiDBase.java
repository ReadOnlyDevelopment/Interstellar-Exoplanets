//package net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.biomes;
//
//import java.util.Random;
//
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyBool;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.passive.EntitySquid;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.BiomeDecorator;
//import net.minecraft.world.chunk.ChunkPrimer;
//import net.rom.api.enums.EnumBiomeType;
//import net.rom.api.world.biome.BiomeSpace;
//import net.rom.exoplanets.astronomy.yzcetisystem.d.ChunkProviderYzCetiD;
//import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.BiomeDecoratorYzCetiD;
//import net.rom.exoplanets.init.BlocksRegister;
//import net.rom.exoplanets.init.PlanetsRegister;
//import net.rom.exoplanets.util.MathUtil;
//
//public class BiomeYzCetiDBase extends BiomeSpace {
//	
//	protected static final IBlockState STONE = BlocksRegister.altumStone.getDefaultState();
//	protected static final IBlockState ROCK = BlocksRegister.altumRock.getDefaultState();
//	protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
//	protected static final IBlockState GRAVEL = BlocksRegister.altumGravel.getDefaultState();
//	protected static final IBlockState DIRT = BlocksRegister.altumDirt.getDefaultState();
//	
//	protected static final int SEA_LEVEL = ChunkProviderYzCetiD.SEA_LEVEL;
//	protected static final int SEA_FLOOR_LEVEL = ChunkProviderYzCetiD.SEA_FLOOR_LEVEL;
//	
//	protected static final PropertyBool WET = PropertyBool.create("wet");
//	public BiomeDecoratorYzCetiD biomeDecor = this.getBiomeDecorator();
//	
//	public BiomeYzCetiDBase(String singleName, BiomeProperties props) {
//		super(singleName, props);
//		props.setBaseHeight(0.5F);
//		props.setHeightVariation(0.6F);
//		this.setTempCategory(TempCategory.MEDIUM);
//		this.setBiomeHeight(45);
//		this.setTemp(84.23F);
//		this.waterColor = 0x00008b;
//		this.setPlanetForBiome(PlanetsRegister.yzcetid);
//	}
//	
//	public final void generateTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
//		int i = worldIn.getSeaLevel();
//		float biomeHeight = this.getBiomeHeight();
//		IBlockState topState = this.topBlock;
//		IBlockState fillState = this.fillerBlock;
//		int j = -1;
//		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
//		int z2 = x & 15;
//		int x2 = z & 15;
//		
//		for (int y = 255; y >= 0; --y) {
//			if (y == 0) {
//				chunkPrimerIn.setBlockState(x2, y, z2, BEDROCK);
//			} else {
//				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(x2, y, z2);
//				if (this.getBiomeType() == EnumBiomeType.OCEAN) {
//					topState = SAND;
//					fillState = ROCK;
//					if ((y < SEA_LEVEL) && (y > SEA_FLOOR_LEVEL)) {
//						chunkPrimerIn.setBlockState(x2, y, z2, WATER);
//					} else if (y < SEA_FLOOR_LEVEL) {
//						chunkPrimerIn.setBlockState(x2, y, z2, STONE);
//					} else if (y == SEA_FLOOR_LEVEL) {
//						if (rand.nextInt(10) == 2) {
//							chunkPrimerIn.setBlockState(x2, (y + 1), (z2 + 1), GRAVEL);
//							if (rand.nextInt(5) == 2) {
//								chunkPrimerIn.setBlockState(x2, (y + 2), (z2 + 1), GRAVEL);
//							}
//						}
//						if (rand.nextInt(300) == 3) {
//							for (int i2 = 0; i2 < MathUtil.randomInt(1, 5); i2++) {
//								IBlockState blockToUse = (MathUtil.getRNG().nextInt(5) == 1) ? DIRT : STONE;
//								chunkPrimerIn.setBlockState(x2, (y + i2), z2, blockToUse);
//							}
//						} else {
//							chunkPrimerIn.setBlockState(x2, y, z2, DIRT);
//						}
//					} else if (y >= SEA_LEVEL) {
//						chunkPrimerIn.setBlockState(x2, y, z2, AIR);
//					}
//				} else {
//					if (iblockstate2.getMaterial() == Material.AIR) {
//						j = -1;
//					} else if (iblockstate2.getBlock() == BlocksRegister.yzd_stone) {
//						if (j == -1) {
//							if (k <= 0) {
//								topState = AIR;
//								fillState = STONE;
//							} else if (y >= i - 4 && y <= i + 1) {
//								topState = this.topBlock;
//								fillState = this.fillerBlock;
//							}
//							
//							if (y < i && (topState == null || topState.getMaterial() == Material.AIR)) {
//								topState = WATER;
//							}
//							
//							j = k;
//							
//							if (y >= i - 1) {
//								chunkPrimerIn.setBlockState(x2, y, z2, topState);
//							} else if (y < i - 7 - k) {
//								topState = AIR;
//								fillState = STONE;
//								chunkPrimerIn.setBlockState(x2, y, z2, GRAVEL);
//							} else {
//								chunkPrimerIn.setBlockState(x2, y, z2, fillState);
//							}
//						} else if (j > 0) {
//							--j;
//							chunkPrimerIn.setBlockState(x2, y, z2, fillState);
//						}
//					}
//				}
//			}
//		}
//	}
//	
//	@Override
//	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
//		this.generateTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
//	}
//	
//	@Override
//	public float getSpawningChance() {
//		return 0.1F;
//	}
//	
//	@Override
//	public BiomeDecorator createBiomeDecorator() {
//		return new BiomeDecoratorYzCetiD();
//	}
//	
//	protected BiomeDecoratorYzCetiD getBiomeDecorator() {
//		return (BiomeDecoratorYzCetiD) this.decorator;
//	}
//}
