package net.romvoid95.common.astronomy.trappist1.d;

import java.util.List;
import java.util.Random;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProviderSpace;
import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.WE_ChunkProvider;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_TerrainGenerator;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IWeatherProvider;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.space.utility.AstronomicalConstants;
import net.romvoid95.api.world.ExoWorldProvider;
import net.romvoid95.api.world.IClimateProvider;
import net.romvoid95.api.world.ICloudProvider;
import net.romvoid95.api.world.IStormProvider;
import net.romvoid95.common.astronomy.trappist1.d.biomes.BiomeOceananic;
import net.romvoid95.common.astronomy.trappist1.d.biomes.Trap1D_Island;
import net.romvoid95.common.astronomy.trappist1.d.client.CloudProviderTrappist1D;
import net.romvoid95.common.astronomy.trappist1.d.client.StormProviderTrappist1D;
import net.romvoid95.core.States;
import net.romvoid95.core.initialization.ExoDimensions;
import net.romvoid95.core.initialization.ExoFluids;
import net.romvoid95.core.initialization.Planets;

public class WorldProviderTrappist1D extends ExoWorldProvider implements IWeatherProvider, IClimateProvider {

	private boolean                raining    = false;
	private float                  targetRain = 0.0F;
	private int                    rainTime   = 100;
	private int                    rainChange = 100;
	public static WE_ChunkProvider chunk;

	private StormProviderTrappist1D storm           = new StormProviderTrappist1D();
	private CloudProviderTrappist1D clouds          = new CloudProviderTrappist1D();
	private IRenderHandler          climateProvider = clouds;

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getCloudRenderer () {
		return climateProvider == null ? climateProvider = new CloudProviderTrappist1D() : climateProvider;

	}

	@Override
	public double getFuelUsageMultiplier () {
		return 0;
	}

	@Override
	public float getFallDamageModifier () {
		return 0;
	}

	@Override
	public double getHorizon () {
		return 150;
	}

	@Override
	public long getDayLength () {
		return 24000L;
	}

	@Override
	public float getGravity () {
		return 0.015f;
	}

	@Override
	public CelestialBody getCelestialBody () {
		return Planets.trappistd;
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
		return null;
	}

	@SideOnly(Side.CLIENT)
	public float getCloudHeight () {
		return 168.0F;
	}

	@SideOnly(Side.CLIENT)
	public Vec3d getCloudColor (float partialTicks) {
		return new Vec3d(0.3D, 0.3D, 0.3D);
	}

	@Override
	public void genSettings (WE_ChunkProvider cp) {
		chunk = cp;

		cp.createChunkGen_List.clear();
		cp.createChunkGen_InXZ_List.clear();
		cp.createChunkGen_InXYZ_List.clear();
		cp.decorateChunkGen_List.clear();

		WE_Biome.setBiomeMap(cp, 1.2D, 6, 1400.0D, 1.1D);

		WE_TerrainGenerator terrainGenerator = new WE_TerrainGenerator();
		terrainGenerator.worldStoneBlock  = States.TRAP1D_STONE_2;
		terrainGenerator.worldSeaGen      = true;
		terrainGenerator.worldSeaGenBlock = ExoFluids.PRESSURED_WATER.getDefaultState();
		terrainGenerator.worldSeaGenMaxY  = 104;
		cp.createChunkGen_List.add(terrainGenerator);

		((WE_ChunkProviderSpace) cp).worldGenerators.clear();
		cp.biomesList.clear();

		WE_Biome.addBiomeToGeneration(cp, new Trap1D_Island());
		WE_Biome.addBiomeToGeneration(cp, new BiomeOceananic());
	}

	@Override
	public float getSolarSize () {
		return 0.3F / this.getCelestialBody().getRelativeDistanceFromCenter().unScaledDistance;
	}

	@Override
	public int getMoonPhase (long worldTime) {
		return (int) (worldTime / this.getDayLength() % 8L + 8L) % 8;
	}

	@Override
	public void onChunkProvider (int cX, int cZ, ChunkPrimer primer) {

	}

	@Override
	public void onPopulate (int x, int z) {}

	@Override
	public void recreateStructures (Chunk chunkIn, int x, int z) {}

	@Override
	public boolean shouldForceRespawn () {
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return WE_ChunkProvider.class;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.TRAPPIST_1D;
	}

	@Override
	public boolean enableAdvancedThermalLevel () {
		return false;
	}

	@Override
	protected float getThermalValueMod () {
		return 2.1F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness (float partialTicks) {
		float angle = this.world.getCelestialAngle(partialTicks);
		float value = 1.0F - (MathHelper.cos(angle * AstronomicalConstants.TWO_PI_F) * 2.0F + 0.25F);
		value = MathHelper.clamp(value, 0.0F, 1.0F);
		return value * value * 0.5F + 0.3F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness (float partialTicks) {
		float f1 = this.world.getCelestialAngle(1.0F);
		float f2 = 1.0F - (MathHelper.cos(f1 * AstronomicalConstants.TWO_PI_F) * 2.0F + 0.2F);
		f2 = MathHelper.clamp(f2, 0.0F, 1.0F);
		f2 = 1.2F - f2;
		return f2 * 0.8F;
	}

	@Override
	public Vector3 getFogColor () {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(182f / 255F * f, 182f / 255F * f, 182f / 255F * f);
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(156f / 255.0F * f, 156f / 255.0F * f, 156f / 255.0F * f);
	}

	@Override
	public boolean isSkyColored () {
		return true;
	}

	@Override
	public boolean hasSunset () {
		return false;
	}

	@Override
	public boolean shouldDisablePrecipitation () {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Particle getParticle (WorldClient world, double x, double y, double z) {
		return null;
	}

	@Override
	public boolean canDoRainSnowIce (net.minecraft.world.chunk.Chunk chunk) {
		return true;
	}

	@Override
	protected void updateWeatherOverride () {
		if (!this.world.isRemote) {
			if (--this.rainTime <= 0) {
				this.raining = !this.raining;
				if (this.raining) {
					this.rainTime = (this.world.rand.nextInt(3600) + 1000);
				}
				else {
					this.rainTime = (this.world.rand.nextInt(2000) + 1000);
				}
			}

			if (--this.rainChange <= 0) {
				this.targetRain = 0.15F + this.world.rand.nextFloat() * 0.45F;
				this.rainChange = (this.world.rand.nextInt(200) + 100);
			}

			float strength = this.world.rainingStrength;
			this.world.prevRainingStrength = strength;
			if (this.raining && strength < this.targetRain) {
				strength += 0.004F;
			}
			else if (!this.raining || strength > this.targetRain) {
				strength -= 0.004F;
			}
			this.world.rainingStrength = MathHelper.clamp(strength, 0.0F, 0.6F);
		}
	}

	@Override
	public void weatherSounds (int j, Minecraft mc, World world, BlockPos blockpos, double xx, double yy, double zz, Random random) {
		if ((int) yy >= blockpos.getY() + 1 && world.getPrecipitationHeight(blockpos).getY() > blockpos.getY()) {
			mc.world.playSound(xx, yy, zz, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.WEATHER, 0.025F, 0.6F
					+ random.nextFloat() * 0.2F, false);
		}
		else {
			mc.world.playSound(xx, yy, zz, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.WEATHER, 0.04F, 0.8F
					+ random.nextFloat() * 0.06F + random.nextFloat() * 0.06F, false);
		}
	}

	@Override
	public int getSoundInterval (float rainStrength) {
		int result = 80 - (int) (rainStrength * 88F);
		return result > 0 ? result : 0;
	}

	@Override
	public ICloudProvider getCloudProvider () {
		return clouds;
	}

	@Override
	public IStormProvider getStormProvider () {
		return storm;
	}

	//	@Override
	//	public float getFogDensity (int x, int y, int z) {
	//		return 0.5F;
	//	}
	//
	//	@Override
	//	public int getFogColor (int x, int y, int z) {
	//		return getColor(66, 70, 80, 10);
	//	}
	//
	//	public static int getColor (int r, int g, int b, int a) {
	//
	//		int color = a << 24 | r << 16 | g << 8 | b;
	//		return color - 16777216;
	//	}

}
