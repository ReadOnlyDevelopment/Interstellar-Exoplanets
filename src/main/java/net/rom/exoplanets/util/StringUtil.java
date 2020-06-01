/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.annotation.ParametersAreNonnullByDefault;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.ResourceLocation;

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
}
