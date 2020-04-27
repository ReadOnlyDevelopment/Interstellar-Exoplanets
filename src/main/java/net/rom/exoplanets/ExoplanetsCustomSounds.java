package net.rom.exoplanets;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
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
