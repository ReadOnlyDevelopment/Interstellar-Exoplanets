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
