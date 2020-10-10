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

package net.romvoid95.common;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.obj.OBJLoader;

import net.romvoid95.api.content.block.BlockDefinition;
import net.romvoid95.client.Assets;

public class CommonUtil {

	public static void registerObjectDomain (String id) {
		OBJLoader.INSTANCE.addDomain(id);
	}

	/**
	 * Gets the minecraft.
	 *
	 * @return The minecraft instance
	 */
	public static Minecraft getMinecraft () {
		return Minecraft.getMinecraft();
	}

	/**
	 * Binds the specified texture.
	 * 
	 * @param texture The texture to bind
	 */
	public static void bindTexture (ResourceLocation texture) {
		getMinecraft().getTextureManager().bindTexture(texture);
	}

	/**
	 * Binds a texture using the specified domain and path.
	 * 
	 * @param domain The domain of the texture
	 * @param path   The path to the texture
	 */
	public static void bindTexture (String domain, String path) {
		String locationString = domain + ":" + path;
		Assets.textures.computeIfAbsent(locationString, ResourceLocation::new);
		getMinecraft().getTextureManager().bindTexture(Assets.textures.get(locationString));
	}

	/**
	 * Binds a texture using specified location.
	 * 
	 * @param path The location to the texture
	 */
	public static void bindTexture (String path) {
		String[] parts = ResourceLocation.splitObjectName(path);
		bindTexture(parts[0], parts[1]);
	}

	public static Block getBlock (String modid, String name) {
		List<BlockDefinition> blockList = new ArrayList<>();
		for (BlockDefinition compound : blockList) {
			if (compound.getRegistryName().equals(new ResourceLocation(modid, name))) {
				return compound.getBlock();
			}
		}
		throw new InvalidParameterException("Block '" + modid + ":" + name
				+ "' cannot be found in the Shooting Star registry");
	}

}
