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

package net.romvoid95.space.yzceti.b.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.core.initialization.Planets;
import net.romvoid95.space.yzceti.b.worldgen.layer.GenLayerYzCetiB;

public class BiomeProviderYzCetiB extends BiomeProvider {
	private GenLayer      unzoomedBiomes;
	private GenLayer      zoomedBiomes;
	private BiomeCache    biomeCache;
	private List<Biome>   biomesToSpawnIn;
	private CelestialBody body;

	protected BiomeProviderYzCetiB() {
		this.body            = Planets.YZCETIB;
		this.biomeCache      = new BiomeCache(this);
		this.biomesToSpawnIn = new ArrayList<>();
	}

	public BiomeProviderYzCetiB(long seed, WorldType type) {
		this();
		GenLayer[] genLayers = GenLayerYzCetiB.createWorld(seed);
		this.unzoomedBiomes = genLayers[0];
		this.zoomedBiomes   = genLayers[1];
	}

	public BiomeProviderYzCetiB(World world) {
		this(world.getSeed(), world.getWorldInfo().getTerrainType());
	}

	@Override
	public List<Biome> getBiomesToSpawnIn () {
		return this.biomesToSpawnIn;
	}

	@Override
	public Biome getBiome (BlockPos pos, Biome defaultBiome) {
		BiomeAdaptive.setBodyMultiBiome(this.body);
		return this.biomeCache.getBiome(pos.getX(), pos.getZ(), BiomeAdaptive.biomeDefault);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getTemperatureAtHeight (float par1, int par2) {
		return par1;
	}

	@Override
	public Biome[] getBiomesForGeneration (Biome[] biomes, int x, int z, int length, int width) {
		IntCache.resetIntCache();
		BiomeAdaptive.setBodyMultiBiome(this.body);

		if (biomes == null || biomes.length < length * width) {
			biomes = new Biome[length * width];
		}

		int[] intArray = this.unzoomedBiomes.getInts(x, z, length, width);

		for (int i = 0; i < length * width; ++i) {
			if (intArray[i] >= 0) {
				biomes[i] = Biome.getBiome(intArray[i]);
			}
			else {
				biomes[i] = BiomeAdaptive.biomeDefault;
			}
		}

		return biomes;
	}

	@Override
	public Biome[] getBiomes (@Nullable Biome[] oldBiomeList, int x, int z, int width, int depth) {
		return getBiomes(oldBiomeList, x, z, width, depth, true);
	}

	@Override
	public Biome[] getBiomes (@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
		IntCache.resetIntCache();
		BiomeAdaptive.setBodyMultiBiome(this.body);

		if (listToReuse == null || listToReuse.length < length * width) {
			listToReuse = new Biome[width * length];
		}

		if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
			Biome[] cached = this.biomeCache.getCachedBiomes(x, z);
			System.arraycopy(cached, 0, listToReuse, 0, width * length);
			return listToReuse;
		}

		int[] zoomed = this.zoomedBiomes.getInts(x, z, width, length);

		for (int i = 0; i < width * length; ++i) {
			if (zoomed[i] >= 0) {
				listToReuse[i] = Biome.getBiome(zoomed[i]);
			}
			else {
				listToReuse[i] = BiomeAdaptive.biomeDefault;
			}
		}

		return listToReuse;
	}

	@Override
	public boolean areBiomesViable (int x, int z, int range, List<Biome> viables) {
		int   i        = x - range >> 2;
		int   j        = z - range >> 2;
		int   k        = x + range >> 2;
		int   l        = z + range >> 2;
		int   diffX    = (k - i) + 1;
		int   diffZ    = (l - j) + 1;
		int[] unzoomed = this.unzoomedBiomes.getInts(i, j, diffX, diffZ);

		for (int a = 0; a < diffX * diffZ; ++a) {
			Biome biome = Biome.getBiome(unzoomed[a]);

			if (!viables.contains(biome)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public BlockPos findBiomePosition (int x, int z, int range, List<Biome> biomes, Random random) {
		int      i        = x - range >> 2;
		int      j        = z - range >> 2;
		int      k        = x + range >> 2;
		int      l        = z + range >> 2;
		int      diffX    = (k - i) + 1;
		int      diffZ    = (l - j) + 1;
		int[]    unzoomed = this.unzoomedBiomes.getInts(i, j, diffX, diffZ);
		BlockPos blockPos = null;
		int      count    = 0;

		for (int a = 0; a < unzoomed.length; ++a) {
			int   x0    = i + a % diffX << 2;
			int   z0    = j + a / diffX << 2;
			Biome biome = Biome.getBiome(unzoomed[a]);

			if (biomes.contains(biome) && (blockPos == null || random.nextInt(count + 1) == 0)) {
				blockPos = new BlockPos(x0, 0, z0);
				count++;
			}
		}

		return blockPos;
	}

	@Override
	public void cleanupCache () {
		this.biomeCache.cleanupCache();
	}
}