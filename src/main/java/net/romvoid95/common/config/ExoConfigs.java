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

package net.romvoid95.common.config;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.romvoid95.core.ExoplanetsMod;

public class ExoConfigs {

	private static String	mainFolder		= ExoplanetsMod.exoPlanetsDirectory;
	public static String	systemFolder	= ExoplanetsMod.exoPlanetsDirectory + "/Systems/";

	public static void init() {

		register(new ConfigCore(new File(mainFolder + "/Core.cfg")));
		register(new ConfigPlanets(new File(mainFolder + "/PlanetsCore.cfg")));
		register(new ConfigSystems(new File(mainFolder + "/SystemsCore.cfg")));

	}

	public static void register(Object handler) {
		MinecraftForge.EVENT_BUS.register(handler);
	}
}
