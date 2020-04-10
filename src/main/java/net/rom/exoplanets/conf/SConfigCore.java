package net.rom.exoplanets.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.exoplanets.Exoplanets;

public class SConfigCore {

	static Configuration config;

	public SConfigCore(File file) {
		SConfigCore.config = new Configuration(file);
		SConfigCore.syncConfig(true);
	}

	public static boolean enableCheckVersion;
	public static boolean enableOverworldOres;
	public static boolean enableDebug;
	public static boolean enableRealism;
	
	private static String CATEGORY_GENERAL_MAIN = "Core Interstellar: Exoplanets Settings";


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

			enableCheckVersion = config.getBoolean("enableCheckVersion", CATEGORY_GENERAL_MAIN, true, "Enable/Disable Check Version", "exoplanets.configgui.enableCheckVersion");
			enableOverworldOres = config.getBoolean("enableDebug", CATEGORY_GENERAL_MAIN, false, "Enable/Disable Generation Ores on Overworld", "exoplanets.configgui.enableOverworldOres");
			enableDebug = config.getBoolean("enableOverworldOres", CATEGORY_GENERAL_MAIN, false, "Enable/Disable Debug mode", "exoplanets.configgui.enableDebug");
			enableRealism = config.getBoolean("enableRealism", CATEGORY_GENERAL_MAIN, false, "Enabling Realism loads the round & realistic Celestial Body Textures on the Celestial Map", "exoplanets.configgui.enableRealism");

			if (config.hasChanged()) {
				config.save();
			}
		} catch (final Exception e) {
			Exoplanets.LOGGER.log(Level.ERROR, "Intersteller Core Config had an issue loading the config file!");
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		ConfigCategory configGeneral = config.getCategory(CATEGORY_GENERAL_MAIN);
		configGeneral.setComment("Core Settings");
		list.add(new ConfigElement(configGeneral));


		return list;
	}

}
