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

package net.rom.exoplanets.client.render;

import java.io.IOException;
import java.util.function.Function;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.api.prefab.entity.EntityAutoRocket;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntityTieredRocket;
import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
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
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.entity.EntityTwoPlayerRocket;
import net.rom.exoplanets.internal.client.ExoModelLoader;

@SideOnly(Side.CLIENT)
public class RocketRenderer extends Render<EntityTwoPlayerRocket> {

    private OBJModel.OBJBakedModel rocketModel;
	
	public RocketRenderer(RenderManager manager) {
		super(manager);
		this.shadowSize = 1F;
	}

    private void updateModel()
    {
        if (this.rocketModel == null)
        {
            try
            {
                IModel model = ExoModelLoader.instance.loadModel(new ResourceLocation(ExoInfo.MODID, "twopersonrocket.obj"));
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
        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 180;
        GlStateManager.disableRescaleNormal();
        GlStateManager.pushMatrix();
        
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(pitch, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0.0F, entity.getRenderOffsetY(), 0.0F);
        float rollAmplitude = entity.rollAmplitude / 3 - partialTicks;

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
