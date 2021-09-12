/**
 * Exoplanets
 * Copyright (C) 2020
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
package com.readonlydev.api;

import java.nio.file.Path;
import java.util.HashMap;

public class ExoplanetsAPI {

	private static final ExoplanetsAPI INSTANCE = new ExoplanetsAPI();

	public static ExoplanetsAPI apiInstance() {
		return INSTANCE;
	}

	/**
	 * This map stores a list of OreDict prefixes (ingot, sheet, gear, nugget) and their ingot relation (ingot:component) <br>
	 * Examples:<br>
	 * "sheet"-{1,1},<br>
	 * "nugget"-{1,9},<br>
	 * "block"-{9,1},<br>
	 * "gear"-{4,1}
	 */
	public static HashMap<String, Integer[]> prefixToIngotMap = new HashMap<>();

	private static Path configPath;

	public static Path getConfigPath() {
		return configPath;
	}

	public static void setConfigPath(Path path) {
		if (configPath == null) {
			configPath = path;
		}
	}

}
