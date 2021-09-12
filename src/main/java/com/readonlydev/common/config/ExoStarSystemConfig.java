package com.readonlydev.common.config;

import com.readonlydev.Exoplanets;
import com.readonlydev.lib.base.config.ReadOnlyConfig;
import com.readonlydev.lib.base.config.impl.Category;
import com.readonlydev.lib.base.config.impl.Range;
import com.readonlydev.lib.base.config.values.ConfigArrayDouble;
import com.readonlydev.lib.base.config.values.ConfigBoolean;
import com.readonlydev.lib.base.config.values.ConfigInteger;

import micdoodle8.mods.galacticraft.api.vector.Vector3;

public class ExoStarSystemConfig extends ReadOnlyConfig {
	
	private final Category C_YZCETI = Category.of("Yz Ceti System");
	private final Category C_WOLF1061 = Category.of("Wolf 1061 System");
	private final Category C_TRAPPIST1 = Category.of("Trappist 1 System");
	private final Category C_KEPLER1649 = Category.of("Kepler 1649 System");

	private final Range<Double> range = Range.of(-50.0D, 50.0D);

	public static ConfigBoolean ENABLE_YZCETI;
	public static ConfigBoolean ENABLE_WOLF1061;
	public static ConfigBoolean ENABLE_TRAPPIST1;
	public static ConfigBoolean ENABLE_KEPLER1649;

	public static ConfigInteger TIER_YZCETI;
	public static ConfigInteger TIER_WOLF1061;
	public static ConfigInteger TIER_TRAPPIST1;
	public static ConfigInteger TIER_KEPLER1649;

	public static ConfigArrayDouble MAP_YZCETI;
	public static ConfigArrayDouble MAP_WOLF1061;
	public static ConfigArrayDouble MAP_TRAPPIST1;
	public static ConfigArrayDouble MAP_KEPLER1649;
	
	public static Vector3 POS_YZCETI;
	public static Vector3 POS_WOLF1061;
	public static Vector3 POS_TRAPPIST1;
	public static Vector3 POS_KEPLER1649;

	public ExoStarSystemConfig() {
		super(Exoplanets._instance, "StarSystems");

		C_YZCETI.setCategoryComment("Settings that affect the entire Yz Ceti System");
		C_YZCETI.setRequiredRestarts(false, true);
		C_WOLF1061.setCategoryComment("Settings that affect the entire Wolf 1061 System");
		C_WOLF1061.setRequiredRestarts(false, true);
		C_TRAPPIST1.setCategoryComment("Settings that affect the entire Trappist 1 System");
		C_TRAPPIST1.setRequiredRestarts(false, true);
		C_KEPLER1649.setCategoryComment("Settings that affect the entire Kepler 1649 System");
		C_KEPLER1649.setRequiredRestarts(false, true);
		addCategories(C_YZCETI, C_WOLF1061, C_TRAPPIST1, C_KEPLER1649);

		ENABLE_YZCETI = new ConfigBoolean(C_YZCETI, "enableYzCeti", d_comment("Yz Ceti"), true);
		TIER_YZCETI = new ConfigInteger(C_YZCETI, "yz ceti tier", t_comment("Yz Ceti"), 3);
		MAP_YZCETI = new ConfigArrayDouble(C_YZCETI, "yz ceti position", m_comment(), range, -2.7D, -2.6D);
		addProperties(ENABLE_YZCETI, TIER_YZCETI, MAP_YZCETI);
		
		ENABLE_WOLF1061 = new ConfigBoolean(C_WOLF1061, "enableWolf1061", d_comment("Wolf 1061"), true);
		TIER_WOLF1061 = new ConfigInteger(C_WOLF1061, "wolf 1061 tier", t_comment("Wolf 1061"), 3);
		MAP_WOLF1061 = new ConfigArrayDouble(C_WOLF1061, "wolf 1061 position", m_comment(), range, -2.0D, -1.5D);
		addProperties(ENABLE_WOLF1061, TIER_WOLF1061, MAP_WOLF1061);
		
		ENABLE_TRAPPIST1 = new ConfigBoolean(C_TRAPPIST1, "enableTrappist1", d_comment("Trappist 1"), true);
		TIER_TRAPPIST1 = new ConfigInteger(C_TRAPPIST1, "trappist 1 tier", t_comment("Trappist 1"), 3);
		MAP_TRAPPIST1 = new ConfigArrayDouble(C_TRAPPIST1, "trappist 1 position", m_comment(), range, 2.0D, -1.5D);
		addProperties(ENABLE_TRAPPIST1, TIER_TRAPPIST1, MAP_TRAPPIST1);
		
		ENABLE_KEPLER1649 = new ConfigBoolean(C_KEPLER1649, "enableKepler1649", d_comment("Kepler 1649"), true);
		TIER_KEPLER1649 = new ConfigInteger(C_KEPLER1649, "kepler 1649 tier", t_comment("Kepler 1649"), 3);
		MAP_KEPLER1649 = new ConfigArrayDouble(C_KEPLER1649, "kepler 1649 position", m_comment(), range, 1.3D, -2.6D);
		addProperties(ENABLE_KEPLER1649, TIER_KEPLER1649, MAP_KEPLER1649);
		
		this.setVectorPostitions();
	}
	
	public void setVectorPostitions() {
		double[] y = MAP_YZCETI.get();
		POS_YZCETI = new Vector3(y[0], y[0], 0.0D);
		double[] w = MAP_WOLF1061.get();
		POS_WOLF1061 = new Vector3(w[0], w[0], 0.0D);
		double[] t = MAP_TRAPPIST1.get();
		POS_TRAPPIST1 = new Vector3(t[0], t[0], 0.0D);
		double[] k = MAP_KEPLER1649.get();
		POS_KEPLER1649 = new Vector3(k[0], k[0], 0.0D);
	}

	private String d_comment(String system) {
		return "Setting to FALSE disables the " + system + " System and all its planets";
	}

	private String t_comment(String system) {
		return "Sets the required Rocket Tier for the entire " + system + " System";
	}

	private String m_comment() {
		return "The position of the system on the Celestial Selection Map";
	}
}
