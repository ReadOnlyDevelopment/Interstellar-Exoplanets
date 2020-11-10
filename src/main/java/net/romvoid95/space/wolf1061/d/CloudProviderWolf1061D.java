/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid95.space.wolf1061.d;

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
