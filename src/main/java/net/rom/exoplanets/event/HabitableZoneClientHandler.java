package net.rom.exoplanets.event;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.init.SystemRegister;

/**
 * This was borrowed from 
 * https://github.com/BlesseNtumble/GalaxySpace/blob/1.12.2/src/main/java/galaxyspace/core/handler/GSMapHandler.java
 * 
 * tweaked a little to allow easier setting of Solar Systems habitable zones
 * 
 * All credit goes to BlesseNtumble
 */
public class HabitableZoneClientHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent) {
		if (renderEvent.celestialBody.equals(SystemRegister.yzCeti.getMainStar())) {
			this.RingRender(renderEvent, renderEvent.celestialBody, 75F, 115F);
		}
		if (renderEvent.celestialBody.equals(SystemRegister.wolf1061.getMainStar())) {
			this.RingRender(renderEvent, renderEvent.celestialBody, 75F, 115F);
		}
		if (renderEvent.celestialBody.equals(SystemRegister.hd219134.getMainStar())) {
			this.RingRender(renderEvent, renderEvent.celestialBody, 75F, 115F);
		}
		if (renderEvent.celestialBody.equals(SystemRegister.trappist1.getMainStar())) {
			this.RingRender(renderEvent, renderEvent.celestialBody, 75F, 115F);
		}
	}

	public void RingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent, CelestialBody aroundBody,
			float start, float end) {
		Vector3f mapPos = renderEvent.parentOffset;

		float sum = renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance
				- renderEvent.celestialBody.getRelativeDistanceFromCenter().scaledDistance;

		float xOffset = (float) mapPos.x;
		float yOffset = (float) mapPos.y;

		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection)
			GL11.glColor4f(0.0F, 0.9F, 0.1F, 0.5F);
		else
			GL11.glColor4f(0.3F, 0.1F, 0.1F, 0.0F);

		renderEvent.setCanceled(true);

		final float theta = (float) (2 * Math.PI / 90);
		final float cos = (float) Math.cos(theta);
		final float sin = (float) Math.sin(theta);

		float min = start;
		float max = end;

		float x = max * (renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance + sum);
		float y = 0;

		float temp;
		// OUTER LINE
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i < 90; i++) {

			GL11.glVertex2f(x + xOffset, y + yOffset);

			temp = x;
			x = cos * x - sin * y;
			y = sin * temp + cos * y;

		}

		GL11.glEnd();

		GL11.glBegin(GL11.GL_LINE_LOOP);
		x = min * (renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance - sum);
		y = 0;

		for (int i = 0; i < 90; i++) {
			GL11.glVertex2f(x + xOffset, y + yOffset);

			temp = x;
			x = cos * x - sin * y;
			y = sin * temp + cos * y;
		}
		GL11.glEnd();
		GL11.glColor4f(0.0F, 0.9F, 0.1F, 0.1F);

		GL11.glBegin(GL11.GL_QUADS);
		x = min * (renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance - sum);
		y = 0;
		float x2 = max * (renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance + sum);
		float y2 = 0;

		for (int i = 0; i < 90; i++) {

			GL11.glVertex2f(x2 + xOffset, y2 + yOffset);
			GL11.glVertex2f(x + xOffset, y + yOffset);

			temp = x;
			x = cos * x - sin * y;
			y = sin * temp + cos * y;
			temp = x2;
			x2 = cos * x2 - sin * y2;
			y2 = sin * temp + cos * y2;

			GL11.glVertex2f(x + xOffset, y + yOffset);
			GL11.glVertex2f(x2 + xOffset, y2 + yOffset);
		}
		GL11.glEnd();

	}
}
