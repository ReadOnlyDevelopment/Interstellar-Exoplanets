package net.rom.exoplanets.astronomy.orbitalplatform;

import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOverworldOrbit;

public class OrbitalPlatform extends Moon implements IChildBody {
	
	protected Planet parentCelestialBody = null;

	public OrbitalPlatform(String bodyName) {
		super(bodyName);
		setDimensionInfo(-4901, WorldProviderOverworldOrbit.class);
	}

    @Override
    public Planet getParentPlanet()
    {
        return this.parentCelestialBody;
    }

    public OrbitalPlatform setParentBody(Planet parentCelestialBody)
    {
        this.parentCelestialBody = parentCelestialBody;
        return this;
    }

    @Override
    public int getID()
    {
        return GalaxyRegistry.getMoonID(this.bodyName);
    }

    @Override
    public String getUnlocalizedNamePrefix()
    {
        return "platform";
    }
}
