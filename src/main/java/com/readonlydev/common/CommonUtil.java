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

package com.readonlydev.common;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.obj.OBJLoader;

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
}
