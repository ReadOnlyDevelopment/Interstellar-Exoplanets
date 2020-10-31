package net.rom.api.space;

import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import net.rom.api.space.impl.IExoSystem;

public class ExoSystem extends SolarSystem implements IExoSystem {

	private float habitableZoneStart;
	private float habitableZoneEnd;
	private String systemName;

	public ExoSystem(String solarSystem, String parentGalaxy) {
		super(solarSystem.toLowerCase(), parentGalaxy);
		this.systemName = solarSystem;
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

	@Override
	public String getSolarSystemName() {
		return this.systemName;
	}
}
