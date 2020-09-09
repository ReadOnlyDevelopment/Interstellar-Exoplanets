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

package net.romvoid95.api.space.prefab;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.space.ExoPlanet;
import net.romvoid95.api.space.interfaces.IExoPlanetWorldProvider;

public abstract class WorldProviderExoPlanet extends WorldProviderSpace
		implements ISolarLevel, IExitHeight, IExoPlanetWorldProvider {

	private static WorldProviderExoPlanet instance;

	public WorldProviderExoPlanet() {
		instance = this;
	}

	public static WorldProviderExoPlanet instance () {
		return instance;
	}

	@Override
	public String getSaveFolder () {
		return "planets/" + this.getExoPlanet().getName();
	}

	public World getWorldObj () {
		return this.world;
	}

	@Override
	public boolean shouldForceRespawn () {
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	public ExoPlanet getExoPlanet () {
		return (ExoPlanet) this.getCelestialBody();

	}

	@Override
	public abstract float getSolarSize ();

	@Override
	public Vector3 getFogColor () {
		return new Vector3(0, 0, 0);
	}

	@Override
	public boolean canBlockFreeze (BlockPos pos, boolean byWater) {
		return false;
	}

	@Override
	public IRenderHandler getCloudRenderer () {
		return new CloudRenderer();
	}

	@Override
	public long getDayLength () {
		return this.getExoPlanet().getDayLength();
	}

	public int AtmosphericPressure () {
		return 5;
	}

	public boolean SolarRadiation () {
		return true;
	}

	public double getSolarWindMultiplier () {
		return 0.6D;
	}

	public ClassBody getClassBody () {
		return ClassBody.SELENA;
	}

	public float getSolarRadiationModify () {
		return 5.0f;
	}

	@Override
	public void updateWeather () {
		World     worldObj  = this.getWorldObj();
		WorldInfo worldInfo = worldObj.getWorldInfo();
		if (!this.shouldDisablePrecipitation()) {
			super.updateWeather();
		}
		else {
			worldInfo.setRainTime(0);
			worldInfo.setRaining(false);
			worldInfo.setThunderTime(0);
			worldInfo.setThundering(false);
			worldObj.rainingStrength    = 0.0F;
			worldObj.thunderingStrength = 0.0F;
		}
	}

	@Override
	public float getWindLevel () {
		return 0.0F;
	}

	@Override
	public Vector3 getSkyColor () {
		return new Vector3(0, 0, 0);
	}

	@Override
	public boolean canRespawnHere () {
		if (EventHandlerGC.bedActivated) {
			EventHandlerGC.bedActivated = false;
			return true;
		}
		return false;
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 0.5D;
	}

	@Override
	public abstract double getMeteorFrequency ();

	@Override
	public float getArrowGravity () {
		return 0.003F;
	}

	@Override
	public int getDungeonSpacing () {
		return 704;
	}

	@Override
	public boolean hasBreathableAtmosphere () {
		return this.getExoPlanet().isBreathable();
	}

	@Override
	public float getGravity () {
		return this.getExoPlanet().getGravity();
	}

	@Override
	public boolean canSpaceshipTierPass (int tier) {
		return tier >= this.getExoPlanet().getTierRequirement();
	}

	@Override
	public boolean hasSkyLight () {
		return true;
	}

	public float getPlanetTemp () {
		ExoPlanet planet     = this.getExoPlanet();
		float     planetTemp = (float) planet.getPlanetTemp();

		if (this.isDaytime()) {
			planetTemp /= 2.2F;
		}
		else {
			planetTemp = (float) planet.getPlanetTemp();
		}

		return planetTemp;
	}

	@Override
	public abstract Class<? extends IChunkGenerator> getChunkProviderClass ();

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness (float par1) {
		float f1 = this.getWorldObj().getCelestialAngle(1.0F);
		float f2 = 1.25F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.2F);
		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		f2 = 1.0F - f2;
		return f2 * 0.8F + 0.2F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness (float par1) {
		final float var2 = this.getWorldObj().getCelestialAngle(par1);
		float       var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (var3 < 0.0F) {
			var3 = 0.0F;
		}

		if (var3 > 1.0F) {
			var3 = 1.0F;
		}

		return var3 * var3 * 0.5F + 0.3F;
	}
}
