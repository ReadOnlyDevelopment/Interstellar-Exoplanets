package net.rom.exoplanets.astronomy.yzcetisystem.b;

import java.util.LinkedList;
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
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.api.implemtations.planet.ExoPlanet;
import net.rom.api.world.WorldProviderExoPlanet;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiDimensions;
import net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.BiomeProviderYzCetiB;
import net.rom.exoplanets.init.BlocksRegister;
import net.rom.exoplanets.init.PlanetsRegister;

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
		float planetTemp = planet.getPlanetTemp();
		if (!this.isDaytime()) {
			planetTemp /= 1.4F;
		}
		return planetTemp;
	}
	
	@Override
	public CelestialBody getCelestialBody() {
		return PlanetsRegister.yzcetib;
	}
	
	@Override
	public double getSolarEnergyMultiplier() {
		return 0.95F;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		float var2 = this.world.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);
		
		if (var3 < 0.0F) {
			var3 = 0.25F;
		}
		if (var3 > 1.0F) {
			var3 = 0.45F;
		}
		return var3 * var3 * 1.0F + 0.6F;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1) {
		float f1 = this.world.getCelestialAngle(1.0F);
		float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);
		
		if (f2 < 0.0F) {
			f2 = 0.6F;
		}
		if (f2 > 1.0F) {
			f2 = 0.95F;
		}
		f2 = 0.95F - f2;
		return f2 * 1.4F;
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
		float f = 0.6F - this.getStarBrightness(1.0F);
		return new Vector3(131 / 255F * f, 3 / 255F * f, 0 / 255F * f);
	}
	
	@Override
	public Vector3 getFogColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(131 / 255F * f, 3 / 255F * f, 0 / 255F * f);
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
		list.add(BlocksRegister.yzb_sedimentary);
		list.add(BlocksRegister.yzb_ingneous);
		return list;
	}
	
	@Override
	protected void renderSky() {
		this.setSkyRenderer(new SkyProviderYzCetiB());
	}
	
	@Override
	protected void renderCloud() {
		this.setCloudRenderer(new CloudRenderer());
	}
	
	@Override
	protected void renderWeather() {
	}
	
	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass() {
		BiomeAdaptive.setBodyMultiBiome(PlanetsRegister.yzcetib);
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
		return 22000L;
	}
	
	@Override
	public float getGravity() {
		return 0.030F;
	}
	
}