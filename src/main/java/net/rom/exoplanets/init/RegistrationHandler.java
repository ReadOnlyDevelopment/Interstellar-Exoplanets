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

package net.rom.exoplanets.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.util.ExoplanetsCustomSounds;

public class RegistrationHandler {

	public static void init(StellarRegistry r) {
		r.addRegistrationHandler(ExoEntities::registerAll, EntityEntry.class);
		r.addRegistrationHandler(ExoBlocks::registerAll, Block.class);
		r.addRegistrationHandler(ExoItems::registerAll, Item.class);
		r.addRegistrationHandler(ExoplanetsCustomSounds::registerAll, SoundEvent.class);
	}
}
