package net.romvoid95.client.event;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.client.RGB;
import net.romvoid95.client.gui.screen.GuiBeta;
import net.romvoid95.client.model.ModelUtil;
import net.romvoid95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.romvoid95.common.config.SConfigCore;
import net.romvoid95.common.constants.ModelNames;
import net.romvoid95.core.ExoInfo;
import net.romvoid95.core.initialization.ExoFluids;
import net.romvoid95.core.initialization.SolarSystems;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public class ClientEventHandler {

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

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRingRender (CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent) {

		if (renderEvent.celestialBody.equals(SolarSystems.yzCeti.getMainStar())) {
			this.RingRender(renderEvent, SolarSystems.yzCeti, 80F, 120F);
		}
		if (renderEvent.celestialBody.equals(SolarSystems.wolf1061.getMainStar())) {
			this.RingRender(renderEvent, SolarSystems.wolf1061, 50F, 100F);
		}
		if (renderEvent.celestialBody.equals(SolarSystems.kepler1649.getMainStar())) {
			this.RingRender(renderEvent, SolarSystems.kepler1649, 75F, 115F);
		}
		if (renderEvent.celestialBody.equals(SolarSystems.trappist1.getMainStar())) {
			this.RingRender(renderEvent, SolarSystems.trappist1, 75F, 115F);
		}
	}

	@SubscribeEvent
	public void onGuiOpen (GuiOpenEvent event) {
		GuiScreen gui = event.getGui();
		if (SConfigCore.warnBetaBuild && gui instanceof GuiMainMenu) {

			event.setGui(new GuiBeta((GuiMainMenu) gui));
			SConfigCore.warnBetaBuild = false;
			MinecraftForge.EVENT_BUS
					.post(new ConfigChangedEvent.OnConfigChangedEvent(ExoInfo.MODID, ExoInfo.NAME, false, false));
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onRenderFogDensity (EntityViewRenderEvent.FogDensity event) {
		if (event.getEntity().world.provider instanceof WorldProviderTrappist1D) {
			event.setDensity(0.09f);
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			event.setCanceled(true);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent()
	public static void onRenderFogColor (EntityViewRenderEvent.FogColors event) {
		RGB   color = new RGB(ExoFluids.pressurizedMaterial.getMaterialMapColor().colorValue);
		float blue  = color.getBlue();
		float red   = color.getRed();
		float green = color.getGreen();
		if (event.getEntity().isInsideOfMaterial(ExoFluids.pressurizedMaterial)) {
			event.setRed(red);
			event.setGreen(green);
			event.setBlue(blue);
		}
	}

	public void RingRender (CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent, SolarSystem solarSystem, float start, float end) {
		Vector3f mapPos = solarSystem.getMapPosition().toVector3f();

		float xX = mapPos.x;
		float yY = mapPos.y;

		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection)
			GL11.glColor4f(0.0F, 0.9F, 0.1F, 0.5F);
		else
			GL11.glColor4f(0.3F, 0.1F, 0.1F, 0.0F);

		renderEvent.setCanceled(true);

		final float theta = (float) (2 * Math.PI / 90);
		final float cos   = (float) Math.cos(theta);
		final float sin   = (float) Math.sin(theta);

		float min = start;
		float max = end;

		float x = max;
		float y = 0;

		float temp;
		// OUTER LINE
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i < 90; i++) {

			GL11.glVertex2f(x + xX, y + yY);

			temp = x;
			x    = cos * x - sin * y;
			y    = sin * temp + cos * y;

		}

		GL11.glEnd();

		GL11.glBegin(GL11.GL_LINE_LOOP);
		x = min;
		y = 0;

		for (int i = 0; i < 90; i++) {
			GL11.glVertex2f(x + xX, y + yY);

			temp = x;
			x    = cos * x - sin * y;
			y    = sin * temp + cos * y;
		}
		GL11.glEnd();
		GL11.glColor4f(0.0F, 0.9F, 0.1F, 0.1F);

		GL11.glBegin(GL11.GL_QUADS);
		x = min;
		y = 0;
		float x2 = max;
		float y2 = 0;

		for (int i = 0; i < 90; i++) {

			GL11.glVertex2f(x2 + xX, y2 + yY);
			GL11.glVertex2f(x + xX, y + yY);

			temp = x;
			x    = cos * x - sin * y;
			y    = sin * temp + cos * y;
			temp = x2;
			x2   = cos * x2 - sin * y2;
			y2   = sin * temp + cos * y2;

			GL11.glVertex2f(x + xX, y + yY);
			GL11.glVertex2f(x2 + xX, y2 + yY);
		}
		GL11.glEnd();

	}

}
