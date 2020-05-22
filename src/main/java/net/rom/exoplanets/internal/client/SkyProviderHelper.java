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

package net.rom.exoplanets.internal.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class SkyProviderHelper {
	
	public static void renderPlanet(Tessellator tessellator, BufferBuilder buffer, float f10, long dl, ResourceLocation planet, float rotate) {
		GL11.glScalef(0.6F, 0.6F, 0.6F);			
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);		
		GL11.glRotatef(dl , 1.0F, 1.0F, 0.0F);
		GL11.glRotatef(rotate, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(planet);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
	}

	public static void renderPlanet(Tessellator tessellator, BufferBuilder buffer, float f10, float dl, ResourceLocation planet, float rotate) {
		GL11.glScalef(0.6F, 0.6F, 0.6F);			
		GL11.glRotatef(0.0F, 1.0F, 0.0F, 1.0F);		
		GL11.glRotatef(dl , 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(rotate, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(planet);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
	}
}
