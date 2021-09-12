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
package com.readonlydev.client.event;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.google.common.collect.ImmutableList;
import com.readonlydev.ExoInfo;
import com.readonlydev.client.gui.screen.GuiBeta;
import com.readonlydev.client.model.ModelUtil;
import com.readonlydev.common.config.ConfigCore;
import com.readonlydev.common.constants.ModelNames;
import com.readonlydev.core.SolarSystemRegistry;
import com.readonlydev.lib.celestial.HabitableZone;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public final class ClientEventHandler {

	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {

		for (ModelNames m : ModelNames.getModels()) {
			ModelUtil.replace(event, m.modelName(), m.objFile(), ImmutableList.of("Base"), m.modelClass(), TRSRTransformation.identity());
		}
	}

	@SubscribeEvent
	public void loadTextures(TextureStitchEvent.Pre event) {

		for (ModelNames m : ModelNames.getModels()) {
			ModelUtil.registerTexture(event, m.modelName());
		}
	}

	@SubscribeEvent
	public void onRingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent) {

		if (renderEvent.celestialBody.equals(SolarSystemRegistry.kepler1649_a)) {
			Vector3f mapPos = SolarSystemRegistry.kepler1649_a.getParentSolarSystem().getMapPosition().toVector3f();
			HabitableZone hz = SolarSystemRegistry.kepler1649_a.getHabitableZone();
			render(renderEvent, mapPos, hz.getConservativeInnerLimit(), hz.getConservativeOuterLimit());
			render(renderEvent, mapPos, hz.getOptimisticInnerLimit(), hz.getOptimisticOuterLimit());
		}
		if (renderEvent.celestialBody.equals(SolarSystemRegistry.trappist1_a)) {
			Vector3f mapPos = SolarSystemRegistry.trappist1_a.getParentSolarSystem().getMapPosition().toVector3f();
			HabitableZone hz = SolarSystemRegistry.trappist1_a.getHabitableZone();
			render(renderEvent, mapPos, hz.getConservativeInnerLimit(), hz.getConservativeOuterLimit());
			render(renderEvent, mapPos, hz.getOptimisticInnerLimit(), hz.getOptimisticOuterLimit());
		}
		if (renderEvent.celestialBody.equals(SolarSystemRegistry.wolf1061_a)) {
			Vector3f mapPos = SolarSystemRegistry.wolf1061_a.getParentSolarSystem().getMapPosition().toVector3f();
			HabitableZone hz = SolarSystemRegistry.wolf1061_a.getHabitableZone();
			render(renderEvent, mapPos, hz.getConservativeInnerLimit(), hz.getConservativeOuterLimit());
			render(renderEvent, mapPos, hz.getOptimisticInnerLimit(), hz.getOptimisticOuterLimit());
		}
		if (renderEvent.celestialBody.equals(SolarSystemRegistry.yzCeti_a)) {
			Vector3f mapPos = SolarSystemRegistry.yzCeti_a.getParentSolarSystem().getMapPosition().toVector3f();
			HabitableZone hz = SolarSystemRegistry.yzCeti_a.getHabitableZone();
			render(renderEvent, mapPos, hz.getConservativeInnerLimit(), hz.getConservativeOuterLimit());
			render(renderEvent, mapPos, hz.getOptimisticInnerLimit(), hz.getOptimisticOuterLimit());
		}
	}

	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		GuiScreen gui = event.getGui();
		if (ConfigCore.warnBetaBuild && (gui instanceof GuiMainMenu)) {

			event.setGui(new GuiBeta((GuiMainMenu) gui));
			ConfigCore.warnBetaBuild = false;
			MinecraftForge.EVENT_BUS.post(
					new ConfigChangedEvent.OnConfigChangedEvent(ExoInfo.MODID, ExoInfo.NAME, false, false));
		}
	}

	public void render(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent, Vector3f pos, float start, float end) {

		float xX = pos.x;
		float yY = pos.y;

		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection) {
			GL11.glColor4f(0.0F, 0.9F, 0.1F, 0.5F);
		} else {
			GL11.glColor4f(0.3F, 0.1F, 0.1F, 0.0F);
		}

		renderEvent.setCanceled(true);

		final float theta = (float) ((2 * Math.PI) / 90);
		final float cos = (float) Math.cos(theta);
		final float sin = (float) Math.sin(theta);

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
			x = (cos * x) - (sin * y);
			y = (sin * temp) + (cos * y);

		}

		GL11.glEnd();

		GL11.glBegin(GL11.GL_LINE_LOOP);
		x = min;
		y = 0;

		for (int i = 0; i < 90; i++) {
			GL11.glVertex2f(x + xX, y + yY);

			temp = x;
			x = (cos * x) - (sin * y);
			y = (sin * temp) + (cos * y);
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
			x = (cos * x) - (sin * y);
			y = (sin * temp) + (cos * y);
			temp = x2;
			x2 = (cos * x2) - (sin * y2);
			y2 = (sin * temp) + (cos * y2);

			GL11.glVertex2f(x + xX, y + yY);
			GL11.glVertex2f(x2 + xX, y2 + yY);
		}
		GL11.glEnd();

	}
}
