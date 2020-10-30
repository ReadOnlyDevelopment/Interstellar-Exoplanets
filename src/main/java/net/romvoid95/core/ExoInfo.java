/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.romvoid95.core;

import net.minecraftforge.common.ForgeVersion;

public final class ExoInfo {

	public static final String	MODID					= "exoplanets";
	public static final String	NAME					= "Interstellar: Exoplanets";
	public static final String	BUILD					= "@BUILD@";
	public static final String	VERSION					= "@VERSION@";
	public static final String	FINGERPRINT				= "@FINGERPRINT@";
	public static final String	FULL_VERSION			= VERSION + "." + BUILD;
	public static final String	ACCEPTED_MC_VERSIONS	= "[1.12.2]";
	public static final String	ACCEPTED_MC_VERSION		= ForgeVersion.mcVersion;
	public static final String	DEPENDENCIES_MODS		= "required-after:galacticraftcore;required-after:galacticraftplanets;required-after:asmodeuscore";
	public static final String	RESOURCE_PREFIX			= MODID + ":";

	public final class Constants {

		// SYSTEMS CONFIG
		public static final String CATEGORY_SYSTEMS_GENERAL      = "General";
		public static final String CATEGORY_SYSTEMS_WIDE_TIERS   = "Tier Requirements";
		public static final String CATEGORY_SYSTEMS_MAP_POSITION = "Celestial Screen Position";

		public static final String	SYSTEMS_GENERAL_COMMENT			= "General Solar System Configurations";
		public static final String	SYSTEMS_WIDE_TIERS_COMMENT		= "Change to Set the Required Tier for Each Planet In A Solar System";
		public static final String	SYSTEMS_MAP_POSITION_COMMENT	= "Change the Map Position of Exoplanets Solar Systems";

		public static final String	SYSTEMS_GENERAL_LANGKEY	= "exoplanets.configgui.category.sysgeneral";
		public static final String	SYSTEMS_TIER_LANGKEY	= "exoplanets.configgui.category.systier";
		public static final String	SYSTEMS_MAP_LANGKEY		= "exoplanets.configgui.category.sysmap";

		// CORE CONFIG
		public static final String	CATEGORY_CORE			= "Core Mod Settings";
		public static final String	LGKEY_CATEGORY_CORE	= "exoplanets.configgui.category.core";
		public static final String  CATEGORY_CORE_PERFORMANCE = "Performance Settings";
		public static final String  LGKEY_CORE_PERFORMANCE = "exoplanets.configgui.category.core.performance";

		// PLANET CONFIGS
		public static final String	CATEGORY_PLANETS_DIMENSION_ID			= "Exoplanets Dimension ID's";
		public static final String	KEY_PLANETS_DIMENSION_ID				= "Dimension ID";
		public static final String	CATEGORY_PLANETS_DIMENSION_ID_LANGKEY	= "exoplanets.configgui.category.dimensionids";
		public static final String	CATEGORY_PLANETS_DIMENSION_ID_COMMENT	= "Change the Dimension IDs of Exoplanets if Conflicts Arise";

		public static final String	CATEGORY_PLANETS				= "exoplanets";
		public static final String	KEY_PLANETS_ENABLE				= "Enabled";
		public static final String	CATEGORY_PLANETS_ENABLE_LANGKEY	= "exoplanets.configgui.category.enabled";
		public static final String	CATEGORY_PLANETS_ENABLE_COMMENT	= "Enable/Disable Individual Planets";

	}

}
