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

package net.romvoid95.common.astronomy.yzceti.b;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;

import asmodeuscore.api.dimension.IAdvancedSpace;
import net.romvoid95.api.space.prefab.ExoPlanet;
import net.romvoid95.api.space.prefab.WorldProviderExoPlanet;
import net.romvoid95.api.space.utility.AstronomicalConstants;
import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;
import net.romvoid95.common.astronomy.yzceti.b.worldgen.BiomeProviderYzCetiB;
import net.romvoid95.core.initialization.Planets;

public class WorldProviderYzCetiB extends WorldProviderExoPlanet implements IAdvancedSpace {

	@Override
	public double getMeteorFrequency () {
		return 2.0;
	}

	@Override
	public float getSolarSize () {
		return 2.5F;
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 1.4D;
	}

	@Override
	public float getFallDamageModifier () {
		return 0.10F;
	}

	@Override
	public float getSoundVolReductionAmount () {
		return 1.0F;
	}

	@Override
	public float getThermalLevelModifier () {
		return 5.5F;
	}

	@Override
	public float getPlanetTemp () {
		ExoPlanet planet     = this.getExoPlanet();
		float     planetTemp = (float) planet.getPlanetTemp();
		if (!this.isDaytime()) {
			planetTemp /= 1.4F;
		}
		return planetTemp;
	}

	@Override
	public CelestialBody getCelestialBody () {
		return Planets.yzcetib;
	}

	@Override
	public double getSolarEnergyMultiplier () {
		return 6.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness (float partialTicks) {
		float angle = this.world.getCelestialAngle(partialTicks);
		float value = 1.0F - ((MathHelper.cos(angle * AstronomicalConstants.TWO_PI_F) * 2.0F) + 0.25F);
		value = MathHelper.clamp(value, 0.0F, 1.0F);
		return (value * value * 0.5F) + 0.3F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness (float partialTicks) {
		float f1 = this.world.getCelestialAngle(1.0F);
		float f2 = 1.0F - ((MathHelper.cos(f1 * AstronomicalConstants.TWO_PI_F) * 2.0F) + 0.2F);
		f2 = MathHelper.clamp(f2, 0.0F, 1.0F);
		f2 = 1.2F - f2;
		return f2 * 0.8F;
	}

	@Override
	public double getHorizon () {
		return 44.0D;
	}

	@Override
	public int getAverageGroundLevel () {
		return 76;
	}

	@Override
	public Vector3 getFogColor () {
		float f = 0.6F - this.getStarBrightness(1.0F);
		return new Vector3((213f / 255F) * f, (72f / 255F) * f, (3f / 255F) * f);
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 0.3F - this.getStarBrightness(1.0F);
		return new Vector3((228 / 255.0F) * f, (75 / 255.0F) * f, (1 / 255.0F) * f);

	}

	@Override
	public boolean hasSunset () {
		return false;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		List<Block> list = new LinkedList<>();
		list.add(YzCetiBlocks.B.YZB_METAMORPHIC);
		list.add(YzCetiBlocks.B.YZB_LOOSE_SEDIMENT);
		list.add(YzCetiBlocks.B.YZB_IGNEOUS);
		return list;
	}

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass () {
		BiomeAdaptive.setBodyMultiBiome(Planets.yzcetib);
		return BiomeProviderYzCetiB.class;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.YZCETIB;
	}

	@Override
	public double getYCoordinateToTeleport () {
		return 1000.0D;
	}

	@Override
	public boolean canCoordinateBeSpawn (int var1, int var2) {
		return true;
	}

	@Override
	public boolean isSkyColored () {
		return true;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return ChunkProviderYzCetiB.class;
	}

	@Override
	public long getDayLength () {
		return 23500L;
	}

	@Override
	public float getGravity () {
		return 0.030f;
	}

	@Override
	public int AtmosphericPressure () {
		return 5;
	}

	@Override
	public boolean SolarRadiation () {
		return true;
	}

	@Override
	public double getSolarWindMultiplier () {
		return 0.6D;
	}

	@Override
	public ClassBody getClassBody () {
		return ClassBody.SELENA;
	}

	@Override
	public float getSolarRadiationModify () {
		return 5.0f;
	}

	@Override
	public ExoPlanet getExoPlanet () {
		return (ExoPlanet) getCelestialBody();
	}

	@Override
	public Block getPlanetGrassBlock() {
		return null;
	}
}