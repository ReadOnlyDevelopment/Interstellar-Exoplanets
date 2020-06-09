/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal.world;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
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
import net.rom.exoplanets.internal.world.planet.ExoPlanet;

public abstract class WorldProviderExoPlanet extends WorldProviderSpace implements ISolarLevel, IExitHeight, IExoPlanetWorldProvider {

	private static WorldProviderExoPlanet instance;

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
		return (ExoPlanet) this.getCelestialBody();
//		ExoPlanet exo = (ExoPlanet) planet;
//		return exo;
	}

	@Override
	public abstract float getSolarSize();

	@Override
	public Vector3 getFogColor() {
		return new Vector3(0, 0, 0);
	}

	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
		return false;
	}
	
    @Override
    public IRenderHandler getCloudRenderer(){
        return new CloudRenderer();
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
	public abstract double getMeteorFrequency();

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
		return (float) this.getPlanet().getGravity();
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
		float planetTemp = (float) planet.getPlanetTemp();

		if (this.isDaytime()) {
			planetTemp /= 2.2F;
		} else {
			planetTemp = (float) planet.getPlanetTemp();
		}

		return planetTemp;
	}

	@Override
	public abstract Class<? extends IChunkGenerator> getChunkProviderClass();

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1) {
       float f1 = this.world.getCelestialAngle(1.0F);
       float f2 = 1.25F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.2F);
       if(f2 < 0.0F) {
          f2 = 0.0F;
       }

       if(f2 > 1.0F) {
          f2 = 1.0F;
       }

       f2 = 1.0F - f2;
       return f2 * 0.8F + 0.2F;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        final float var2 = this.world.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        return var3 * var3 * 0.5F + 0.3F;
    }
}
