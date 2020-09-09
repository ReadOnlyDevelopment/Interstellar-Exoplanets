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
import net.romvoid95.core.ExoInfo;
import net.romvoid95.core.ExoplanetsMod;

public class SConfigDimensionID {

	static Configuration config;

	public SConfigDimensionID(File file) {
		SConfigDimensionID.config = new Configuration((file), "1.0");
		SConfigDimensionID.syncConfig(true);
	}

	// yz Ceti
	public static int id_yz_b;
	public static int id_yz_c;
	public static int id_yz_d;

	// wolf 1061
	public static int id_wolf_b;
	public static int id_wolf_c;
	public static int id_wolf_d;

	// trappist 1
	public static int id_trap_b;
	public static int id_trap_c;
	public static int id_trap_d;
	public static int id_trap_e;
	public static int id_trap_f;
	public static int id_trap_g;
	public static int id_trap_h;

	// kepler 1649
	public static int id_kepler_b;
	public static int id_kepler_c;

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

			config.addCustomCategoryComment(CATEGORY_PLANETS_DIMENSION_ID, CATEGORY_PLANETS_DIMENSION_ID_COMMENT);
			config.setCategoryLanguageKey(CATEGORY_PLANETS_DIMENSION_ID, CATEGORY_PLANETS_DIMENSION_ID_LANGKEY);
			config.setCategoryRequiresMcRestart(CATEGORY_PLANETS_DIMENSION_ID, true);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_yz_b", -4101);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Yz Ceti B");
			prop.setLanguageKey("exoplanets.configgui.id_yz_b");
			id_yz_b = prop.getInt(-4101);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_yz_c", -4102);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Yz Ceti C");
			prop.setLanguageKey("exoplanets.configgui.id_yz_c");
			id_yz_c = prop.getInt(-4102);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_yz_d", -4103);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Yz Ceti D");
			prop.setLanguageKey("exoplanets.configgui.id_yz_d");
			id_yz_d = prop.getInt(-4103);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_wolf_b", -4201);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Wolf 1061 B");
			prop.setLanguageKey("exoplanets.configgui.id_wolf_b");
			id_wolf_b = prop.getInt(-4201);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_wolf_c", -4202);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Wolf 1061 C");
			prop.setLanguageKey("exoplanets.configgui.id_wolf_c");
			id_wolf_c = prop.getInt(-4202);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_wolf_d", -4203);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Wolf 1061 D");
			prop.setLanguageKey("exoplanets.configgui.id_wolf_d");
			id_wolf_d = prop.getInt(-4203);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_trap_b", -4501);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Trappist 1 B");
			prop.setLanguageKey("exoplanets.configgui.id_trap_b");
			id_trap_b = prop.getInt(-4501);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_trap_c", -4502);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Trappist 1 C");
			prop.setLanguageKey("exoplanets.configgui.id_trap_c");
			id_trap_c = prop.getInt(-4502);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_trap_d", -4503);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Trappist 1 D");
			prop.setLanguageKey("exoplanets.configgui.id_trap_d");
			id_trap_d = prop.getInt(-4503);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_trap_e", -4504);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Trappist 1 E");
			prop.setLanguageKey("exoplanets.configgui.id_trap_e");
			id_trap_e = prop.getInt(-4504);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_trap_f", -4505);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Trappist 1 F");
			prop.setLanguageKey("exoplanets.configgui.id_trap_f");
			id_trap_f = prop.getInt(-4505);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_trap_g", -4506);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Trappist 1 G");
			prop.setLanguageKey("exoplanets.configgui.id_trap_g");
			id_trap_g = prop.getInt(-4506);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_trap_h", -4507);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Trappist 1 H");
			prop.setLanguageKey("exoplanets.configgui.id_trap_h");
			id_trap_h = prop.getInt(-4507);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_kepler_b", -4301);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Kepler 1649 B");
			prop.setLanguageKey("exoplanets.configgui.id_kepler_b");
			id_kepler_b = prop.getInt(-4301);
			finishProp(prop);

			prop = getConfig(CATEGORY_PLANETS_DIMENSION_ID, "id_kepler_c", -4302);
			prop.setMinValue(-2147483647);
			prop.setMaxValue(2147483647);
			prop.setComment("Kepler 1649 C");
			prop.setLanguageKey("exoplanets.configgui.id_kepler_c");
			id_kepler_c = prop.getInt(-4302);
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

	private static Property getConfig (String cat, String key, int defaultValue) {
		config.moveProperty(CATEGORY_PLANETS_DIMENSION_ID, key, cat);
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
		List<IConfigElement> list          = new ArrayList<IConfigElement>();
		ConfigCategory       configGeneral = config.getCategory(CATEGORY_PLANETS_DIMENSION_ID);
		configGeneral.setComment("Dimension ID's");
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
