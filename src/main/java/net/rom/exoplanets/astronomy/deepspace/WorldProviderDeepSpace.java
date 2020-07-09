/**
 * Exoplanets
 * Copyright (C) 2020
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
package net.rom.exoplanets.astronomy.deepspace;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.api.world.IZeroGDimension;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.client.SkyProviderOrbit;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderSpaceStation;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.world.gen.BiomeProviderOrbit;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.RoomTreasure;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.init.ExoUniverse;

public class WorldProviderDeepSpace extends WorldProviderSpaceStation
		implements IZeroGDimension, ISolarLevel, IExitHeight {
	Set<Entity> freefallingEntities = new HashSet<Entity>();

	@Override
	public DimensionType getDimensionType() {
		return DeepSpaceDimension.DEEP_SPACE;
	}

	@Override
	public Vector3 getFogColor() {
		return new Vector3(0, 0, 0);
	}

	@Override
	public Vector3 getSkyColor() {
		return new Vector3(0, 0, 0);
	}

	@Override
	public boolean hasSunset() {
		return false;
	}

	@Override
	public long getDayLength() {
		return 24000L;
	}

	@Override
	public boolean isDaytime() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		return 1.0F;
	}

	@Override
	public boolean isSkyColored() {
		return false;
	}

	@Override
	public double getHorizon() {
		return 44.0D;
	}

	@Override
	public int getAverageGroundLevel() {
		return 64;
	}

	@Override
	public boolean canCoordinateBeSpawn(int var1, int var2) {
		return true;
	}

	@Override
	public CelestialBody getCelestialBody() {
		return ExoUniverse.stationDeepSpace;
	}

	@Override
	public float getGravity() {
		return 0.075F;
	}

	@Override
	public double getMeteorFrequency() {
		return 0;
	}

	@Override
	public double getFuelUsageMultiplier() {
		return 0.5D;
	}

	@Override
	public String getSaveFolder() {
		return "DIM-GCDEEPSPACE";
	}

	@Override
	public double getSolarEnergyMultiplier() {
		return ConfigManagerCore.spaceStationEnergyScalar;
	}

	@Override
	public double getYCoordinateToTeleport() {
		return 750;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return tier > 2;
	}

	@Override
	public float getFallDamageModifier() {
		return 0.4F;
	}

	@Override
	public boolean inFreefall(Entity entity) {
		return freefallingEntities.contains(entity);
	}

	@Override
	public void setInFreefall(Entity entity) {
		freefallingEntities.add(entity);
	}

	@Override
	public void updateWeather() {
		freefallingEntities.clear();
		super.updateWeather();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setSpinDeltaPerTick(float angle) {
		SkyProviderDeepSpace skyProvider = ((SkyProviderDeepSpace) this.getSkyRenderer());
		if (skyProvider != null)
			skyProvider.spinDeltaPerTick = angle;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSkyRotation() {
		SkyProviderDeepSpace skyProvider = ((SkyProviderDeepSpace) this.getSkyRenderer());
		return skyProvider.spinAngle;
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void createSkyProvider()
    {
        this.setSkyRenderer(new SkyProviderDeepSpace(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/jupiter.png"), true, true));
        this.setSpinDeltaPerTick(this.getSpinManager().getSpinRate());
        
        if (this.getCloudRenderer() == null)
            this.setCloudRenderer(new CloudRenderer());
    }

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return RoomTreasure.MOONCHEST;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return ChunkProviderDeepSpace.class;
	}

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass() {
		return BiomeProviderOrbit.class;
	}
}
