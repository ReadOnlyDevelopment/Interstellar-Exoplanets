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

package net.rom.exoplanets.internal;

import java.util.List;
import java.util.Random;

import com.google.common.base.Function;

//import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
//import micdoodle8.mods.galacticraft.core.util.GCLog;
//import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.internal.annotations.UtilClass;
import net.rom.exoplanets.internal.client.ExoModelLoader;
import net.rom.exoplanets.internal.wrapper.ModelTransWrapper;

@UtilClass
public final class MCUtil {

	private static MinecraftServer serverCached;

	private MCUtil() {
		throw new IllegalAccessError("Util Class");
	}

	public static Random getRandom (BlockPos pos) {
		long blockSeed = ((pos.getY() << 28) + pos.getX() + 30000000 << 28) + pos.getZ() + 30000000;
		return new Random(blockSeed);
	}

	/**
	 * Check if this is the client side.
	 *
	 * @return True if and only if we are on the client side
	 */
	public static boolean isClient () {
		return FMLCommonHandler.instance().getSide().isClient();
	}

	/**
	 * Check if this is the server side.
	 *
	 * @return True if and only if we are on the server side
	 */
	public static boolean isServer () {
		return FMLCommonHandler.instance().getSide().isServer();
	}

	/**
	 * Check if this is a deobfuscated (development) environment.
	 *
	 * @return True if and only if we are running in a deobfuscated environment
	 */
	public static boolean isDeobfuscated () {
		return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}

	public static MinecraftServer getServer () {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server == null) {
			return serverCached;
		}
		return server;
	}

	public static Minecraft getClient () {
		return FMLClientHandler.instance().getClient();
	}

	public static void replaceModel (String modid, ModelBakeEvent event, String resLoc, String objLoc, List<String> visibleGroups, Class<? extends ModelTransWrapper> clazz, IModelState parentState, String... variants) {
		OBJModel model;
		try {
			model = (OBJModel) ExoModelLoader.instance.loadModel(new ResourceLocation(modid, objLoc));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft()
				.getTextureMapBlocks().getAtlasSprite(location.toString());
		IBakedModel                                    newModelBase   = model
				.bake(new OBJModel.OBJState(visibleGroups, false, parentState), DefaultVertexFormats.ITEM, spriteFunction);
		IBakedModel                                    newModelAlt    = null;
		if (variants.length == 0) {
			variants = new String[] { "inventory" };
		}
		else if (variants.length > 1 || !variants[0].equals("inventory")) {
			newModelAlt = model.bake(new OBJModel.OBJState(visibleGroups, false, TRSRTransformation
					.identity()), DefaultVertexFormats.ITEM, spriteFunction);
		}

		for (String variant : variants) {
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(modid + ":" + resLoc, variant);
			IBakedModel           object                = event.getModelRegistry().getObject(modelResourceLocation);
			if (object != null) {
				IBakedModel newModel = variant.equals("inventory") ? newModelBase : newModelAlt;
				if (clazz != null) {
					try {
						newModel = clazz.getConstructor(IBakedModel.class).newInstance(newModel);
					}
					catch (Exception e) {
						ExoplanetsMod.logger.bigFatal("ItemModel constructor problem for " + modelResourceLocation);
						e.printStackTrace();
					}
				}
				event.getModelRegistry().putObject(modelResourceLocation, newModel);
			}
		}
	}

}
