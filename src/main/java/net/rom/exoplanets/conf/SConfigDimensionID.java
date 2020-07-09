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

package net.rom.exoplanets.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;

public class SConfigDimensionID {

	static Configuration config;

	public SConfigDimensionID(File file) {
		SConfigDimensionID.config = new Configuration((file), "1.0");
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

			config.load();
			
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
			ExoplanetsMod.logger.bigError("Intersteller Core Config had an issue loading the config file!");
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		ConfigCategory configYzId = config.getCategory(CATEGORY_DIMENSION_IDS);
		configYzId.setComment("Yz Ceti Planet Dimension ID's");
		list.add(new ConfigElement(configYzId));


		return list;
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(ExoInfo.MODID)) {
			config.save();
		}
	}


}
