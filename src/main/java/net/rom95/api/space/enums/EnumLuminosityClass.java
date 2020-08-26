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

package net.rom95.api.space.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumLuminosityClass implements IStringSerializable {

	EXT_LUMINOUS_SUPERGIANTS("Ia-O"),
	LUMINOUS_SUPERGIANTS("Ia"),
	LESS_LUMINOUS_SUPERGIANTS("Ib"),
	BRIGHT_GIANTS("II"),
	GIANTS("III"),
	SUB_GIANTS("IV"),
	MAIN_SEQUENCE("V"),
	SUB_DWARF("VI"),
	SUPERGIANTS_I("D");

	private String clazz;
	
	EnumLuminosityClass(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getName() {
		return this.clazz;
	}


}
