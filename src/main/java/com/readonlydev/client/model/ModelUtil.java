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
package com.readonlydev.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.readonlydev.ExoInfo;
import com.readonlydev.Exoplanets;

import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

@UtilityClass
@SuppressWarnings("deprecation")
public final class ModelUtil {

	static String defaultId = ExoInfo.MODID;

	public static void registerTexture(TextureStitchEvent.Pre event, String texture) {
		event.getMap().registerSprite(new ResourceLocation(ExoInfo.MODID, "models/" + texture));
	}

	public static void repalce(ModelBakeEvent event, String resLoc, String objLoc, List<String> visibleGroups,
			Class<? extends ModelTransWrapper> clazz, IModelState parentState, String... variants) {
		OBJModel model;
		try {
			model = (OBJModel) RocketModelLoader.instance.loadModel(new ResourceLocation(defaultId, objLoc));
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
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(defaultId + ":" + resLoc, variant);
			IBakedModel object = event.getModelRegistry().getObject(modelResourceLocation);
			if (object != null) {
				IBakedModel newModel = variant.equals("inventory") ? newModelBase : newModelAlt;
				if (clazz != null) {
					try {
						newModel = clazz.getConstructor(IBakedModel.class).newInstance(newModel);
					} catch (Exception e) {
						Exoplanets.log.warn("ItemModel constructor problem for " + modelResourceLocation);
					}
				}
				event.getModelRegistry().putObject(modelResourceLocation, newModel);
			}
		}
	}

	public static void replace(ModelBakeEvent event, String modelName, String objFile, List<String> groups,
			Class<? extends ModelTransWrapper> modelClass, TRSRTransformation identity) {
		OBJModel model;
		String[] variants = {};
		try {
			model = (OBJModel) RocketModelLoader.instance.loadModel(new ResourceLocation(defaultId, objFile));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft()
				.getTextureMapBlocks().getAtlasSprite(location.toString());
		IBakedModel newModelBase = model.bake(new OBJModel.OBJState(groups, false, identity), DefaultVertexFormats.ITEM,
				spriteFunction);
		IBakedModel newModelAlt = null;
		if (variants.length == 0) {
			variants = new String[] { "inventory" };
		} else if (variants.length > 1 || !variants[0].equals("inventory")) {
			newModelAlt = model.bake(new OBJModel.OBJState(groups, false, TRSRTransformation.identity()),
					DefaultVertexFormats.ITEM, spriteFunction);
		}
		for (String variant : variants) {
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(defaultId + ":" + modelName,
					variant);
			IBakedModel object = event.getModelRegistry().getObject(modelResourceLocation);
			if (object != null) {
				IBakedModel newModel = variant.equals("inventory") ? newModelBase : newModelAlt;
				if (modelClass != null) {
					try {
						newModel = modelClass.getConstructor(IBakedModel.class).newInstance(newModel);
					} catch (Exception e) {
						Exoplanets.log.warn("ItemModel constructor problem for " + modelResourceLocation);
						e.printStackTrace();
					}
				}
				event.getModelRegistry().putObject(modelResourceLocation, newModel);
			}
		}
	}

	public static void registerToState(Block b, int itemMeta, IBlockState state) {
		registerToState(b, itemMeta, state, new DefaultStateMapper());
	}

	public static void registerToState(Block b, int itemMeta, IBlockState state, IStateMapper stateMapper) {
		ModelResourceLocation mrl = stateMapper.putStateModelLocations(state.getBlock()).get(state);
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), itemMeta, mrl);
	}

	public static <T extends Comparable<T>> void registerToStateSingleVariant(Block b, IProperty<T> variant) {
		registerToStateSingleVariant(b, variant, new DefaultStateMapper());
	}

	public static <T extends Comparable<T>> void registerToStateSingleVariant(Block b, IProperty<T> variant,
			IStateMapper stateMapper) {
		List<T> variants = new ArrayList<>(variant.getAllowedValues());
		for (int i = 0; i < variants.size(); i++) {
			registerToState(b, i, b.getDefaultState().withProperty(variant, variants.get(i)), stateMapper);
		}
	}
}
