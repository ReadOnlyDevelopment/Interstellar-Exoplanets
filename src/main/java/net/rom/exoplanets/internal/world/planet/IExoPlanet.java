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

package net.rom.exoplanets.internal.world.planet;

import java.util.ArrayList;

import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import net.rom.exoplanets.internal.enums.EnumDiscMethod;
import net.rom.exoplanets.internal.enums.EnumPlanetType;
import net.rom.exoplanets.internal.enums.EnumTPHClass;
import net.rom.exoplanets.internal.world.WorldProviderExoPlanet;

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
	public float getGravity();
	
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
