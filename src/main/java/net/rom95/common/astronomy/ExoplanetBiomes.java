/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.rom95.common.astronomy;

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
import net.rom95.common.astronomy.yzceti.b.worldgen.biome.BiomeYzCetiB;
import net.rom95.common.astronomy.yzceti.b.worldgen.biome.BiomeYzCetiBDirty;
import net.rom95.common.astronomy.yzceti.c.worldgen.biomes.BiomeYzCetiC;
import net.rom95.common.astronomy.yzceti.c.worldgen.biomes.BiomeYzCetiCUnknown;

public class ExoplanetBiomes {

	public static final List<BiomeGenBaseGC> biomeList = new LinkedList<>();

	public static final Biome CETIB_BASE  = new BiomeYzCetiB(new BiomeProperties("Yz Ceti B"));
	public static final Biome CETIB_DIRTY = new BiomeYzCetiBDirty(new BiomeProperties("Yz Ceti B Dirty"));

	public static final Biome CETIC_BASE    = new BiomeYzCetiC(new BiomeProperties("Yz Ceti C"));
	public static final Biome CETIC_UNKNWON = new BiomeYzCetiCUnknown(new BiomeProperties("Yz Ceti Unknown"));

	public static void init () {

		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIB_BASE, DEAD, DRY);
		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIB_DIRTY, SPOOKY, DEAD);

		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIC_BASE, DEAD, DRY);
		ExoplanetBiomes.addBiome(ExoplanetBiomes.CETIC_UNKNWON, SPOOKY, DEAD);
	}

	private static void addBiome (Biome biome, BiomeDictionary.Type... biomeType) {
		ExoplanetBiomes.registerBiome(biome);
		ExoplanetBiomes.registerBiomeType(biome, biomeType);
	}

	public static void registerBiome (Biome biome) {
		ForgeRegistries.BIOMES.register(biome);
	}

	public static void registerBiomeType (Biome biome, @Nonnull BiomeDictionary.Type... biomeType) {
		BiomeDictionary.addTypes(biome, biomeType);
	}
}