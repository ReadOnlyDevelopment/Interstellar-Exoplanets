package net.rom.api.stellar.interfaces;

import net.rom.api.stellar.enums.EnumLuminosityClass;
import net.rom.api.stellar.enums.EnumSpectralClass;

public interface IExoStar {
	
	/**
	 * Gets the star name.
	 *
	 * @return the planetName
	 */
	public String getStarName();
	
//	/**
//	 * Gets Solar System the star belongs too.
//	 *
//	 * @return the planetSystem
//	 */
//	public SolarSystem getStarSystem();
	
	/**
	 * Gets the surface temperature of the star in Kelvins
	 *
	 * @return the surface temperature
	 */
	public int getSurfaceTemp();
	
	/**
	 * Gets the stars radius in solar units
	 *
	 * @return the stars radius in solar units
	 */
	public double getStellarRadius();

	/**
	 * Gets the stars radius in solar units
	 *
	 * @return the stars radius in solar units
	 */
	public double getMass();
	
	/**
	 * Gets the Spectral Classifcation
	 *
	 * @return the SpectralClassifcation
	 */
	public EnumSpectralClass getSpectralClassifcation();
	
	public EnumLuminosityClass getLuminosityClass();

}
