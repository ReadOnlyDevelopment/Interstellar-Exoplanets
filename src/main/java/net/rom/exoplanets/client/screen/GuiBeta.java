/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.client.screen;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.conf.SConfigCore;
import net.rom.exoplanets.util.RGB;

@SideOnly(Side.CLIENT)
public class GuiBeta extends GuiScreen {

    private static final Logger log = LogManager.getLogger();
	private static final ResourceLocation[] background = new ResourceLocation[] { new ResourceLocation(ExoInfo.MODID, "textures/gui/teleport.png") };
	private final GuiMainMenu guiMainMenu;

	public GuiBeta(GuiMainMenu guiMainMenu) {
		this.guiMainMenu = guiMainMenu;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		GuiButton toMenu = new GuiButton(0, this.width / 2 + 160, this.height / 4 + 180, I18n.format("gui.done"));
		GuiButton discord = new GuiButton(1, this.width / 2 - 200, this.height / 4 + 180, I18n.format("exoplanets.gui.discordLink"));
		//GuiButtonExt setBoolean = new GuiButtonExt(2,this.width / 2, this.height / 4 + 180, 100, 20, getBoolean(SConfigCore.warnBetaBuild));
		toMenu.setWidth(50);
		discord.setWidth(150);
		
		this.buttonList.addAll(Arrays.asList(toMenu, discord));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		mc.renderEngine.bindTexture(background[0]);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos(0 + 0, 0 + height, zLevel).tex(0, 1).endVertex();
		vertexbuffer.pos(0 + width, 0 + height, zLevel).tex(1, 1).endVertex();
		vertexbuffer.pos(0 + width, 0 + 0, zLevel).tex(1, 0).endVertex();
		vertexbuffer.pos(0 + 0, 0 + 0, zLevel).tex(0, 0).endVertex();
		tessellator.draw();

		int x = this.width / 2;
		int y = this.height / 4;
		Gui.drawRect(x - 250, y - 20, x + 260, y + 220, new RGB(68, 68, 68, 200).getColorWithA());

		for (int i = 0; i < 6; i++) {
			String s = I18n.format("information." + ExoInfo.MODID + ":beta." + (i + 1));
			this.drawCenteredString(this.fontRenderer, s, x, y, 0xFFFFFF);
			y += 12;
		}
		this.drawCenteredString(this.fontRenderer, "Running Version: " + ExoInfo.FULL_VERSION, x, y, 0xFFFFFF);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		
		switch (button.id) {
			case 0:
				Minecraft.getMinecraft().displayGuiScreen(this.guiMainMenu);
				break;
			case 1:
	            try {
	            	String discordUrl = "https://discord.gg/fscJ2gG";
					ReflectionHelper.findField(GuiScreen.class, "clickedLinkURI", "field_175286_t").set(this, new URI(discordUrl));
	                mc.displayGuiScreen(new GuiConfirmOpenLink(this, discordUrl, 31102009, false));
				} catch (IllegalArgumentException | IllegalAccessException | URISyntaxException e) {
					log.error("Exception when discord link menu button clicked:", e);
	                button.displayString = I18n.format("exoplanets.gui.failed");
	                button.enabled = false;				
	                e.printStackTrace();
				}
			default:
				break;

		}
	}
	
	private static String getBoolean(boolean configOption) {
		return (!configOption) ? "ShowGUI: True" : "ShowGUI: False";
	}
}
