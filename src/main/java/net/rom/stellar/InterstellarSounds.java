package net.rom.stellar;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InterstellarSounds {

	private static final List<SoundEvent> sounds = new LinkedList<>();
	public static SoundEvent SPACEDOOR_OPEN;
	public static SoundEvent SPACEDOOR_CLOSE;

	public InterstellarSounds() {
		registerSounds();
	}

	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		sounds.stream().forEach(event.getRegistry()::register);
	}

	private void registerSounds() {
		SPACEDOOR_OPEN = registerSound("block.spacedoor.open");
		SPACEDOOR_CLOSE = registerSound("block.spacedoor.close");

	}

	private static SoundEvent registerSound(String soundName) {

		final ResourceLocation soundID = new ResourceLocation(Exoplanets.MODID, soundName);
		SoundEvent event = new SoundEvent(soundID).setRegistryName(soundID);
		sounds.add(event);
		return event;
	}
}
