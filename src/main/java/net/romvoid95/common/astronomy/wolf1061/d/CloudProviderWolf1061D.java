package net.romvoid95.common.astronomy.wolf1061.d;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.romvoid95.api.world.weather.MultiCloudProvider;
import net.romvoid95.client.RGB;
import net.romvoid95.client.gui.rendering.CloudTexture;
import net.romvoid95.common.CommonUtil;

@EventBusSubscriber
public class CloudProviderWolf1061D extends MultiCloudProvider {

	public static final CloudTexture	CLOUD	= new CloudTexture("textures/world/dense-clouds.png", 72.0F, 12, RGB.RED);
	public static final CloudTexture	CLOUDS	= new CloudTexture("textures/world/clouds01.png", 86.0F, 6, RGB.DARKRED);
	public static final CloudTexture	CLOUDS1	= new CloudTexture("textures/world/clouds11.png", 102.0F, 2, RGB.ORANGERED);

	@Override
	public float getCloudMovementSpeed(World world) {
		return 4.0f;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		if (world.provider.getCloudRenderer() instanceof MultiCloudProvider) {
			if (CommonUtil.getMinecraft().gameSettings.shouldRenderClouds() >= 1) {
				GL11.glPushMatrix();
				this.renderClouds(partialTicks, CLOUD);
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				this.renderClouds(partialTicks, CLOUDS);
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				this.renderClouds(partialTicks, CLOUDS1);
				GL11.glPopMatrix();
			}
		}
	}
}
