package com.readonlydev.common.config;

import java.util.HashMap;
import java.util.Map;

import com.readonlydev.Exoplanets;
import com.readonlydev.api.mod.IEventListener;
import com.readonlydev.core.ExoplanetsRegistry;
import com.readonlydev.lib.base.config.ReadOnlyConfig;
import com.readonlydev.lib.base.config.impl.Category;
import com.readonlydev.lib.base.config.impl.Range;
import com.readonlydev.lib.base.config.values.ConfigBoolean;
import com.readonlydev.lib.base.config.values.ConfigInteger;
import com.readonlydev.lib.celestial.objects.Exoplanet;

public class ExoplanetConfig extends ReadOnlyConfig implements IEventListener {

	private final Category ID_EXOPLANETS = Category.of("Exoplanet Dimension ID");
	private final Category R_EXOPLANETS = Category.of("Exoplanet Options");
	private final Category T_EXOPLANETS = Category.of(R_EXOPLANETS, "Exoplanet Tier Override");

	private final Range<Integer> range = Range.of(Integer.MIN_VALUE, Integer.MAX_VALUE);

	public static ConfigInteger YZCETI_B_ID;
	public static ConfigInteger YZCETI_C_ID;
	public static ConfigInteger YZCETI_D_ID;
	public static ConfigInteger WOLF1061_B_ID;
	public static ConfigInteger WOLF1061_C_ID;
	public static ConfigInteger WOLF1061_D_ID;
	public static ConfigInteger TRAPPIST1_B_ID;
	public static ConfigInteger TRAPPIST1_C_ID;
	public static ConfigInteger TRAPPIST1_D_ID;
	public static ConfigInteger TRAPPIST1_E_ID;
	public static ConfigInteger TRAPPIST1_F_ID;
	public static ConfigInteger TRAPPIST1_G_ID;
	public static ConfigInteger TRAPPIST1_H_ID;
	public static ConfigInteger KEPLER1649_B_ID;
	public static ConfigInteger KEPLER1649_C_ID;

	public static ConfigBoolean YZCETI_B_REACHABLE;
	public static ConfigBoolean YZCETI_C_REACHABLE;
	public static ConfigBoolean YZCETI_D_REACHABLE;
	public static ConfigBoolean WOLF1061_B_REACHABLE;
	public static ConfigBoolean WOLF1061_C_REACHABLE;
	public static ConfigBoolean WOLF1061_D_REACHABLE;
	public static ConfigBoolean TRAPPIST1_B_REACHABLE;
	public static ConfigBoolean TRAPPIST1_C_REACHABLE;
	public static ConfigBoolean TRAPPIST1_D_REACHABLE;
	public static ConfigBoolean TRAPPIST1_E_REACHABLE;
	public static ConfigBoolean TRAPPIST1_F_REACHABLE;
	public static ConfigBoolean TRAPPIST1_G_REACHABLE;
	public static ConfigBoolean TRAPPIST1_H_REACHABLE;
	public static ConfigBoolean KEPLER1649_B_REACHABLE;
	public static ConfigBoolean KEPLER1649_C_REACHABLE;
		
	public static ConfigInteger YZCETI_B_TIER;
	public static ConfigInteger YZCETI_C_TIER;
	public static ConfigInteger YZCETI_D_TIER;
	public static ConfigInteger WOLF1061_B_TIER;
	public static ConfigInteger WOLF1061_C_TIER;
	public static ConfigInteger WOLF1061_D_TIER;
	public static ConfigInteger TRAPPIST1_B_TIER;
	public static ConfigInteger TRAPPIST1_C_TIER;
	public static ConfigInteger TRAPPIST1_D_TIER;
	public static ConfigInteger TRAPPIST1_E_TIER;
	public static ConfigInteger TRAPPIST1_F_TIER;
	public static ConfigInteger TRAPPIST1_G_TIER;
	public static ConfigInteger TRAPPIST1_H_TIER;
	public static ConfigInteger KEPLER1649_B_TIER;
	public static ConfigInteger KEPLER1649_C_TIER;
	
	private static Map<Exoplanet, Integer> dimIdMap = new HashMap<>();
	private static Map<Exoplanet, Boolean> reachableMap = new HashMap<>();
	private static Map<Exoplanet, Integer> tierMap = new HashMap<>();

	public ExoplanetConfig() {
		super(Exoplanets._instance, "Exoplanets");
		mod.addEventListeners(this);

		ID_EXOPLANETS.setCategoryComment("Exoplanet Dimension ID's (CHANGE ONLY IF NEEDED)");
		ID_EXOPLANETS.setRequiredRestarts(false, true);
		R_EXOPLANETS.setCategoryComment("Set per planet reachability");
		R_EXOPLANETS.setRequiredRestarts(false, true);
		T_EXOPLANETS.setCategoryComment("Exoplanet Tier Requirement that overrides the StarSystem tier");
		T_EXOPLANETS.setRequiredRestarts(false, true);
		addCategories(ID_EXOPLANETS, R_EXOPLANETS, T_EXOPLANETS);

		YZCETI_B_ID = new ConfigInteger(ID_EXOPLANETS, "yz ceti b id", "", -4101, range);
		YZCETI_C_ID = new ConfigInteger(ID_EXOPLANETS, "yz ceti c id", "", -4102, range);
		YZCETI_D_ID = new ConfigInteger(ID_EXOPLANETS, "yz ceti d id", "", -4103, range);
		addProperties(YZCETI_B_ID, YZCETI_C_ID, YZCETI_D_ID);

		WOLF1061_B_ID = new ConfigInteger(ID_EXOPLANETS, "wolf 1061 b id", "", -4201, range);
		WOLF1061_C_ID = new ConfigInteger(ID_EXOPLANETS, "wolf 1061 c id", "", -4202, range);
		WOLF1061_D_ID = new ConfigInteger(ID_EXOPLANETS, "wolf 1061 d id", "", -4203, range);
		addProperties(WOLF1061_B_ID, WOLF1061_C_ID, WOLF1061_D_ID);

		TRAPPIST1_B_ID = new ConfigInteger(ID_EXOPLANETS, "trappist 1 b id", "", -4301, range);
		TRAPPIST1_C_ID = new ConfigInteger(ID_EXOPLANETS, "trappist 1 c id", "", -4302, range);
		TRAPPIST1_D_ID = new ConfigInteger(ID_EXOPLANETS, "trappist 1 d id", "", -4303, range);
		TRAPPIST1_E_ID = new ConfigInteger(ID_EXOPLANETS, "trappist 1 e id", "", -4304, range);
		TRAPPIST1_F_ID = new ConfigInteger(ID_EXOPLANETS, "trappist 1 f id", "", -4305, range);
		TRAPPIST1_G_ID = new ConfigInteger(ID_EXOPLANETS, "trappist 1 g id", "", -4306, range);
		TRAPPIST1_H_ID = new ConfigInteger(ID_EXOPLANETS, "trappist 1 h id", "", -4307, range);
		addProperties(TRAPPIST1_B_ID, TRAPPIST1_C_ID, TRAPPIST1_D_ID, TRAPPIST1_E_ID, TRAPPIST1_F_ID, TRAPPIST1_G_ID,
				TRAPPIST1_H_ID);

		KEPLER1649_B_ID = new ConfigInteger(ID_EXOPLANETS, "kepler 1649 b id", "", -4401, range);
		KEPLER1649_C_ID = new ConfigInteger(ID_EXOPLANETS, "kepler 1649 c id", "", -4402, range);
		addProperties(KEPLER1649_B_ID, KEPLER1649_C_ID);

		YZCETI_B_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "yz ceti b landable", "", true);
		YZCETI_C_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "yz ceti c landable", "", true);
		YZCETI_D_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "yz ceti d landable", "", true);
		addProperties(YZCETI_B_REACHABLE, YZCETI_C_REACHABLE, YZCETI_D_REACHABLE);

		WOLF1061_B_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "wolf 1061 b landable", "", true);
		WOLF1061_C_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "wolf 1061 c landable", "", true);
		WOLF1061_D_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "wolf 1061 d landable", "", true);
		addProperties(WOLF1061_B_REACHABLE, WOLF1061_C_REACHABLE, WOLF1061_D_REACHABLE);

		TRAPPIST1_B_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "trappist 1 b landable", "", true);
		TRAPPIST1_C_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "trappist 1 c landable", "", true);
		TRAPPIST1_D_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "trappist 1 d landable", "", true);
		TRAPPIST1_E_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "trappist 1 e landable", "", true);
		TRAPPIST1_F_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "trappist 1 f landable", "", true);
		TRAPPIST1_G_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "trappist 1 g landable", "", true);
		TRAPPIST1_H_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "trappist 1 h landable", "", true);
		addProperties(TRAPPIST1_B_REACHABLE, TRAPPIST1_C_REACHABLE, TRAPPIST1_D_REACHABLE, TRAPPIST1_E_REACHABLE,
				TRAPPIST1_F_REACHABLE, TRAPPIST1_G_REACHABLE, TRAPPIST1_H_REACHABLE);

		KEPLER1649_B_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "kepler 1649 b landable", "", true);
		KEPLER1649_C_REACHABLE = new ConfigBoolean(R_EXOPLANETS, "kepler 1649 c landable", "", true);
		addProperties(KEPLER1649_B_REACHABLE, KEPLER1649_C_REACHABLE);
		
		YZCETI_B_TIER = new ConfigInteger(T_EXOPLANETS, "yz ceti b tier", "", 0, range);
		YZCETI_C_TIER = new ConfigInteger(T_EXOPLANETS, "yz ceti c tier", "", 0, range);
		YZCETI_D_TIER = new ConfigInteger(T_EXOPLANETS, "yz ceti d tier", "", 0, range);
		addProperties(YZCETI_B_TIER, YZCETI_C_TIER, YZCETI_D_TIER);

		WOLF1061_B_TIER = new ConfigInteger(T_EXOPLANETS, "wolf 1061 b tier", "", 0, range);
		WOLF1061_C_TIER = new ConfigInteger(T_EXOPLANETS, "wolf 1061 c tier", "", 0, range);
		WOLF1061_D_TIER = new ConfigInteger(T_EXOPLANETS, "wolf 1061 d tier", "", 0, range);
		addProperties(WOLF1061_B_TIER, WOLF1061_C_TIER, WOLF1061_D_TIER);

		TRAPPIST1_B_TIER = new ConfigInteger(T_EXOPLANETS, "trappist 1 b tier", "", 0, range);
		TRAPPIST1_C_TIER = new ConfigInteger(T_EXOPLANETS, "trappist 1 c tier", "", 0, range);
		TRAPPIST1_D_TIER = new ConfigInteger(T_EXOPLANETS, "trappist 1 d tier", "", 0, range);
		TRAPPIST1_E_TIER = new ConfigInteger(T_EXOPLANETS, "trappist 1 e tier", "", 0, range);
		TRAPPIST1_F_TIER = new ConfigInteger(T_EXOPLANETS, "trappist 1 f tier", "", 0, range);
		TRAPPIST1_G_TIER = new ConfigInteger(T_EXOPLANETS, "trappist 1 g tier", "", 0, range);
		TRAPPIST1_H_TIER = new ConfigInteger(T_EXOPLANETS, "trappist 1 h tier", "", 0, range);
		addProperties(TRAPPIST1_B_TIER, TRAPPIST1_C_TIER, TRAPPIST1_D_TIER, TRAPPIST1_E_TIER, TRAPPIST1_F_TIER, TRAPPIST1_G_TIER,
				TRAPPIST1_H_TIER);

		KEPLER1649_B_TIER = new ConfigInteger(T_EXOPLANETS, "kepler 1649 b tier", "", 0, range);
		KEPLER1649_C_TIER = new ConfigInteger(T_EXOPLANETS, "kepler 1649 c tier", "", 0, range);
		addProperties(KEPLER1649_B_TIER, KEPLER1649_C_TIER);

	}

	@Override
	public void on(EventStep eventStep) {
		if(eventStep == EventStep.PREINIT) {
			dimIdMap.put(ExoplanetsRegistry.KEPLER_1649_B, KEPLER1649_B_ID.get());
			dimIdMap.put(ExoplanetsRegistry.KEPLER_1649_C, KEPLER1649_B_ID.get());
			dimIdMap.put(ExoplanetsRegistry.TRAPPIST_1_B, TRAPPIST1_B_ID.get());
			dimIdMap.put(ExoplanetsRegistry.TRAPPIST_1_C, TRAPPIST1_C_ID.get());
			dimIdMap.put(ExoplanetsRegistry.TRAPPIST_1_D, TRAPPIST1_D_ID.get());
			dimIdMap.put(ExoplanetsRegistry.TRAPPIST_1_E, TRAPPIST1_E_ID.get());
			dimIdMap.put(ExoplanetsRegistry.TRAPPIST_1_F, TRAPPIST1_F_ID.get());
			dimIdMap.put(ExoplanetsRegistry.TRAPPIST_1_G, TRAPPIST1_G_ID.get());
			dimIdMap.put(ExoplanetsRegistry.TRAPPIST_1_H, TRAPPIST1_H_ID.get());
			dimIdMap.put(ExoplanetsRegistry.WOLF_1061_B, WOLF1061_B_ID.get());
			dimIdMap.put(ExoplanetsRegistry.WOLF_1061_C, WOLF1061_C_ID.get());
			dimIdMap.put(ExoplanetsRegistry.WOLF_1061_D, WOLF1061_D_ID.get());
			dimIdMap.put(ExoplanetsRegistry.YZ_CETI_B, YZCETI_B_ID.get());
			dimIdMap.put(ExoplanetsRegistry.YZ_CETI_C, YZCETI_C_ID.get());
			dimIdMap.put(ExoplanetsRegistry.YZ_CETI_D, YZCETI_D_ID.get());
			
			reachableMap.put(ExoplanetsRegistry.KEPLER_1649_B, KEPLER1649_B_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.KEPLER_1649_C, KEPLER1649_B_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.TRAPPIST_1_B, TRAPPIST1_B_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.TRAPPIST_1_C, TRAPPIST1_C_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.TRAPPIST_1_D, TRAPPIST1_D_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.TRAPPIST_1_E, TRAPPIST1_E_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.TRAPPIST_1_F, TRAPPIST1_F_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.TRAPPIST_1_G, TRAPPIST1_G_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.TRAPPIST_1_H, TRAPPIST1_H_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.WOLF_1061_B, WOLF1061_B_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.WOLF_1061_C, WOLF1061_C_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.WOLF_1061_D, WOLF1061_D_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.YZ_CETI_B, YZCETI_B_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.YZ_CETI_C, YZCETI_C_REACHABLE.get());
			reachableMap.put(ExoplanetsRegistry.YZ_CETI_D, YZCETI_D_REACHABLE.get());
			
			tierMap.put(ExoplanetsRegistry.KEPLER_1649_B, KEPLER1649_B_TIER.get());
			tierMap.put(ExoplanetsRegistry.KEPLER_1649_C, KEPLER1649_B_TIER.get());
			tierMap.put(ExoplanetsRegistry.TRAPPIST_1_B, TRAPPIST1_B_TIER.get());
			tierMap.put(ExoplanetsRegistry.TRAPPIST_1_C, TRAPPIST1_C_TIER.get());
			tierMap.put(ExoplanetsRegistry.TRAPPIST_1_D, TRAPPIST1_D_TIER.get());
			tierMap.put(ExoplanetsRegistry.TRAPPIST_1_E, TRAPPIST1_E_TIER.get());
			tierMap.put(ExoplanetsRegistry.TRAPPIST_1_F, TRAPPIST1_F_TIER.get());
			tierMap.put(ExoplanetsRegistry.TRAPPIST_1_G, TRAPPIST1_G_TIER.get());
			tierMap.put(ExoplanetsRegistry.TRAPPIST_1_H, TRAPPIST1_H_TIER.get());
			tierMap.put(ExoplanetsRegistry.WOLF_1061_B, WOLF1061_B_TIER.get());
			tierMap.put(ExoplanetsRegistry.WOLF_1061_C, WOLF1061_C_TIER.get());
			tierMap.put(ExoplanetsRegistry.WOLF_1061_D, WOLF1061_D_TIER.get());
			tierMap.put(ExoplanetsRegistry.YZ_CETI_B, YZCETI_B_TIER.get());
			tierMap.put(ExoplanetsRegistry.YZ_CETI_C, YZCETI_C_TIER.get());
			tierMap.put(ExoplanetsRegistry.YZ_CETI_D, YZCETI_D_TIER.get());
		}
	}
}
