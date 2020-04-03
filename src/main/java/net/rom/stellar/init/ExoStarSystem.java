package net.rom.stellar.init;

import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.rom.core.space.AstroBuilder;
import net.rom.stellar.Exoplanets;

public class ExoStarSystem {
	
	public static SolarSystem YZCETI;
	static AstroBuilder b = new AstroBuilder(Exoplanets.MODID);
	
	public static void init() {
		registerSolarSystems();
		initializeSolarSystems();
	}

	private static void registerSolarSystems() {
		
		YZCETI = b.buildSolarSystem("yzceti", "milky_way", new Vector3(1.0F,  1.0F, 0.0F), "yzcetia");
	}

	private static void initializeSolarSystems() {
		b.registerSolarSystem(YZCETI);
	}
	
}