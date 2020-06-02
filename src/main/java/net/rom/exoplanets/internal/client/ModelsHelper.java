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

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.rom.exoplanets.internal.MCUtil;

public class ModelsHelper {
	public static void drawBakedModel(IBakedModel model) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder worldrenderer = tessellator.getBuffer();
		worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);

		for (BakedQuad bakedquad : model.getQuads(null, null, 0)) {
			worldrenderer.addVertexData(bakedquad.getVertexData());
		}

		tessellator.draw();
	}

	public static void drawBakedModelColored(IBakedModel model, int color) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder worldrenderer = tessellator.getBuffer();
		worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);

		for (BakedQuad bakedquad : model.getQuads(null, null, 0)) {
			int[] data = bakedquad.getVertexData();
			data[3] = color;
			data[10] = color;
			data[17] = color;
			data[24] = color;
			worldrenderer.addVertexData(data);
		}

		tessellator.draw();
	}

	public static IBakedModel modelFromOBJForge(ResourceLocation loc) throws Exception {
		return modelFromOBJForge(loc, ImmutableList.of("main"));
	}

	public static IBakedModel modelFromOBJForge(ResourceLocation loc, List<String> visibleGroups) throws Exception {
		return modelFromOBJForge(loc, visibleGroups, TRSRTransformation.identity());
	}

	public static IBakedModel modelFromOBJForge(ResourceLocation loc, List<String> visibleGroups,
			IModelState parentState) throws Exception {
		OBJModel model = (OBJModel) ModelLoaderRegistry.getModel(loc);
		Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft()
				.getTextureMapBlocks().getAtlasSprite(location.toString());
		return model.bake(new OBJModel.OBJState(visibleGroups, false, parentState), DefaultVertexFormats.ITEM,
				spriteFunction);
	}

	public static IBakedModel modelFromOBJ(ResourceLocation loc) throws IOException {
		return modelFromOBJ(loc, ImmutableList.of("main"));
	}

	public static IBakedModel modelFromOBJ(ResourceLocation loc, List<String> visibleGroups) throws IOException {
		return modelFromOBJ(loc, visibleGroups, TRSRTransformation.identity());
	}

	public static IBakedModel modelFromOBJ(ResourceLocation loc, List<String> visibleGroups, IModelState parentState)
			throws IOException {
		IModel model = ExoModelLoader.instance.loadModel(loc);
		Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft()
				.getTextureMapBlocks().getAtlasSprite(location.toString());
		return model.bake(new OBJModel.OBJState(visibleGroups, false, parentState), DefaultVertexFormats.ITEM,
				spriteFunction);
	}

	public static IBakedModel getModelFromRegistry(ModelResourceLocation modelResourceLocation) {
		return MCUtil.getClient().getRenderItem().getItemModelMesher().getModelManager()
				.getModel(modelResourceLocation);
	}

	public static IBakedModel getModelFromRegistry(String texturePrefix, String name) {
		return MCUtil.getClient().getRenderItem().getItemModelMesher().getModelManager()
				.getModel(new ModelResourceLocation(texturePrefix + name, "inventory"));
	}
}
