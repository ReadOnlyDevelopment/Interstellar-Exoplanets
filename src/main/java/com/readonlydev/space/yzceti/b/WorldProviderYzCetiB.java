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

package com.readonlydev.space.yzceti.b;

import java.util.LinkedList;
import java.util.List;

import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.core.ExoplanetRegistry;
import com.readonlydev.lib.world.world.WorldProviderExoplanet;
import com.readonlydev.space.yzceti.b.worldgen.BiomeProviderYzCetiB;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderYzCetiB extends WorldProviderExoplanet {

	@Override
	public boolean canCoordinateBeSpawn (int var1, int var2) {
		return true;
	}

	@Override
	public int getAverageGroundLevel () {
		return 76;
	}

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass () {
		BiomeAdaptive.setBodyMultiBiome(ExoplanetRegistry.YZCETIB);
		return BiomeProviderYzCetiB.class;
	}

	@Override
	public CelestialBody getCelestialBody () {
		return ExoplanetRegistry.YZCETIB;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return ChunkProviderYzCetiB.class;
	}

	@Override
	public long getDayLength () {
		return 23500L;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.YZCETIB;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public float getFallDamageModifier () {
		return 0.10F;
	}

	@Override
	public Vector3 getFogColor () {
		float f = 0.6F - this.getStarBrightness(1.0F);
		return new Vector3((213f / 255F) * f, (72f / 255F) * f, (3f / 255F) * f);
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 1.4D;
	}

	@Override
	public float getGravity () {
		return 0.030f;
	}

	@Override
	public double getHorizon () {
		return 44.0D;
	}

	@Override
	public double getMeteorFrequency () {
		return 2.0;
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 0.3F - this.getStarBrightness(1.0F);
		return new Vector3((228 / 255.0F) * f, (75 / 255.0F) * f, (1 / 255.0F) * f);

	}

	@Override
	public float getSolarSize () {
		return 2.5F;
	}

	@Override
	public float getSoundVolReductionAmount () {
		return 1.0F;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		List<Block> list = new LinkedList<>();
		return list;
	}

	@Override
	public float getThermalLevelModifier () {
		return 5.5F;
	}

	@Override
	public boolean hasSunset () {
		return false;
	}

	@Override
	public boolean isSkyColored () {
		return true;
	}
}