package net.rom.exoplanets.conf;

import java.io.File;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class InitConfigFiles {
	
	public static void init(FMLPreInitializationEvent event) {
		new SConfigSystems(new File(event.getModConfigurationDirectory(), "Exoplanets/systems.cfg"));
		new SConfigDimensionID(new File(event.getModConfigurationDirectory(), "Exoplanets/dimensions.cfg"));
		new SConfigCore(new File(event.getModConfigurationDirectory(), "Exoplanets/core.cfg"));
	}
}
