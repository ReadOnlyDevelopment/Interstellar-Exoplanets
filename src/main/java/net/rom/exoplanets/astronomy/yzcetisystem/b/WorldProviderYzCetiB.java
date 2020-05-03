package net.rom.exoplanets.astronomy.yzcetisystem.b;

import java.util.LinkedList;
import java.util.List;

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
import net.rom.api.stellar.impl.planet.ExoPlanet;
import net.rom.api.stellar.world.WorldProviderExoPlanet;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiDimensions;
import net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.BiomeProviderYzCetiB;
import net.rom.exoplanets.init.InitPlanets;

public class WorldProviderYzCetiB extends WorldProviderExoPlanet {

    @Override
    public double getMeteorFrequency() {
        return 2.0;
    }

    @Override
    public float getSolarSize() {
        return 1.5F;
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
        return -2.2F;
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
        return InitPlanets.yzcetib;
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 0.95F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks) {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * ((float) Math.PI * 2.0F)) * 2.0F + 0.25F);
        value = MathHelper.clamp(value, 0.0F, 1.0F);
        return value * value * 0.5F + 0.3F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks) {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * ((float) Math.PI * 2.0F)) * 2.0F + 0.1F);
        value = MathHelper.clamp(value, 0.55F, 1.0F);
        value = 1.0F - value;
        return value * 0.9F;
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
    public Vector3 getSkyColor() {
        return new Vector3(0, 0, 0);
    }

    @Override
    public boolean canRainOrSnow() {
        return false;
    }

    @Override
    public boolean hasSunset() {
        return true;
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
        BiomeAdaptive.setBodyMultiBiome(InitPlanets.yzcetib);
        return BiomeProviderYzCetiB.class;
    }

    @Override
    public DimensionType getDimensionType() {
        return YzCetiDimensions.YZCETIB;
    }

    @Override
    public double getYCoordinateToTeleport() {
        return 120;
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
        return getPlanet().getDayLength();
    }

    @Override
    public float getGravity() {
        return (float) getPlanet().getGravity();
    }

}