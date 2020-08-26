package net.rom95.client.event;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom95.client.model.ModelUtil;
import net.rom95.common.constants.ModelNames;
import net.rom95.core.ExoInfo;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public class ModelEventHandler {

	private List<String> groups = ImmutableList.of("Base");

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onModelBakeEvent (ModelBakeEvent event) {

		for (ModelNames m : ModelNames.getModels()) {

			ModelUtil.replace(event, m.modelName(), m.objFile(), groups, m.modelClass(), TRSRTransformation.identity());

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void loadTextures (TextureStitchEvent.Pre event) {

		for (ModelNames m : ModelNames.getModels()) {
			ModelUtil.registerTexture(event, m.modelName());
		}
	}
}
