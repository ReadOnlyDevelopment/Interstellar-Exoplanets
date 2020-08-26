package net.rom95.client;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
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

}
