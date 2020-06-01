/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.content;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoplanetsCustomSounds {
	
	private static final List<SoundEvent> ALL = new ArrayList<>();

	public static final SoundEvent SPACEDOOR_OPEN = create("space_door_open");
	public static final SoundEvent SPACEDOOR_CLOSE  = create("space_door_close");
	public static final SoundEvent HYDOOR_OPEN  = create("hydoor_open");
	public static final SoundEvent HYDOOR_CLOSE  = create("hydoor_close");
	public static final SoundEvent CUSTOMDOOR_OPEN  = create("customdoor_open");
	public static final SoundEvent CUSTOMDOOR_CLOSE  = create("customdoor_close");

	public static void registerAll(StellarRegistry registry) {
		registry.registerSoundEvent(SPACEDOOR_CLOSE, "space_door_open");
		registry.registerSoundEvent(SPACEDOOR_OPEN, "space_door_close");
		registry.registerSoundEvent(HYDOOR_OPEN, "hydoor_open");
		registry.registerSoundEvent(HYDOOR_CLOSE, "hydoor_close");
		registry.registerSoundEvent(CUSTOMDOOR_OPEN, "customdoor_open");
		registry.registerSoundEvent(CUSTOMDOOR_CLOSE, "customdoor_close");
	}
	
    private static SoundEvent create(String soundId) {
        SoundEvent soundEvent = new SoundEvent(new ResourceLocation(ExoInfo.MODID, soundId));
        ALL.add(soundEvent);
        return soundEvent;
    }
    
    public static void playAllHotswapFix(EntityPlayer player) {
        for (SoundEvent sound : ALL) {
            player.world.playSound(null, player.getPosition(), sound, SoundCategory.PLAYERS, 0.05f, 1f);
        }
    }

}
