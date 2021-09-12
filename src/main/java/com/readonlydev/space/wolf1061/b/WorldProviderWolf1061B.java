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

package com.readonlydev.space.wolf1061.b;

import java.util.List;

import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.core.ExoplanetRegistry;
import com.readonlydev.lib.world.world.WorldProviderExoplanet;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderWolf1061B extends WorldProviderExoplanet {
	
	@Override
	public boolean canCoordinateBeSpawn (int var1, int var2) {
		return true;
	}

	@Override
	public int getAverageGroundLevel () {
		return 44;
	}

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass () {
		BiomeAdaptive.setBodyMultiBiome(ExoplanetRegistry.WOLF1061B);
		return BiomeProviderWolf1061B.class;
	}

	@Override
	public CelestialBody getCelestialBody () {
		return ExoplanetRegistry.WOLF1061B;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return ChunkProviderWolfB.class;
	}

	@Override
	public long getDayLength () {
		return 35000L;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.WOLF1061_1B;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public int getDungeonSpacing () {
		return 0;
	}

	@Override
	public float getFallDamageModifier () {
		return 0.38F;

	}

	@Override
	public double getFuelUsageMultiplier () {
		return 1.2D;
	}

	@Override
	public float getGravity () {
		return 0.037F;
	}

	@Override
	public int getHeight () {
		return 512;
	}

	@Override
	public double getHorizon () {
		return 44.0D;
	}

	@Override
	public double getMeteorFrequency () {
		return 1.0D;
	}

	@Override
	public Vector3 getSkyColor () {
		return new Vector3(0, 0, 0);
	}

	@Override
	public float getSolarSize () {
		return 0.5F;
	}

	@Override
	public float getThermalLevelModifier () {
		return 5.0F;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public Vector3 getFogColor() {
		return new Vector3(0, 0, 0);
	}

	@Override
	public boolean hasSunset() {
		return false;
	}
}