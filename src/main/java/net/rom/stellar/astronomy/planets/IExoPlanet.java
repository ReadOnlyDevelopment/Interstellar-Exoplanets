package net.rom.stellar.astronomy.planets;

import java.util.ArrayList;

import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import net.rom.core.space.WorldProviderExoPlanet;
import net.rom.core.space.enums.EnumClass;
import net.rom.core.space.enums.EnumDiscMethod;

/**
 * The Interface IExoPlanet.
 */
public interface IExoPlanet {

	/**
	 * Gets the planet name.
	 *
	 * @return the planetName
	 */
	public String getPlanetName();
	
	/**
	 * Gets Solar System the planet belongs too.
	 *
	 * @return the planetSystem
	 */
	public SolarSystem getPlanetSystem();
	
	/**
	 * Gets planets Host star.
	 *
	 * @return the planetHost
	 */
	public Star getPlanetHost();
	
	/**
	 * Gets the planets letter assignment by Solar System name.
	 *
	 * @return the planetLetter
	 */
	public String getPlanetLetter();
	
	/**
	 * Gets ExoPlanet classification set by 
	 *
	 * @return the exoClass
	 */
	public EnumClass getExoClass();
	
	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public EnumDiscMethod getMethod();
	
	/**
	 * Gets the orbit period.
	 *
	 * @return the orbitPeriod
	 */
	public float getOrbitPeriod();
	
	/**
	 * Gets the planet density.
	 *
	 * @return the planetDensity
	 */
	public int getPlanetDensity();
	
	/**
	 * Gets the planet mass.
	 *
	 * @return the planetMass
	 */
	public float getPlanetMass();
	
	/**
	 * Gets the planet temp.
	 *
	 * @return the planetTemp
	 */
	public float getPlanetTemp();
	
	/**
	 * Gets the base radiation.
	 *
	 * @return the baseRadiation
	 */
	public float getBaseRadiation();
	
	/**
	 * Gets the base toxicity.
	 *
	 * @return the baseToxicity
	 */
	public float getBaseToxicity();
	
	/**
	 * Checks if is breathable.
	 *
	 * @return the isBreathable
	 */
	public boolean isBreathable();
	
	/**
	 * Checks if is does rain.
	 *
	 * @return the doesRain
	 */
	public boolean DoesRain();
	
	/**
	 * Gets the atmos.
	 *
	 * @return the atmos
	 */
	public AtmosphereInfo getAtmos();
	
	/**
	 * Gets the atmos gasses.
	 *
	 * @return the atmosGasses
	 */
	public ArrayList<EnumAtmosphericGas> getAtmosGasses();
	
	/**
	 * Gets the planet provider.
	 *
	 * @return the planetProvider
	 */
	public WorldProviderExoPlanet getPlanetProvider();
}
