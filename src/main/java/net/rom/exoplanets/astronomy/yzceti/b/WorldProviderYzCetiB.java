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

package net.rom.exoplanets.astronomy.yzceti.b;

import java.util.LinkedList;
import java.util.List;

import asmodeuscore.api.dimension.IAdvancedSpace;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzceti.YzCetiDimensions;
import net.rom.exoplanets.astronomy.yzceti.b.worldgen.BiomeProviderYzCetiB;
import net.rom.exoplanets.init.ExoUniverse;
import net.rom.exoplanets.internal.AstronomicalConstants;
import net.rom.exoplanets.internal.world.WorldProviderExoPlanet;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;

public class WorldProviderYzCetiB extends WorldProviderExoPlanet implements IAdvancedSpace {

    @Override
    public double getMeteorFrequency() {
        return 2.0;
    }

    @Override
    public float getSolarSize() {
        return 2.5F;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 1.4D;
    }

    @Override
    public float getFallDamageModifier() {
        return 0.10F;
    }

    @Override
    public float getSoundVolReductionAmount() {
        return 1.0F;
    }

    @Override
    public float getThermalLevelModifier() {
        return 5.5F;
    }

    @Override
    public float getPlanetTemp() {
        ExoPlanet planet = this.getPlanet();
        float planetTemp = (float) planet.getPlanetTemp();
        if (!this.isDaytime()) {
            planetTemp /= 1.4F;
        }
        return planetTemp;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return ExoUniverse.yzcetib;
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 6.5F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks) {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * AstronomicalConstants.TWO_PI_F) * 2.0F + 0.25F);
        value = MathHelper.clamp(value, 0.0F, 1.0F);
        return value * value * 0.5F + 0.3F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks) {
        float f1 = this.world.getCelestialAngle(1.0F);
        float f2 = 1.0F - (MathHelper.cos(f1 * AstronomicalConstants.TWO_PI_F) * 2.0F + 0.2F);
        f2 = MathHelper.clamp(f2, 0.0F, 1.0F);
        f2 = 1.2F - f2;
        return f2 * 0.8F;
    }

    @Override
    public double getHorizon() {
        return 44.0D;
    }

    @Override
    public int getAverageGroundLevel() {
        return 76;
    }

    @Override
    public Vector3 getFogColor()
    {
        float f = 0.6F - this.getStarBrightness(1.0F);
        return new Vector3(213f / 255F * f, 72f / 255F * f, 3f / 255F * f);        
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 0.3F - this.getStarBrightness(1.0F);
        return new Vector3(228 / 255.0F * f, 75 / 255.0F * f, 1 / 255.0F * f);
       
    }

    @Override
    public boolean canRainOrSnow() {
        return false;
    }

    @Override
    public boolean hasSunset() {
        return false;
    }

    @Override
    public ResourceLocation getDungeonChestType() {
        return null;
    }

    @Override
    public List<Block> getSurfaceBlocks() {
        List<Block> list = new LinkedList<>();
        list.add(YzCetiBlocks.CetiB.B_METAMORPHIC);
        list.add(YzCetiBlocks.CetiB.B_LOOSE_SEDIMENT);
        list.add(YzCetiBlocks.CetiD.D_IGNEOUS);
        return list;
    }

    @Override
    public Class<? extends BiomeProvider> getBiomeProviderClass() {
        BiomeAdaptive.setBodyMultiBiome(ExoUniverse.yzcetib);
        return BiomeProviderYzCetiB.class;
    }

    @Override
    public DimensionType getDimensionType() {
        return YzCetiDimensions.YZCETIB;
    }

    @Override
    public double getYCoordinateToTeleport() {
        return 1500.0D;
    }

    @Override
    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return true;
    }

    @Override
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderYzCetiB.class;
    }

    @Override
    public long getDayLength() {
        return 15000L;
    }

    @Override
    public float getGravity() {
        return getPlanet().getGravity();
    }

	@Override
	public int AtmosphericPressure() {
		return 5;
	}

	@Override
	public boolean SolarRadiation() {
		return true;
	}

	@Override
	public double getSolarWindMultiplier() {
		return 0.6D;
	}

	@Override
	public ClassBody getClassBody() {
		return ClassBody.SELENA;
	}

	@Override
	public float getSolarRadiationModify() {
		return 5.0f;
	}

	@Override
	public ExoPlanet getExoPlanet() {
		return (ExoPlanet) getCelestialBody();
	}

}