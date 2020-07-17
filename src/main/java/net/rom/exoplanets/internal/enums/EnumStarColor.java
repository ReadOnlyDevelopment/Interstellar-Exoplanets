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

import micdoodle8.mods.galacticraft.api.vector.Vector3;

public enum EnumStarColor {

	BRIGHT_BLUE(194, 254, 252), WHITE(234, 240, 240), DARK_WHTIE(249, 252, 200), YELLOW(244, 254, 40),
	ORANGE(255, 155, 0), RED(255, 42, 27);

	private Vector3 color;

	EnumStarColor(int r, int g, int b) {
		this.color = new Vector3(r, g, b);
	}

	public Vector3 getColor() {
		return color;
	}
}
