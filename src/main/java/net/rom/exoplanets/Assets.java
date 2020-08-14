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
package net.rom.exoplanets;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.rom.exoplanets.conf.SConfigCore;

public class Assets {
	/**
	 * Private constructor for singleton.
	 */
	private Assets() {}

	private static final String                   DOMAIN        = ExoInfo.MODID;
	public static Map<String, ResourceLocation>   textures      = new HashMap<>();
	public static Map<String, ResourceLocation>   spaceTextures = new HashMap<>();
	public static Map<String, ResourceLocation[]> textureGroups = new HashMap<>();
	private static Map<String, SoundEvent>        sounds        = new HashMap<>();

	// ==================================================
	//                        Add
	// ==================================================
	// ========== Texture ==========
	public static void addTexture (String name, String domain, String path) {
		name = name.toLowerCase();
		textures.put(name, new ResourceLocation(domain, path));
	}

	public static void addTexture (String name, String path) {
		name = name.toLowerCase();
		textures.put(name, new ResourceLocation(DOMAIN, path));
	}

	public static void addCelestialTexture (String name, String systemName) {
		systemName = systemName.toLowerCase();
		String path = "textures/celestialbodies/" + systemName + "/" + name + ".png";
		spaceTextures.put(name, new ResourceLocation(DOMAIN, path));
	}

	public static void addRealisticCelestialTexture (String name, String systemName) {
		systemName = systemName.toLowerCase();
		String planetName = name.replace("real", "");
		String path       = "textures/celestialbodies/" + systemName + "/realism/" + planetName + ".png";
		spaceTextures.put(name, new ResourceLocation(DOMAIN, path));
	}

	// ==================================================
	//                        Get
	// ==================================================
	// ========== Texture ==========
	public static ResourceLocation getTexture (String name) {
		name = name.toLowerCase();
		if (!textures.containsKey(name))
			return null;
		return textures.get(name);
	}

	public static ResourceLocation getCelestialTexture (String name) {
		if (!SConfigCore.enableRealism) {
			return getCelestial(name);
		}
		else {
			return getRealistic("real" + name);
		}
	}

	private static ResourceLocation getCelestial (String name) {
		name = name.toLowerCase();
		if (!spaceTextures.containsKey(name)) {
			ExoplanetsMod.logger.error("Cannot assign texture " + name + " as it does not exist");
			return null;
		}
		else {
			return spaceTextures.get(name);
		}
	}

	private static ResourceLocation getRealistic (String name) {
		name = name.toLowerCase();
		if (!spaceTextures.containsKey(name)) {
			ExoplanetsMod.logger.error("Cannot assign texture " + name + " as it does not exist");
			return null;
		}
		else {
			return spaceTextures.get(name);
		}
	}
}
