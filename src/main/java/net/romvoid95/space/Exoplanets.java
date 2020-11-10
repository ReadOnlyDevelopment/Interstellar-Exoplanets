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
package net.romvoid95.space;

import static net.romvoid95.common.config.ConfigPlanets.*;
import static net.romvoid95.core.initialization.SolarSystems.*;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;
import net.minecraft.world.WorldProvider;
import net.romvoid95.api.space.prefab.ExoPlanet;
import net.romvoid95.api.space.prefab.ExoSystem;
import net.romvoid95.api.space.utility.AstronomicalConstants;
import net.romvoid95.space.kepler1649.b.WorldProviderKepler1649B;
import net.romvoid95.space.trappist1.c.WorldProviderTrappist1C;
import net.romvoid95.space.trappist1.d.WorldProviderTrappist1D;
import net.romvoid95.space.trappist1.e.WorldProviderTrappist1E;
import net.romvoid95.space.wolf1061.b.WorldProviderWolf1061B;
import net.romvoid95.space.wolf1061.c.WorldProviderWolf1061C;
import net.romvoid95.space.wolf1061.d.WorldProviderWolf1061D;
import net.romvoid95.space.yzceti.b.WorldProviderYzCetiB;
import net.romvoid95.space.yzceti.c.WorldProviderYzCetiC;
import net.romvoid95.space.yzceti.d.WorldProviderYzCetiD;

public enum Exoplanets implements IStringSerializable {

	// @formatter:off
    //                              SYSTEM    	DIM-ID   		AU   	MASS   	RADI        PROVIDERCLASS
	TRAPPIST1B		("Trappist1B", 	trappist1, 	id_trap_b, 		0.3F, 	0.85, 	1.086, 		null),
	TRAPPIST1C		("Trappist1C", 	trappist1, 	id_trap_c, 		0.4F, 	1.38, 	1.056, 		WorldProviderTrappist1C.class),
	TRAPPIST1D		("Trappist1D", 	trappist1, 	id_trap_d, 		0.6F, 	0.41, 	0.772, 		WorldProviderTrappist1D.class),
	TRAPPIST1E		("Trappist1E", 	trappist1, 	id_trap_e, 		0.8F, 	0.62, 	0.918, 		WorldProviderTrappist1E.class),
	TRAPPIST1F		("Trappist1F", 	trappist1, 	id_trap_f, 		1.0F, 	0.68, 	1.045, 		null),
	TRAPPIST1G		("Trappist1G", 	trappist1, 	id_trap_g, 		1.2F, 	1.34, 	1.127, 		null),
	TRAPPIST1H		("Trappist1H", 	trappist1, 	id_trap_h, 		1.6F, 	0.331, 	0.752, 		null),
	YZCETIB			("YzCetiB", 	yzCeti, 	id_yz_b, 		0.3F, 	0.75, 	0.93, 		WorldProviderYzCetiB.class),
	YZCETIC			("YzCetiC", 	yzCeti, 	id_yz_c, 		0.4F, 	0.98, 	1.0, 		WorldProviderYzCetiC.class),
	YZCETID			("YzCetiD", 	yzCeti, 	id_yz_d, 		0.590F, 1.14, 	1.05, 		WorldProviderYzCetiD.class),
	WOLF1061B		("Wolf1061B", 	wolf1061, 	id_wolf_b, 		0.3F, 	1.91, 	1.21, 		WorldProviderWolf1061B.class),
	WOLF1061C		("Wolf1061C", 	wolf1061, 	id_wolf_c, 		0.6F, 	3.41, 	1.66, 		WorldProviderWolf1061C.class),
	WOLF1061D		("Wolf1061D", 	wolf1061, 	id_wolf_d, 		2.5F, 	7.7, 	r(0.24), 	WorldProviderWolf1061D.class),
	KEPLER1649B		("Kepler1649B", kepler1649, id_kepler_b, 	0.4F, 	1.03, 	1.017, 		WorldProviderKepler1649B.class),
	KEPLER1649C		("Kepler1649C", kepler1649, id_kepler_c, 	0.8F, 	1.2, 	1.06, 		null);
	// @formatter:on

	private final String	name;
	private final ExoSystem	parentSystem;
	private final int		dimensionId;
	private final float		orbitalAu;
	private final double	earthMass;
	private final double	earthRadius; 
	private final Class<? extends WorldProvider> providerClass;

	Exoplanets (String name, ExoSystem parentSystem, int dimensionId, float orbitalAu, double earthMass, double earthRadius, Class<? extends WorldProvider> providerClass) {
		this.name = name;
		this.parentSystem = parentSystem;
		this.dimensionId = dimensionId;
		this.orbitalAu = orbitalAu;
		this.earthMass = earthMass;
		this.earthRadius = earthRadius;
		this.providerClass = providerClass;
	}

	public ExoPlanet get() {
		ExoPlanet exo = new ExoPlanet(this.name);
		exo.setParentSolarSystem(this.parentSystem);
		exo.setDistanceFromCenter(this.orbitalAu);
		exo.setOrbitPeriod(this.orbitalAu);
		return exo;
	}
	
	public ExoPlanet getExoPlanet() {
		ExoPlanet exo = new ExoPlanet(this.name);
		exo.setPlanetSystem(this.parentSystem);
		exo.setPlanetMass(this.earthMass);
		exo.setPlanetRadius(this.earthRadius);
		exo.setDimensionInfo(this.dimensionId, this.providerClass);
		exo.setDistanceFromCenter(this.orbitalAu);
		exo.setOrbitPeriod(this.orbitalAu);
		return exo;
	}

	@Override
	public String getName () {
		return name.toLowerCase(Locale.ROOT);
	}
	
	public Class<? extends WorldProvider> getProviderClass() {
		return providerClass;
	}

	public String getPlanetName() {
		return name;
	}

	public ExoSystem getParentSystem() {
		return parentSystem;
	}

	public float getOrbitalAu() {
		return orbitalAu;
	}

	public double getEarthMass() {
		return earthMass;
	}

	public double getEarthRadius() {
		return earthRadius;
	}

	public int getDimensionId() {
		return dimensionId;
	}
	
	public static double r(double radius) {
		return (AstronomicalConstants.JUPITER_RADIUS_IN_EARTHS * radius);
	}

}
