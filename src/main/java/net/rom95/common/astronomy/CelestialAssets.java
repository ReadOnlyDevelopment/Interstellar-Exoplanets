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
package net.rom95.common.astronomy;

import net.rom95.client.Assets;

public class CelestialAssets {

	private static final String YZCETI     = "yzceti";
	private static final String KELPER1649 = "kepler1649";
	private static final String TRAPPIST   = "trappist1";
	private static final String WOLF1061   = "wolf1061";

	public static void init () {
		registerNormal();
		registerRealistic();
	}

	private static void registerNormal () {

		put("yzcetistar", YZCETI);
		put("kepler1649star", KELPER1649);
		put("trappist1star", TRAPPIST);
		put("wolf1061star", WOLF1061);
		put("yzcetib", YZCETI);
		put("yzcetic", YZCETI);
		put("yzcetid", YZCETI);
		put("kepler1649b", KELPER1649);
		put("kepler1649c", KELPER1649);
		put("trappist1b", TRAPPIST);
		put("trappist1c", TRAPPIST);
		put("trappist1d", TRAPPIST);
		put("trappist1e", TRAPPIST);
		put("trappist1f", TRAPPIST);
		put("trappist1g", TRAPPIST);
		put("trappist1h", TRAPPIST);
		put("wolf1061b", WOLF1061);
		put("wolf1061c", WOLF1061);
		put("wolf1061d", WOLF1061);

	}

	private static void registerRealistic () {
		putRealistic("realyzcetistar", YZCETI);
		putRealistic("realkepler1649star", KELPER1649);
		putRealistic("realtrappist1star", TRAPPIST);
		putRealistic("realwolf1061star", WOLF1061);
		putRealistic("realyzcetib", YZCETI);
		putRealistic("realyzcetic", YZCETI);
		putRealistic("realyzcetid", YZCETI);
		putRealistic("realkepler1649b", KELPER1649);
		putRealistic("realkepler1649c", KELPER1649);
		putRealistic("realtrappist1b", TRAPPIST);
		putRealistic("realtrappist1c", TRAPPIST);
		putRealistic("realtrappist1d", TRAPPIST);
		putRealistic("realtrappist1e", TRAPPIST);
		putRealistic("realtrappist1f", TRAPPIST);
		putRealistic("realtrappist1g", TRAPPIST);
		putRealistic("realtrappist1h", TRAPPIST);
		putRealistic("realwolf1061b", WOLF1061);
		putRealistic("realwolf1061c", WOLF1061);
		putRealistic("realwolf1061d", WOLF1061);

	}

	private static void put (String name, String systemName) {
		Assets.addCelestialTexture(name, systemName);
	}

	private static void putRealistic (String name, String systemName) {
		Assets.addRealisticCelestialTexture(name, systemName);
	}
}
