package net.rom.stellar.event;

import org.lwjgl.opengl.GL11;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.core.utils.CoreUtil;
import net.rom.stellar.Exoplanets;
import net.rom.stellar.init.PlanetsRegister;
import net.rom.stellar.init.SystemRegister;

public class HabitableZoneClientHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent) {
		if (renderEvent.celestialBody.equals(PlanetsRegister.yzcetib)) {
			if (CoreUtil.getClient().currentScreen instanceof GuiCelestialSelection)
				GL11.glColor4f(0.0F, 0.0F, 0.7F, 0.5F);
			else
				GL11.glColor4f(0.1F, 0.1F, 0.3F, 1.0F);
			renderEvent.setCanceled(true);
			GL11.glBegin(GL11.GL_LINE_LOOP);

			final float theta = (float) (2 * Math.PI / 90);
			final float cos = (float) Math.cos(theta);
			final float sin = (float) Math.sin(theta);

			float min = 100.0F;
			float max = 32.0F;

			float x = max * renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance;
			float y = 0;

			float temp;
			for (int i = 0; i < 90; i++) {
				GL11.glVertex2f(x, y);

				temp = x;
				x = cos * x - sin * y;
				y = sin * temp + cos * y;
			}

			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);

			x = min * renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance;
			y = 0;

			for (int i = 0; i < 90; i++) {
				GL11.glVertex2f(x, y);

				temp = x;
				x = cos * x - sin * y;
				y = sin * temp + cos * y;
			}

			GL11.glEnd();
			GL11.glColor4f(0.0F, 0.0F, 0.7F, 0.1F);
			GL11.glBegin(GL11.GL_QUADS);

			x = min * renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance;
			y = 0;
			float x2 = max * renderEvent.celestialBody.getRelativeDistanceFromCenter().unScaledDistance;
			float y2 = 0;

			for (int i = 0; i < 90; i++) {
				GL11.glVertex2f(x2, y2);
				GL11.glVertex2f(x, y);

				temp = x;
				x = cos * x - sin * y;
				y = sin * temp + cos * y;
				temp = x2;
				x2 = cos * x2 - sin * y2;
				y2 = sin * temp + cos * y2;

				GL11.glVertex2f(x, y);
				GL11.glVertex2f(x2, y2);
			}

			GL11.glEnd();
		}
	}
}
