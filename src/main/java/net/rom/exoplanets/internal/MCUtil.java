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

package net.rom.exoplanets.internal;

import java.util.List;

import com.google.common.base.Function;

import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;

public final class MCUtil {

	private static MinecraftServer serverCached;

	private MCUtil() {
		throw new IllegalAccessError("Util Class");
	}

	/**
	 * Check if this is the client side.
	 *
	 * @return True if and only if we are on the client side
	 */
	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}

	/**
	 * Check if this is the server side.
	 *
	 * @return True if and only if we are on the server side
	 */
	public static boolean isServer() {
		return FMLCommonHandler.instance().getSide().isServer();
	}

	/**
	 * Check if this is a deobfuscated (development) environment.
	 *
	 * @return True if and only if we are running in a deobfuscated environment
	 */
	public static boolean isDeobfuscated() {
		return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}

	public static MinecraftServer getServer() {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server == null) {
			return serverCached;
		}
		return server;
	}

	public static Minecraft getClient() {
		return FMLClientHandler.instance().getClient();
	}

	public static void replaceModel(String modid, ModelBakeEvent event, String resLoc, String objLoc,
			List<String> visibleGroups, Class<? extends ModelTransformWrapper> clazz, IModelState parentState,
			String... variants) {
		OBJModel model;
		try {
			model = (OBJModel) OBJLoaderGC.instance.loadModel(new ResourceLocation(modid, objLoc));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft()
				.getTextureMapBlocks().getAtlasSprite(location.toString());
		IBakedModel newModelBase = model.bake(new OBJModel.OBJState(visibleGroups, false, parentState),
				DefaultVertexFormats.ITEM, spriteFunction);
		IBakedModel newModelAlt = null;
		if (variants.length == 0) {
			variants = new String[] { "inventory" };
		} else if (variants.length > 1 || !variants[0].equals("inventory")) {
			newModelAlt = model.bake(new OBJModel.OBJState(visibleGroups, false, TRSRTransformation.identity()),
					DefaultVertexFormats.ITEM, spriteFunction);
		}

		for (String variant : variants) {
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(modid + ":" + resLoc, variant);
			IBakedModel object = event.getModelRegistry().getObject(modelResourceLocation);
			if (object != null) {
				IBakedModel newModel = variant.equals("inventory") ? newModelBase : newModelAlt;
				if (clazz != null) {
					try {
						newModel = clazz.getConstructor(IBakedModel.class).newInstance(newModel);
					} catch (Exception e) {
						GCLog.severe("ItemModel constructor problem for " + modelResourceLocation);
						e.printStackTrace();
					}
				}
				event.getModelRegistry().putObject(modelResourceLocation, newModel);
			}
		}
	}

}
