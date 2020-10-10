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

package net.romvoid95.client.gui.screen;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;

import net.romvoid95.common.config.ConfigCore;
import net.romvoid95.common.config.PlanetCoreConfig;
import net.romvoid95.core.ExoInfo;

public class ExoplanetsConfigGuiFactory implements IModGuiFactory {

	public static class ExoCoreConfGui extends GuiConfig {

		public ExoCoreConfGui(GuiScreen parent) {
			super(parent, ConfigCore.getConfigElements(), ExoInfo.MODID, false, true,
					GCCoreUtil.translate("exoplanets.configgui.coretitle"));
			this.configElements.addAll(PlanetCoreConfig.getConfigElements());
			//this.configElements.addAll(SConfigSystems.getConfigElements());
		}
	}

	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ExoCoreConfGui(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

}
