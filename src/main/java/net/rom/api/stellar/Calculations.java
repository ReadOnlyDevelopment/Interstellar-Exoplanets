package net.rom.api.stellar;

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
