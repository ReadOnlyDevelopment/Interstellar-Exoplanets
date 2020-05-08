package net.rom.exoplanets.init;

import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.internal.item.IEnumItems;
import net.rom.exoplanets.item.EnumIngots;
import net.rom.exoplanets.item.ItemTwoPlayerRocket;

public class ExoItems {

	public static void registerAll(StellarRegistry registry) {

		IEnumItems.RegistrationHelper enumItems = new IEnumItems.RegistrationHelper(registry);

		enumItems.registerItems(EnumIngots.values());
		registry.registerItem(new ItemTwoPlayerRocket("two_player_rocket"), "two_player_rocket");
	}

}
