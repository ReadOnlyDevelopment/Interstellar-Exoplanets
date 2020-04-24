//package net.rom.exoplanets.astronomy.yzcetisystem.d;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
//import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
//import micdoodle8.mods.galacticraft.api.vector.Vector3;
//import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
//import net.minecraft.block.Block;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.DimensionType;
//import net.minecraft.world.biome.BiomeProvider;
//import net.minecraft.world.chunk.Chunk;
//import net.minecraft.world.gen.IChunkGenerator;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import net.rom.api.implemtations.planet.ExoPlanet;
//import net.rom.api.world.WorldProviderExoPlanet;
//import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiDimensions;
//import net.rom.exoplanets.astronomy.yzcetisystem.c.worldgen.BiomeProviderYzCetiC;
//import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.BiomeDecoratorYzCetiD;
//import net.rom.exoplanets.init.BlocksRegister;
//import net.rom.exoplanets.init.PlanetsRegister;
//
//public class WorldProviderYzCetiD extends WorldProviderExoPlanet {
//
//	@Override
//	public float getSolarSize() {
//		return 2.0F;
//	}
//
//	@Override
//	public double getMeteorFrequency() {
//		return 2.0;
//	}
//
//	@Override
//	public double getFuelUsageMultiplier() {
//		return 3.8D;
//	}
//
//	@Override
//	public boolean hasBreathableAtmosphere() {
//		return this.getPlanet().isBreathable();
//	}
//
//	@Override
//	public float getFallDamageModifier() {
//		return 0.56F;
//	}
//
//	@Override
//	public float getSoundVolReductionAmount() {
//		return 0.0F;
//	}
//
//	@Override
//	public float getThermalLevelModifier() {
//		return 2.5F;
//	}
//
//	@Override
//	public float getPlanetTemp() {
//		ExoPlanet planet = this.getPlanet();
//		float planetTemp = planet.getPlanetTemp();
//
//		if (this.isDaytime()) {
//			planetTemp *= 4.5F;
//		} else {
//			planetTemp = planet.getPlanetTemp();
//		}
//		return planetTemp;
//	}
//
//	@Override
//	public double getSolarEnergyMultiplier() {
//		return 8.65;
//	}
//
//	@Override
//	public double getYCoordinateToTeleport() {
//		return 150;
//	}
//
//	@Override
//	public float getCloudHeight() {
//		return 128F;
//	}
//
//	@Override
//	public Vector3 getFogColor() {
//		float f = 1.1F - this.getStarBrightness(1.0F);
//		return new Vector3(232F / 255F * f, 180F / 255F * f, 56F / 255F * f);
//	}
//
//	@Override
//	public Vector3 getSkyColor() {
//		float f = 1.15F - this.getStarBrightness(1.0F);
//		return new Vector3(255 / 255F * f, 241 / 255F * f, 91 / 255F * f);
//	}
//
//	@Override
//	public boolean canRainOrSnow() {
//		return this.getPlanet().isDoesRain();
//	}
//
//	@Override
//	public boolean hasSunset() {
//		return true;
//	}
//
//	@Override
//	public boolean shouldDisablePrecipitation() {
//		return !this.canRainOrSnow();
//	}
//
//	@Override
//	public boolean canDoRainSnowIce(Chunk chunk) {
//		return this.canRainOrSnow();
//	}
//
//	@Override
//	public boolean canRespawnHere() {
//		return this.shouldForceRespawn();
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public float getStarBrightness(float par1) {
//		float f1 = this.world.getCelestialAngle(par1);
//		float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.30F);
//
//		if (f2 < 0.0F) {
//			f2 = 0.0F;
//		}
//		if (f2 > 1.0F) {
//			f2 = 1.0F;
//		}
//		return f2 * f2 * 1.8F;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public float getSunBrightness(float par1) {
//		float f1 = this.world.getCelestialAngle(1.0F);
//		float f2 = 0.9F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);
//
//		if (f2 < 0.0F) {
//			f2 = 0.0F;
//		}
//		if (f2 > 1.0F) {
//			f2 = 1.0F;
//		}
//		f2 = 1.0F - f2;
//		return f2 * 1.5F;
//	}
//
//	@Override
//	public CelestialBody getCelestialBody() {
//		return PlanetsRegister.yzcetid;
//	}
//
//	@Override
//	public double getHorizon() {
//		return 44.0D;
//	}
//
//	@Override
//	public int getAverageGroundLevel() {
//		return 76;
//	}
//
//	@Override
//	public boolean canCoordinateBeSpawn(int var1, int var2) {
//		return true;
//	}
//
//	@Override
//	public ResourceLocation getDungeonChestType() {
//		return null;
//	}
//
//	@Override
//	public List<Block> getSurfaceBlocks() {
//		ArrayList<Block> blockList = new ArrayList<Block>();
//		blockList.add(BlocksRegister.YZC_SEDIMENTARY);
//		return blockList;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	protected void renderSky() {
//		// this.setSkyRenderer(new SkyProviderAtheon(this));
//	}
//
//	@Override
//	protected void renderCloud() {
//		this.setCloudRenderer(new CloudRenderer());
//	}
//
//	@Override
//	protected void renderWeather() {
//	}
//
//	@Override
//	public DimensionType getDimensionType() {
//		return YzCetiDimensions.YZCETIC;
//	}
//
//	@Override
//	public boolean isSkyColored() {
//		return true;
//	}
//
//	@Override
//	public Class<? extends BiomeProvider> getBiomeProviderClass() {
//		BiomeAdaptive.setBodyMultiBiome(PlanetsRegister.yzcetid);
//		return BiomeDecoratorYzCetiD.class;
//	}
//
//	@Override
//	public Class<? extends IChunkGenerator> getChunkProviderClass() {
//		return ChunkProviderYzCetiD.class;
//	}
//
//	@Override
//	public long getDayLength() {
//		return 22000L;
//	}
//	
//	@Override
//	public float getGravity() {
//		return 0.030F;
//	}
//
//}