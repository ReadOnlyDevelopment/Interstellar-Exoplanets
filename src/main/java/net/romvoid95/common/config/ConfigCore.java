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

package net.romvoid95.common.config;

import static net.romvoid95.core.ExoInfo.Constants.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.ExoplanetsMod;
import net.romvoid95.core.ExoInfo;

public class ConfigCore {

	static Configuration config;

	public ConfigCore(File file) {
		ConfigCore.config = new Configuration((file));
		ConfigCore.syncConfig(true);
	}

	public static boolean enableCheckVersion;
	public static boolean enableOverworldOres;
	public static boolean enableDebug;
	public static boolean enableRealism;
	public static int biomeCommandTimeout;

	public static boolean warnBetaBuild;
	public static int     configVersion;
	public static int 	  leavesLightOpacity;

	private static Map<String, List<String>> propOrder = new TreeMap<>();
	private static String                    currentCat;

	public static void syncConfig (boolean load) {
		try {
			propOrder.clear();
			Property prop;
			if (!config.isChild) {
				if (load) {
					config.load();
				}
			}

			config.addCustomCategoryComment(CATEGORY_CORE_PERFORMANCE, CATEGORY_CORE_PERFORMANCE);
			config.addCustomCategoryComment(CATEGORY_CORE, CATEGORY_CORE);
			config.setCategoryLanguageKey(CATEGORY_CORE, LGKEY_CATEGORY_CORE);
			config.setCategoryRequiresMcRestart(CATEGORY_CORE, true);

			prop = getConfig(CATEGORY_CORE, "enableCheckVersion", true);
			prop.setComment("Enable/Disable Check Version");
			prop.setLanguageKey("exoplanets.configgui.enableCheckVersion");
			enableCheckVersion = prop.getBoolean(true);
			finishProp(prop);

			prop = getConfig(CATEGORY_CORE, "enableOverworldOres", true);
			prop.setComment("Enable/Disable Generation Ores on Overworld");
			prop.setLanguageKey("exoplanets.configgui.enableDebug");
			enableOverworldOres = prop.getBoolean(true);
			finishProp(prop);

			prop = getConfig(CATEGORY_CORE, "enableRealism", false);
			prop.setComment("Enabling loads the round & realistic Celestial Body Textures on the Celestial Map");
			prop.setLanguageKey("exoplanets.configgui.enableRealism");
			enableRealism = prop.getBoolean(false);
			finishProp(prop);

			prop = getConfig(CATEGORY_CORE, "warnBetaBuild", true);
			prop.setComment("Set to false to stop the Beta GUI from appearing at startup");
			prop.setLanguageKey("exoplanets.configgui.guiBeta");
			warnBetaBuild = prop.getBoolean(true);
			finishProp(prop);
			
			prop = getConfig(CATEGORY_CORE_PERFORMANCE, "leavesLightOpacity", 1);
			prop.setComment("controls the opacity of leaves, changing the amount of light blocked. \nCan be used to decrease complexity in some lighting checks.");
			prop.setLanguageKey("exoplanets.configgui.leavesLightOpacity");
			prop.setMinValue(0);
			prop.setMaxValue(255);
			leavesLightOpacity = prop.getInt(1);
			finishProp(prop);
			
			prop = getConfig(CATEGORY_CORE, "biomeCommandTimeout", 35000);
			prop.setComment("How long, in ms, the /locatebiome command will search for biomes before timing out, normally 20000ms");
			prop.setLanguageKey("exoplanets.configgui.biomeCommandTimeout");
			biomeCommandTimeout = prop.getInt(35000);
			finishProp(prop);


			// Cleanup older GC config files
			// cleanConfig(config, propOrder);

			if (config.hasChanged()) {
				config.save();
			}
		}
		catch (final Exception e) {
			ExoplanetsMod.logger
			.noticableWarning(true, "Intersteller Core Config had an issue loading the config file!");
		}
	}

	public static void cleanConfig (Configuration config, Map<String, List<String>> propOrder) {
		List<String> categoriesToRemove = new LinkedList<>();
		for (String catName : config.getCategoryNames()) {
			List<String> newProps = propOrder.get(catName);
			if (newProps == null) {
				categoriesToRemove.add(catName);
			}
			else {
				ConfigCategory cat      = config.getCategory(catName);
				List<String>   toRemove = new LinkedList<>();
				for (String oldprop : cat.keySet()) {
					if (!newProps.contains(oldprop)) {
						toRemove.add(oldprop);
					}
				}
				for (String removeMe : toRemove) {
					cat.remove(removeMe);
				}
				config.setCategoryPropertyOrder(catName, propOrder.get(catName));
			}
		}
		for (String catName : categoriesToRemove) {
			config.removeCategory(config.getCategory(catName));
		}
	}

	private static Property getConfig (String cat, String key, boolean defaultValue) {
		config.moveProperty(CATEGORY_CORE, key, cat);
		currentCat = cat;
		return config.get(cat, key, defaultValue);
	}
	
	private static Property getConfig (String cat, String key, int defaultValue) {
		config.moveProperty(CATEGORY_CORE, key, cat);
		currentCat = cat;
		return config.get(cat, key, defaultValue);
	}

	private static void finishProp (Property prop) {
		if (propOrder.get(currentCat) == null) {
			propOrder.put(currentCat, new ArrayList<String>());
		}
		propOrder.get(currentCat).add(prop.getName());
	}

	public static List<IConfigElement> getConfigElements () {
		List<IConfigElement> list          = new ArrayList<>();
		ConfigCategory       configGeneral = config.getCategory(CATEGORY_CORE);
		configGeneral.setComment("Core Settings");
		list.add(new ConfigElement(configGeneral));
		return list;
	}

	@SubscribeEvent
	public void onConfigChanged (ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(ExoInfo.MODID)) {
			config.save();
		}
	}

}
