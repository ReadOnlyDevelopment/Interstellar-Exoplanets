package net.rom.stellar.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.stellar.Exoplanets;

public class SConfigSystems {

	public static boolean loaded;

	static Configuration config;

	public SConfigSystems(File file) {
		if (!SConfigSystems.loaded) {
			SConfigSystems.config = new Configuration(file);
			SConfigSystems.syncConfig(true);
		}
	}

	// SYSTEMS
	public static boolean disable_yzceti_system; // bcde
	
	//SYSTEM-WIDE-TIER
	public static int yzceti_tier;
	
	//SYSTEMS MAP OFFSETS
	public static double yzceti_x;
	public static double yzceti_y;
	public static double yzceti_z;
	
	// PLANETS
	public static int id_yz_b;
	public static int id_yz_c;
	public static int id_yz_d;
	
	public static void syncConfig(boolean load) {
		List<String> order = new ArrayList<String>();
		try {
			Property prop;
			if(!config.isChild) {
				if(load) {
					config.load();
				}
			}
			

			
			prop = config.get("Solar System Configuration", "disable_zyceti_system", false);
			prop.setComment("False = Disable System | True = Enable System (Yz Ceti Solar System)");
			prop.setLanguageKey("exoplanets.configgui.toggleyzceti").setRequiresMcRestart(true);
			disable_yzceti_system = prop.getBoolean();
			order.add(prop.getName()); 

			prop = config.get("Solar System Configuration", "yzceti_tier", 3);
			prop.setComment("Set the Rocket Tier Required for Yz Ceti Solar System");
			prop.setLanguageKey("interstellar.configgui.yzceti_tier").setRequiresMcRestart(true);
			yzceti_tier = prop.getInt();
			order.add(prop.getName());
			
			prop = config.get("Solar System Configuration", "yzceti_x", 1.0);
			prop.setComment("Set the Rocket Tier Required for Yz Ceti Solar System");
			prop.setLanguageKey("interstellar.configgui.yzceti_tier").setRequiresMcRestart(true);
			yzceti_x = prop.getDouble();
			order.add(prop.getName());
			
			prop = config.get("Solar System Configuration", "yzceti_y", 1.0);
			prop.setComment("Set the Rocket Tier Required for Yz Ceti Solar System");
			prop.setLanguageKey("interstellar.configgui.yzceti_tier").setRequiresMcRestart(true);
			yzceti_y = prop.getDouble();
			order.add(prop.getName());
			
			prop = config.get("Solar System Configuration", "yzceti_z", 1.0);
			prop.setComment("Set the Rocket Tier Required for Yz Ceti Solar System");
			prop.setLanguageKey("interstellar.configgui.yzceti_tier").setRequiresMcRestart(true);
			yzceti_z = prop.getDouble();
			order.add(prop.getName());
			
			prop = config.get("Dimension Configuration", "id_yz_b", -4101);
			prop.setComment("Dimension ID for Yz Ceti c");
			prop.setLanguageKey("interstellar.configgui.id_yz_b").setRequiresMcRestart(true);
			id_yz_b = prop.getInt();
			order.add(prop.getName());

			prop = config.get("Dimension Configuration", "id_yz_c", -4102);
			prop.setComment("Enable/Disable Yz Ceti c");
			prop.setLanguageKey("interstellar.configgui.id_yz_c").setRequiresMcRestart(true);
			id_yz_c = prop.getInt();
			order.add(prop.getName());
			
			prop = config.get("Dimension Configuration", "id_yz_d", -4103);
			prop.setComment("Dimension ID for Yz Ceti b");
			prop.setLanguageKey("interstellar.configgui.id_yz_d").setRequiresMcRestart(true);
			id_yz_d = prop.getInt();
			order.add(prop.getName());

			config.setCategoryPropertyOrder("Dimension Configuration", order);
			
			if(config.hasChanged()) {
				config.save();
			}
		}	catch (Exception e) {
			Exoplanets.LOGGER.fatal("Interstellar Dimensions Configuration File had an issue loding correctly");
		}
	}
	
	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> configList = new ArrayList<IConfigElement>();
		
		configList.addAll(new ConfigElement(config.getCategory("Dimension Configuation")).getChildElements());
		
		return configList;
	}

}
