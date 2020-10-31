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

package net.rom.exoplanets.astronomy.yzceti.c;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.api.space.ExoPlanet;
import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzceti.YzCetiDimensions;
import net.rom.exoplanets.astronomy.yzceti.c.worldgen.BiomeProviderYzCetiC;
import net.rom.exoplanets.init.Planets;
import net.rom.exoplanets.internal.world.WorldProviderExoPlanet;

public class WorldProviderYzCetiC extends WorldProviderExoPlanet {

	@Override
	public float getSolarSize () {
		return 1.0F;
	}

	@Override
	public double getMeteorFrequency () {
		return 2.0;
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 3.8D;
	}

	@Override
	public boolean hasBreathableAtmosphere () {
		return this.getExoPlanet().isBreathable();
	}

	@Override
	public float getFallDamageModifier () {
		return 0.56F;
	}

	@Override
	public float getSoundVolReductionAmount () {
		return 0.0F;
	}

	@Override
	public float getThermalLevelModifier () {
		return 2.5F;
	}

	@Override
	public float getPlanetTemp () {
		ExoPlanet planet     = this.getExoPlanet();
		float     planetTemp = (float) planet.getPlanetTemp();

		if (this.isDaytime()) {
			planetTemp *= 4.5F;
		}
		else {
			planetTemp = (float) planet.getPlanetTemp();
		}
		return planetTemp;
	}

	@Override
	public double getSolarEnergyMultiplier () {
		return 8.65;
	}

	@Override
	public double getYCoordinateToTeleport () {
		return 1500.0D;
	}

	@Override
	public float getCloudHeight () {
		return 128F;
	}

	@Override
	public Vector3 getSkyColor () {
		return new Vector3(0, 0, 0);
	}

	@Override
	public boolean canRainOrSnow () {
		return this.getExoPlanet().isDoesRain();
	}

	@Override
	public boolean hasSunset () {
		return true;
	}

	@Override
	public boolean shouldDisablePrecipitation () {
		return !this.canRainOrSnow();
	}

	@Override
	public boolean canDoRainSnowIce (Chunk chunk) {
		return this.canRainOrSnow();
	}

	@Override
	public boolean canRespawnHere () {
		return this.shouldForceRespawn();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness (float par1) {
		float f1 = this.world.getCelestialAngle(par1);
		float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.30F);

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}
		if (f2 > 1.0F) {
			f2 = 1.0F;
		}
		return f2 * f2 * 1.8F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness (float par1) {
		float f1 = this.world.getCelestialAngle(1.0F);
		float f2 = 0.9F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}
		if (f2 > 1.0F) {
			f2 = 1.0F;
		}
		f2 = 1.0F - f2;
		return f2 * 1.5F;
	}

	@Override
	public CelestialBody getCelestialBody () {
		return Planets.YZCETIC;
	}

	@Override
	public double getHorizon () {
		return 44.0D;
	}

	@Override
	public int getAverageGroundLevel () {
		return 65;
	}

	@Override
	public boolean canCoordinateBeSpawn (int var1, int var2) {
		return true;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		ArrayList<Block> blockList = new ArrayList<>();
		blockList.add(YzCetiBlocks.CetiC.C_SEDIMENTARYROCK);
		blockList.add(YzCetiBlocks.CetiC.C_IGNEOUS);
		blockList.add(YzCetiBlocks.CetiC.C_GRAVEL);
		return blockList;
	}

	@Override
	public DimensionType getDimensionType () {
		return YzCetiDimensions.YZCETIC;
	}

	@Override
	public boolean isSkyColored () {
		return true;
	}

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass () {
		BiomeAdaptive.setBodyMultiBiome(Planets.YZCETIC);
		return BiomeProviderYzCetiC.class;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return ChunkProviderYzCetiC.class;
	}

	@Override
	public long getDayLength () {
		return 28000L;
	}

	@Override
	public float getGravity () {
		return 0.030F;
	}

	@Override
	public ExoPlanet getExoPlanet () {
		return (ExoPlanet) getCelestialBody();
	}
	
	
    @Override
    public IRenderHandler getCloudRenderer(){
    	
    	if(super.getCloudRenderer() == null)
    		this.setCloudRenderer(new CloudRenderer());
    	
        return super.getCloudRenderer();
    }
    
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer()
    {
    	if (super.getSkyRenderer() == null)
		{
			this.setSkyRenderer(new SkyProviderYzCetiC());
		}
    	
		return super.getSkyRenderer();
    }


}