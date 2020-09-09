package net.romvoid95.client.model;

import java.util.List;

import com.google.common.base.Function;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.romvoid95.core.ExoInfo;
import net.romvoid95.core.ExoplanetsMod;

@UtilityClass
public final class ModelUtil {

	static String defaultId = ExoInfo.MODID;

	public static void registerTexture (TextureStitchEvent.Pre event, String texture) {
		event.getMap().registerSprite(new ResourceLocation(ExoInfo.MODID, "models/" + texture));
	}

	public static void repalce (ModelBakeEvent event, String resLoc, String objLoc, List<String> visibleGroups, Class<? extends ModelTransWrapper> clazz, IModelState parentState, String... variants) {
		OBJModel model;
		try {
			model = (OBJModel) RocketModelLoader.instance.loadModel(new ResourceLocation(defaultId, objLoc));
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
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(defaultId + ":" + resLoc, variant);
			IBakedModel           object                = event.getModelRegistry().getObject(modelResourceLocation);
			if (object != null) {
				IBakedModel newModel = variant.equals("inventory") ? newModelBase : newModelAlt;
				if (clazz != null) {
					try {
						newModel = clazz.getConstructor(IBakedModel.class).newInstance(newModel);
					}
					catch (Exception e) {
						ExoplanetsMod.logger
								.noticableWarning(true, "ItemModel constructor problem for " + modelResourceLocation);
					}
				}
				event.getModelRegistry().putObject(modelResourceLocation, newModel);
			}
		}
	}

	public static void replace (ModelBakeEvent event, String modelName, String objFile, List<String> groups, Class<? extends ModelTransWrapper> modelClass, TRSRTransformation identity) {
		OBJModel model;
		String[] variants = {};
		try {
			model = (OBJModel) RocketModelLoader.instance.loadModel(new ResourceLocation(defaultId, objFile));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft()
				.getTextureMapBlocks().getAtlasSprite(location.toString());
		IBakedModel                                    newModelBase   = model
				.bake(new OBJModel.OBJState(groups, false, identity), DefaultVertexFormats.ITEM, spriteFunction);
		IBakedModel                                    newModelAlt    = null;
		if (variants.length == 0) {
			variants = new String[] { "inventory" };
		}
		else if (variants.length > 1 || !variants[0].equals("inventory")) {
			newModelAlt = model.bake(new OBJModel.OBJState(groups, false, TRSRTransformation
					.identity()), DefaultVertexFormats.ITEM, spriteFunction);
		}
		for (String variant : variants) {
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(defaultId + ":"
					+ modelName, variant);
			IBakedModel           object                = event.getModelRegistry().getObject(modelResourceLocation);
			if (object != null) {
				IBakedModel newModel = variant.equals("inventory") ? newModelBase : newModelAlt;
				if (modelClass != null) {
					try {
						newModel = modelClass.getConstructor(IBakedModel.class).newInstance(newModel);
					}
					catch (Exception e) {
						ExoplanetsMod.logger
								.noticableWarning(true, "ItemModel constructor problem for " + modelResourceLocation);
						e.printStackTrace();
					}
				}
				event.getModelRegistry().putObject(modelResourceLocation, newModel);
			}
		}

	}
}
