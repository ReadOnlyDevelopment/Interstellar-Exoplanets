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

package net.romvoid95.common.lib;

import javax.annotation.Nonnull;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum EnumMachineState implements IStringSerializable {
	NORTH_OFF(2, "north_off", false),
	SOUTH_OFF(3, "south_off", false),
	WEST_OFF(4, "west_off", false),
	EAST_OFF(5, "east_off", false),
	NORTH_ON(10, "north_on", true),
	SOUTH_ON(11, "south_on", true),
	WEST_ON(12, "west_on", true),
	EAST_ON(13, "east_on", true);

	public final int     meta;
	public final String  name;
	public final boolean isOn;

	EnumMachineState(int meta, String name, boolean isOn) {
		this.meta = meta;
		this.name = name;
		this.isOn = isOn;
	}

	@Override
	public String getName () {
		return name;
	}

	@Nonnull
	public static EnumMachineState fromEnumFacing (EnumFacing facing) {
		switch (facing) {
		case EAST:
			return EAST_OFF;
		case NORTH:
			return NORTH_OFF;
		case SOUTH:
			return SOUTH_OFF;
		case WEST:
			return WEST_OFF;
		default:
			return SOUTH_OFF;
		}
	}

	public static EnumMachineState fromMeta (int meta) {
		for (EnumMachineState state : values()) {
			if (state.meta == meta) {
				return state;
			}
		}
		return SOUTH_OFF;
	}
}
