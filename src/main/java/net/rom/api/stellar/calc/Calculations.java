package net.rom.api.stellar.calc;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;

public class Calculations {

	public static final long yearFactor = 8640000L;
	public static final long monthFactor = 192000L;
	public static final String nameSeparator = "\\";
	// try doing this in AUs
	public static final double moonDistanceFactor = 0.00015;
	// earth<-->sun = 1 -> 149598023 => 39
	public static final double planetDistanceFactor = 1.0;
	// sun<-->ra = 1069,17 (raw value from map coords). proportional value from
	// pixels: 12,8
	public static final double systemDistanceFactor = 12.3 / 1069.17;

	public static final float maxTemperature = 5.0F;
	public static final float maxSolarLevel = 10.0F;

	public static final double AUlength = 149597870700.0;

	public static final double maxSpeed = 299792458.0D; // this used to be an arbitary value, but
														// the actual speed of light makes for a
														// good maxSpeed

	/**
	 * Should calculate a thermal level depending on that body's distance from the
	 * star in it's system
	 *
	 * @param body
	 * @return
	 */
	public static float getThermalLevel(CelestialBody body) {
		if (body instanceof Star) {
			return maxTemperature;
		}
		body = getParentPlanet(body);
		float dist = body.getRelativeDistanceFromCenter().unScaledDistance;
		float temperature = -4 * dist + 4;
		if (temperature < -maxTemperature) {
			temperature = -maxTemperature;
		} else if (temperature > maxTemperature) {
			temperature = maxTemperature;
		}

		return temperature;
	}

	public static CelestialBody getParentPlanet(CelestialBody body) {
		if (body == null) {
			return null;
		}
		if (body instanceof Moon) {
			return ((Moon) body).getParentPlanet();
		}
		if (body instanceof IChildBody) {
			return ((IChildBody) body).getParentPlanet();
		}

		return body;
	}

	/**
	 * Obtains Schwartzchild radius for an object of certain mass. If the radius is
	 * lower than this value, then the object is a black hole.
	 *
	 * @param M Mass in solar masses.
	 * @return Radius in solar radii.
	 */
	public static double schwartzchildRadius(double M) {
		double r = 2.0 * AstronomicalConstants.GRAVITATIONAL_CONSTANT * M * AstronomicalConstants.SUN_MASS
				/ (AstronomicalConstants.SPEED_OF_LIGHT * AstronomicalConstants.SPEED_OF_LIGHT);

		return r / (1000.0 * AstronomicalConstants.SUN_RADIUS);
	}
	
	/**
	 * Obtain the luminosity using the mass-luminosity relation for main sequence
	 * stars. Formula used depends on if the mass is lower than 0.43, lower than 2,
	 * and lower than 20 or not. See http://en.wikipedia.org/wiki/Mass%E2%80%93luminosity_relation
	 * for the different formulae using Cassisi (2005) and Duric (2004).
	 * @param mass Mass in solar units.
	 * @return Luminosity in solar units.
	 */
	public static double getLuminosityFromMassLuminosityRelation(double mass)
	{
		if (mass < 0.43) return 0.23 * Math.pow(mass, 2.3);
		if (mass < 2) return Math.pow(mass, 4);
		if (mass > 20) return (1.5 * Math.pow(20, 3.5)) * Math.pow(mass / 20, 2); // Wikipedia says 1 as exponent here, but seems excesive
		return 1.5 * Math.pow(mass, 3.5);
	}
	

	/**
	 * Obtains star radius.
	 *
	 * @param luminosity Luminosity in solar units.
	 * @param temperature Temperature in K.
	 * @return Radius in solar radii.
	 */
	public static double getStarRadius(double luminosity, double temperature)
	{
		return Math.sqrt(luminosity * AstronomicalConstants.SUN_LUMINOSITY / (4.0 * Math.PI * AstronomicalConstants.STEFAN_BOLTZMANN_CONSTANT *
				Math.pow(temperature, 4.0))) / (AstronomicalConstants.SUN_RADIUS * 1000.0);
	}
	/**
	 * Obtains star luminosity.
	 *
	 * @param radius Radius in solar units.
	 * @param temperature Temperature in K.
	 * @return Luminosity in solar units.
	 */
	public static double getStarLuminosity(double radius, double temperature)
	{
		double luminosity =  Math.pow(radius * AstronomicalConstants.SUN_RADIUS * 1000.0, 2.0) *
			(4.0 * Math.PI * AstronomicalConstants.STEFAN_BOLTZMANN_CONSTANT * Math.pow(temperature, 4.0));

		return luminosity / AstronomicalConstants.SUN_LUMINOSITY;
	}
	
	/**
	 * Obtains the surface gravity.
	 * @param mass Mass in solar masses.
	 * @param radius Radius in solar radii.
	 * @return Gravity in m/s^2.
	 */
	public static double getSurfaceGravity(double mass, double radius)
	{
		double g = AstronomicalConstants.GRAVITATIONAL_CONSTANT * mass * AstronomicalConstants.SUN_MASS / (Math.pow(radius * AstronomicalConstants.SUN_RADIUS * 1000.0, 2.0));
		return g;
	}

	protected static String getSystemMainStarName(SolarSystem sys) {
		return sys.getName();
	}

	protected static String getPlanetName(Planet planet) {
		return getSystemMainStarName(planet.getParentSolarSystem()) + nameSeparator + planet.getName();
	}

	protected static String getMoonName(Moon moon) {
		return getPlanetName(moon.getParentPlanet()) + nameSeparator + moon.getName();
	}
}
