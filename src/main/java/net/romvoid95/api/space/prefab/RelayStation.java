package net.romvoid95.api.space.prefab;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;

import asmodeuscore.api.space.ICelestialRegistry;
import net.romvoid95.api.space.ExoSpaceRegistry;

public class RelayStation extends CelestialBody implements ICelestialRegistry {

	private CelestialBody parent = null;
	private SolarSystem   system = null;

	public RelayStation(String bodyName, CelestialBody parent, SolarSystem system) {
		super(bodyName);
		this.parent = parent;
		this.system = system;
	}

	public CelestialBody getParent () {
		return this.parent;
	}

	public CelestialBody setRelativeDistanceFromCenter (float relativeDistanceFromCenter) {
		ScalableDistance distance = new ScalableDistance(relativeDistanceFromCenter, relativeDistanceFromCenter);
		return super.setRelativeDistanceFromCenter(distance);
	}

	@Override
	public int getID () {
		return ExoSpaceRegistry.getRelayStationID(this.bodyName);
	}

	@Override
	public String getUnlocalizedNamePrefix () {
		return "relay";
	}

	@Override
	public boolean canRegistry () {
		return true;
	}

	@Override
	public SolarSystem getParentSolarSystem () {
		return this.system;
	}

}
