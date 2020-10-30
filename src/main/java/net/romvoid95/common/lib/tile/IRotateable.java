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
package net.romvoid95.common.lib.tile;

import net.minecraft.util.EnumFacing;

public class IRotateable {
	
	public interface IRotatableXAxis {

		/**
		 * Gets the rotation X axis.
		 *
		 * @return the rotation X axis
		 */
		public EnumFacing getRotationXAxis ();

		/**
		 * Sets the rotation X axis.
		 *
		 * @param facing the new rotation X axis
		 */
		public void setRotationXAxis (EnumFacing facing);
	}
	
	public interface IRotatableYAxis {

		/**
		 * Gets the rotation Y axis.
		 *
		 * @return the rotation Y axis
		 */
		public EnumFacing getRotationYAxis ();

		/**
		 * Sets the rotation Y axis.
		 *
		 * @param facing the new rotation Y axis
		 */
		public void setRotationYAxis (EnumFacing facing);
	}
	
	public interface IRotatableZAxis {

		/**
		 * Gets the rotation Z axis.
		 *
		 * @return the rotation Z axis
		 */
		public EnumFacing getRotationZAxis ();

		/**
		 * Sets the rotation Z axis.
		 *
		 * @param facing the new rotation Z axis
		 */
		public void setRotationZAxis (EnumFacing facing);
	}

}
