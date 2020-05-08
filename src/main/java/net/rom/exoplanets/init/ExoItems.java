package net.rom.exoplanets.init;

import net.rom.exoplanets.content.item.EnumIngots;
import net.rom.exoplanets.content.item.ItemTwoPlayerRocket;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.internal.item.IEnumItems;

public class ExoItems {
	
	public static final ItemTwoPlayerRocket passengerRocket = new ItemTwoPlayerRocket();

	public static void registerAll(StellarRegistry registry) {
		
		registry.registerItem(passengerRocket, "two_player_rocket");

		IEnumItems.RegistrationHelper enumItems = new IEnumItems.RegistrationHelper(registry);
		enumItems.registerItems(EnumIngots.values());

	}

}
