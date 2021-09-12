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

package com.readonlydev.space.wolf1061.d;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.readonlydev.client.Assets;

import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.client.FMLClientHandler;

public class SkyProviderWolf1061D extends IRenderHandler {

    public int starGLCallList = GLAllocation.generateDisplayLists(3);
    public int glSkyList;
    public int glSkyList2;

    private float sunSize;

    public SkyProviderWolf1061D()
    {
        this.sunSize = 17.5F * 0.1F;
        GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        final Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldRenderer = tessellator.getBuffer();
        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
        final byte byte2 = 64;
        final int i = 256 / byte2 + 2;
        float f = 16F;

        for (int j = -byte2 * i; j <= byte2 * i; j += byte2)
        {
            for (int l = -byte2 * i; l <= byte2 * i; l += byte2)
            {
                worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
                worldRenderer.pos(j + 0, f, l + 0).endVertex();
                worldRenderer.pos(j + byte2, f, l + 0).endVertex();
                worldRenderer.pos(j + byte2, f, l + byte2).endVertex();
                worldRenderer.pos(j + 0, f, l + byte2).endVertex();
                tessellator.draw();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
        f = -16F;
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

        for (int k = -byte2 * i; k <= byte2 * i; k += byte2)
        {
            for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2)
            {
                worldRenderer.pos(k + byte2, f, i1 + 0).endVertex();
                worldRenderer.pos(k + 0, f, i1 + 0).endVertex();
                worldRenderer.pos(k + 0, f, i1 + byte2).endVertex();
                worldRenderer.pos(k + byte2, f, i1 + byte2).endVertex();
            }
        }

        tessellator.draw();
        GL11.glEndList();
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {

        float var12;
        final Tessellator var23 = Tessellator.getInstance();
        BufferBuilder worldRenderer = var23.getBuffer();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.disableRescaleNormal();
        GL11.glColor3f(1F, 1F, 1F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(0, 0, 0);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(0.7F, 0.7F, 0.7F, 0.7F);
        GL11.glCallList(this.starGLCallList);

        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        // Sun:
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
        var12 = this.sunSize / 4.2F;
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldRenderer.pos(-var12, 90.0D, -var12).endVertex();
        worldRenderer.pos(var12, 90.0D, -var12).endVertex();
        worldRenderer.pos(var12, 90.0D, var12).endVertex();
        worldRenderer.pos(-var12, 90.0D, var12).endVertex();
        var23.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        var12 = this.sunSize / 1.2F;
        //110 distance instead of the normal 100, because there is no atmosphere to make the disk seem larger
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Assets.getCelestialTexture("wolf1061star"));
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(-var12, 90.0D, -var12).tex(0.0D, 0.0D).endVertex();
        worldRenderer.pos(var12, 90.0D, -var12).tex(1.0D, 0.0D).endVertex();
        worldRenderer.pos(var12, 90.0D, var12).tex(1.0D, 1.0D).endVertex();
        worldRenderer.pos(-var12, 90.0D, var12).tex(0.0D, 1.0D).endVertex();
        var23.draw();

        GL11.glPopMatrix();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);

        GL11.glColor3f(70F / 256F, 70F / 256F, 70F / 256F);

        GlStateManager.enableRescaleNormal();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);

        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_BLEND);
    }

    private void renderStars()
    {
        final Random random = new Random(10842L);
        final Tessellator tes = Tessellator.getInstance();
        BufferBuilder buffer = tes.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

        for (int idx = 0; idx < 55000; ++idx)
        {
            double v0 = random.nextFloat() * 2.0F - 1.0F;
            double v1 = random.nextFloat() * 2.0F - 1.0F;
            double v2 = random.nextFloat() * 2.0F - 1.0F;
            double v3 = 0.03F + random.nextFloat() * 0.05F;
            double v4 = v0 * v0 + v1 * v1 + v2 * v2;

            if (v4 < 1.0D && v4 > 0.001D)
            {
            	v4 = 1.0D / Math.sqrt(v4);
                v0 *= v4;
                v1 *= v4;
                v2 *= v4;
                final double vX = v0 * (random.nextDouble() * 130D + 150D);
                final double vY = v1 * (random.nextDouble() * 130D + 150D);
                final double vZ = v2 * (random.nextDouble() * 130D + 150D);
                final double v6 = Math.atan2(v0, v2);
                final double v7 = Math.sin(v6);
                final double v8 = Math.cos(v6);
                final double v9 = Math.atan2(Math.sqrt(v0 * v0 + v2 * v2), v1);
                final double v10 = Math.sin(v9);
                final double v11 = Math.cos(v9);
                final double v12 = random.nextDouble() * Math.PI * 1.01D;
                final double v13 = Math.sin(v12);
                final double v14 = Math.cos(v12);

                for (int i = 0; i < 4; ++i)
                {
                    final double v15 = ((i & 2) - 1) * v3;
                    final double v16 = ((i + 1 & 2) - 1) * v3;
                    final double v17 = v15 * v14 - v16 * v13;
                    final double v18 = v16 * v14 + v15 * v13;
                    final double v19 = -v17 * v11;
                    final double dX = v19 * v7 - v18 * v8;
                    final double dZ = v18 * v7 + v19 * v8;
                    final double dY = v17 * v10; 

                    buffer.pos(vX + dX, vY + dY, vZ + dZ).color(0, 0, 0, random.nextInt(225)).endVertex();
                    //ExoplanetsMod.logger.info("pos(pX + dX, pY + pz, pZ + dZ)");
                    //ExoplanetsMod.logger.info("pos("+vX+" + "+dX+", "+vY+" + "+dY+", "+vZ+" + "+dZ+")");
                }

                
            }
        }

        tes.draw();
    }

    public float getSkyBrightness(float par1)
    {
        final float var2 = FMLClientHandler.instance().getClient().world.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.sin(var2 * Constants.twoPI) * 2.0F + 0.25F);

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        return var3 * var3 * 1F;
    }

//	@Override
//	protected void rendererSky (Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
//		GL11.glEnable(GL11.GL_ALPHA_TEST);
//
//	}
//
//	@Override
//	protected ModeLight modeLight () {
//		return ModeLight.DEFAULT;
//	}
//
//	@Override
//	protected boolean enableBaseImages () {
//		return true;
//	}
//
//	@Override
//	protected float sunSize () {
//		return 5.0F;
//	}
//
//	@Override
//	protected ResourceLocation sunImage () {
//		return Assets.getCelestialTexture("wolf1061star");
//	}
//
//	@Override
//	protected boolean enableStar () {
//		return true;
//	}
//
//	@Override
//	protected StarColor colorSunAura () {
//		return StarColor.RED;
//	}
//
//	@Override
//	protected Vector3 getAtmosphereColor () {
//		return null;
//	}
//
//	@Override
//	public int expandSizeAura () {
//		return 10;
//	}

}
