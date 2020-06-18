package net.rom.exoplanets.events;

import asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.client.screen.GuiFixedCelestialScreen;

public class GuiCelestial {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiOpenEvent(GuiOpenEvent event) {
		if (((event.getGui() instanceof NewGuiCelestialSelection))) {
			if (GameSettings.isKeyDown(KeyHandlerClient.galaxyMap)) {
				event.setGui(new GuiFixedCelestialScreen(true, null, false, null));
				ExoplanetsMod.logger.formatted_Info("Registered Fixed Celestial Selection Screen");
			} else {
				event.setGui(new GuiFixedCelestialScreen(false, null, false, null));
				ExoplanetsMod.logger.formatted_Info("Registered Fixed Celestial Selection Screen");
			}
		}
	}
}
