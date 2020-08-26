package net.rom95.client.gui.rendering;

import java.awt.Dimension;
import java.awt.Point;

import javax.vecmath.Vector2d;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom95.common.CommonUtil;

@SideOnly(Side.CLIENT)
public class Screen {

	/**
	 * Compatibility version of the ScaledResolution class. Returns the current game
	 * display resolution.
	 * 
	 * @return Returns an instance of the compatibility version of ScaledResolution.
	 */
	public static ScaledResolution scaledDisplayResolution () {
		return new ScaledResolution(CommonUtil
				.getMinecraft(), CommonUtil.getMinecraft().displayWidth, CommonUtil.getMinecraft().displayHeight);
	}

	/**
	 * Scaled mouse position.
	 *
	 * @return Returns a Vector2d instance containing the mouse's scaled coordinates
	 *         in-game.
	 */
	public static Vector2d scaledMousePosition () {
		final int SCALED_WIDTH  = scaledDisplayResolution().getScaledWidth();
		final int SCALED_HEIGHT = scaledDisplayResolution().getScaledHeight();
		final int MOUSE_X       = Mouse.getX() * SCALED_WIDTH / CommonUtil.getMinecraft().displayWidth;
		final int MOUSE_Y       = SCALED_HEIGHT - Mouse.getY() * SCALED_HEIGHT / CommonUtil.getMinecraft().displayHeight
				- 1;
		return new Vector2d(MOUSE_X, MOUSE_Y);
	}

	/**
	 * Display resolution.
	 *
	 * @return Returns the current game display width and height as a Dimension
	 */
	public static Dimension displayResolution () {
		Minecraft mc = CommonUtil.getMinecraft();
		return new Dimension(mc.displayWidth, mc.displayHeight);
	}

	/**
	 * Gets the mouse location.
	 *
	 * @return Returns the mouse location in-game.
	 */
	public static Point getMouseLocation () {
		ScaledResolution size = scaledDisplayResolution();
		Dimension        res  = displayResolution();
		return new Point(Mouse.getX() * size.getScaledWidth() / res.width, size.getScaledHeight()
				- Mouse.getY() * size.getScaledHeight() / res.height - 1);
	}

	@SideOnly(Side.CLIENT)
	public static class ScaledResolution {

		private int scaledWidth, scaledHeight;

		private double scaledWidthD, scaledHeightD;

		private int scaleFactor;

		public ScaledResolution(Minecraft mc, int width, int height) {
			this.scaledWidth  = width;
			this.scaledHeight = height;
			this.scaleFactor  = 1;
			boolean flag  = mc.getLanguageManager().isCurrentLocaleUnicode() || mc.gameSettings.forceUnicodeFont;
			int     scale = mc.gameSettings.guiScale;

			if (scale == 0) {
				scale = 1000;
			}

			while (this.scaleFactor < scale && this.scaledWidth / (this.scaleFactor + 1) >= 320
					&& this.scaledHeight / (this.scaleFactor + 1) >= 240) {
				++this.scaleFactor;
			}

			if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
				--this.scaleFactor;
			}

			this.scaledWidthD  = (double) this.scaledWidth / (double) this.scaleFactor;
			this.scaledHeightD = (double) this.scaledHeight / (double) this.scaleFactor;
			this.scaledWidth   = MathHelper.ceil(this.scaledWidthD);
			this.scaledHeight  = MathHelper.ceil(this.scaledHeightD);
		}

		public int getScaledWidth () {
			return this.scaledWidth;
		}

		public int getScaledHeight () {
			return this.scaledHeight;
		}

		public double getScaledWidth_double () {
			return this.scaledWidthD;
		}

		public double getScaledHeight_double () {
			return this.scaledHeightD;
		}

		public int getScaleFactor () {
			return this.scaleFactor;
		}
	}

}