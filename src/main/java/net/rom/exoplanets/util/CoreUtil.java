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

package net.rom.exoplanets.util;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;

import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.rom.exoplanets.ExoplanetsMod;

public class CoreUtil {

	public static void registerObjectDomain(String id) {
		OBJLoader.INSTANCE.addDomain(id);
	}
	
	public static <T extends TileEntity> void registerTileEntityRenderer(Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}

	public static <T extends Entity> void registerEntityRenderer(Class<T> entityClass,
			IRenderFactory<? super T> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

	public static void registerMultiModelItems(String id, Item item, String name, int max) {
		ModelResourceLocation modelResourceLocation;
		for (int i = 0; i < max; ++i) {
			modelResourceLocation = new ModelResourceLocation(id + name, "inventory");
			registerModel(item, i, modelResourceLocation);
		}
	}

	public static void registerModel(Item item, int meta, ModelResourceLocation location) {
		ModelLoader.setCustomModelResourceLocation(item, meta, location);
	}

	public static void replaceModelDefault(String modID, ModelBakeEvent event, String loc, List<String> visibleGroups,
			Class<? extends ModelTransformWrapper> clazz, String... variants) {
		replaceModelDefault(modID, event, loc, loc + ".obj", visibleGroups, clazz, TRSRTransformation.identity(),
				variants);
	}

	public static void replaceModelDefault(String modID, ModelBakeEvent event, String resLoc, String objLoc,
			List<String> visibleGroups, Class<? extends ModelTransformWrapper> clazz, IModelState parentState,
			String... variants) {
		if (variants.length == 0) {
			variants = new String[] { "inventory" };
		}

		OBJModel model;

		try {
			model = (OBJModel) ModelLoaderRegistry.getModel(new ResourceLocation(modID, objLoc));
			model = (OBJModel) model.process(ImmutableMap.of("flip-v", "true"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft()
				.getTextureMapBlocks().getAtlasSprite(location.toString());
		for (String variant : variants) {
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(modID + ":" + resLoc, variant);
			IBakedModel object = event.getModelRegistry().getObject(modelResourceLocation);
			if (object != null) {
				if (!variant.equals("inventory"))
					parentState = TRSRTransformation.identity();

				IBakedModel newModel = model.bake(new OBJModel.OBJState(visibleGroups, false, parentState),
						DefaultVertexFormats.ITEM, spriteFunction);
				if (clazz != null) {
					try {
						newModel = clazz.getConstructor(IBakedModel.class).newInstance(newModel);
					} catch (Exception e) {
						ExoplanetsMod.logger.bigFatal("ItemModel constructor problem for " + modelResourceLocation);
						e.printStackTrace();
					}
				}
				event.getModelRegistry().putObject(modelResourceLocation, newModel);
			}
		}
	}

}
