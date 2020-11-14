package net.romvoid95.space.kepler1649.b;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.romvoid95.api.space.prefab.WorldProviderExoPlanet;
import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.core.Planets;
import net.romvoid95.space.kepler1649.KeplerBlocks;
import net.romvoid95.space.kepler1649.b.biomes.BiomeProviderKepler1649B;

public class WorldProviderKepler1649B extends WorldProviderExoPlanet {


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
		return Planets.KEPLER1649B;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return ChunkProviderKepler1649B.class;
	}
	
	@Override
	public Class<? extends BiomeProvider> getBiomeProviderClass() {
		BiomeAdaptive.setBodyMultiBiome(Planets.KEPLER1649B);
		return BiomeProviderKepler1649B.class;
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
	public double getSolarEnergyMultiplier() {
		return 0;
	}

	@Override
	public double getVoidFogYFactor() {
		return 8.0f / 256f;
	}

	@Override
	public IBlockState getWorldStoneBlockState() {
		return KeplerBlocks.Kepler1649B.KEPLERB_STONE.getDefaultState();
	}

	@Override
	public double getYCoordinateToTeleport() {
		return 1500;
	}

	@Override
	public boolean hasSunset() {
		return false;
	}
}
