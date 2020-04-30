package net.rom.api.stellar.world;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.api.stellar.impl.planet.ExoPlanet;

public abstract class WorldProviderExoPlanet extends WorldProviderSpace implements ISolarLevel, IExitHeight {

	private static WorldProviderExoPlanet instance;
	private static int dayLength;

	public WorldProviderExoPlanet() {
		instance = this;
	}

	public static WorldProviderExoPlanet instance() {
		return instance;
	}

	@Override
    public String getSaveFolder() {
		return "planets/" + this.getPlanet().getName();
	}

	public World getWorldObj() {
		return this.world;
	}

	@Override
	public boolean shouldForceRespawn() {
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	protected ExoPlanet getPlanet() {
		CelestialBody planet = this.getCelestialBody();
		ExoPlanet exo = (ExoPlanet) planet;
		return exo;
	}

	@Override
	public float getSolarSize() {
		return 1.0F;
	}

	@Override
	public Vector3 getFogColor() {
		return new Vector3(0, 0, 0);
	}

	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
		return false;
	}

	@Override
    public long getDayLength() {
		return this.getPlanet().getDayLength();
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

	@Override
	public float getWindLevel() {
		return 0.0F;
	}

	@Override
	public Vector3 getSkyColor() {
		return new Vector3(0, 0, 0);
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
	public double getFuelUsageMultiplier() {
		return 0.5D;
	}

	@Override
	public double getMeteorFrequency() {
		return 0.0;
	}

	@Override
	public float getArrowGravity() {
		return 0.003F;
	}

	@Override
	public int getDungeonSpacing() {
		return 704;
	}

	@Override
	public boolean hasBreathableAtmosphere() {
		return this.getPlanet().isBreathable();
	}

	@Override
	public float getGravity() {
		return this.getPlanet().getGravity();
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return tier >= this.getPlanet().getTierRequirement();
	}

	@Override
	public boolean hasSkyLight() {
		return true;
	}

	public float getPlanetTemp() {
		ExoPlanet planet = this.getPlanet();
		float planetTemp = planet.getPlanetTemp();

		if (this.isDaytime()) {
			planetTemp /= 2.2F;
		} else {
			planetTemp = planet.getPlanetTemp();
		}

		return planetTemp;
	}

	@Override
	public abstract Class<? extends IChunkGenerator> getChunkProviderClass();

}
