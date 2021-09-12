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
package com.readonlydev.space;

import com.readonlydev.lib.celestial.Physics;
import com.readonlydev.lib.celestial.objects.Exoplanet;
import com.readonlydev.space.kepler1649.b.WorldProviderKepler1649B;
import com.readonlydev.space.trappist1.c.WorldProviderTrappist1C;
import com.readonlydev.space.trappist1.d.WorldProviderTrappist1D;
import com.readonlydev.space.trappist1.e.WorldProviderTrappist1E;
import com.readonlydev.space.wolf1061.b.WorldProviderWolf1061B;
import com.readonlydev.space.wolf1061.c.WorldProviderWolf1061C;
import com.readonlydev.space.wolf1061.d.WorldProviderWolf1061D;
import com.readonlydev.space.yzceti.b.WorldProviderYzCetiB;
import com.readonlydev.space.yzceti.c.WorldProviderYzCetiC;
import com.readonlydev.space.yzceti.d.WorldProviderYzCetiD;

import net.minecraft.world.WorldProvider;

public enum ExoplanetProperty {

	// @formatter:off
    //               AU   	MASS   	RADI        PROVIDERCLASS
	TRAPPIST1B		(0.3, 	0.85, 	1.086, 		null),
	TRAPPIST1C		(0.4, 	1.38, 	1.056, 		WorldProviderTrappist1C.class),
	TRAPPIST1D		(0.6, 	0.41, 	0.772, 		WorldProviderTrappist1D.class),
	TRAPPIST1E		(0.8, 	0.62, 	0.918, 		WorldProviderTrappist1E.class),
	TRAPPIST1F		(1.0, 	0.68, 	1.045, 		null),
	TRAPPIST1G		(1.2, 	1.34, 	1.127, 		null),
	TRAPPIST1H		(1.6, 	0.331, 	0.752, 		null),
	YZCETIB			(0.3, 	0.75, 	0.93, 		WorldProviderYzCetiB.class),
	YZCETIC			(0.4, 	0.98, 	1.0, 		WorldProviderYzCetiC.class),
	YZCETID			(0.590,  1.14, 	1.05, 		WorldProviderYzCetiD.class),
	WOLF1061B		(0.3, 	1.91, 	1.21, 		WorldProviderWolf1061B.class),
	WOLF1061C		(0.6, 	3.41, 	1.66, 		WorldProviderWolf1061C.class),
	WOLF1061D		(2.5, 	7.7, 	r(0.24), 	WorldProviderWolf1061D.class),
	KEPLER1649B		(0.4, 	1.03, 	1.017, 		WorldProviderKepler1649B.class),
	KEPLER1649C		(0.8, 	1.2, 	1.06, 		null);
	// @formatter:on

	public final double	au;
	public final double	mass;
	public final double	radius; 
	public final Class<? extends WorldProvider> provider;

	ExoplanetProperty (double orbitalAu, double earthMass, double earthRadius, Class<? extends WorldProvider> providerClass) {
		this.au = orbitalAu;
		this.mass = earthMass;
		this.radius = earthRadius;
		this.provider = providerClass;
	}

	private static double r(double radius) {
		return (Physics.JUPITER_RADIUS_IN_EARTHS * radius);
	}

}
