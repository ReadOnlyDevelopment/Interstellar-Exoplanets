package net.rom.exoplanets.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.rom.exoplanets.ExoplanetsCustomSounds;
import net.rom.exoplanets.internal.StellarRegistry;

public class RegistrationHandler {

	public static void init(StellarRegistry r) {
		r.addRegistrationHandler(ExoEntities::registerAll, EntityEntry.class);
		r.addRegistrationHandler(ExoBlocks::registerAll, Block.class);
		r.addRegistrationHandler(ExoItems::registerAll, Item.class);
		r.addRegistrationHandler(ExoplanetsCustomSounds::registerAll, SoundEvent.class);
	}
}
