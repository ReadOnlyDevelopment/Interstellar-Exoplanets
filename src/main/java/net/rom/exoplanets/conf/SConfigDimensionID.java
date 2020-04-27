package net.rom.exoplanets.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.exoplanets.util.LogHelper;

public class SConfigDimensionID {

	static Configuration config;

	public SConfigDimensionID(File file) {
		SConfigDimensionID.config = new Configuration(file);
		SConfigDimensionID.syncConfig(true);
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
	
	private static String CATEGORY_DIMENSION_IDS = "Exoplanets Dimension ID's";

	public static void syncConfig(boolean load) {
		try {
			if (!config.isChild) {
				if (load) {
					config.load();
				}
			}
			
			config.addCustomCategoryComment(CATEGORY_DIMENSION_IDS,
					"Change the Dimension IDs of Exoplanets if Conflicts Arise");
			config.setCategoryLanguageKey(CATEGORY_DIMENSION_IDS, "exoplanets.configgui.category.dimensionids");
			config.setCategoryRequiresMcRestart(CATEGORY_DIMENSION_IDS, true);
			
			id_yz_b = config.getInt("Yz Ceti B", CATEGORY_DIMENSION_IDS, -4101, -2147483647, 2147483647, "Yz Ceti B Dimension ID", "interstellar.configgui.id_yz_b");
			id_yz_c = config.getInt("Yz Ceti C", CATEGORY_DIMENSION_IDS, -4102, -2147483647, 2147483647, "Yz Ceti C Dimension ID", "interstellar.configgui.id_yz_c");
			id_yz_d = config.getInt("Yz Ceti D", CATEGORY_DIMENSION_IDS, -4103, -2147483647, 2147483647, "Yz Ceti D Dimension ID", "interstellar.configgui.id_yz_d");

			//wolf1061
			id_wolf_b = config.getInt("Wolf 1061 B", CATEGORY_DIMENSION_IDS, -4201, -2147483647, 2147483647, "Wolf 1061 B Dimension ID", "interstellar.configgui.id_wolf_b");
			id_wolf_c = config.getInt("Wolf 1061 C", CATEGORY_DIMENSION_IDS, -4202, -2147483647, 2147483647, "Wolf 1061 C Dimension ID", "interstellar.configgui.id_wolf_c");
			id_wolf_d = config.getInt("Wolf 1061 D", CATEGORY_DIMENSION_IDS, -4203, -2147483647, 2147483647, "Wolf 1061 D Dimension ID", "interstellar.configgui.id_wolf_d");
			
			//hd19134
			id_hd_b = config.getInt("HD 219134 B", CATEGORY_DIMENSION_IDS, -4401, -2147483647, 2147483647, "HD 219134 B Dimension ID", "interstellar.configgui.id_hd_b");
			id_hd_c = config.getInt("HD 219134 C", CATEGORY_DIMENSION_IDS, -4402, -2147483647, 2147483647, "HD 219134 C Dimension ID", "interstellar.configgui.id_hd_c");
			id_hd_d = config.getInt("HD 219134 D", CATEGORY_DIMENSION_IDS, -4403, -2147483647, 2147483647, "HD 219134 D Dimension ID", "interstellar.configgui.id_hd_d");
			id_hd_f = config.getInt("HD 219134 F", CATEGORY_DIMENSION_IDS, -4404, -2147483647, 2147483647, "HD 219134 F Dimension ID", "interstellar.configgui.id_hd_f");
			id_hd_g = config.getInt("HD 219134 G", CATEGORY_DIMENSION_IDS, -4405, -2147483647, 2147483647, "HD 219134 G Dimension ID", "interstellar.configgui.id_hd_g");
			id_hd_h = config.getInt("HD 219134 H", CATEGORY_DIMENSION_IDS, -4406, -2147483647, 2147483647, "HD 219134 H Dimension ID", "interstellar.configgui.id_hd_h");
			
			//trappist1
			id_trap_b = config.getInt("Trappist 1 B", CATEGORY_DIMENSION_IDS, -4501, -2147483647, 2147483647, "Trappist 1 B Dimension ID", "interstellar.configgui.id_trap_b");
			id_trap_c = config.getInt("Trappist 1 C", CATEGORY_DIMENSION_IDS, -4502, -2147483647, 2147483647, "Trappist 1 C Dimension ID", "interstellar.configgui.id_trap_c");
			id_trap_d = config.getInt("Trappist 1 D", CATEGORY_DIMENSION_IDS, -4503, -2147483647, 2147483647, "Trappist 1 D Dimension ID", "interstellar.configgui.id_trap_d");
			id_trap_e = config.getInt("Trappist 1 E", CATEGORY_DIMENSION_IDS, -4504, -2147483647, 2147483647, "Trappist 1 E Dimension ID", "interstellar.configgui.id_trap_e");
			id_trap_f = config.getInt("Trappist 1 F", CATEGORY_DIMENSION_IDS, -4505, -2147483647, 2147483647, "Trappist 1 F Dimension ID", "interstellar.configgui.id_trap_f");
			id_trap_g = config.getInt("Trappist 1 G", CATEGORY_DIMENSION_IDS, -4506, -2147483647, 2147483647, "Trappist 1 G Dimension ID", "interstellar.configgui.id_trap_g");
			id_trap_h = config.getInt("Trappist 1 H", CATEGORY_DIMENSION_IDS, -4507, -2147483647, 2147483647, "Trappist 1 H Dimension ID", "interstellar.configgui.id_trap_h");

			if (config.hasChanged()) {
				config.save();
			}
		} catch (final Exception e) {
			LogHelper.bigError("Intersteller Core Config had an issue loading the config file!");
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		ConfigCategory configYzId = config.getCategory(CATEGORY_DIMENSION_IDS);
		configYzId.setComment("Yz Ceti Planet Dimension ID's");
		list.add(new ConfigElement(configYzId));


		return list;
	}


}
