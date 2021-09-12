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

package com.readonlydev.space.trappist1.c;

import java.util.List;

import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.core.ExoplanetRegistry;
import com.readonlydev.lib.world.world.WorldProviderExoplanet;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderTrappist1C extends WorldProviderExoplanet {

	@Override
	public CelestialBody getCelestialBody () {
		return ExoplanetRegistry.TRAPPIST1C;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return null;
	}

	@Override
	public long getDayLength () {
		return 24000L;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.TRAPPIST_1C;
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
	public Vector3 getFogColor () {
		float f = 0.6F - this.getStarBrightness(1.0F);
		return new Vector3(213f / 255F * f, 72f / 255F * f, 3f / 255F * f);
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 0;
	}

	@Override
	public float getGravity () {
		return 0.015f;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getLightmapColors (float partialTicks, float sunBrightness, float skyLight, float blockLight, float[] colors) {
		EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

		if (player != null) {
			int phase = this.getMoonPhase(this.getWorldTime());
			if (skyLight > 0 && sunBrightness > 0.07f && phase != 0 && phase != 6) {

				colors[0] = colors[0] + skyLight + 0.3F;
				colors[1] = colors[1] + skyLight / 6;
			}
		}
	}

	@Override
	public double getMeteorFrequency() {
		return 0;
	}

	@Override
	public int getMoonPhase (long worldTime) {
		return (int) (worldTime / this.getDayLength() % 8L + 8L) % 8;
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 0.3F - this.getStarBrightness(1.0F);
		return new Vector3(228 / 255.0F * f, 75 / 255.0F * f, 1 / 255.0F * f);

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
		return false;
	}

	@Override
	public boolean shouldForceRespawn () {
		return !ConfigManagerCore.forceOverworldRespawn;
	}

}
