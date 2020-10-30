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
package net.romvoid95.common.utility.logic;

import net.minecraft.util.EnumFacing;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnumFaceUtil {
	/**
	 * Gets the rotation count for the facing.
	 *
	 * @param facing the facing
	 * @return the rotation count
	 */
	public static int getRotationCount (EnumFacing facing) {
		if (facing == null) {
			return 0;
		}

		switch (facing) {
			case EAST:
				return 1;
			case NORTH:
				return 2;
			case WEST:
				return 3;
			case SOUTH:
			default:
				return 0;
		}
	}

	/**
	 * Rotates facing {@code count} times.
	 *
	 * @param facing the facing
	 * @param count the count
	 * @return the enum facing
	 */
	public static EnumFacing rotateFacing (EnumFacing facing, int count) {
		if (facing == null) {
			return null;
		}

		while (count-- > 0) {
			facing = facing.rotateAround(EnumFacing.Axis.Y);
		}
		return facing;
	}

}
