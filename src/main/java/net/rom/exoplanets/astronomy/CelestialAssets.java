package net.rom.exoplanets.astronomy;

import net.rom.exoplanets.Assets;

public class CelestialAssets {
	
	private static final String YZCETI = "yzceti";
	private static final String KELPER1649 = "kepler1649";
	private static final String TRAPPIST = "trappist1";
	private static final String WOLF1061 = "wolf1061";
	
	public static void init() {
		registerNormal();
		registerRealistic();
	}
	
	private static void registerNormal() {
		
		put("yzcetistar", YZCETI, "star");
		put("kepler1649star", KELPER1649, "star");
		put("trappist1star", TRAPPIST, "star");
		put("wolf1061star", WOLF1061, "star");
		put("yzcetib", YZCETI, "b");
		put("yzcetic", YZCETI, "c");
		put("yzcetid", YZCETI, "d");
		put("kepler1649b", KELPER1649, "b");
		put("kepler1649c", KELPER1649, "c");
		put("trappist1b", TRAPPIST, "b");
		put("trappist1c", TRAPPIST, "c");
		put("trappist1d", TRAPPIST, "d");
		put("trappist1e", TRAPPIST, "e");
		put("trappist1f", TRAPPIST, "f");
		put("trappist1g", TRAPPIST, "g");
		put("trappist1h", TRAPPIST, "h");
		put("wolf1061b", WOLF1061, "b");
		put("wolf1061c", WOLF1061, "c");
		put("wolf1061d", WOLF1061, "d");
		
	}
	
	private static void registerRealistic() {
		putRealistic("realyzcetistar", YZCETI, "star");
		putRealistic("realkepler1649star", KELPER1649, "star");
		putRealistic("realtrappist1star", TRAPPIST, "star");
		putRealistic("realwolf1061star", WOLF1061, "star");
		putRealistic("realyzcetib", YZCETI, "b");
		putRealistic("realyzcetic", YZCETI, "c");
		putRealistic("realyzcetid", YZCETI, "d");
		putRealistic("realkepler1649b", KELPER1649, "b");
		putRealistic("realkepler1649c", KELPER1649, "c");
		putRealistic("realtrappist1b", TRAPPIST, "b");
		putRealistic("realtrappist1c", TRAPPIST, "c");
		putRealistic("realtrappist1d", TRAPPIST, "d");
		putRealistic("realtrappist1e", TRAPPIST, "e");
		putRealistic("realtrappist1f", TRAPPIST, "f");
		putRealistic("realtrappist1g", TRAPPIST, "g");
		putRealistic("realtrappist1h", TRAPPIST, "h");
		putRealistic("realwolf1061b", WOLF1061, "b");
		putRealistic("realwolf1061c", WOLF1061, "c");
		putRealistic("realwolf1061d", WOLF1061, "d");

	}
	
	private static void put(String name, String systemName, String planetName) {
		Assets.addCelestialTexture(name, systemName, planetName);
	}
	
	private static void putRealistic(String name, String systemName, String planetName) {
		Assets.addRealisticCelestialTexture(name, systemName, planetName);
	}
}
