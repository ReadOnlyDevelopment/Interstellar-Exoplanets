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

package com.readonlydev.space.yzceti.c;

import java.util.ArrayList;
import java.util.List;

import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.core.ExoplanetRegistry;
import com.readonlydev.lib.world.world.WorldProviderExoplanet;
import com.readonlydev.space.yzceti.YzCetiBlocks;
import com.readonlydev.space.yzceti.c.worldgen.BiomeProviderYzCetiC;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderYzCetiC extends WorldProviderExoplanet {

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass () {
		BiomeAdaptive.setBodyMultiBiome(ExoplanetRegistry.YZCETIC);
		return BiomeProviderYzCetiC.class;
	}

	@Override
	public CelestialBody getCelestialBody () {
		return ExoplanetRegistry.YZCETIC;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return ChunkProviderYzCetiC.class;
	}

	@Override
	public float getCloudHeight () {
		return 128F;
	}

	@Override
	public long getDayLength () {
		return 28000L;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.YZCETIC;
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
		return 0.56F;
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 3.8D;
	}

	@Override
	public float getGravity () {
		return 0.030F;
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
		return new Vector3(0, 0, 0);
	}

	@Override
	public float getSolarSize () {
		return 1.0F;
	}

	@Override
	public float getSoundVolReductionAmount () {
		return 0.0F;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		ArrayList<Block> blockList = new ArrayList<>();
		blockList.add(YzCetiBlocks.C.YZC_SEDIMENTARYROCK);
		blockList.add(YzCetiBlocks.C.YZC_IGNEOUS);
		blockList.add(YzCetiBlocks.C.YZC_GRAVEL);
		return blockList;
	}

	@Override
	public float getThermalLevelModifier () {
		return 2.5F;
	}

	@Override
	public boolean hasSunset () {
		return true;
	}

	@Override
	public boolean isSkyColored () {
		return true;
	}

	@Override
	public Vector3 getFogColor() {
		return null;
	}
}