/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.astronomy;

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
import net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome.BiomeYzCetiB;
import net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome.BiomeYzCetiBDirty;
import net.rom.exoplanets.astronomy.yzcetisystem.c.worldgen.biomes.BiomeYzCetiC;
import net.rom.exoplanets.astronomy.yzcetisystem.c.worldgen.biomes.BiomeYzCetiCUnknown;

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