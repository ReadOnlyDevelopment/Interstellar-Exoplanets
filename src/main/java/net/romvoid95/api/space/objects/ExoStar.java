package net.romvoid95.api.space.objects;

import asmodeuscore.api.space.ICelestialRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;

public class ExoStar extends Star implements ICelestialRegistry {

	protected SolarSystem parentSolarSystem = null;

	public ExoStar(String starName) {
		super(starName);
	}

	@Override
	public SolarSystem getParentSolarSystem () {
		return this.parentSolarSystem;
	}

	@Override
	public int getID () {
		return this.parentSolarSystem.getID();
	}

	@Override
	public String getUnlocalizedNamePrefix () {
		return "exostar";
	}

	@Override
	public ExoStar setParentSolarSystem (SolarSystem galaxy) {
		this.parentSolarSystem = galaxy;
		return this;
	}

	@Override
	public boolean canRegistry () {
		return true;
	}
}
