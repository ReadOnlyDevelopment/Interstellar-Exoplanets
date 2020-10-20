package net.romvoid95.common.astronomy.wolf1061.d;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;

import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.common.astronomy.wolf1061.d.gen.BiomeProviderWolf1061D;
import net.romvoid95.common.astronomy.wolf1061.d.gen.ChunkProviderWolf1061D;
import net.romvoid95.common.config.systems.ConfigSystems;
import net.romvoid95.core.initialization.Planets;

public class WorldProviderWolf1061D extends WorldProviderSpace implements IGalacticraftWorldProvider {
	
	private CloudProviderWolf1061D clouds          = new CloudProviderWolf1061D();

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getCloudRenderer () {
		return clouds;

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
	public boolean canRainOrSnow() {
		return false;
	}

	@Override
	public void updateWeather() {
		super.updateWeather();
	}

	@Override
	public boolean hasSunset() {
		return false;
	}

	@Override
	public long getDayLength() {
		return 240000L;
	}

	@Override
	public boolean shouldForceRespawn() {
		return true;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return ChunkProviderWolf1061D.class;
	}

	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass() {
		return BiomeProviderWolf1061D.class;
	}

	@Override
	public int getAverageGroundLevel() {
		return 70;
	}

	@Override
	public boolean canCoordinateBeSpawn(int var1, int var2) {
		return true;
	}

	@Override
	public float getGravity() {
		return 0.0085F;
	}

	@Override
	public int getHeight() {
		return 1500;
	}

	@Override
	public double getMeteorFrequency() {
		return 0.0D;
	}

	@Override
	public double getFuelUsageMultiplier() {
		return 0;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return tier >= ConfigSystems.wolf_tier;
	}

	@Override
	public float getFallDamageModifier() {
		return 0;
	}

	@Override
	public float getSoundVolReductionAmount() {
		return 0;
	}

	@Override
	public CelestialBody getCelestialBody() {
		return Planets.wolf1061d;
	}

	@Override
	public boolean hasBreathableAtmosphere() {
		return false;
	}

	@Override
	public float getThermalLevelModifier() {
		return 0;
	}

	@Override
	public float getWindLevel() {
		return 0;
	}

	@Override
	public boolean shouldDisablePrecipitation() {
		return true;
	}

	@Override
	public boolean shouldCorrodeArmor() {
		return false;
	}

	@Override
	public DimensionType getDimensionType() {
		return ExoDimensions.WOLF1061_1D;
	}

	@Override
	public boolean isDaytime() {
		final float a = this.world.getCelestialAngle(0F);
		return a < 0.42F || a > 0.58F;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
			return super.calculateCelestialAngle(p_76563_1_, p_76563_3_);
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

}
