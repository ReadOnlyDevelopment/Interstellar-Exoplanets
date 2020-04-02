package net.rom.stellar.astronomy.biomes.yzceti.decorators;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom.core.space.world.EnumOreGen;
import net.rom.stellar.astronomy.BiomeDecoratorExoplanet;
import net.rom.stellar.astronomy.world.WorldGenMinableExo;
import net.rom.stellar.init.ExoplanetsBlocks;

public class BiomeDecoratorYzCetiC extends BiomeDecoratorExoplanet {

	private WorldGenerator dirtGen;
	private WorldGenerator ironGen;
	private WorldGenerator goldGen;
	private WorldGenerator tinGen;
	private WorldGenerator copperGen;
	private WorldGenerator leadGen;
	private WorldGenerator aluminumGen;
	private WorldGenerator redstoneGen;
	private WorldGenerator diamondGen;
	private WorldGenerator emeraldGen;
	private WorldGenerator meteoricIronGen;

	public BiomeDecoratorYzCetiC() {
		this.dirtGen = new WorldGenMinableExo(ExoplanetsBlocks.dirt, ExoplanetsBlocks.yzc_metamorphic, EnumOreGen.DIRT);
//		this.tinGen = new WorldGenMinableZG(ExoplanetsBlocks.purgTinOre, ExoplanetsBlocks.purgStone, EnumOreGen.TIN);
//		this.copperGen = new WorldGenMinableZG(ExoplanetsBlocks.purgCopperOre, ExoplanetsBlocks.purgStone, EnumOreGen.COPPER);
//		this.ironGen = new WorldGenMinableZG(ExoplanetsBlocks.purgIronOre, ExoplanetsBlocks.purgStone, EnumOreGen.IRON);
//		this.goldGen = new WorldGenMinableZG(ExoplanetsBlocks.purgGoldOre, ExoplanetsBlocks.purgStone, EnumOreGen.GOLD);
//		this.leadGen = new WorldGenMinableZG(ExoplanetsBlocks.purgLeadOre, ExoplanetsBlocks.purgStone, EnumOreGen.LEAD);
//		this.aluminumGen = new WorldGenMinableZG(ExoplanetsBlocks.purgAluminumOre, ExoplanetsBlocks.purgStone, EnumOreGen.ALUMINUM);
//		this.redstoneGen = new WorldGenMinableZG(ExoplanetsBlocks.purgRedstoneOre, ExoplanetsBlocks.purgStone, EnumOreGen.REDSTONE);
//		this.diamondGen = new WorldGenMinableZG(ExoplanetsBlocks.purgDiamondOre, ExoplanetsBlocks.purgStone, EnumOreGen.DIAMOND);
//		this.emeraldGen = new WorldGenMinableZG(ExoplanetsBlocks.purgEmeraldOre, ExoplanetsBlocks.purgStone, EnumOreGen.EMERALD);
//		this.meteoricIronGen = new WorldGenMinableZG(ExoplanetsBlocks.purgMeteoricIronOre, ExoplanetsBlocks.purgStone,
//				EnumOreGen.METEORIC_IRON);
	}

	@Override
	protected void generate(Biome biome, World world, Random rand) {
		int x = rand.nextInt(16) + 8;
		int z = rand.nextInt(16) + 8;

//		this.generateOre(this.dirtGen, EnumOreGen.DIRT, world, rand);
//		this.generateOre(this.ironGen, EnumOreGen.IRON, world, rand);
//		this.generateOre(this.goldGen, EnumOreGen.GOLD, world, rand);
//		this.generateOre(this.tinGen, EnumOreGen.TIN, world, rand);
//		this.generateOre(this.copperGen, EnumOreGen.COPPER, world, rand);
//		this.generateOre(this.leadGen, EnumOreGen.LEAD, world, rand);
//		this.generateOre(this.aluminumGen, EnumOreGen.ALUMINUM, world, rand);
//		this.generateOre(this.redstoneGen, EnumOreGen.REDSTONE, world, rand);
//		this.generateOre(this.diamondGen, EnumOreGen.DIAMOND, world, rand);
//		this.generateOre(this.emeraldGen, EnumOreGen.EMERALD, world, rand);
//		this.generateOre(this.meteoricIronGen, EnumOreGen.METEORIC_IRON, world, rand);
	}
}