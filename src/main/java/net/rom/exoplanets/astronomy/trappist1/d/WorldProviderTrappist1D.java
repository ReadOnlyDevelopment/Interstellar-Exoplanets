package net.rom.exoplanets.astronomy.trappist1.d;

import java.util.List;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProviderSpace;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProviderSpace;
import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.WE_ChunkProvider;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_TerrainGenerator;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.trappist1.TrappistDimensions;
import net.rom.exoplanets.astronomy.trappist1.d.biomes.BiomeOceananic;
import net.rom.exoplanets.init.Planets;
import net.rom.exoplanets.internal.AstronomicalConstants;
import net.rom.exoplanets.util.RGB;

public class WorldProviderTrappist1D extends WE_WorldProviderSpace {

	public static WE_ChunkProvider chunk;

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
		return 5;
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

	@Override
	public ClassBody getClassBody () {
		return ClassBody.OCEANIDE;
	}

	@Override
	public void genSettings (WE_ChunkProvider cp) {
		chunk = cp;

		cp.createChunkGen_List.clear();
		cp.createChunkGen_InXZ_List.clear();
		cp.createChunkGen_InXYZ_List.clear();
		cp.decorateChunkGen_List.clear();

		WE_Biome.setBiomeMap(cp, 2.6D, 4, 1000.0D, 1.0D);

		WE_TerrainGenerator terrainGenerator = new WE_TerrainGenerator();
		terrainGenerator.worldSeaGen      = true;
		terrainGenerator.worldSeaGenBlock = Blocks.WATER.getDefaultState();//ExoFluids.fluidBlockMantle.getDefaultState();
		terrainGenerator.worldSeaGenMaxY  = 95;
		cp.createChunkGen_List.add(terrainGenerator);

		((WE_ChunkProviderSpace) cp).worldGenerators.clear();
		cp.biomesList.clear();

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
	@SideOnly(Side.CLIENT)
	public void getLightmapColors (float partialTicks, float sunBrightness, float skyLight, float blockLight, float[] colors) {
		EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

		if (player != null) {
			int phase = this.getMoonPhase(this.getWorldTime());
			if (skyLight > 0 && sunBrightness > 0.07f && phase != 0 && phase != 6) {

				colors[0] = colors[0] + skyLight + 0.3F;
				colors[1] = colors[1] + skyLight / 6;
			}
		}
	}

	@Override
	public void onChunkProvider (int cX, int cZ, ChunkPrimer primer) {
		//		BlockPos pos = new ChunkPos(cX, cZ).getBlock(0, 126, 0);
		//		new GenSpaceStation(ExoBlocks.metaldecoration.getStateFromMeta(4).getBlock())
		//				.generate(world, new Random(getSeed()), pos);
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
		return TrappistDimensions.TRAPPIST_1D;
	}

	@Override
	public boolean enableAdvancedThermalLevel () {
		return true;
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
		float f = 0.6F - this.getStarBrightness(1.0F);
		RGB   c = RGB.parse("SkyBlue");
		return new Vector3(c.getRed() * f, c.getGreen() * f, c.getBlue() * f);
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 0.3F - this.getStarBrightness(1.0F);
		RGB   c = RGB.parse("SkyBlue");
		return new Vector3(c.getRed() * f, c.getGreen() * f, c.getBlue() * f);

	}

	@Override
	public boolean isSkyColored () {
		return false;
	}

	@Override
	public boolean hasSunset () {
		return false;
	}

	public static int getGroundFromAbove (World world, int x, int z) {
		int     y           = 255;
		boolean foundGround = false;
		while (!foundGround && y-- > 0) {
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			if (block == Blocks.AIR) {
				y = -1;
				break;
			}
			foundGround = block != Blocks.AIR;
		}
		return y;
	}
}
