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

public enum EnumAtmosphereContent implements Comparable<EnumAtmosphereContent> {

	NITROGEN("N", false),
	OXYGEN("O", false),
	CARBON_DIOXIDE("CO2", false),
	CARBON_MONOXIDE("CO", false),
	WATER("H20", false),
	METHANE("CH4", false),
	HYDROGEN("H", false),
	HELIUM("Ne", false),
	ARGON("Ar", false),
	SODIUM("N", false),
	CYANOGEN("CN2", true),
	CARBON_CYANIDE("HCN", true),
	SULFUR_DIOXIDE("S02", true);

	private String  symbol;
	private boolean corrosive;

	EnumAtmosphereContent(String symbol, boolean corrosive) {
		this.symbol    = symbol;
		this.corrosive = corrosive;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol () {
		return symbol;
	}

	/**
	 * @return the corrosive
	 */
	public boolean isCorrosive () {
		return corrosive;
	}

}
