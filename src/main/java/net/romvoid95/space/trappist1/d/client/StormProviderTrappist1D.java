package net.romvoid95.space.trappist1.d.client;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.romvoid95.api.space.prefab.WorldProviderWE_ExoPlanet;
import net.romvoid95.api.world.weather.StormProvider;
import net.romvoid95.client.Assets;
import net.romvoid95.client.gui.rendering.Texture;
import net.romvoid95.space.trappist1.d.WorldProviderTrappist1D;

public class StormProviderTrappist1D extends StormProvider {
	
	private Texture RAIN = new Texture(Assets.getTexture("heavyrain"));

	@Override
	public boolean isStormApplicableTo(WorldProvider provider) {
		return provider instanceof WorldProviderTrappist1D;
	}

	@Override
	public boolean isStormActive(World world) {
		WorldProviderWE_ExoPlanet provider = (WorldProviderWE_ExoPlanet) world.provider;
		for (EntityLivingBase player : world.playerEntities) {
			if (player.lastTickPosY < provider.getCloudHeight()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getStormSize() {
		return 64;
	}

	@Override
	public float getStormDownfallSpeed() {
		return 14.0F;
	}

	@Override
	public Texture getStormTexture(World world, Biome biome) {
		return RAIN;
	}

	@Override
	public void spawnParticleOnGround(World world, double pX, double pY, double pZ) {
	}

	@Override
	public void playStormSound(World world, double x, double y, double z) {
	}

}
