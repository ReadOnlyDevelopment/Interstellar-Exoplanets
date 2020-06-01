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

package net.rom.exoplanets.astronomy.yzceti.b.worldgen.biome.genlayer;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.internal.enums.CachedEnum;

public class GenLayerYzCetiBBiomes extends GenLayerYzCetiB {

	@SuppressWarnings("unchecked")
	private List<BiomeEntry>[] biomes = new ArrayList[CachedEnum.valuesBiomeCached().length];
	private ArrayList<BiomeEntry>[] biomesList = this.setupBiomes();

	public GenLayerYzCetiBBiomes(long seed) {
		super(seed);

		for (BiomeType type : CachedEnum.valuesBiomeCached()) {
			ImmutableList<BiomeEntry> biomesToAdd = this.getBiomes(type);
			int idx = type.ordinal();

			if (this.biomes[idx] == null) {
				this.biomes[idx] = new ArrayList<>();
			}
			if (biomesToAdd != null) {
				this.biomes[idx].addAll(biomesToAdd);
			}
		}
	}

	private ArrayList<BiomeEntry>[] setupBiomes() {
		@SuppressWarnings("unchecked")
		ArrayList<BiomeEntry>[] currentBiomes = new ArrayList[CachedEnum.valuesBiomeCached().length];
		List<BiomeEntry> list = new ArrayList<>();
		list.add(new BiomeEntry(ExoplanetBiomes.CETIB_BASE, 75));
		list.add(new BiomeEntry(ExoplanetBiomes.CETIB_DIRTY, 70));
		currentBiomes[BiomeType.WARM.ordinal()] = new ArrayList<>(list);
		return currentBiomes;
	}

	private ImmutableList<BiomeEntry> getBiomes(BiomeType type) {
		int idx = type.ordinal();
		List<BiomeEntry> list = idx >= this.biomesList.length ? null : this.biomesList[idx];
		return list != null ? ImmutableList.copyOf(list) : null;
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] dest = IntCache.getIntCache(areaWidth * areaHeight);

		for (int dz = 0; dz < areaHeight; dz++) {
			for (int dx = 0; dx < areaWidth; dx++) {
				this.initChunkSeed(dx + areaX, dz + areaY);
				dest[dx + dz * areaWidth] = Biome.getIdForBiome(this.getWeightedBiomeEntry(BiomeType.WARM).biome);
			}
		}
		return dest;
	}

	protected BiomeEntry getWeightedBiomeEntry(BiomeType type) {
		List<BiomeEntry> biomeList = this.biomes[type.ordinal()];
		int totalWeight = WeightedRandom.getTotalWeight(biomeList);
		int weight = BiomeManager.isTypeListModded(type) ? this.nextInt(totalWeight)
				: this.nextInt(totalWeight / 10) * 10;
		return WeightedRandom.getRandomItem(biomeList, weight);
	}
}