package net.rom.stellar.conf;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.stellar.Exoplanets;

public class SConfigCore {

	static Configuration config;

	public SConfigCore(File file) {
		SConfigCore.config = new Configuration(file);
		SConfigCore.syncConfig(true);
	}

	public static boolean enableCheckVersion;
	public static boolean enableOverworldOres;
	public static boolean enableDebug;

	public static void syncConfig(boolean load) {
		List<String> propOrder = new ArrayList<String>();

		try {
			propOrder.clear();
			Property prop;
			if (!config.isChild) {
				if (load) {
					config.load();
				}
			}

			prop = config.get("general", "enableCheckVersion", true);
			prop.setComment("Enable/Disable Check Version.");
			prop.setLanguageKey("interstellar.configgui.enableCheckVersion").setRequiresMcRestart(true);
			enableCheckVersion = prop.getBoolean(true);
			propOrder.add(prop.getName());

			prop = config.get("worldgen", "enableOverworldOres", true);
			prop.setComment("Enable/Disable Generation Ores on Overworld.");
			prop.setLanguageKey("interstellar.configgui.enableOverworldOres").setRequiresMcRestart(true);
			enableOverworldOres = prop.getBoolean(true);
			propOrder.add(prop.getName());

			prop = config.get("development", "enableDebug", false);
			prop.setComment("Enable/Disable Debug mode");
			prop.setLanguageKey("interstellar.configgui.enableDebug").setRequiresMcRestart(false);
			enableDebug = prop.getBoolean(false);
			propOrder.add(prop.getName());

			config.setCategoryPropertyOrder(CATEGORY_GENERAL, propOrder);

			if (config.hasChanged()) {
				config.save();
			}
		} catch (final Exception e) {
			Exoplanets.LOGGER.log(Level.ERROR, "Intersteller Core Config had an issue loading the config file!");
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(config.getCategory("general")).getChildElements());
		list.addAll(new ConfigElement(config.getCategory("worldgen")).getChildElements());
		list.addAll(new ConfigElement(config.getCategory("development")).getChildElements());

		return list;
	}

}
