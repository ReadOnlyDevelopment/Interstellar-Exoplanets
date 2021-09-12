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
package com.readonlydev.common.utility;

public class JavaUtility extends SecurityManager {

	public static JavaUtility INSTANCE = new JavaUtility();

	public boolean isCalledBy (Class<?>... clazz) {
		Class<?>[] context = getClassContext();

		int imax = Math.min(context.length, 6);
		for (int i = 2; i < imax; i++) {
			Class<?> test = context[i];
			for (Class<?> cl : clazz) {
				if (test == cl) {
					return true;
				}
			}
		}
		return false;
	}
}
