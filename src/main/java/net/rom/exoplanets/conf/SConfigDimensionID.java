package net.rom.exoplanets.conf;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.exoplanets.Exoplanets;

public class SConfigDimensionID {

	static Configuration config;

	public SConfigDimensionID(File file) {
		SConfigCore.config = new Configuration(file);
		SConfigCore.syncConfig(true);
	}

	//yzCeti
	public static int id_yz_b;
	public static int id_yz_c;
	public static int id_yz_d;

	//wolf1061
	public static int id_wolf_b;
	public static int id_wolf_c;
	public static int id_wolf_d;
	
	//hd19134
	public static int id_hd_b;
	public static int id_hd_c;
	public static int id_hd_d;
	public static int id_hd_f;
	public static int id_hd_g;
	public static int id_hd_h;
	
	//trappist1
	public static int id_trap_b;
	public static int id_trap_c;
	public static int id_trap_d;
	public static int id_trap_e;
	public static int id_trap_f;
	public static int id_trap_g;
	public static int id_trap_h;
	
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
			//Yz Ceti
			prop = config.get("Yz Ceti", "Yz Ceti B ID", -4101);
			prop.setComment("Dimension ID for Yz Ceti c");
			prop.setLanguageKey("interstellar.configgui.id_yz_b").setRequiresMcRestart(true);
			id_yz_b = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Yz Ceti", "Yz Ceti C ID", -4102);
			prop.setComment("Enable/Disable Yz Ceti c");
			prop.setLanguageKey("interstellar.configgui.id_yz_c").setRequiresMcRestart(true);
			id_yz_c = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Yz Ceti", "Yz Ceti D ID", -4103);
			prop.setComment("Dimension ID for Yz Ceti b");
			prop.setLanguageKey("interstellar.configgui.id_yz_d").setRequiresMcRestart(true);
			id_yz_d = prop.getInt();
			propOrder.add(prop.getName());
			
			//wolf1061
			prop = config.get("Wolf 1061", "Wolf 1061 B ID", -4301);
			prop.setComment("Dimension ID for Wolf 1061 B");
			prop.setLanguageKey("interstellar.configgui.id_wolf_b").setRequiresMcRestart(true);
			id_wolf_b = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Wolf 1061", "Wolf 1061 C ID", -4302);
			prop.setComment("Dimension ID for Wolf 1061 C");
			prop.setLanguageKey("interstellar.configgui.id_wolf_c").setRequiresMcRestart(true);
			id_wolf_c = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Wolf 1061", "Wolf 1061 D ID", -4303);
			prop.setComment("Dimension ID for Wolf 1061 D");
			prop.setLanguageKey("interstellar.configgui.id_wolf_d").setRequiresMcRestart(true);
			id_wolf_d = prop.getInt();
			propOrder.add(prop.getName());
			
			//hd19134
			prop = config.get("HD 19134", "HD 19134 B ID", -4401);
			prop.setComment("Dimension ID for Wolf 1061 B");
			prop.setLanguageKey("interstellar.configgui.id_hd_b").setRequiresMcRestart(true);
			id_hd_b = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("HD 19134", "HD 19134 C ID", -4402);
			prop.setComment("Dimension ID for Wolf 1061 C");
			prop.setLanguageKey("interstellar.configgui.id_hd_c").setRequiresMcRestart(true);
			id_hd_c = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("HD 19134", "HD 19134 D ID", -4403);
			prop.setComment("Dimension ID for Wolf 1061 D");
			prop.setLanguageKey("interstellar.configgui.id_hd_d").setRequiresMcRestart(true);
			id_hd_d = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("HD 19134", "HD 19134 F ID", -4404);
			prop.setComment("Dimension ID for Wolf 1061 F");
			prop.setLanguageKey("interstellar.configgui.id_hd_f").setRequiresMcRestart(true);
			id_hd_f = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("HD 19134", "HD 19134 G ID", -4405);
			prop.setComment("Dimension ID for Wolf 1061 G");
			prop.setLanguageKey("interstellar.configgui.id_hd_g").setRequiresMcRestart(true);
			id_hd_g = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("HD 19134", "HD 19134 H ID", -4406);
			prop.setComment("Dimension ID for Wolf 1061 H");
			prop.setLanguageKey("interstellar.configgui.id_hd_h").setRequiresMcRestart(true);
			id_hd_h = prop.getInt();
			propOrder.add(prop.getName());
			
			//trappist1
			prop = config.get("Trappist 1", "Trappist 1 B ID", -4601);
			prop.setComment("Dimension ID for Trappist 1 B");
			prop.setLanguageKey("interstellar.configgui.id_trap_b").setRequiresMcRestart(true);
			id_trap_b = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Trappist 1", "Trappist 1 C ID", -4602);
			prop.setComment("Dimension ID for Trappist 1 C");
			prop.setLanguageKey("interstellar.configgui.id_trap_c").setRequiresMcRestart(true);
			id_trap_c = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Trappist 1", "Trappist 1 D ID", -4603);
			prop.setComment("Dimension ID for Trappist 1 D");
			prop.setLanguageKey("interstellar.configgui.id_trap_d").setRequiresMcRestart(true);
			id_trap_d = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Trappist 1", "Trappist 1 E ID", -4604);
			prop.setComment("Dimension ID for Trappist 1 E");
			prop.setLanguageKey("interstellar.configgui.id_trap_e").setRequiresMcRestart(true);
			id_trap_e = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Trappist 1", "Trappist 1 F ID", -4605);
			prop.setComment("Dimension ID for Trappist 1 F");
			prop.setLanguageKey("interstellar.configgui.id_trap_f").setRequiresMcRestart(true);
			id_trap_f = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Trappist 1", "Trappist 1 G ID", -4607);
			prop.setComment("Dimension ID for Trappist 1 G");
			prop.setLanguageKey("interstellar.configgui.id_trap_g").setRequiresMcRestart(true);
			id_trap_g = prop.getInt();
			propOrder.add(prop.getName());
			
			prop = config.get("Trappist 1", "Trappist 1 H ID", -4608);
			prop.setComment("Dimension ID for Trappist 1 H");
			prop.setLanguageKey("interstellar.configgui.id_trap_h").setRequiresMcRestart(true);
			id_trap_h = prop.getInt();
			propOrder.add(prop.getName());
			
			config.setCategoryPropertyOrder("Yz Ceti", propOrder);
			config.setCategoryPropertyOrder("Wolf 1061", propOrder);
			config.setCategoryPropertyOrder("HD 19134", propOrder);
			config.setCategoryPropertyOrder("Trappist 1", propOrder);

			if (config.hasChanged()) {
				config.save();
			}
		} catch (final Exception e) {
			Exoplanets.LOGGER.log(Level.ERROR, "Intersteller Core Config had an issue loading the config file!");
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(config.getCategory("Yz Ceti")).getChildElements());
		list.addAll(new ConfigElement(config.getCategory("Wolf 1061")).getChildElements());
		list.addAll(new ConfigElement(config.getCategory("HD 19134")).getChildElements());
		list.addAll(new ConfigElement(config.getCategory("Trappist 1")).getChildElements());

		return list;
	}


}
