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
package com.readonlydev.space.yzceti.c.worldgen.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;

import com.readonlydev.core.Planets;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;

import micdoodle8.mods.miccore.IntCache;

public class GenLayerYzCetiCBiomes extends GenLayer {
	public static final Biome[] biomes = BiomeAdaptive.getBiomesListFor(Planets.YZCETIC).toArray(new Biome[0]);

	public GenLayerYzCetiCBiomes(long l, GenLayer parent) {
		super(l);
		this.parent = parent;
	}

	public GenLayerYzCetiCBiomes(long l) {
		super(l);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);

		for (int k = 0; k < depth; ++k) {
			for (int i = 0; i < width; ++i) {
				initChunkSeed(x + i, z + k);
				dest[i + k * width] = Biome.getIdForBiome(biomes[nextInt(biomes.length)]);
			}
		}

		return dest;
	}
}