package net.rom.exoplanets.internal.world;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;

public interface IExoPlanetWorldProvider extends IGalacticraftWorldProvider {
	
	abstract ExoPlanet getExoPlanet();
}
