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
package net.rom.exoplanets.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class Rendering {
	private static ResourceLocation WHITE = null;
	private static ResourceLocation BLANK = null;

	public static ResourceLocation getWhiteImageResource () {
		if (WHITE != null) {
			return WHITE;
		}
		if (Minecraft.getMinecraft().getTextureManager() == null) {
			return null;
		}
		BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		i.setRGB(0, 0, Color.WHITE.getRGB());
		ResourceLocation r = Minecraft.getMinecraft().getTextureManager()
				.getDynamicTextureLocation("whiteback", new DynamicTexture(i));
		WHITE = r;
		return r;
	}

	public static ResourceLocation getBlankImageResource () {
		if (BLANK != null) {
			return BLANK;
		}
		if (Minecraft.getMinecraft().getTextureManager() == null) {
			return null;
		}
		BufferedImage i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		i.setRGB(0, 0, new Color(255, 255, 255, 0).getRGB());
		ResourceLocation r = Minecraft.getMinecraft().getTextureManager()
				.getDynamicTextureLocation("blankback", new DynamicTexture(i));
		BLANK = r;
		return r;
	}

	public static void setScale (float scale) {
		GL11.glPushMatrix();
		GlStateManager.enableBlend();
		GL11.glPushMatrix();
		GL11.glScaled(scale, scale, scale);
	}

	public static void postScale () {
		GL11.glPopMatrix();
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

}
