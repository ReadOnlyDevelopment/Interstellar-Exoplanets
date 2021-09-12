package com.readonlydev.space.kepler1649.b;

import java.util.List;

import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.core.ExoplanetsRegistry;
import com.readonlydev.lib.world.biome.BiomeProviderExoplanet;
import com.readonlydev.lib.world.world.WorldProviderExoplanet;
import com.readonlydev.space.kepler1649.KeplerBlocks;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderKepler1649B extends WorldProviderExoplanet {

	@Override
	protected void init() {
		super.init();
		this.biomeProvider = new BiomeProviderExoplanet(this.world.getSeed(), getCelestialBody());
	}
	
	@Override
	public Vec3d getCloudColor(float partialTicks) {
		return new Vec3d(0.5f, 0.43f, 0.5f);
	}

	@Override
	public float getCloudHeight() {
		return 242;
	}

	@Override
	public DimensionType getDimensionType() {
		return ExoDimensions.KEPLER1649_B;
	}
	

	@Override
	public CelestialBody getCelestialBody() {
		return ExoplanetsRegistry.KEPLER_1649_B;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public float getFallDamageModifier() {
		return 0;
	}

	@Override
	public Vec3d getFogColor(float time, float p_76562_2_) {
		float f1 = 0.4f;
		float f2 = 0.3f;
		float f3 = 0.2F;
		f1 = f1 * (0.70F + 0.06F);
		f2 = f2 * (0.84F + 0.06F);
		f3 = f3 * (0.70F + 0.09F);
		return new Vec3d(f1, f2, f3);
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 0.3F - this.getStarBrightness(1.0F);
		return new Vector3(228 / 255.0F * f, 75 / 255.0F * f, 1 / 255.0F * f);

	}

	@Override
	public double getVoidFogYFactor() {
		return 8.0f / 256f;
	}

	public IBlockState getWorldStoneBlockState() {
		return KeplerBlocks.Kepler1649B.KEPLERB_STONE.getDefaultState();
	}

	@Override
	public Vector3 getFogColor() {
		return new Vector3(0.0D, 0.0D, 0.0D);
	}

	@Override
	public float getGravity() {
		return 0;
	}

	@Override
	public double getFuelUsageMultiplier() {
		return 0;
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderKepler1649B(this.world, this.world.getSeed());
	}
}
