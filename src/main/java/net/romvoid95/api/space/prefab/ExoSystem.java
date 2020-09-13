package net.romvoid95.api.space.prefab;

import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import net.romvoid95.api.space.interfaces.IExoSystem;

public class ExoSystem extends SolarSystem implements IExoSystem {

	private float habitableZoneStart;
	private float habitableZoneEnd;

	public ExoSystem(String solarSystem, String parentGalaxy) {
		super(solarSystem, parentGalaxy);
	}

	public ExoSystem setHabitableZoneStart (float start) {
		this.habitableZoneStart = start;
		return this;
	}

	public ExoSystem setHabitableZoneEnd (float end) {
		this.habitableZoneEnd = end;
		return this;
	}

	@Override
	public float getHabitableZoneStart () {
		return this.habitableZoneStart;
	}

	@Override
	public float getHabitableZoneEnd () {
		return this.habitableZoneEnd;
	}

}
