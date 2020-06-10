package net.rom.exoplanets.client.screen.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiAdvanceButton extends GuiButtonExt {
	
	private final ResourceLocation texture;
    private final int bWidth, bHeight;

    public GuiAdvanceButton(int id, int xPos, int yPos, int width, int height, String displayString, ResourceLocation texture) {
		super(id, xPos, yPos, width, height, displayString);
		this.texture = texture;
		this.bWidth = width;
		this.bHeight = height;
	}

	protected void drawBackground(Minecraft minecraft, int hoverState) {
		minecraft.renderEngine.bindTexture(this.texture);
		GlStateManager.color(1, 1, 1, 1);
		drawTexturedModalRect(x, y, 0, 46 + hoverState * 20, bWidth / 2, bHeight / 2);														 // top left
		drawTexturedModalRect(x + bWidth / 2, y, 200 - bWidth / 2, 46 + hoverState * 20, bWidth / 2, bHeight / 2);								 // top right
		drawTexturedModalRect(x, y + bHeight / 2, 0, 46 + hoverState * 20 + 20 - bHeight / 2, bWidth / 2, bHeight / 2);							 // bottom left
		drawTexturedModalRect(x + bWidth / 2, y + bHeight / 2, 200 - bWidth / 2, 46 + hoverState * 20 + 20 - bHeight / 2, bWidth / 2, bHeight / 2);// bottom right
	}

	@Override
	public void drawButton(Minecraft minecraft, int i, int j, float partialTicks) {
		if (visible) {
			boolean mouseOver = i >= x && j >= y && i < x + width && j < y + height;
			int hoverState = getHoverState(mouseOver);
			drawBackground(minecraft, hoverState);
			mouseDragged(minecraft, i, j);
		}
	}
}
