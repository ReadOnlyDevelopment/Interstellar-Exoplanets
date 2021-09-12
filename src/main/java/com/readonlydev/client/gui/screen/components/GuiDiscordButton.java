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
package com.readonlydev.client.gui.screen.components;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_HEIGHT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WIDTH;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glGetTexLevelParameteri;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.opengl.GL11;

import com.readonlydev.client.Assets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class GuiDiscordButton extends GuiButtonBase {
	
	private int imgWidth, imgHeight;

	public GuiDiscordButton(int buttonID, int x, int y, int width, int height,int imgWidth, int imgHeight, String text) {
		super(buttonID, x, y, width, height, text);
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			int buttonX = this.x;
			int buttonY = this.y;
			boolean newHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			this.hovered = newHovered;
			int k = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			mc.getTextureManager().bindTexture(Assets.getTexture("GuiDiscordButton"));
			GuiDiscordButton.drawPartialImage(buttonX, buttonY, 0, (k - 1) * this.imgHeight, this.width, this.height, this.imgWidth, this.imgHeight);

			this.mouseDragged(mc, mouseX, mouseY);
			int textColor = 14737632;

			if (!this.enabled) {
				textColor = -6250336;
			} else if (this.isMouseOver()) {
				textColor = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, buttonX + 5, buttonY + 2, textColor);
		}
	}
	public static void drawPartialImage(int posX, int posY, int imageX, int imageY, int width, int height, int imagePartWidth, int imagePartHeight)
	{
		double imageWidth = glGetTexLevelParameteri(GL_TEXTURE_2D, 0, GL_TEXTURE_WIDTH);
		double imageHeight = glGetTexLevelParameteri(GL_TEXTURE_2D, 0, GL_TEXTURE_HEIGHT);

		double einsTeilerWidth = 1F / imageWidth;
		double uvWidth = einsTeilerWidth * imagePartWidth;
		double uvX = einsTeilerWidth * imageX;

		double einsTeilerHeight = 1F / imageHeight;
		double uvHeight = einsTeilerHeight * imagePartHeight;
		double uvY = einsTeilerHeight * imageY;

		glPushMatrix();

		glTranslatef(posX, posY, 0);
		glBegin(GL_QUADS);

		glTexCoord2d(uvX, uvY);
		glVertex3f(0, 0, 0);
		glTexCoord2d(uvX, uvY + uvHeight);
		glVertex3f(0, height, 0);
		glTexCoord2d(uvX + uvWidth, uvY + uvHeight);
		glVertex3f(width, height, 0);
		glTexCoord2d(uvX + uvWidth, uvY);
		glVertex3f(width, 0, 0);
		glEnd();

		glPopMatrix();
	}
}
