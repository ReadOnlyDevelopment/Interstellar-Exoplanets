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

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.romvoid95.api.space.AstroBuilder;
import net.romvoid95.api.space.Universe;
import net.romvoid95.api.space.prefab.ExoStar;
import net.romvoid95.common.config.SConfigSystems;
import net.romvoid95.core.ExoInfo;

public class SolarSystems {

	public static Star    yzCetiStar;
	public static ExoStar wolf1061Star;
	public static ExoStar trappist1Star;
	public static ExoStar kepler1649star;

	public static SolarSystem yzCeti;
	public static SolarSystem wolf1061;
	public static SolarSystem trappist1;
	public static SolarSystem kepler1649;

	public static boolean buildyzCeti;
	public static boolean buildwolf1061;
	public static boolean buildtrappist1;
	public static boolean buildkepler1649;

	static AstroBuilder b = new AstroBuilder(ExoInfo.MODID);

	public static void init () {
		registerSolarSystems();
	}

	private static void registerSolarSystems () {
		BodiesData data;

		if (!SConfigSystems.disable_yzceti_system) {
			yzCetiStar = b.buildExoStar("yzcetistar", 3058, 0.130D, 0.168D);
			yzCeti     = Universe.buildSolarSystem("yzceti", "milky_way", yzPos(), "yzcetistar");
			data       = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(yzCeti.getMainStar(), data);
			b.registerSolarSystem(yzCeti);
			buildyzCeti = true;
		}
		else {
			buildyzCeti = false;
		}

		if (!SConfigSystems.disable_wolf_system) {
			wolf1061Star = b.buildExoStar("wolf1061star", 3342, 0.294D, 0.307D);
			wolf1061     = b.buildSolarSystem("wolf1061", wolfPos(), wolf1061Star);
			data         = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(wolf1061.getMainStar(), data);
			b.registerSolarSystem(wolf1061);
			buildwolf1061 = true;
		}
		else {
			buildwolf1061 = false;
		}

		if (!SConfigSystems.disable_trap_system) {
			trappist1Star = b.buildExoStar("trappist1star", 2511, 0.089D, 0.121D);
			trappist1     = b.buildSolarSystem("trappist1", trapPos(), trappist1Star);
			data          = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(trappist1.getMainStar(), data);
			b.registerSolarSystem(trappist1);
			buildtrappist1 = true;
		}
		else {
			buildtrappist1 = false;
		}

		if (!SConfigSystems.disable_k1649_system) {
			kepler1649star = b.buildExoStar("kepler1649star", 2150, 0.02D, 0.230D);
			kepler1649     = b.buildSolarSystem("kepler1649", k1649Pos(), kepler1649star);
			data           = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesRegistry.registerBodyData(kepler1649.getMainStar(), data);
			b.registerSolarSystem(kepler1649);
			buildkepler1649 = true;
		}
		else {
			buildkepler1649 = false;
		}
	}

	/*
	 * Coords ZOLERN GALAXY (Psios) "X", -2.5 "Y", 1.2 (Praedyth) "X", -1.2 "Y",
	 * 1.4 (Sol-2) unused "X", 1.2 "Y", -1.2 (Pantheon) unused "X", 1.2 "Y",
	 * -1.2 (Olympus) unused "X", 1.2 "Y", -1.2 (Asgard) unused "X", 1.2 "Y",
	 * -1.2 (Vega) unused "X", 1.2 "Y", -1.2 (Nova) unused "X", 1.2 "Y", -1.2
	 * EXTRA PLANETS (kepler22) "X", 0.8 "Y", -0.6 (kepler47) "X", -0.40 "Y",
	 * -0.8 (kepler62) "X", -0.65 "Y", 0.9 (kepler69) "X", -0.90 "Y", 0.0 MORE
	 * PLANETS (LAZENDUS) "X", 0.75 "Y", 1.25
	 */

	public static Vector3 yzPos () {
		return new Vector3(SConfigSystems.yzceti_map[0], SConfigSystems.yzceti_map[1], 0.0F);
	}

	public static Vector3 wolfPos () {
		return new Vector3(SConfigSystems.wolf_map[0], SConfigSystems.wolf_map[1], 0.0F);
	}

	public static Vector3 trapPos () {
		return new Vector3(SConfigSystems.trap_map[0], SConfigSystems.trap_map[1], 0.0F);
	}

	public static Vector3 k1649Pos () {
		return new Vector3(SConfigSystems.k1649_map[0], SConfigSystems.k1649_map[1], 0.0F);
	}

}