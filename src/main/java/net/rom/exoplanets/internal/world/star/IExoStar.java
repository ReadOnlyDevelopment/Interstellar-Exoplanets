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

package net.rom.exoplanets.internal.world.star;

import net.rom.exoplanets.internal.enums.EnumLuminosityClass;
import net.rom.exoplanets.internal.enums.EnumSpectralClass;

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
