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
package net.romvoid95.client;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@UtilityClass
public final class ClientUtil {

	/**
	 * Gets the font renderer.
	 *
	 * @return The minecraft font renderer instance
	 */
	@SideOnly(Side.CLIENT)
	public static FontRenderer getFontRenderer () {
		return Minecraft.getMinecraft().fontRenderer;
	}

	/**
	 * Gets the partial ticks.
	 *
	 * @return The percentage from last update and this update
	 */
	@SideOnly(Side.CLIENT)
	public static float getPartialTicks () {
		return Minecraft.getMinecraft().getRenderPartialTicks();
	}

	/**
	 * Gets the client world.
	 *
	 * @return the client world
	 */
	@SideOnly(Side.CLIENT)
	public static World getClientWorld () {
		return Minecraft.getMinecraft() != null ? Minecraft.getMinecraft().world : null;
	}

	/**
	 * Gets the client player.
	 *
	 * @return the client player
	 */
	@SideOnly(Side.CLIENT)
	public static EntityPlayer getClientPlayer () {
		return Minecraft.getMinecraft() != null ? Minecraft.getMinecraft().player : null;
	}
}
