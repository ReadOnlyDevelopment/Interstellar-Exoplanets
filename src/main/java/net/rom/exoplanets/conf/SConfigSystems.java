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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;

public class SConfigSystems {

	static Configuration config;

	public SConfigSystems(File file) {
		SConfigSystems.config = new Configuration((file), "1.0");
		SConfigSystems.syncConfig(true);

	}

	// SYSTEMS
	public static boolean disable_yzceti_system; // bcde

	// NOT FINISHED
	public static boolean hideUnfinishedSystems;

	// SYSTEM-WIDE-TIER
	public static int yzceti_tier;
	public static int ups_tier;
	public static int wolf_tier;
	public static int hd_tier;
	public static int trap_tier;
	public static int k1649_tier;

	// SYSTEMS MAP OFFSETS
	public static double yzceti_x;
	public static double yzceti_y;

	public static double wolf_x;
	public static double wolf_y;

	public static double hd_x;
	public static double hd_y;

	public static double trap_x;
	public static double trap_y;

	public static double k1649_x;
	public static double k1649_y;

	private static String CATEGORY_GENERAL_SYSTEMS_CONFIG = "general systems configuration";
	private static String CATEGORY_SYSTEM_WIDE_TIERS = "solar system wide tiers";
	private static String CATEGORY_SYSTEMS_MAP_POSITION = "map postition";

	public static void syncConfig(boolean load) {
		try {

			config.load();

			config.addCustomCategoryComment(CATEGORY_GENERAL_SYSTEMS_CONFIG, "General Solar System Configurations");
			config.addCustomCategoryComment(CATEGORY_SYSTEM_WIDE_TIERS, "Change to Set the Required Tier for Each Planet In A Solar System");
			config.addCustomCategoryComment(CATEGORY_SYSTEMS_MAP_POSITION, "Change the Map Position of Exoplanets Solar Systems");
			config.setCategoryLanguageKey(CATEGORY_GENERAL_SYSTEMS_CONFIG, "exoplanets.configgui.category.generalsystemsconf");
			config.setCategoryLanguageKey(CATEGORY_SYSTEM_WIDE_TIERS, "exoplanets.configgui.category.systemstier");
			config.setCategoryLanguageKey(CATEGORY_SYSTEMS_MAP_POSITION, "exoplanets.configgui.category.systemsmappos");
			config.setCategoryRequiresMcRestart(CATEGORY_GENERAL_SYSTEMS_CONFIG, true);
			config.setCategoryRequiresMcRestart(CATEGORY_SYSTEM_WIDE_TIERS, true);
			config.setCategoryRequiresMcRestart(CATEGORY_SYSTEMS_MAP_POSITION, true);

			disable_yzceti_system = config.getBoolean("Disable Yz Ceti System", CATEGORY_GENERAL_SYSTEMS_CONFIG, false,
					"Setting this option to false will disable the Yz Ceti System & Planets", "exoplanets.configgui.toggleyzceti");
			hideUnfinishedSystems = config.getBoolean("Hide / Disable Unfinished Systems", CATEGORY_GENERAL_SYSTEMS_CONFIG, false,
					"Setting this option to false will disable & hide unfinished Solar Systems & Planets", "exoplanets.configgui.toggleunfinished");

			yzceti_tier = config.getInt("Tier Required for Yz Ceti", CATEGORY_SYSTEM_WIDE_TIERS, 3, -1, 10, "Set the Rocket Tier Required for Yz Ceti Solar System",
					"exoplanets.configgui.yzceti_tier");
			wolf_tier = config.getInt("Tier Required for Wolf 1061", CATEGORY_SYSTEM_WIDE_TIERS, 3, -1, 10, "Set the Rocket Tier Required for Wolf 1061 Solar System",
					"exoplanets.configgui.wolf_tier");
			hd_tier = config.getInt("Tier Required for HD 219134", CATEGORY_SYSTEM_WIDE_TIERS, 3, -1, 10, "Set the Rocket Tier Required for HD 219134 System",
					"exoplanets.configgui.hd_tier");
			trap_tier = config.getInt("Tier Required for Trappist 1", CATEGORY_SYSTEM_WIDE_TIERS, 3, -1, 10, "Set the Rocket Tier Required for Trappist 1 System",
					"exoplanets.configgui.trap_tier");

			yzceti_x = config.getFloat("Yz Ceti X", CATEGORY_SYSTEMS_MAP_POSITION, 1.3F, -1000.0F, 1000.0F, "X Coord for the Yz Ceti System", "exoplanets.configgui.yzceti_x");
			yzceti_y = config.getFloat("Yz Ceti Y", CATEGORY_SYSTEMS_MAP_POSITION, -2.14F, -1000.0F, 1000.0F, "Y Coord for the Yz Ceti System", "exoplanets.configgui.yzceti_y");

			wolf_x = config.getFloat("Wolf 1061 X", CATEGORY_SYSTEMS_MAP_POSITION, -1.5F, -1000.0F, 1000.0F, "Y Coord for the Wolf 1061 System", "exoplanets.configgui.wolf_x");
			wolf_y = config.getFloat("Wolf 1061 Y", CATEGORY_SYSTEMS_MAP_POSITION, 1.1F, -1000.0F, 1000.0F, "Y Coord for the Wolf 1061 System", "exoplanets.configgui.wolf_y");

			hd_x = config.getFloat("HD 219134 X", CATEGORY_SYSTEMS_MAP_POSITION, -1.5F, -1000.0F, 1000.0F, "Y Coord for the HD 219134 System", "exoplanets.configgui.hd_x");
			hd_y = config.getFloat("HD 219134 Y", CATEGORY_SYSTEMS_MAP_POSITION, -2.50F, -1000.0F, 1000.0F, "Y Coord for the HD 219134 System", "exoplanets.configgui.hd_y");

			trap_x = config.getFloat("Trappist 1 X", CATEGORY_SYSTEMS_MAP_POSITION, 2.0F, -1000.0F, 1000.0F, "Y Coord for the Trappist 1 System", "exoplanets.configgui.trap_x");
			trap_y = config.getFloat("Trappist 1 Y", CATEGORY_SYSTEMS_MAP_POSITION, -1.5F, -1000.0F, 1000.0F, "Y Coord for the Trappist 1 System", "exoplanets.configgui.trap_y");

			k1649_x = config.getFloat("Kepler 1649 X", CATEGORY_SYSTEMS_MAP_POSITION, 1.3F, -1000.0F, 1000.0F, "Y Coord for the Kepler 1649 System",
					"exoplanets.configgui.k1649_x");
			k1649_y = config.getFloat("Kepler 1649 Y", CATEGORY_SYSTEMS_MAP_POSITION, -2.6F, -1000.0F, 1000.0F, "Y Coord for the Kepler 1649 System",
					"exoplanets.configgui.k1649_y");

			if (config.hasChanged()) {
				config.save();
			}
		} catch (Exception e) {
			ExoplanetsMod.logger.fatal("exoplanets Systems Configuration File had an issue loding correctly");
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> configList = new ArrayList<IConfigElement>();

		ConfigCategory configSystemsMain = config.getCategory(CATEGORY_GENERAL_SYSTEMS_CONFIG);
		configSystemsMain.setComment("Main Solar Systems Settings");
		configSystemsMain.isChild();
		configList.add(new ConfigElement(configSystemsMain));

		ConfigCategory configSystemsTier = config.getCategory(CATEGORY_SYSTEM_WIDE_TIERS);
		configSystemsTier.setComment("Solar Systems Tier Settings");
		configList.add(new ConfigElement(configSystemsTier));

		ConfigCategory configPos = config.getCategory(CATEGORY_SYSTEMS_MAP_POSITION);
		configPos.setComment("Solar Systsems Map Position");
		configList.add(new ConfigElement(configPos));

		return configList;
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(ExoInfo.MODID)) {
			config.save();
		}
	}

}
