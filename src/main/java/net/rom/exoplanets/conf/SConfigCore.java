/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import net.rom.exoplanets.internal.LogHelper;

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
			enableOverworldOres = config.getBoolean("enableDebug", CATEGORY_GENERAL_MAIN, false, "Enable/Disable Generation Ores on Overworld", "exoplanets.configgui.enableOverworldOres");
			enableDebug = config.getBoolean("enableOverworldOres", CATEGORY_GENERAL_MAIN, false, "Enable/Disable Debug mode", "exoplanets.configgui.enableDebug");
			enableRealism = config.getBoolean("enableRealism", CATEGORY_GENERAL_MAIN, false, "Enabling Realism loads the round & realistic Celestial Body Textures on the Celestial Map", "exoplanets.configgui.enableRealism");

			warnBetaBuild = config.getBoolean("warnOnBetaBuild", CATEGORY_INTERNAL, true, "DO NOT CHANGE");
			configVersion = config.getInt("configVersion", CATEGORY_INTERNAL, 1, 1, 1, "DO NOT CHANGE");
			
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
