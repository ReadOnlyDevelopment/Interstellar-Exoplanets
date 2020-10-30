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

import java.io.IOException;


/**
 * Signals that the requested operation does not support the
 * requested data type.
 *
 */
public class UnsupportedDataTypeException extends IOException {

	private static final long serialVersionUID = 7557557952618258262L;

	/**
     * Constructs an UnsupportedDataTypeException with no detail
     * message.
     */
    public UnsupportedDataTypeException() {
	super();
    }

    /**
     * Constructs an UnsupportedDataTypeException with the specified 
     * message.
     * 
     * @param s The detail message.
     */
    public UnsupportedDataTypeException(String s) {
	super(s);
    }
}
