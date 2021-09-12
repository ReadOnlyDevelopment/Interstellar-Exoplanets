/**
 * 
 */
package com.readonlydev.api.space;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class PlanetConstants {

	@UtilityClass
	public static final class AU {
		// KEPLER 1649
		public static final float KEPLER_B_AU = 0.4F;
		public static final float KEPLER_C_AU = 0.8F;

		// TRAPPIST 1
		public static final float TRAPPIST_B_AU = 0.3F;
		public static final float TRAPPIST_C_AU = 0.4F;
		public static final float TRAPPIST_D_AU = 0.6F;
		public static final float TRAPPIST_E_AU = 0.8F;
		public static final float TRAPPIST_F_AU = 1.0F;
		public static final float TRAPPIST_G_AU = 1.2F;
		public static final float TRAPPIST_H_AU = 1.6F;

		// WOLF 1061
		public static final float WOLF_B_AU = 0.3F;
		public static final float WOLF_C_AU = 0.6F;
		public static final float WOLF_D_AU = 2.5F;

		// YZ CETI
		public static final float CETI_B_AU = 0.3F;
		public static final float CETI_C_AU = 0.4F;
		public static final float CETI_D_AU = 0.590F;
	}

	@UtilityClass
	public static final class Orbit {
		// KEPLER 1649
		public static final float KEPLER_B_ORBIT = 0.4F;
		public static final float KEPLER_C_ORBIT = 0.8F;

		// TRAPPIST 1 AU
		public static final float TRAPPIST_B_ORBIT = 0.3F;
		public static final float TRAPPIST_C_ORBIT = 0.4F;
		public static final float TRAPPIST_D_ORBIT = 0.6F;
		public static final float TRAPPIST_E_ORBIT = 0.8F;
		public static final float TRAPPIST_F_ORBIT = 1.0F;
		public static final float TRAPPIST_G_ORBIT = 1.2F;
		public static final float TRAPPIST_H_ORBIT = 1.6F;

		// WOLF 1061 AU
		public static final float WOLF_B_ORBIT = 0.3F;
		public static final float WOLF_C_ORBIT = 0.6F;
		public static final float WOLF_D_ORBIT = 2.5F;

		// YZ CETI AU
		public static final float CETI_B_ORBIT = 0.3F;
		public static final float CETI_C_ORBIT = 0.4F;
		public static final float CETI_D_ORBIT = 0.590F;
	}

}
