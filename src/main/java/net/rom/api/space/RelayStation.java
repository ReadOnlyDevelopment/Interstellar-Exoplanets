package net.rom.api.space;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;

public class RelayStation extends CelestialBody {

	private CelestialBody parent = null;

	public RelayStation(String bodyName) {
		super(bodyName);
	}

	public RelayStation setParent (ExoStar parent) {
		this.parent = parent;
		return this;
	}

	public ExoStar getParent () {
		return (ExoStar) this.parent;
	}

	public CelestialBody setRelativeDistanceFromCenter (float relativeDistanceFromCenter) {
		ScalableDistance distance = new ScalableDistance(relativeDistanceFromCenter, relativeDistanceFromCenter);
		return super.setRelativeDistanceFromCenter(distance);
	}

	@Override
	public int getID () {
		return ExoRegistry.getRelayStationID(this.bodyName);
	}

	@Override
	public String getUnlocalizedNamePrefix () {
		return "relay";
	}

}
