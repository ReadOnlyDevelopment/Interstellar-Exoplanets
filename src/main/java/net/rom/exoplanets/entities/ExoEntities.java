package net.rom.exoplanets.entities;

import net.rom.exoplanets.internal.StellarRegistry;

public class ExoEntities {
	public static void registerAll(StellarRegistry registry) {
		registry.registerEntity(EntityTwoPlayerRocket.class, "two_person_rocket", 150, 1, false);
	}
}
