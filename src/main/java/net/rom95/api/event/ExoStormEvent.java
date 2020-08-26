package net.rom95.api.event;

import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.rom95.api.world.StormProvider;

/**
 * Event is thrown when a StormProvider is set to execute
 */
public class ExoStormEvent extends Event {
	StormProvider        storm;
	public WorldProvider provider;

	public ExoStormEvent(StormProvider storm) {
		this.storm    = storm;
		this.provider = storm.getProvider();
	}

	/**
	 * You can call this to cancel the Storm Event
	 * 
	 * Make sure you perform a check if the WorldProvider is instanceof 
	 * your desired World
	 *
	 */
	@Cancelable
	public static class Pre extends ExoStormEvent {
		public Pre(StormProvider storm) {
			super(storm);
		}
	}

	public static class Post extends ExoStormEvent {
		public Post(StormProvider storm) {
			super(storm);
		}
	}
}
