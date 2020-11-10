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

import java.util.LinkedList;
import java.util.List;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.space.interfaces.IExoPlanet;

public abstract class WorldProviderExoPlanet extends WorldProviderSpace implements ISolarLevel, IExitHeight {

	private static WorldProviderExoPlanet instance;

	public static WorldProviderExoPlanet instance() {
		return instance;
	}

	public WorldProviderExoPlanet() {
		instance = this;
	}

	public int AtmosphericPressure() {
		return 5;
	}

	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {

		return false;
	}

	@Override
	public boolean canRespawnHere() {
		if (EventHandlerGC.bedActivated) {
			EventHandlerGC.bedActivated = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return tier >= instance().getExoPlanet().getTierRequirement();
	}

	@Override
	public float getArrowGravity() {
		return 0.003F;
	}

	public ClassBody getClassBody() {

		return ClassBody.SELENA;
	}

	@Override
	public long getDayLength() {

		if (this.getCelestialBody() instanceof IExoPlanet)
			return ((IExoPlanet) this.getCelestialBody()).getDayLength();

		return 24000L;
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	public ExoPlanet getExoPlanet() {
		CelestialBody celestialBody = this.getCelestialBody();
		ExoPlanet exoPlanet = (ExoPlanet) celestialBody;
		return exoPlanet;
	}

	@Override
	public Vector3 getFogColor() {
		return new Vector3(0, 0, 0);
	}

	@Override
	public double getFuelUsageMultiplier() {
		return 0.5D;
	}

	@Override
	public float getGravity() {
		return instance().getExoPlanet().getGravity();
	}

	public float getPlanetTemp() {
		ExoPlanet planet = instance().getExoPlanet();
		float planetTemp = (float) planet.getPlanetTemp();

		if (this.isDaytime()) {
			planetTemp /= 2.2F;
		} else {
			planetTemp = (float) planet.getPlanetTemp();
		}

		return planetTemp;
	}

	@Override
	public String getSaveFolder() {
		return "exoplanets/" + this.getExoPlanet().getName();
	}

	@Override
	public Vector3 getSkyColor() {
		return new Vector3(0, 0, 0);
	}

	public float getSolarRadiationModify() {
		return 5.0f;
	}

	public double getSolarWindMultiplier() {
		return 0.6D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		final float var2 = this.getWorldObj().getCelestialAngle(par1);
		float var3 = 1.0F - ((MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F) + 0.25F);

		if (var3 < 0.0F) {
			var3 = 0.0F;
		}

		if (var3 > 1.0F) {
			var3 = 1.0F;
		}

		return (var3 * var3 * 0.5F) + 0.3F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1) {
		float f1 = this.getWorldObj().getCelestialAngle(1.0F);
		float f2 = 1.25F - ((MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F) + 0.2F);
		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		f2 = 1.0F - f2;
		return (f2 * 0.8F) + 0.2F;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		List<Block> list = new LinkedList<>();
		return list;
	}

	@Override
	public float getWindLevel() {
		return 0.0F;
	}

	public World getWorldObj() {
		return this.world;
	}

	public IBlockState getWorldStoneBlockState() {
		return null;
	}

	@Override
	public boolean hasSkyLight() {
		return true;
	}

	@Override
	public boolean shouldForceRespawn() {
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	public boolean SolarRadiation() {
		return true;
	}
	

	@Override
	public void updateWeather() {
		World worldObj = this.getWorldObj();
		WorldInfo worldInfo = worldObj.getWorldInfo();
		if (!this.shouldDisablePrecipitation()) {
			super.updateWeather();
		} else {
			worldInfo.setRainTime(0);
			worldInfo.setRaining(false);
			worldInfo.setThunderTime(0);
			worldInfo.setThundering(false);
			worldObj.rainingStrength = 0.0F;
			worldObj.thunderingStrength = 0.0F;
		}
	}
}
