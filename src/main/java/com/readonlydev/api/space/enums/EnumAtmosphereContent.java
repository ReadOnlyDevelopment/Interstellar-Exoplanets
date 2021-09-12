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

package com.readonlydev.api.space.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumAtmosphereContent implements Comparable<EnumAtmosphereContent>, IStringSerializable {

	NITROGEN("N", false, false),
	OXYGEN("O", false, false),
	CARBON_DIOXIDE("CO2", false, false),
	CARBON_MONOXIDE("CO", false, true),
	WATER("H20", false, false),
	METHANE("CH4", false, false),
	HYDROGEN("H", false, false),
	HELIUM("Ne", false, false),
	ARGON("Ar", false, false),
	SODIUM("N", false, true),
	CYANOGEN("CN2", true, true),
	CARBON_CYANIDE("HCN", true, true),
	SULFUR_DIOXIDE("S02", true, true);

	private String  name;
	private String  symbol;
	private boolean corrosive;
	private boolean add;

	EnumAtmosphereContent(String symbol, boolean corrosive, boolean add) {
		this.name      = toString();
		this.symbol    = symbol;
		this.corrosive = corrosive;
		this.add       = add;
	}

	/**
	 * @return the elements symbol
	 */
	public String getSymbol () {
		return symbol;
	}

	/**
	 * @return if the element is Corrosive
	 */
	public boolean isCorrosive () {
		return corrosive;
	}

	public boolean add () {
		return add;
	}

	@Override
	public String getName () {
		return name;
	}

}
