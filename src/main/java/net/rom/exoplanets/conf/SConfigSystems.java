package net.rom.exoplanets.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.exoplanets.Exoplanets;

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
	
	//NOT FINISHED
	public static boolean hideUnfinishedSystems;
	
	//SYSTEM-WIDE-TIER
	public static int yzceti_tier;
	public static int ups_tier;
	public static int wolf_tier;
	public static int hd_tier;
	public static int trap_tier;
	
	//SYSTEMS MAP OFFSETS
	public static double yzceti_x;
	public static double yzceti_y;
	
	public static double wolf_x;
	public static double wolf_y;
	
	public static double hd_x;
	public static double hd_y;
	
	public static double trap_x;
	public static double trap_y;

	
	public static void syncConfig(boolean load) {
		List<String> order = new ArrayList<String>();
		try {
			Property prop;
			if(!config.isChild) {
				if(load) {
					config.load();
				}
			}
			

			//Core Configs
			prop = config.get("General Systems Configuration", "disable_zyceti_system", false);
			prop.setComment("False = Disable System | True = Enable System (Yz Ceti Solar System)");
			prop.setLanguageKey("exoplanets.configgui.toggleyzceti").setRequiresMcRestart(true);
			disable_yzceti_system = prop.getBoolean();
			order.add(prop.getName()); 
			
			prop = config.get("General Systems Configuration", "hideUnfinishedSystems", false);
			prop.setComment("set to true to hide unfinished Solar Systems & planets");
			prop.setLanguageKey("exoplanets.configgui.toggleunfinished").setRequiresMcRestart(true);
			hideUnfinishedSystems = prop.getBoolean();
			order.add(prop.getName()); 

			// Systems Tier
			prop = config.get("Solar System Tiers", "yzceti_tier", 3);
			prop.setComment("Set the Rocket Tier Required for Yz Ceti Solar System");
			prop.setLanguageKey("interstellar.configgui.yzceti_tier").setRequiresMcRestart(true);
			yzceti_tier = prop.getInt();
			order.add(prop.getName());
			
			prop = config.get("Solar System Tiers", "ups_tier", 3);
			prop.setComment("Set the Rocket Tier Required for Upsilon Andromedae Solar System");
			prop.setLanguageKey("interstellar.configgui.ups_tier").setRequiresMcRestart(true);
			ups_tier = prop.getInt();
			order.add(prop.getName());
			
			prop = config.get("Solar System Tiers", "wolf_tier", 3);
			prop.setComment("Set the Rocket Tier Required for Wolf 1061 Solar System");
			prop.setLanguageKey("interstellar.configgui.wolf_tier").setRequiresMcRestart(true);
			wolf_tier = prop.getInt();
			order.add(prop.getName());
			
			prop = config.get("Solar System Tiers", "hd_tier", 3);
			prop.setComment("Set the Rocket Tier Required for HD 219134 Solar System");
			prop.setLanguageKey("interstellar.configgui.hd_tier").setRequiresMcRestart(true);
			hd_tier = prop.getInt();
			order.add(prop.getName());
			
			prop = config.get("Solar System Tiers", "trap_tier", 3);
			prop.setComment("Set the Rocket Tier Required for Trappist 1 Solar System");
			prop.setLanguageKey("interstellar.configgui.trap_tier").setRequiresMcRestart(true);
			trap_tier = prop.getInt();
			order.add(prop.getName());
			
			//Map Positions
			//Yz Ceti
			prop = config.get("Yz Ceti Map Position", "Map Position X", 0.7);
			prop.setComment("Set the Map Position X Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.yzceti_x").setRequiresMcRestart(true);
			yzceti_x = prop.getDouble();
			order.add(prop.getName());
			
			prop = config.get("Yz Ceti Map Position", "Map Position Y", 1.3);
			prop.setComment("Set the Map Position Y Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.yzceti_y").setRequiresMcRestart(true);
			yzceti_y = prop.getDouble();
			order.add(prop.getName());
			
			//Wolf 1061
			prop = config.get("Upsilon Andromedae Map Position", "Map Position X", -1.5);
			prop.setComment("Set the Map Position X Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.wolf_x").setRequiresMcRestart(true);
			wolf_x = prop.getDouble();
			order.add(prop.getName());
			
			prop = config.get("Upsilon Andromedae Map Position", "Map Position Y", 0.8);
			prop.setComment("Set the Map Position Y Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.wolf_y").setRequiresMcRestart(true);
			wolf_y = prop.getDouble();
			order.add(prop.getName());
			
			//HD 219134
			prop = config.get("HD 219134 Map Position", "Map Position X", 1.5);
			prop.setComment("Set the Map Position X Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.hd_x").setRequiresMcRestart(true);
			hd_x = prop.getDouble();
			order.add(prop.getName());
			
			prop = config.get("HD 219134 Map Position", "Map Position Y", 0.6);
			prop.setComment("Set the Map Position Y Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.hd_y").setRequiresMcRestart(true);
			hd_y = prop.getDouble();
			order.add(prop.getName());
			
			//Trappist 1
			prop = config.get("Trappist 1 Map Position", "Map Position X", 0.1);
			prop.setComment("Set the Map Position X Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.trap_x").setRequiresMcRestart(true);
			trap_x = prop.getDouble();
			order.add(prop.getName());
			
			prop = config.get("Trappist 1 Map Position", "Map Position Y", 1.2);
			prop.setComment("Set the Map Position Y Coord for the Solar System");
			prop.setLanguageKey("interstellar.configgui.trap_y").setRequiresMcRestart(true);
			trap_y = prop.getDouble();
			order.add(prop.getName());

			config.setCategoryPropertyOrder("General Systems Configuration", order);
			config.setCategoryPropertyOrder("Solar System Tiers", order);
			config.setCategoryPropertyOrder("Yz Ceti Map Position", order);
			config.setCategoryPropertyOrder("Upsilon Andromedae Map Position", order);
			config.setCategoryPropertyOrder("Wolf 1061 Map Position", order);
			config.setCategoryPropertyOrder("HD 219134 Map Position", order);
			config.setCategoryPropertyOrder("Trappist 1 Map Position", order);
			
			if(config.hasChanged()) {
				config.save();
			}
		}	catch (Exception e) {
			Exoplanets.LOGGER.fatal("Interstellar Systems Configuration File had an issue loding correctly");
		}
	}
	
	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> configList = new ArrayList<IConfigElement>();
		
		configList.addAll(new ConfigElement(config.getCategory("General Systems Configuration")).getChildElements());
		configList.addAll(new ConfigElement(config.getCategory("Solar System Tiers")).getChildElements());
		configList.addAll(new ConfigElement(config.getCategory("Yz Ceti Map Position")).getChildElements());
		configList.addAll(new ConfigElement(config.getCategory("Upsilon Andromedae Map Position")).getChildElements());
		configList.addAll(new ConfigElement(config.getCategory("Wolf 1061 Map Position")).getChildElements());
		configList.addAll(new ConfigElement(config.getCategory("HD 219134 Map Position")).getChildElements());
		configList.addAll(new ConfigElement(config.getCategory("Trappist 1 Map Position")).getChildElements());
		
		return configList;
	}

}
