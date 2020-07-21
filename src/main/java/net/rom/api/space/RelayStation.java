package net.rom.api.space;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;

public class RelayStation extends CelestialBody implements IChildBody {

	private Planet parent = null;

	public RelayStation(String bodyName) {
		super(bodyName);
	}

	public RelayStation setParentPlanet(Planet parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public Planet getParentPlanet() {
		return this.parent;
	}

	@Override
	public int getID() {
		return ExoRegistry.getRelayStationID(this.bodyName);
	}

	@Override
	public String getUnlocalizedNamePrefix() {
		return "relay";
	}

}
