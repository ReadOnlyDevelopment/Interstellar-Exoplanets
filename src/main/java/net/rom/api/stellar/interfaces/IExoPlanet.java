package net.rom.api.stellar.interfaces;

import java.util.ArrayList;

import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import net.rom.api.stellar.enums.EnumClass;
import net.rom.api.stellar.enums.EnumDiscMethod;
import net.rom.api.stellar.enums.EnumPlanetType;
import net.rom.api.stellar.enums.EnumTPHClass;
import net.rom.api.stellar.world.WorldProviderExoPlanet;

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
	 * Gets the method.
	 *
	 * @return the method
	 */
	public EnumDiscMethod getDiscoveryMethod();
	
	/**
	 * Gets the EnumTPHClass.
	 *
	 * @return the method
	 */
	public EnumTPHClass getTphClass();
	
	/**
	 * Gets the EnumPlanetType.
	 *
	 * @return the method
	 */
	public EnumPlanetType getPlanetType();
	
	/**
	 * Gets the orbit period.
	 *
	 * @return the orbitPeriod
	 */
	public double getOrbitPeriod();
	
	/**
	 * Gets the planet mass.
	 *
	 * @return the planetMass
	 */
	public double getPlanetMass();
	
	/**
	 * Gets the planet radius.
	 *
	 * @return the planetMass
	 */
	public double getPlanetRadius();
	
	/**
	 * Gets the planet temp.
	 *
	 * @return the planetTemp
	 */
	public double getPlanetTemp();
	
	/**
	 * Gets the planets gravity.
	 *
	 * @return the gravity
	 */
	public double getGravity();
	
	/**
	 * Gets the planets day length.
	 *
	 * @return the day length
	 */
	public long getDayLength();
	
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
	public boolean isDoesRain();
	
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
