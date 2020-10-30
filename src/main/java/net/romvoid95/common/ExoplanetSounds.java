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

package net.romvoid95.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;

import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.core.ExoInfo;

public class ExoplanetSounds {

	private static final List<SoundEvent> ALL = new ArrayList<>();

	public static final SoundEvent CUSTOM_THUNDER   = create("thunder");

	public static void registerAll (ExoRegistry registry) {

		registry.registerSoundEvent(CUSTOM_THUNDER, "thunder");
	}

	private static SoundEvent create (String soundId) {
		SoundEvent soundEvent = new SoundEvent(new ResourceLocation(ExoInfo.MODID, soundId));
		ALL.add(soundEvent);
		return soundEvent;
	}

	public static void playAllHotswapFix (EntityPlayer player) {
		for (SoundEvent sound : ALL) {
			player.world.playSound(null, player.getPosition(), sound, SoundCategory.PLAYERS, 0.05f, 1f);
		}
	}

}
