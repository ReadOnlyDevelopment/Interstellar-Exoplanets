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

package net.romvoid95.core.initialization;

import micdoodle8.mods.galacticraft.api.vector.Vector3;

import asmodeuscore.api.dimension.IAdvancedSpace.*;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import net.romvoid95.api.space.Universe;
import net.romvoid95.api.space.prefab.ExoStar;
import net.romvoid95.api.space.prefab.ExoSystem;
import net.romvoid95.common.config.ConfigSystems;

public class SolarSystems {

	public static ExoStar	yzCetiStar;
	public static ExoStar	wolf1061Star;
	public static ExoStar	trappist1Star;
	public static ExoStar	kepler1649star;

	public static ExoSystem	yzCeti;
	public static ExoSystem	wolf1061;
	public static ExoSystem	trappist1;
	public static ExoSystem	kepler1649;

	public static boolean	buildyzCeti		= !ConfigSystems.disable_yzceti_system ? true : false;
	public static boolean	buildwolf1061	= !ConfigSystems.disable_yzceti_system ? true : false;
	public static boolean	buildtrappist1	= !ConfigSystems.disable_yzceti_system ? true : false;
	public static boolean	buildkepler1649	= !ConfigSystems.disable_yzceti_system ? true : false;

	public static void init() {
		preBuild();
		registerSolarSystems();
	}

	private static void preBuild() {
		yzCetiStar = Universe.buildExoStar("yzcetistar", 3058, 0.130D, 0.168D);
		yzCeti     = Universe.buildSolarSystem("YzCeti", yzCetiStar, yzPos());
		yzCeti.setHabitableZoneStart(80.0F);
		yzCeti.setHabitableZoneEnd(120.0F);

		wolf1061Star = Universe.buildExoStar("wolf1061star", 3342, 0.294D, 0.307D);
		wolf1061     = Universe.buildSolarSystem("Wolf1061", wolf1061Star, wolfPos());
		wolf1061.setHabitableZoneStart(50.0F);
		wolf1061.setHabitableZoneEnd(100.0F);
		
		trappist1Star = Universe.buildExoStar("trappist1star", 2511, 0.089D, 0.121D);
		trappist1     = Universe.buildSolarSystem("Trappist1", trappist1Star, trapPos());
		trappist1.setHabitableZoneStart(75.0F);
		trappist1.setHabitableZoneEnd(115.0F);
		
		kepler1649star = Universe.buildExoStar("kepler1649star", 2150, 0.02D, 0.230D);
		kepler1649     = Universe.buildSolarSystem("Kepler1649", kepler1649star, k1649Pos());
		kepler1649.setHabitableZoneStart(75.0F);
		kepler1649.setHabitableZoneEnd(115.0F);
	}

	private static void registerSolarSystems() {
		BodiesData data;
		if (buildyzCeti) {
			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(yzCeti.getMainStar(), data);
			Universe.registerSolarSystem(yzCeti);
		}

		if (buildwolf1061) {
			wolf1061.setMapPosition(wolfPos());
			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(wolf1061.getMainStar(), data);
			Universe.registerSolarSystem(wolf1061);
		}

		if (buildtrappist1) {
			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(trappist1.getMainStar(), data);
			Universe.registerSolarSystem(trappist1);
		}

		if (buildkepler1649) {
			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(kepler1649.getMainStar(), data);
			Universe.registerSolarSystem(kepler1649);
		}
	}

	public static Vector3 yzPos() {
		return new Vector3(ConfigSystems.yzceti_map[0], ConfigSystems.yzceti_map[1], 0.0F);
	}

	public static Vector3 wolfPos() {
		return new Vector3(ConfigSystems.wolf_map[0], ConfigSystems.wolf_map[1], 0.0F);
	}

	public static Vector3 trapPos() {
		return new Vector3(ConfigSystems.trap_map[0], ConfigSystems.trap_map[1], 0.0F);
	}

	public static Vector3 k1649Pos() {
		return new Vector3(ConfigSystems.k1649_map[0], ConfigSystems.k1649_map[1], 0.0F);
	}
}