package com.readonlydev.common.config;

import com.readonlydev.Exoplanets;
import com.readonlydev.lib.base.config.ReadOnlyConfig;
import com.readonlydev.lib.base.config.impl.Category;
import com.readonlydev.lib.base.config.values.ConfigBoolean;

public class CoreConfig extends ReadOnlyConfig {
	
	private final Category MOD_SETTINGS = Category.of("Mod Settings");
	private final Category CLIENT_ONLY_SETTINGS = Category.of("Client Settings");
	
	public static ConfigBoolean ENABLE_VERSION_CHECK;
	public static ConfigBoolean ENABLE_OVERWORLD_ORE_GEN;
	public static ConfigBoolean ENABLE_REALISTIC_CELESTIAL_ICONS;
	public static ConfigBoolean ENABLE_DEBUG_LOG_OUTPUT;
	public static ConfigBoolean ENABLE_BETA_WARNING;

	protected CoreConfig() {
		super(Exoplanets._instance, "Core");
		
		addCategories(MOD_SETTINGS, CLIENT_ONLY_SETTINGS);
		
		ENABLE_VERSION_CHECK = new ConfigBoolean(MOD_SETTINGS, "enableVersionCheck", "Disable to prevent checking for newer Exoplanet Versions", true);
		ENABLE_OVERWORLD_ORE_GEN = new ConfigBoolean(MOD_SETTINGS, "enableOverworldOreGen", "Disable to prevent Exoplanet Mod oregen on the Overworld", true);
		ENABLE_DEBUG_LOG_OUTPUT = new ConfigBoolean(MOD_SETTINGS, "enableDebugLogOutput", "Enables Debug output logs for debugging purposes (ONLY enable this if asked by the developer)", true);
		
		ENABLE_REALISTIC_CELESTIAL_ICONS = new ConfigBoolean(CLIENT_ONLY_SETTINGS, "enableRealisticIcons", "Forces the Celestial Screen to use realistic round celestial body icons", true);
		ENABLE_BETA_WARNING = new ConfigBoolean(CLIENT_ONLY_SETTINGS, "enableBetaWarningScreen", "Disable to prevent the Beta Build Warning Screen at startup", true);

	}

}
