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

package com.readonlydev.space.kepler1649.c;

import java.util.List;

import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.core.ExoplanetRegistry;
import com.readonlydev.lib.world.world.WorldProviderExoplanet;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderKepler1649C extends WorldProviderExoplanet {

	@Override
	public CelestialBody getCelestialBody () {
		return ExoplanetRegistry.KEPLER1649C;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getCloudColor (float partialTicks) {
		return new Vec3d(0.3D, 0.3D, 0.3D);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight () {
		return 250.0F;
	}

	@Override
	public long getDayLength () {
		return 24000L;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.KEPLER1649_C;
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
		return 0;
	}

	@Override
	public Vector3 getFogColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(250 / 255F * f, 192 / 255F * f, 115 / 255F * f);
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 0;
	}

	@Override
	public float getGravity () {
		return 0.00015f;
	}

	@Override
	public double getHorizon () {
		return 1;
	}

	@Override
	public double getMeteorFrequency() {
		return 0;
	}

	@Override
	public int getMoonPhase (long worldTime) {
		return (int) (((worldTime / this.getDayLength()) % 8L) + 8L) % 8;
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3((156f / 255.0F) * f, (156f / 255.0F) * f, (156f / 255.0F) * f);
	}

	@Override
	public float getSolarSize () {
		return 0.3F / this.getCelestialBody().getRelativeDistanceFromCenter().unScaledDistance;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		return null;
	}

	@Override
	public boolean hasSunset () {
		return false;
	}

	@Override
	public boolean isSkyColored () {
		return true;
	}

	@Override
	public boolean shouldDisablePrecipitation () {
		return false;
	}

	@Override
	public boolean shouldForceRespawn () {
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return false;
	}
}