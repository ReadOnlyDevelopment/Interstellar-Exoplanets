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

package net.rom.exoplanets.astronomy.kepler1649.c;

import org.lwjgl.opengl.GL11;

import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.rom.exoplanets.Assets;

public class SkyProviderKepler1649c extends SkyProviderBase {

	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
		GL11.glPushMatrix();		
		World world = mc.world;
		int phase = world.provider.getMoonPhase(world.getWorldTime());
		
		GL11.glRotatef(this.mc.world.getCelestialAngle(ticks) * 360.0F, 0.0F, 0.0F, 1.0F);         
		this.renderImage(Assets.getCelestialTexture("kepler1649b"), -90F, 182F, 35F, 2.0F);
		
		GL11.glPushMatrix();
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        
        GL11.glRotatef(35F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(2F, 0.0F, 0.0F, 1.0F);
		this.renderSunAura(tessellator, 0.0F, 0.8F);
		GL11.glRotatef(5F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-2F, 0.0F, 0.0F, 1.0F);
		this.renderSunAura(tessellator, 0.0F, 0.5F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
    GL11.glPopMatrix();
	}

//	private void renderIImage(ResourceLocation image, float x, float y, float z, float f10) {
//
//
//		Tessellator tessellator1 = Tessellator.getInstance();
//		BufferBuilder worldRenderer = tessellator1.getBuffer();
//
//		GL11.glRotatef(x, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(y, 1.0F, 0.0F, 0.0F);
//		GL11.glRotatef(z, 0.0F, 0.0F, 1.0F);
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//		FMLClientHandler.instance().getClient().renderEngine.bindTexture(image);
//
//		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//		worldRenderer.pos(-f10, -100.0D, f10).tex(0, 1).endVertex();
//		worldRenderer.pos(f10, -100.0D, f10).tex(1, 1).endVertex();
//		worldRenderer.pos(f10, -100.0D, -f10).tex(1, 0).endVertex();
//		worldRenderer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
//		tessellator1.draw();
//
//	}

	@Override
	protected ModeLight modeLight() {
		return ModeLight.DEFAULT;
	}

	@Override
	protected boolean enableBaseImages() {
		return true;
	}

	@Override
	protected float sunSize() {
		return 3.0F;
	}

	@Override
	protected ResourceLocation sunImage() {
		return Assets.getCelestialTexture("kepler1649star");
	}

	@Override
	protected boolean enableStar() {
		return true;
	}

	@Override
	protected StarColor colorSunAura() {
		return StarColor.RED;
	}

	@Override
	protected Vector3 getAtmosphereColor() {
		return null;
	}

	@Override
	public int expandSizeAura() {
		return 7;
	}

}
