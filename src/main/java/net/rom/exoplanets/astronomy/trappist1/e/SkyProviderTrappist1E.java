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

package net.rom.exoplanets.astronomy.trappist1.e;

import org.lwjgl.opengl.GL11;

import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.rom.exoplanets.Assets;
import net.rom.exoplanets.internal.enums.EnumStarColor;

public class SkyProviderTrappist1E extends SkyProviderBase {

	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		long daylength = ((WorldProviderSpace) this.mc.world.provider).getDayLength();
		if (!this.mc.world.isRaining()) {
			renderIImage(Assets.getCelestialTexture("trappist1b"), 10.0F, 0.F, this.getCelestialAngle(daylength), 1.1F);
			renderIImage(Assets.getCelestialTexture("trappist1c"), 25.0F, 0.F, this.getCelestialAngle(daylength), 1.4F);
			renderIImage(Assets.getCelestialTexture("trappist1d"), 35.0F, 0.F, this.getCelestialAngle(daylength), 1.8F);
			renderIImage(Assets.getCelestialTexture("trappist1f"), -20.0F, 0.F, this.getCelestialAngle(daylength),
					1.4F);
			renderIImage(Assets.getCelestialTexture("trappist1g"), -35.0F, 0.F, this.getCelestialAngle(daylength),
					1.1F);
			renderIImage(Assets.getCelestialTexture("trappist1h"), -44.0F, 0.F, this.getCelestialAngle(daylength),
					0.9F);
		}
		GL11.glDisable(GL11.GL_BLEND);

	}

	private void renderIImage(ResourceLocation image, float x, float y, float z, float f10) {


		Tessellator tessellator1 = Tessellator.getInstance();
		BufferBuilder worldRenderer = tessellator1.getBuffer();

		GL11.glRotatef(x, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(y, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(z, 0.0F, 0.0F, 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(image);

		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		worldRenderer.pos(-f10, -100.0D, f10).tex(0, 1).endVertex();
		worldRenderer.pos(f10, -100.0D, f10).tex(1, 1).endVertex();
		worldRenderer.pos(f10, -100.0D, -f10).tex(1, 0).endVertex();
		worldRenderer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator1.draw();

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
		return 5.0F;
	}

	@Override
	protected ResourceLocation sunImage() {
		return Assets.getCelestialTexture("trappist1star");
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
		return null;
	}

	@Override
	public int addSizeAura() {
		return 10;
	}

}
