package net.romvoid95.api.world.weather;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.client.gui.rendering.Texture;

public interface IStormProvider {
	public boolean isStormApplicableTo (WorldProvider provider);

	public boolean isStormActive (World world);

	public float getStormStrength ();

	public float getStormDensity ();

	public int getStormSize ();

	public boolean isStormVisibleInBiome (Biome biome);

	public float getStormDownfallSpeed ();

	public boolean doesLightingApply ();

	public boolean useGroundParticle ();

	public WorldProvider getProvider ();

	@SideOnly(Side.CLIENT)
	public Texture getStormTexture (World world, Biome biome);

	public void spawnParticleOnGround (World world, double pX, double pY, double pZ);

	public void playStormSound (World world, double x, double y, double z);

	public void updateStorm (World world);

	@SideOnly(Side.CLIENT)
	public void renderStorm (float partialTicks, WorldClient world, Minecraft minecraft);
}