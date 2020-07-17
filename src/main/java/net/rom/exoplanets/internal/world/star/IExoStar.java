/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
