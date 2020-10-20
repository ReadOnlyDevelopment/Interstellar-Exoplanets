package net.romvoid95.api.world.weather;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.romvoid95.api.space.Calculations;
import net.romvoid95.client.gui.rendering.CloudTexture;
import net.romvoid95.common.CommonUtil;

@EventBusSubscriber
public abstract class MultiCloudProvider extends IRenderHandler {
	public float	cloudSpeed	= 1.0f;
	public long		cloudTicks;
	public long		cloudTicksPrev;

	@SideOnly(Side.CLIENT)
	@Override
	public abstract void render(float partialTicks, WorldClient world, Minecraft mc);
	
	@SideOnly(Side.CLIENT)
	public void renderClouds(float renderPartialTicks, CloudTexture texture) {
		GlStateManager.disableCull();
		Entity entity = CommonUtil.getMinecraft().getRenderViewEntity();
		float yOffset = (float) (entity.lastTickPosY + ((entity.posY - entity.lastTickPosY) * renderPartialTicks));
		byte cloudSections = 4;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		double viewX = (getCloudMovementX(entity.world, cloudTicksPrev, cloudTicks ) * 0.029999999329447746D) / texture.getSpeed();
		double viewZ = (getCloudMovementZ(entity.world, cloudTicksPrev, cloudTicks) * 0.029999999329447746D) / texture.getSpeed();
		float cloudHeight = texture.getHeight() - yOffset;
		viewX = viewX - MathHelper.floor(viewX / 2048.0D) * 2048;
		viewZ = viewZ - MathHelper.floor(viewZ / 2048.0D) * 2048;
		texture.bind();
		GlStateManager.enableBlend();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);

		float r = texture.getColor().getRed();
		float g = texture.getColor().getGreen();
		float b = texture.getColor().getBlue();

		float f17 = MathHelper.floor(viewX) * 0.00390625F;
		float f18 = MathHelper.floor(viewZ) * 0.00390625F;
		float f19 = (float) (viewX - MathHelper.floor(viewX - 6));
		float f20 = (float) (viewZ - MathHelper.floor(viewZ - 6));
		GlStateManager.scale(12.0F, 1.0F, 12.0F);

		for (int pass = 0; pass < 2; ++pass) {
			if (pass == 0) {
				GL11.glColorMask(false, false, false, false);
			} else {
				GL11.glColorMask(true, true, true, true);
			}

			for (int x = -cloudSections + 1; x <= cloudSections; ++x) {
				for (int z = -cloudSections + 1; z <= cloudSections; ++z) {
					vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
					float cU = x * 8;
					float cV = z * 8;
					float cX = cU - f19;
					float cZ = cV - f20;

					if (cloudHeight > -5.0F) {
						vertexbuffer.pos(cX + 0.0F, cloudHeight + 0.0F, cZ + 8.0F).tex(
								((cU + 0.0F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, -1.0F, 0.0F).endVertex();
						vertexbuffer.pos(cX + 8.0F, cloudHeight + 0.0F, cZ + 8.0F).tex(
								((cU + 8.0F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, -1.0F, 0.0F).endVertex();
						vertexbuffer.pos(cX + 8.0F, cloudHeight + 0.0F, cZ + 0.0F).tex(
								((cU + 8.0F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, -1.0F, 0.0F).endVertex();
						vertexbuffer.pos(cX + 0.0F, cloudHeight + 0.0F, cZ + 0.0F).tex(
								((cU + 0.0F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, -1.0F, 0.0F).endVertex();
					}

					if (cloudHeight <= 5.0F) {
						vertexbuffer.pos(cX + 0.0F, (cloudHeight + 4.0F) - 9.765625E-4F, cZ + 8.0F).tex(
								((cU + 0.0F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, 1.0F, 0.0F).endVertex();
						vertexbuffer.pos(cX + 8.0F, (cloudHeight + 4.0F) - 9.765625E-4F, cZ + 8.0F).tex(
								((cU + 8.0F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, 1.0F, 0.0F).endVertex();
						vertexbuffer.pos(cX + 8.0F, (cloudHeight + 4.0F) - 9.765625E-4F, cZ + 0.0F).tex(
								((cU + 8.0F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, 1.0F, 0.0F).endVertex();
						vertexbuffer.pos(cX + 0.0F, (cloudHeight + 4.0F) - 9.765625E-4F, cZ + 0.0F).tex(
								((cU + 0.0F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r, g, b,
										1.0F).normal(0.0F, 1.0F, 0.0F).endVertex();
					}

					if (x > -1) {
						for (int v = 0; v < 8; ++v) {
							vertexbuffer.pos(cX + v + 0.0F, cloudHeight + 0.0F, cZ + 8.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							vertexbuffer.pos(cX + v + 0.0F, cloudHeight + 4.0F, cZ + 8.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							vertexbuffer.pos(cX + v + 0.0F, cloudHeight + 4.0F, cZ + 0.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(-1.0F, 0.0F, 0.0F).endVertex();
							vertexbuffer.pos(cX + v + 0.0F, cloudHeight + 0.0F, cZ + 0.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(-1.0F, 0.0F, 0.0F).endVertex();
						}
					}

					if (x <= 1) {
						for (int v = 0; v < 8; ++v) {
							vertexbuffer.pos((cX + v + 1.0F) - 9.765625E-4F, cloudHeight + 0.0F, cZ + 8.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(1.0F, 0.0F, 0.0F).endVertex();
							vertexbuffer.pos((cX + v + 1.0F) - 9.765625E-4F, cloudHeight + 4.0F, cZ + 8.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 8.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(1.0F, 0.0F, 0.0F).endVertex();
							vertexbuffer.pos((cX + v + 1.0F) - 9.765625E-4F, cloudHeight + 4.0F, cZ + 0.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(1.0F, 0.0F, 0.0F).endVertex();
							vertexbuffer.pos((cX + v + 1.0F) - 9.765625E-4F, cloudHeight + 0.0F, cZ + 0.0F).tex(
									((cU + v + 0.5F) * 0.00390625F) + f17, ((cV + 0.0F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(1.0F, 0.0F, 0.0F).endVertex();
						}
					}

					if (z > -1) {
						for (int v = 0; v < 8; ++v) {
							vertexbuffer.pos(cX + 0.0F, cloudHeight + 4.0F, cZ + v + 0.0F).tex(
									((cU + 0.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, -1.0F).endVertex();
							vertexbuffer.pos(cX + 8.0F, cloudHeight + 4.0F, cZ + v + 0.0F).tex(
									((cU + 8.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, -1.0F).endVertex();
							vertexbuffer.pos(cX + 8.0F, cloudHeight + 0.0F, cZ + v + 0.0F).tex(
									((cU + 8.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, -1.0F).endVertex();
							vertexbuffer.pos(cX + 0.0F, cloudHeight + 0.0F, cZ + v + 0.0F).tex(
									((cU + 0.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, -1.0F).endVertex();
						}
					}

					if (z <= 1) {
						for (int v = 0; v < 8; ++v) {
							vertexbuffer.pos(cX + 0.0F, cloudHeight + 4.0F, (cZ + v + 1.0F) - 9.765625E-4F).tex(
									((cU + 0.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, 1.0F).endVertex();
							vertexbuffer.pos(cX + 8.0F, cloudHeight + 4.0F, (cZ + v + 1.0F) - 9.765625E-4F).tex(
									((cU + 8.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, 1.0F).endVertex();
							vertexbuffer.pos(cX + 8.0F, cloudHeight + 0.0F, (cZ + v + 1.0F) - 9.765625E-4F).tex(
									((cU + 8.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, 1.0F).endVertex();
							vertexbuffer.pos(cX + 0.0F, cloudHeight + 0.0F, (cZ + v + 1.0F) - 9.765625E-4F).tex(
									((cU + 0.0F) * 0.00390625F) + f17, ((cV + v + 0.5F) * 0.00390625F) + f18).color(r,
											g, b, 1.0F).normal(0.0F, 0.0F, 1.0F).endVertex();
						}
					}

					tessellator.draw();
				}
			}
		}
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		GlStateManager.enableCull();
		GL11.glDisable(GL11.GL_ALPHA_TEST);
	}

	private double getCloudMovementX(World world, float cloudTicksPrev, float cloudTicks) {
		return Calculations.interpolateRotation(cloudTicksPrev, cloudTicks, Minecraft.getMinecraft().getRenderPartialTicks());
	}

	private double getCloudMovementZ(World world, float cloudTicksPrev, float cloudTicks) {
		return Calculations.interpolateRotation(cloudTicksPrev, cloudTicks, Minecraft.getMinecraft().getRenderPartialTicks());
	}

	public abstract float getCloudMovementSpeed(World world);

}