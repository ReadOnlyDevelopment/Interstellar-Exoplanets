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
package com.readonlydev.space.trappist1.d.client;

import java.lang.reflect.Field;
import java.util.Random;

import com.readonlydev.client.Assets;
import com.readonlydev.client.gui.rendering.OpenGL;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.IRenderHandler;

public class WeatherRendererTrappistD extends IRenderHandler {
	private final Random  random      = new Random();
	private final float[] rainXCoords = new float[1024];
	private final float[] rainYCoords = new float[1024];

	public WeatherRendererTrappistD() {
		for (int i = 0; i < 32; ++i) {
			float f1 = (float) (i - 16);
			for (int j = 0; j < 32; ++j) {
				float f  = (float) (j - 16);
				float f2 = MathHelper.sqrt(f * f + f1 * f1);
				this.rainXCoords[i << 5 | j] = -f1 / f2;
				this.rainYCoords[i << 5 | j] = f / f2;
			}
		}
	}

	@Override
	public void render (float partialTicks, WorldClient world, Minecraft mc) {
		float  strength = world.getRainStrength(partialTicks);
		float  clouds   = world.provider.getCloudHeight();
		Entity entity   = mc.getRenderViewEntity();
		if (strength > 0.0F) {
			if (entity.posY < clouds) {
				int rendererUpdateCount = 0;
				try {
					Field fieldRUC = mc.entityRenderer.getClass()
							.getDeclaredField(GCCoreUtil.isDeobfuscated() ? "rendererUpdateCount" : "field_78529_t");
					fieldRUC.setAccessible(true);
					rendererUpdateCount = fieldRUC.getInt(mc.entityRenderer);
				}
				catch (Exception e) {}

				OpenGL.pushMatrix();
				OpenGL.enableLighting();
				Tessellator   tessellator   = Tessellator.getInstance();
				BufferBuilder worldrenderer = tessellator.getBuffer();
				GlStateManager.disableCull();
				GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
				GlStateManager.enableBlend();
				OpenGL.blendClear();
				GlStateManager.enableColorMaterial();
				GlStateManager
						.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

				double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks;
				double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks;
				double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks;
				int    l  = MathHelper.floor(d1);

				int r = 4;
				if (mc.gameSettings.fancyGraphics) {
					r = 8;
				}

				int drawFlag = -1;
				worldrenderer.setTranslation(-d0, -d1, -d2);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

				BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
				int                      px         = MathHelper.floor(entity.posX);
				int                      py         = MathHelper.floor(entity.posY);
				int                      pz         = MathHelper.floor(entity.posZ);

				for (int z = pz - r; z <= pz + r; ++z) {
					int indexZ = (z - pz + 16) * 32;
					for (int x = px - r; x <= px + r; ++x) {
						mutablePos.setPos(x, 0, z);
						int yHeight = world.getPrecipitationHeight(mutablePos).getY() + 4 - (int) (4.8F * strength);
						int y       = py - r;
						int ymax    = py + r;

						if (y < yHeight) {
							y = yHeight;
						}

						if (ymax < yHeight) {
							ymax = yHeight;
						}

						int yBase = yHeight;

						if (yHeight < l) {
							yBase = l;
						}

						if (y != ymax) {
							this.random.setSeed((long) (x * x * 3121 + x * 45238971 ^ z * z * 418711 + z * 13761));
							OpenGL.enableCullFace();

							if (drawFlag != 0) {
								if (drawFlag >= 0) {
									tessellator.draw();
								}

								drawFlag = 0;
								mc.getTextureManager().bindTexture(Assets.getTexture("heavyrain"));
								worldrenderer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
							}

							int    index = indexZ + x - px + 16;
							double dx    = (double) this.rainXCoords[index] * 0.5D;
							double dz    = (double) this.rainYCoords[index] * 0.5D;
							double dy    = -((double) (rendererUpdateCount + x * x * 3121 + x * 45238971
									+ z * z * 418711 + z * 13761 & 31) + (double) partialTicks) / 80.0D
									* (3.0D + this.random.nextDouble());
							double yo    = this.random.nextDouble() / 1.8D;
							double xx    = x + 0.5D - entity.posX;
							double zz    = z + 0.5D - entity.posZ;
							float  rr    = MathHelper.sqrt(xx * xx + zz * zz) / r;
							float  alpha = ((1.0F - rr * rr) * 0.5F + 0.5F) * strength / 0.6F;          //0.6F is the max rainstrength on Venus
							mutablePos.setPos(x, yBase, z);
							int    light = world.getCombinedLight(mutablePos, 0);
							int    lx    = light >> 16 & 65535;
							int    ly    = light & 65535;
							double xc    = x + 0.5D;
							double zc    = z + 0.5D;
							worldrenderer.pos(xc - dx, (double) ymax - yo, zc - dz).tex(0.0D, (double) y * 0.25D + dy)
									.color(1.0F, 1.0F, 1.0F, alpha).lightmap(lx, ly).endVertex();
							worldrenderer.pos(xc + dx, (double) ymax - yo, zc + dz).tex(1.0D, (double) y * 0.25D + dy)
									.color(1.0F, 1.0F, 1.0F, alpha).lightmap(lx, ly).endVertex();
							worldrenderer.pos(xc + dx, (double) y - yo, zc + dz).tex(1.0D, (double) ymax * 0.25D + dy)
									.color(1.0F, 1.0F, 1.0F, alpha).lightmap(lx, ly).endVertex();
							worldrenderer.pos(xc - dx, (double) y - yo, zc - dz).tex(0.0D, (double) ymax * 0.25D + dy)
									.color(1.0F, 1.0F, 1.0F, alpha).lightmap(lx, ly).endVertex();
						}
					}
				}

				if (drawFlag >= 0) {
					tessellator.draw();
				}

				worldrenderer.setTranslation(0.0D, 0.0D, 0.0D);
				GlStateManager.enableCull();
				GlStateManager.disableBlend();
				GlStateManager.disableColorMaterial();
				GlStateManager.alphaFunc(516, 0.1F);
				OpenGL.disableLight();
				OpenGL.popMatrix();
			}
		}
	}
}
