package net.rom.stellar.astronomy.biomes.yzceti.decorators;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom.stellar.astronomy.BiomeDecoratorExoplanet;
import net.rom.stellar.init.ExoplanetsBlocks;

public class BiomeDecoratorYzCetiB extends BiomeDecoratorExoplanet {
	
	private static final Block STONE = ExoplanetsBlocks.yzb_ingneous;
	
	private WorldGenerator amaranthGen;
	private WorldGenerator redstoneGen;
	private WorldGenerator tinGen;
	private WorldGenerator diamondGen;
	private WorldGenerator zollerniumGen;
	private WorldGenerator zincGen;
	private WorldGenerator constructGen;
	private WorldGenerator goldenConstructGen;
	
	public boolean generateLakes = true;
	
	public int whiteLavaLakesPerChunk = 1;
	
	public BiomeDecoratorYzCetiB() {
//		this.amaranthGen = new WorldGenMinableZG(ExoplanetsBlocks.atheonAmaranthOre, STONE, EnumOreGen.AMARANTH);
//		this.redstoneGen = new WorldGenMinableZG(ExoplanetsBlocks.atheonRedstoneOre, STONE, EnumOreGen.REDSTONE);
//		this.tinGen = new WorldGenMinableZG(ExoplanetsBlocks.atheonTinOre, STONE, EnumOreGen.TIN);
//		this.diamondGen = new WorldGenMinableZG(ExoplanetsBlocks.atheonDiamondOre, STONE, EnumOreGen.DIAMOND);
//		this.zollerniumGen = new WorldGenMinableZG(ExoplanetsBlocks.atheonZollerniumOre, STONE, EnumOreGen.ZOLLERNIUM);
//		this.zincGen = new WorldGenMinableZG(ExoplanetsBlocks.atheonZincOre, STONE, EnumOreGen.ZINC);
//		this.constructGen = new WorldGenMinableZG(ExoplanetsBlocks.xantheonConstructBlock, STONE, EnumOreGen.CONSTRUCTED);
//		this.goldenConstructGen = new WorldGenMinableZG(ExoplanetsBlocks.atheonConstructBlock, STONE,
//				EnumOreGen.CONSTRUCTED.setBlockCount(20));
	}
	
	@Override
	protected void generate(Biome biome, World world, Random rand) {
//		int x = rand.nextInt(16) + 8;
//		int z = rand.nextInt(16) + 8;
//		
//		this.generateOre(this.amaranthGen, EnumOreGen.AMARANTH, world, rand);
//		this.generateOre(this.redstoneGen, EnumOreGen.REDSTONE, world, rand);
//		this.generateOre(this.tinGen, EnumOreGen.TIN, world, rand);
//		this.generateOre(this.diamondGen, EnumOreGen.DIAMOND, world, rand);
//		this.generateOre(this.zollerniumGen, EnumOreGen.ZOLLERNIUM, world, rand);
//		this.generateOre(this.zincGen, EnumOreGen.ZINC, world, rand);
//		this.generateOre(this.constructGen, EnumOreGen.CONSTRUCTED, world, rand);
//		this.generateOre(this.goldenConstructGen, EnumOreGen.CONSTRUCTED, world, rand);
//		
//		int genY = 248;
//		int y = genY;
//		
//		if (this.generateLakes && this.whiteLavaLakesPerChunk > 0) {
//			for (int i = 0; i < this.whiteLavaLakesPerChunk; ++i) {
//				y = rand.nextInt(rand.nextInt(genY) + 8);
//				
//				if (rand.nextInt(130) <= 10) {
//					if (y <= 72) {
//						(new WorldGenLakesExo(ZGFluids.blockWhiteLavaFluid, STONE)).generate(world, rand,
//								this.chunkPos.add(x, y, z));
//					}
//				}
//			}
//		}
	}
}