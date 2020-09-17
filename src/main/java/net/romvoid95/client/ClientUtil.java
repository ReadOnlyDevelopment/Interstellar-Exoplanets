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
