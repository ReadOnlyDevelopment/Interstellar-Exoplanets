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

package net.rom.exoplanets.astronomy.yzcetisystem.b;

import org.lwjgl.opengl.GL11;

import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.rom.api.stellar.enums.EnumStarColor;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.Textures;
import net.rom.exoplanets.conf.SConfigCore;

public class SkyProviderB extends SkyProviderBase {
	
	
	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
		GL11.glPopMatrix();
		GL11.glPushMatrix();

		long daylength = ((WorldProviderSpace) this.mc.world.provider).getDayLength();


		f10 =6.0F;
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		GL11.glRotatef(-180.0F, 50.0F, 1.0F, 0.0F);
		GL11.glRotatef(this.getCelestialAngle(daylength / 2), 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.cetiC);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
		
		GL11.glPopMatrix();
		GL11.glPushMatrix();

		f10 = 4.7F;
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		GL11.glRotatef(-180.0F, 1.0F, 1.0F, 0.0F);
		GL11.glRotatef(this.getCelestialAngle(daylength), 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-180.0F, 1.0F, 1.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.cetiD);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
		
		GL11.glPopMatrix();
		GL11.glPushMatrix();
	}

	@Override
	protected int modeLight() {
		return 0;
	}

	@Override
	protected boolean enableBaseImages() {
		return true;
	}

	@Override
	protected float sunSize() {
		return 30.0F;
	}

	@Override
	protected ResourceLocation sunImage() {
		 return SConfigCore.enableRealism ? Textures.redDwarfReal : Textures.redDwarf;
	}

	@Override
	protected boolean enableStar() {
		return true;
	}

	@Override
	protected Vector3 colorSunAura() {
		return EnumStarColor.RED.getColor();
	}

	@Override
	protected Vector3 getAtmosphereColor() {
		return new Vector3(0, 0, 0);
	}

	@Override
	public int addSizeAura() {
		return 20;
	}

}
