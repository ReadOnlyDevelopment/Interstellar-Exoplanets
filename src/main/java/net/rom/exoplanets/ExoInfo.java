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

package net.rom.exoplanets;

import net.minecraftforge.common.ForgeVersion;

public class ExoInfo {

	public static final String MODID = "exoplanets";
	public static final String NAME = "Interstellar: Exoplanets";
	public static final String BUILD = "@BUILD@";
	public static final String VERSION = "@VERSION@";
	public static final String FINGERPRINT = "1a0493631079de0190679303c225b8b5c0a49ae6";
	public static final String FULL_VERSION = VERSION + "." + BUILD;
	public static final String ACCEPTED_MC_VERSIONS = "[1.12.2]";
	public static final String ACCEPTED_MC_VERSION = ForgeVersion.mcVersion;
	public static final String DEPENDENCIES_MODS = "required-after:galacticraftcore@[4.0.2.261,];required-after:galacticraftplanets;after:planetprogression;required-after:asmodeuscore@[0.0.15,)";
	public static final String RESOURCE_PREFIX = MODID + ":";

}
