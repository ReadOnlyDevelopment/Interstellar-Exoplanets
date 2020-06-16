package net.rom.exoplanets.client.screen.button;

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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.rom.exoplanets.Assets;

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
