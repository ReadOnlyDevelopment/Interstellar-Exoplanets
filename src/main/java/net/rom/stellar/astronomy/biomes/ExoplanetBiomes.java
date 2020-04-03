package net.rom.stellar.astronomy.biomes;

import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.rom.stellar.astronomy.biomes.yzceti.BiomeYzCetiB;
import net.rom.stellar.astronomy.biomes.yzceti.BiomeYzCetiBDirty;
import net.rom.stellar.astronomy.biomes.yzceti.BiomeYzCetiC;
import net.rom.stellar.astronomy.biomes.yzceti.BiomeYzCetiCUnknown;

public class ExoplanetBiomes {

	public static final List<BiomeGenBaseGC> biomeList = new LinkedList<>();

	public static final Biome CETIB_BASE = new BiomeYzCetiB(new BiomeProperties("Yz Ceti B"));
	public static final Biome CETIB_DIRTY = new BiomeYzCetiBDirty(new BiomeProperties("Yz Ceti B Dirty"));
	
	public static final Biome CETIC_BASE = new BiomeYzCetiC(new BiomeProperties("Yz Ceti C"));
	public static final Biome CETIC_UNKNWON = new BiomeYzCetiCUnknown(new BiomeProperties("Yz Ceti Unknown"));

	public static void init() {

		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIB_BASE, DEAD, DRY);
		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIB_DIRTY, SPOOKY, DEAD);
		
		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIC_BASE, DEAD, DRY);
		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIC_UNKNWON, SPOOKY, DEAD);
	}

	private static void addBiome(Biome biome, BiomeDictionary.Type... biomeType) {
		ExoplanetBiomes.registerBiome(biome);
		ExoplanetBiomes.registerBiomeType(biome, biomeType);
	}

	public static void registerBiome(Biome biome) {
		ForgeRegistries.BIOMES.register(biome);
	}

	public static void registerBiomeType(Biome biome, @Nonnull BiomeDictionary.Type... biomeType) {
		BiomeDictionary.addTypes(biome, biomeType);
	}

}