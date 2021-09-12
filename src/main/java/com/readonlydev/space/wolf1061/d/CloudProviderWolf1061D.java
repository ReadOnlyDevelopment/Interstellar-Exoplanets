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
package com.readonlydev.space.wolf1061.d;

import org.lwjgl.opengl.GL11;

import com.readonlydev.api.world.weather.MultiCloudProvider;
import com.readonlydev.client.RGB;
import com.readonlydev.client.gui.rendering.CloudTexture;
import com.readonlydev.common.CommonUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class CloudProviderWolf1061D extends MultiCloudProvider {

	public static final CloudTexture	CLOUD	= new CloudTexture("textures/world/dense-clouds.png", 72.0F, 9, new RGB(0.6F, 0.5F, 0.1F));
	public static final CloudTexture	CLOUDS	= new CloudTexture("textures/world/clouds01.png", 86.0F, 4, new RGB(0.8F, 0.2F, 0.0F));
	public static final CloudTexture	CLOUDS1	= new CloudTexture("textures/world/clouds11.png", 102.0F, 7, new RGB(0.7F, 0.3F, 0.0F));

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
