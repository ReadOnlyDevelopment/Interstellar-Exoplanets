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

package com.readonlydev.client.render;

import java.io.IOException;
import java.util.function.Function;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.ImmutableList;
import com.readonlydev.ExoInfo;
import com.readonlydev.client.model.RocketModelLoader;
import com.readonlydev.common.entity.EntityTwoPlayerRocket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;

@SideOnly(Side.CLIENT)
public class RocketRenderer extends Render<EntityTwoPlayerRocket> {

	private OBJModel.OBJBakedModel rocketModel;

	public RocketRenderer(RenderManager manager) {
		super(manager);
		this.shadowSize = 1F;
	}

	@SuppressWarnings("deprecation")
	private void updateModel()
	{
		if (this.rocketModel == null)
		{
			try
			{
				IModel model = RocketModelLoader.instance.loadModel(new ResourceLocation(ExoInfo.MODID, "twopersonrocket.obj"));
				Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
				this.rocketModel = (OBJModel.OBJBakedModel) model.bake(new OBJModel.OBJState(ImmutableList.of("Base"), false), DefaultVertexFormats.ITEM, spriteFunction);
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTwoPlayerRocket entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	@Override
	public void doRender(EntityTwoPlayerRocket entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		float pitch = entity.prevRotationPitch + ((entity.rotationPitch - entity.prevRotationPitch) * partialTicks) + 180;
		GlStateManager.disableRescaleNormal();
		GlStateManager.pushMatrix();

		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(pitch, 0.0F, 0.0F, 1.0F);
		GlStateManager.translate(0.0F, entity.getRenderOffsetY(), 0.0F);
		float rollAmplitude = (entity.rollAmplitude / 3) - partialTicks;

		if (rollAmplitude > 0.0F)
		{
			final float i = entity.getLaunched() ? (5 - MathHelper.floor(entity.timeUntilLaunch / 85)) / 10F : 0.3F;
			GlStateManager.rotate(MathHelper.sin(rollAmplitude) * rollAmplitude * i * partialTicks, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(MathHelper.sin(rollAmplitude) * rollAmplitude * i * partialTicks, 1.0F, 0.0F, 1.0F);
		}

		this.updateModel();
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (Minecraft.isAmbientOcclusionEnabled())
		{
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
		}
		else
		{
			GlStateManager.shadeModel(GL11.GL_FLAT);
		}

		GlStateManager.scale(0.8F, 0.8F, 0.8F);
		ClientUtil.drawBakedModel(this.rocketModel);

		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();

		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldRender(EntityTwoPlayerRocket rocket, ICamera camera, double camX, double camY, double camZ)
	{
		AxisAlignedBB axisalignedbb = rocket.getEntityBoundingBox().grow(0.5D, 2.0D, 0.5D);
		return rocket.isInRangeToRender3d(camX, camY, camZ) && camera.isBoundingBoxInFrustum(axisalignedbb);
	}

}
