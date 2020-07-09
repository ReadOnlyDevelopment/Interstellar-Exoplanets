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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ParametersAreNonnullByDefault
public class StringUtil {

	public static String readFromFile(ResourceLocation location) {
		StringBuilder text = new StringBuilder();
		try {
			String path = "/assets/" + location.getResourceDomain() + "/" + location.getResourcePath();
			InputStream descriptionStream = StringUtil.class.getResourceAsStream(path);
			if (descriptionStream == null)
				return text.toString();
			LineNumberReader descriptionReader = new LineNumberReader(new InputStreamReader(descriptionStream));
			String line;

			while ((line = descriptionReader.readLine()) != null) {
				text.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return text.toString();
	}

	public static String addPrefix(String name, String prefix) {
		if (prefix.endsWith("-")) {
			return prefix.substring(0, prefix.length() - 2) + Character.toLowerCase(name.charAt(0)) + name.substring(1);
		} else {
			return prefix + " " + name;
		}
	}

	public static String addSuffix(String name, String suffix) {
		if (suffix.startsWith("-")) {
			return name + suffix.substring(1);
		} else {
			return name + " " + suffix;
		}
	}

	public static String[] formatVariations(String unlocalizedName, String unlocalizedSuffix, int count) {
		String[] variations = new String[count];
		for (int i = 0; i < count; i++) {
			variations[i] = unlocalizedName + "." + i + "." + unlocalizedSuffix;
		}
		return variations;
	}

	public static boolean isControlKeyDown() {
		return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
	}

	public static boolean isAltKeyDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU);
	}

	@SideOnly(Side.CLIENT)
	public static void drawSplitString(FontRenderer renderer, String strg, int x, int y, int width, int color, boolean shadow) {

		List<String> list = renderer.listFormattedStringToWidth(strg, width);
		for (int i = 0; i < list.size(); i++) {
			String s1 = (String) list.get(i);
			renderer.drawString(s1, x, y + (i * renderer.FONT_HEIGHT), color, shadow);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void renderScaledAsciiString(FontRenderer font, String text, int x, int y, int color, boolean shadow, float scale) {

		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);
		boolean oldUnicode = font.getUnicodeFlag();
		font.setUnicodeFlag(false);

		font.drawString(text, x / scale, y / scale, color, shadow);

		font.setUnicodeFlag(oldUnicode);
		GlStateManager.popMatrix();
	}

	@SideOnly(Side.CLIENT)
	public static void renderSplitScaledAsciiString(FontRenderer font, String text, int x, int y, int color, boolean shadow, float scale, int length) {

		List<String> lines = font.listFormattedStringToWidth(text, (int) (length / scale));
		for (int i = 0; i < lines.size(); i++) {
			renderScaledAsciiString(font, lines.get(i), x, y + (i * (int) (font.FONT_HEIGHT * scale + 3)), color, shadow, scale);
		}
	}
}
