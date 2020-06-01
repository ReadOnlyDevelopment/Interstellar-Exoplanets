/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import net.rom.exoplanets.internal.enums.EnumPlanetType;
import net.rom.exoplanets.internal.enums.EnumTPHClass;

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
	 * and lower than 20 or not. See
	 * http://en.wikipedia.org/wiki/Mass%E2%80%93luminosity_relation for the
	 * different formulae using Cassisi (2005) and Duric (2004).
	 * 
	 * @param mass Mass in solar units.
	 * @return Luminosity in solar units.
	 */
	public static double getLuminosityFromMassLuminosityRelation(double mass) {
		if (mass < 0.43)
			return 0.23 * Math.pow(mass, 2.3);
		if (mass < 2)
			return Math.pow(mass, 4);
		if (mass > 20)
			return (1.5 * Math.pow(20, 3.5)) * Math.pow(mass / 20, 2); // Wikipedia says 1 as exponent here, but seems
																		// excesive
		return 1.5 * Math.pow(mass, 3.5);
	}
	
	

	/**
	 * Obtains star radius.
	 *
	 * @param luminosity  Luminosity in solar units.
	 * @param temperature Temperature in K.
	 * @return Radius in solar radii.
	 */
	public static double getStarRadius(double luminosity, double temperature) {
		return Math.sqrt(luminosity * AstronomicalConstants.SUN_LUMINOSITY
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
	public static double getStarLuminosity(double radius, double temperature) {
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
	public static double getStarSurfaceGravity(double mass, double radius) {
		double g = AstronomicalConstants.GRAVITATIONAL_CONSTANT * mass * AstronomicalConstants.SUN_MASS
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
	public static double getPlanetSurfaceGravity(double mass, double radius) {
		double g = AstronomicalConstants.GRAVITATIONAL_CONSTANT * (mass * AstronomicalConstants.EARTH_RADIUS)
				/ (Math.pow(radius * AstronomicalConstants.EARTH_RADIUS, 2.0));
		return g;
	}

	/**
	 * Between.
	 *
	 * @param i                 the i
	 * @param minValueInclusive the min value inclusive
	 * @param maxValueInclusive the max value inclusive
	 * @return true, if successful
	 */
	public static boolean between(double i, double minValueInclusive, double maxValueInclusive) {
		return (i >= minValueInclusive && i <= maxValueInclusive);
	}

	/**
	 * Checks if is more than.
	 *
	 * @param i   the i
	 * @param max the max
	 * @return true, if is more than
	 */
	public static boolean isMoreThan(double i, double max) {
		return i >= max;
	}

	/**
	 * Checks if is less than.
	 *
	 * @param i   the i
	 * @param min the min
	 * @return true, if is less than
	 */
	public static boolean isLessThan(double i, double min) {
		return i <= min;
	}

	/**
	 * Gets the TPH from temp.
	 *
	 * @param temp the temp
	 * @return the TPH from temp
	 */
	public static EnumTPHClass getTPHFromTemp(double temp) {
		if (between(temp, -100.0, -50.0)) {
			return EnumTPHClass.HP;
		}
		if (between(temp, -49.0, 0.0)) {
			return EnumTPHClass.P;
		}
		if (between(temp, 1.0, 49.0)) {
			return EnumTPHClass.M;
		}
		if (between(temp, 50.0, 100.0)) {
			return EnumTPHClass.T;
		}
		if (between(temp, 101.0, 150.0)) {
			return EnumTPHClass.HT;
		}
		if (isLessThan(temp, -100.0)) {
			return EnumTPHClass.HP;
		} else {
			return EnumTPHClass.HT;
		}
	}

	public static EnumPlanetType getPlanetType(double mass, double radius) {
		if (datasetAsteroidan(mass, radius)) {
			return EnumPlanetType.ASTEROIDAN;
		}
		if (datasetMercurian(mass, radius)) {
			return EnumPlanetType.MERCURIAN;
		}
		if (datasetSubterran(mass, radius)) {
			return EnumPlanetType.SUBTERRAN;
		}
		if (datasetTerran(mass, radius)) {
			return EnumPlanetType.TERRAN;
		}
		if (datasetSuperterran(mass, radius)) {
			return EnumPlanetType.SUPERTERRAN;
		}
		if (datasetNeptunian(mass, radius)) {
			return EnumPlanetType.NEPTUNIAN;
		}
		if (datasetJovian(mass, radius)) {
			return EnumPlanetType.JOVIAN;
		} else {
			return null;
		}

	}

	private static boolean datasetAsteroidan(double mass, double radius) {
		return between(mass, 0, 0.00001) && between(radius, 0, 0.03) ? true : false;

	}

	private static boolean datasetMercurian(double mass, double radius) {
		return between(mass, 0.00001, 0.1) && between(radius, 0.03, 0.7) ? true : false;

	}

	private static boolean datasetSubterran(double mass, double radius) {
		return between(mass, 0.1, 0.5) && between(radius, 0.5, 1.2) ? true : false;

	}

	private static boolean datasetTerran(double mass, double radius) {
		return between(mass, 0.5, 2) && between(radius, 0.8, 1.9) ? true : false;

	}

	private static boolean datasetSuperterran(double mass, double radius) {
		return between(mass, 2, 10) && between(radius, 1.3, 3.3) ? true : false;
	}

	private static boolean datasetNeptunian(double mass, double radius) {
		return between(mass, 10, 50) && between(radius, 2.1, 5.7) ? true : false;

	}

	private static boolean datasetJovian(double mass, double radius) {
		return between(mass, 50, 5000) && between(radius, 3.5, 27) ? true : false;
	}

}
