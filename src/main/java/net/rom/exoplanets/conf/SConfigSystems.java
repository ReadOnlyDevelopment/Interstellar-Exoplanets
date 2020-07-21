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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static net.rom.exoplanets.ExoInfo.Constants.*;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;

public class SConfigSystems {

	static Configuration config;

	public SConfigSystems(File file) {
		SConfigSystems.config = new Configuration((file), "1.1");
		SConfigSystems.syncConfig(true);

	}

	// SYSTEMS

	public static boolean disable_yzceti_system;
	public static boolean disable_wolf_system;
	public static boolean disable_trap_system;
	public static boolean disable_k1649_system;


	// NOT FINISHED
	public static boolean hideUnfinishedSystems;

	// SYSTEM-WIDE-TIER
	public static int yzceti_tier;
	public static int wolf_tier;
	public static int hd_tier;
	public static int trap_tier;
	public static int k1649_tier;

	// SYSTEMS MAP OFFSETS

	public static double[] yzceti_map = { -1.0D, -1.1D };
	public static double[] wolf_map   = { -2.0D, -1.5D };
	public static double[] trap_map   = { 2.0D, -1.5D };
	public static double[] k1649_map  = { 1.3D, -2.6D };

	public static double yzceti_x;
	public static double yzceti_y;

	public static double wolf_x;
	public static double wolf_y;

	public static double hd_x;
	public static double hd_y;

	public static double trap_x;
	public static double trap_y;


	private static Map<String, List<String>> propOrder = new TreeMap<>();
	private static String                    currentCat;

	public static void syncConfig(boolean load) {
		try {
			propOrder.clear();
			Property prop;
			if (!config.isChild) {
				if (load) {
					config.load();
				}
			}

			config.addCustomCategoryComment(CATEGORY_SYSTEMS_GENERAL, SYSTEMS_GENERAL_COMMENT);
			config.addCustomCategoryComment(CATEGORY_SYSTEMS_WIDE_TIERS, SYSTEMS_WIDE_TIERS_COMMENT);
			config.addCustomCategoryComment(CATEGORY_SYSTEMS_MAP_POSITION, SYSTEMS_MAP_POSITION_COMMENT);
			config.setCategoryLanguageKey(CATEGORY_SYSTEMS_GENERAL, SYSTEMS_GENERAL_LANGKEY);
			config.setCategoryLanguageKey(CATEGORY_SYSTEMS_WIDE_TIERS, SYSTEMS_TIER_LANGKEY);
			config.setCategoryLanguageKey(CATEGORY_SYSTEMS_MAP_POSITION, SYSTEMS_MAP_LANGKEY);
			config.setCategoryRequiresMcRestart(CATEGORY_SYSTEMS_GENERAL, true);
			config.setCategoryRequiresMcRestart(CATEGORY_SYSTEMS_WIDE_TIERS, true);
			config.setCategoryRequiresMcRestart(CATEGORY_SYSTEMS_MAP_POSITION, true);


			prop = getConfig(CATEGORY_SYSTEMS_GENERAL, "Disable Yz Ceti System", false);
			prop.setComment("Setting this option to false will disable the Yz Ceti System & Planets");
			prop.setLanguageKey("exoplanets.configgui.toggleyzceti");
			disable_yzceti_system = prop.getBoolean(false);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_GENERAL, "Disable Wolf 1061 System", false);
			prop.setComment("Setting this option to false will disable the Wolf 1061 System & Planets");
			prop.setLanguageKey("exoplanets.configgui.togglewolf1061");
			disable_wolf_system = prop.getBoolean(false);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_GENERAL, "Disable Trappist 1 System", false);
			prop.setComment("Setting this option to false will disable the Trappist 1 System & Planets");
			prop.setLanguageKey("exoplanets.configgui.toggletrappist1");
			disable_trap_system = prop.getBoolean(false);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_GENERAL, "Disable Kepler 1649 System", false);
			prop.setComment("Setting this option to false will disable the Kepler 1649 System & Planets");
			prop.setLanguageKey("exoplanets.configgui.togglekepler1649");
			disable_k1649_system = prop.getBoolean(false);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_GENERAL, "Hide / Disable Unfinished Systems", false);
			prop.setComment("Setting this option to false will disable & hide unfinished Solar Systems & Planets");
			prop.setLanguageKey("exoplanets.configgui.toggleunfinished");
			hideUnfinishedSystems = prop.getBoolean(false);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_WIDE_TIERS, "Yz Ceti Tier", 3);
			prop.setMinValue(-1);
			prop.setMaxValue(3);
			prop.setComment("Set the Rocket Tier Required for Yz Ceti Solar System");
			prop.setLanguageKey("exoplanets.configgui.yzceti_tier");
			yzceti_tier = prop.getInt(3);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_WIDE_TIERS, "Wolf 1061 Tier", 3);
			prop.setMinValue(-1);
			prop.setMaxValue(3);
			prop.setComment("Set the Rocket Tier Required for Wolf 1061 Solar System");
			prop.setLanguageKey("exoplanets.configgui.wolf_tier");
			wolf_tier = prop.getInt(3);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_WIDE_TIERS, "Trappist 1 Tier", 3);
			prop.setMinValue(-1);
			prop.setMaxValue(3);
			prop.setComment("Set the Rocket Tier Required for Trappist 1 System");
			prop.setLanguageKey("exoplanets.configgui.trap_tier");
			trap_tier = prop.getInt(3);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_WIDE_TIERS, "Kepler 1649 Tier", 3);
			prop.setMinValue(-1);
			prop.setMaxValue(3);
			prop.setComment("Set the Rocket Tier Required for Kepler 1649 System");
			prop.setLanguageKey("exoplanets.configgui.kepler_tier");
			k1649_tier = prop.getInt(3);
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_MAP_POSITION, "Yz Ceti System Coords", new double[] { -1.0D, -1.1D });
			prop.setComment("Map Coords for Yz Ceti");
			prop.setLanguageKey("exoplanets.configgui.yzceticoord");
			yzceti_map = prop.getDoubleList();
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_MAP_POSITION, "Wolf 1061 System Coords", new double[] { -2.0D, -1.5D });
			prop.setComment("Map Postition for Wolf 1061");
			prop.setLanguageKey("exoplanets.configgui.wolfcoord");
			wolf_map = prop.getDoubleList();
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_MAP_POSITION, "Trappist 1 System Coords", new double[] { 2.0D, -1.5D });
			prop.setComment("Map Postition for Trappist 1");
			prop.setLanguageKey("exoplanets.configgui.trappistcoord");
			trap_map = prop.getDoubleList();
			finishProp(prop);

			prop = getConfig(CATEGORY_SYSTEMS_MAP_POSITION, "Kepler 1649 System Coords", new double[] { 1.3D, -2.6D });
			prop.setComment("Map Postition for Kepler 1649");
			prop.setLanguageKey("exoplanets.configgui.k1649coord");
			k1649_map = prop.getDoubleList();
			finishProp(prop);

			if (config.hasChanged()) {
				config.save();
			}
		} catch (Exception e) {
			ExoplanetsMod.logger.fatal("exoplanets Systems Configuration File had an issue loding correctly");
		}
	}

	public static void cleanConfig(Configuration config, Map<String, List<String>> propOrder) {
		List<String> categoriesToRemove = new LinkedList<>();
		for (String catName : config.getCategoryNames()) {
			List<String> newProps = propOrder.get(catName);
			if (newProps == null) {
				categoriesToRemove.add(catName);
			} else {
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

	private static Property getConfig(String cat, String key, int defaultValue) {
		config.moveProperty(CATEGORY_SYSTEMS_WIDE_TIERS, key, cat);
		currentCat = cat;
		return config.get(cat, key, defaultValue);
	}

	private static Property getConfig(String cat, String key, double[] defaultValue) {
		config.moveProperty(CATEGORY_SYSTEMS_MAP_POSITION, key, cat);
		currentCat = cat;
		return config.get(cat, key, defaultValue);
	}

	private static Property getConfig(String cat, String key, boolean defaultValue) {
		config.moveProperty(CATEGORY_SYSTEMS_GENERAL, key, cat);
		currentCat = cat;
		return config.get(cat, key, defaultValue);
	}

	private static void finishProp(Property prop) {
		if (propOrder.get(currentCat) == null) {
			propOrder.put(currentCat, new ArrayList<String>());
		}
		propOrder.get(currentCat).add(prop.getName());
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		ConfigCategory configSystemsMain = config.getCategory(CATEGORY_SYSTEMS_GENERAL);
		configSystemsMain.setComment("Solar System Settings");
		list.add(new ConfigElement(configSystemsMain));

		ConfigCategory configSystemsTier = config.getCategory(CATEGORY_SYSTEMS_WIDE_TIERS);
		configSystemsTier.setComment("System Tier Settings");
		list.add(new ConfigElement(configSystemsTier));

		ConfigCategory configPos = config.getCategory(CATEGORY_SYSTEMS_MAP_POSITION);
		configPos.setComment("Systsem Map Position");
		list.add(new ConfigElement(configPos));

		return list;
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(ExoInfo.MODID)) {
			config.save();
		}
	}

}
