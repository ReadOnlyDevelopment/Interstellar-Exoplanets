package net.romvoid95.api.space.stellarmathmatics;

import lombok.experimental.UtilityClass;
import net.romvoid95.api.space.utility.AstronomicalConstants;

@UtilityClass
public class StarMath {

	/**
	 * Obtains surface brightness of an object.
	 *
	 * @param m Magnitude.
	 * @param r Radius in arcseconds. If the object is not circular, an
	 *        equivalent value can be given so that the area (PI * r^2) in equal
	 *        to the area of the object in the sky.
	 * @return Brightness in mag/arcsecond^2, or 0 if radius is 0.
	 */
	public static double getSurfaceBrightness (double m, double r) {
		double bright = 0.0;
		if (r <= 0.0) {
			return bright;
		}

		double area = Math.PI * (r / 60.0) * (r / 60.0);
		double s1   = m + (Math.log(area) / Math.log(Math.pow(100.0, 0.2)));
		bright = s1 + 8.890756;
		return bright;
	}

	/**
	 * Obtain the luminosity using the mass-luminosity relation for main sequence
	 * stars. Formula used depends on if the mass is lower than 0.43, lower than 2,
	 * and lower than 20 or not. See http://en.wikipedia.org/wiki/Mass%E2%80%93luminosity_relation
	 * for the different formulae using Cassisi (2005) and Duric (2004).
	 * @param mass Mass in solar units.
	 * @return Luminosity in solar units.
	 */
	public static double getLuminosityFromMassLuminosityRelation (double mass) {
		if (mass < 0.43) {
			return 0.23 * Math.pow(mass, 2.3);
		}
		if (mass < 2) {
			return Math.pow(mass, 4);
		}
		if (mass > 20)
		{
			return (1.5 * Math.pow(20, 3.5)) * Math.pow(mass / 20, 2); // Wikipedia says 1 as exponent here, but seems excesive
		}
		return 1.5 * Math.pow(mass, 3.5);
	}

	/**
	 * Obtain the luminosity using the mass-luminosity relation for main sequence
	 * stars.
	 * @param luminosity Luminosity in solar units.
	 * @return Mass in solar units.
	 */
	public static double getMassFromMassLuminosityRelation (double luminosity) {
		if (luminosity < 0.033) {
			return Math.pow(luminosity / 0.23, 1.0 / 2.3);
		}
		if (luminosity < 16) {
			return Math.pow(luminosity, 0.25);
		}
		double ul = 1.5 * Math.pow(20, 3.5);
		if (luminosity > ul)
		{
			return Math.pow((luminosity / ul), 1.0 / 2) * 20; //(luminosity * 20) / ul;
		}
		return Math.pow(luminosity / 1.5, 1.0 / 3.5);
	}

	/**
	 * Obtains approximate star life time for a given mass.
	 * @param mass Star mass in solar units.
	 * @return Lifetime in years.
	 */
	public static double getStarLifeTime (double mass) {
		double time = (1.0E10 * mass) / getLuminosityFromMassLuminosityRelation(mass);
		return time;
	}

	/**
	 * Obtains star radius.
	 *
	 * @param luminosity  Luminosity in solar units.
	 * @param temperature Temperature in K.
	 * @return Radius in solar radii.
	 */
	public static double getStarRadius (double luminosity, double temperature) {
		return Math.sqrt((luminosity * AstronomicalConstants.SUN_LUMINOSITY)
				/ (4.0 * Math.PI * AstronomicalConstants.STEFAN_BOLTZMANN_CONSTANT * Math.pow(temperature, 4.0)))
				/ (AstronomicalConstants.SUN_RADIUS * 1000.0);
	}

	/**
	 * Obtains star luminosity.
	 *
	 * @param radius      Radius in solar units.
	 * @param temperature Temperature in K.
	 * @return Luminosity in solar units.
	 */
	public static double getStarLuminosity (double radius, double temperature) {
		double luminosity = Math.pow(radius * AstronomicalConstants.SUN_RADIUS * 1000.0, 2.0)
				* (4.0 * Math.PI * AstronomicalConstants.STEFAN_BOLTZMANN_CONSTANT * Math.pow(temperature, 4.0));

		return luminosity / AstronomicalConstants.SUN_LUMINOSITY;
	}

	/**
	 * Obtains the surface gravity of a star.
	 * 
	 * @param mass   Mass in solar masses.
	 * @param radius Radius in solar radii.
	 * @return Gravity in m/s^2.
	 */
	public static double getStarSurfaceGravity (double mass, double radius) {
		double g = (AstronomicalConstants.GRAVITATIONAL_CONSTANT * mass * AstronomicalConstants.SUN_MASS)
				/ (Math.pow(radius * AstronomicalConstants.SUN_RADIUS * 1000.0, 2.0));
		return g;
	}

	/**
	 * Obtains the surface gravity of a star.
	 * 
	 * @param mass   Mass in solar masses.
	 * @param radius Radius in solar radii.
	 * @return Gravity in m/s^2.
	 */
	public static double getPlanetSurfaceGravity (double mass, double radius) {
		double g = (AstronomicalConstants.GRAVITATIONAL_CONSTANT * (mass * AstronomicalConstants.EARTH_RADIUS))
				/ (Math.pow(radius * AstronomicalConstants.EARTH_RADIUS, 2.0));
		return g;
	}
	
	public static double toEarthRadi(double radius) {
		return (AstronomicalConstants.JUPITER_RADIUS_IN_EARTHS * radius);
	}

}
