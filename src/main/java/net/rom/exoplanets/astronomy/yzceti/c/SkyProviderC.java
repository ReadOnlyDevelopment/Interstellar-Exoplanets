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

package net.rom.exoplanets.astronomy.yzceti.c;

import org.lwjgl.opengl.GL11;

import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.rom.exoplanets.Assets;
import net.rom.exoplanets.internal.enums.EnumStarColor;

public class SkyProviderC extends SkyProviderBase {
	
	
	


	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
		GL11.glPopMatrix();
        GL11.glPushMatrix();
        
        GL11.glEnable(GL11.GL_BLEND);
        
        f10 = 0.1F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(-180.0F, 50.0F, 1.0F, 0.0F);
        GL11.glRotatef(90F, 190.0F, 50.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Assets.getCelestialTexture("yzcetib"));
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
        buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
        buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
        buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
        tessellator.draw();		
        
        f10 = 0.5F;
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);  
        GL11.glRotatef(140.0F, 0.0F, 0.0F, 1.0F);	
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Assets.getCelestialTexture("yzcetid"));
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
        buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
        buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
        buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
        tessellator.draw();
    
        GL11.glDisable(GL11.GL_BLEND);
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
		return 10.0F;
	}

	@Override
	protected ResourceLocation sunImage() {
		return Assets.getCelestialTexture("yzcetistar");
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
		return 15;
	}

}
