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

package net.rom.exoplanets.astronomy.yzceti.d;

import java.util.LinkedList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.api.space.ExoPlanet;
import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzceti.YzCetiDimensions;
import net.rom.exoplanets.astronomy.yzceti.d.worldgen.BiomeProviderYzCetiD;
import net.rom.exoplanets.astronomy.yzceti.d.worldgen.ChunkProviderYzCetiD;
import net.rom.exoplanets.conf.ConfigSystems;
import net.rom.exoplanets.init.Planets;
import net.rom.exoplanets.internal.world.WorldProviderExoPlanet;

public class WorldProviderYzCetiD extends WorldProviderExoPlanet {

	@Override
	public Vector3 getSkyColor () {
		return new Vector3(0, 0, 0);
	}

	@Override
	public float getSolarSize () {
		return 0.5F;
	}

	@Override
	public boolean hasSunset () {
		return false;
	}

	@Override
	public long getDayLength () {
		return 35000L;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return ChunkProviderYzCetiD.class;
	}

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass () {
		BiomeAdaptive.setBodyMultiBiome(Planets.YZCETID);
		return BiomeProviderYzCetiD.class;
	}

	@Override
	public double getHorizon () {
		return 44.0D;
	}

	@Override
	public int getAverageGroundLevel () {
		return 44;
	}

	@Override
	public boolean canCoordinateBeSpawn (int var1, int var2) {
		return true;
	}

	@Override
	public float getGravity () {
		return 0.037F;
	}

	@Override
	public int getHeight () {
		return 512;
	}

	@Override
	public double getMeteorFrequency () {
		return 1.0D;
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 1.2D;
	}

	@Override
	public boolean canSpaceshipTierPass (int tier) {
		return tier >= ConfigSystems.yzceti_tier;
	}

	@Override
	public float getFallDamageModifier () {
		return 0.38F;

	}

	@Override
	public CelestialBody getCelestialBody () {
		return Planets.YZCETID;
	}

	@Override
	public float getThermalLevelModifier () {
		return 5.0F;
	}

	@Override
	public double getSolarEnergyMultiplier () {
		return 3.5D;
	}

	@Override
	public DimensionType getDimensionType () {
		return YzCetiDimensions.YZCETID;
	}

	@Override
	public int getDungeonSpacing () {
		return 0;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		List<Block> list = new LinkedList<>();
		list.add(YzCetiBlocks.CetiD.D_SEDIMENTARYROCK);
		list.add(YzCetiBlocks.CetiD.D_IGNEOUS);
		list.add(YzCetiBlocks.CetiD.D_GRAVEL);
		return list;
	}

	@Override
	public double getYCoordinateToTeleport () {
		return 1500.0D;
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
			this.setSkyRenderer(new SkyProviderYzCetiD());
		}
    	
		return super.getSkyRenderer();
    }

}