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

package net.rom.exoplanets.internal.enums;

import net.minecraft.util.IStringSerializable;

/**
 * The Enum EnumPlanetType.
 */
public enum EnumPlanetType implements IStringSerializable{

	/**
	 * <b>Asteroidan</b><br>
	 * <b>Mass </b> = 0 - 0.00001</br>
	 * <b>Radius </b> = 0 - 0.03
	 * <p>
	 * Asteroidans are small irregular bodies (below the hydrostatic equilibrium)
	 * that are not able to hold a stable atmosphere.
	 * <p>
	 * 
	 */
	ASTEROIDAN("Asteroidan"),
	
	/**
	 * <b>Mercurian</b><br>
	 * <b>Mass </b> = 0.00001 - 0.1</br>
	 * <b>Radius </b> = 0.03 - 0.7
	 * <p>
	 * Mercurians are only able to hold a significant atmospheres in the cold zones
	 * beyond the snow line (i.e. Titan).
	 * <p>
	 */
	MERCURIAN("Mercurian"),
	
	/**
	 * <b>SubTerran</b><br>
	 * <b>Mass </b> = 0.1 - 0.5</br>
	 * <b>Radius </b> = 0.5 - 1.2
	 * <p>
	 * Subterrans are able to hold a significant atmospheres after the outer edges
	 * of the habitable zone (i.e. Mars).
	 * <p>
	 */
	SUBTERRAN("Subterran"),
	
	/**
	 * <b>Terran</b><br>
	 * <b>Mass </b> = 0.5 - 2</br>
	 * <b>Radius </b> = 0.8 - 1.9
	 * <p>
	 * Terrans are able to hold a significant atmosphere with liquid water within
	 * the habitable zone (i.e. Earth)
	 * <p>
	 */
	TERRAN("Terran"),
	
	/**
	 * <b>SuperTerran</b><br>
	 * <b>Mass </b> = 2 - 10</br>
	 * <b>Radius </b> = 1.3 - 3.3
	 * <p>
	 * Superterrans are able to hold dense atmospheres with liquid water within the
	 * habitable zone.
	 * <p>
	 */
	SUPERTERRAN("Superterran"),
	
	/**
	 * <b>Neptunian</b><br>
	 * <b>Mass </b> = 10 - 50</br>
	 * <b>Radius </b> = 2.1 - 5.7
	 * <p>
	 * Neptunians can have dense atmospheres in the hot zone.
	 * <p>
	 */
	NEPTUNIAN("Neptunian"),
	
	/**
	 * <b>Jovian</b><br>
	 * <b>Mass </b> = 50 - 5000</br>
	 * <b>Radius </b> = 3.5 - 27
	 * <p>
	 * Jovians can have superdense atmospheres in the hot zone.
	 * <p>
	 */
	JOVIAN("Jovian");
	
	private String name;

	/**
	 * Instantiates a new enum planet type.
	 *
	 * @param string the string
	 */
	EnumPlanetType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
