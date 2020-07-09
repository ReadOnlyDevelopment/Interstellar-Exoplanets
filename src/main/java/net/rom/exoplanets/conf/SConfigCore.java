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

package net.rom.exoplanets.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;

public class SConfigCore {

	static Configuration config;

	public SConfigCore(File file) {
		SConfigCore.config = new Configuration((file), "1.0");
		SConfigCore.syncConfig(true);
	}

	public static boolean enableCheckVersion;
	public static boolean enableOverworldOres;
	public static boolean enableDebug;
	public static boolean enableRealism;
	
	public static boolean warnBetaBuild;
	public static int configVersion;
	
	private static String CATEGORY_GENERAL_MAIN = "Core Interstellar: Exoplanets Settings";
	private static String CATEGORY_INTERNAL = "Core Internals !DO NOT CHANGE!";



	public static void syncConfig(boolean load) {
		try {
			if (!config.isChild) {
				if (load) {
					config.load();
				}
			}
			
			config.addCustomCategoryComment(CATEGORY_GENERAL_MAIN, "The Core Settings for Exoplanets");
			config.setCategoryLanguageKey(CATEGORY_GENERAL_MAIN, "exoplanets.configgui.category.generalmain");
			config.setCategoryRequiresMcRestart(CATEGORY_GENERAL_MAIN, true);
			
			config.addCustomCategoryComment(CATEGORY_INTERNAL, "Core Internals !DO NOT CHANGE!");
			config.setCategoryLanguageKey(CATEGORY_INTERNAL, "exoplanets.configgui.category.interals");
			

			enableCheckVersion = config.getBoolean("enableCheckVersion", CATEGORY_GENERAL_MAIN, true, "Enable/Disable Check Version", "exoplanets.configgui.enableCheckVersion");
			enableOverworldOres = config.getBoolean("enableOverworldOres", CATEGORY_GENERAL_MAIN, false, "Enable/Disable Generation Ores on Overworld", "exoplanets.configgui.enableOverworldOres");
			enableDebug = config.getBoolean("enableDebug", CATEGORY_GENERAL_MAIN, false, "Enable/Disable Debug mode", "exoplanets.configgui.enableDebug");
			enableRealism = config.getBoolean("enableRealism", CATEGORY_GENERAL_MAIN, false, "Enabling Realism loads the round & realistic Celestial Body Textures on the Celestial Map", "exoplanets.configgui.enableRealism");

			warnBetaBuild = config.getBoolean("warnOnBetaBuild", CATEGORY_INTERNAL, true, "DO NOT CHANGE");
			
			if (config.hasChanged()) {
				config.save();
			}
		} catch (final Exception e) {
			ExoplanetsMod.logger.bigError("Intersteller Core Config had an issue loading the config file!");
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		ConfigCategory configGeneral = config.getCategory(CATEGORY_GENERAL_MAIN);
		configGeneral.setComment("Core Settings");
		list.add(new ConfigElement(configGeneral));
		


		return list;
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(ExoInfo.MODID)) {
			config.save();
		}
	}

}
