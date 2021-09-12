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
package com.readonlydev.space.wolf1061.c;

import java.util.LinkedList;
import java.util.List;

import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.core.ExoplanetRegistry;
import com.readonlydev.core.Planets;
import com.readonlydev.lib.world.world.WorldProviderExoplanet;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderWolf1061C extends WorldProviderExoplanet {
	
	@Override
	public boolean canCoordinateBeSpawn(int var1, int var2) {
		return true;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return tier >= 3;
	}

	@Override
	public int getAverageGroundLevel() {
		return 44;
	}

	@Override
	public CelestialBody getCelestialBody() {
		return ExoplanetRegistry.WOLF1061C;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return ChunkProviderWolf1061C.class;
	}

	@Override
	public long getDayLength() {
		return 90000L;
	}

	@Override
	public DimensionType getDimensionType() {
		return ExoDimensions.WOLF1061_1C;
	}

	@Override
	public int getDungeonSpacing() {
		return 800;
	}

	@Override
	public float getFallDamageModifier() {
		return 0.38F;
	}

	@Override
	public Vector3 getFogColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
			return new Vector3(0 / 255.0F * f, 0 / 255.0F * f, 0 / 255.0F * f);

	}

	@Override
	public double getFuelUsageMultiplier() {
		return 1.9D;
	}

	@Override
	public float getGravity() {
			return 0.065F;
	}

	@Override
	public int getHeight() {
		return 800;
	}

	@Override
	public double getHorizon() {
		return 44.0D;
	}
	
	@Override
	public double getMeteorFrequency() {
		return 10.0D;
	}

	@Override
	public Vector3 getSkyColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
			return new Vector3(0 / 255.0F * f, 0 / 255.0F * f, 0 / 255.0F * f);

	}

	@Override
	public List<Block> getSurfaceBlocks() {
		List<Block> list = new LinkedList<>();
		return list;
	}

	@Override
	public boolean hasSunset() {
		return false;
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}
}