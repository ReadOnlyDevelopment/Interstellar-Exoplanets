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

package com.readonlydev.core;

import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.*;
import static net.romvoid95.api.world.ExoBiomes.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.readonlydev.api.mod.IEventListener;
import com.readonlydev.lib.celestial.data.Mass;
import com.readonlydev.lib.celestial.data.Radius;
import com.readonlydev.lib.celestial.data.Temperature;
import com.readonlydev.lib.celestial.data.UnitType;
import com.readonlydev.lib.celestial.objects.ExoStar;
import com.readonlydev.lib.celestial.objects.ExoStarSystem;
import com.readonlydev.lib.celestial.objects.Exoplanet;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import lombok.var;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeOverworld;
import net.romvoid95.api.space.Universe;
import net.romvoid95.api.space.prefab.ExoPlanet;
import net.romvoid95.api.space.prefab.RelayStation;
import net.romvoid95.common.config.ConfigPlanets;
import net.romvoid95.common.config.ConfigSystems;
import net.romvoid95.common.config.ExoStarSystemConfig;
import net.romvoid95.common.config.ExoplanetConfig;
import net.romvoid95.space.ExoplanetProperty;
import net.romvoid95.space.Exoplanets;
import net.romvoid95.space.wolf1061.d.TeleportTypeWolf1061D;

public class ExoplanetsRegistry implements IEventListener {
	
	private static ExoplanetsRegistry _instance;
	
	public static ExoStarSystem YZ_CETI;
	public static ExoStarSystem WOLF_1061;
	public static ExoStarSystem TRAPPIST_1;
	public static ExoStarSystem KEPLER_1649;

	public static Exoplanet YZ_CETI_B;
	public static Exoplanet YZ_CETI_C;
	public static Exoplanet YZ_CETI_D;
	public static Exoplanet WOLF_1061_B;
	public static Exoplanet WOLF_1061_C;
	public static Exoplanet WOLF_1061_D;
	public static Exoplanet TRAPPIST_1_B;
	public static Exoplanet TRAPPIST_1_C;
	public static Exoplanet TRAPPIST_1_D;
	public static Exoplanet TRAPPIST_1_E;
	public static Exoplanet TRAPPIST_1_F;
	public static Exoplanet TRAPPIST_1_G;
	public static Exoplanet TRAPPIST_1_H;
	public static Exoplanet KEPLER_1649_B;
	public static Exoplanet KEPLER_1649_C;

	public static ExoplanetsRegistry instance() {
		if(_instance == null) {
			_instance = new ExoplanetsRegistry();
		}
		return _instance;
	}
	
	@Override
	public void on(EventStep eventStep) {
		if(eventStep == EventStep.PREINIT) {
			registerStarSystems();
		}
		
	}
	
	private void registerStarSystems() {
		
		if(ExoStarSystemConfig.ENABLE_KEPLER1649.get()) {
			KEPLER_1649 = ExoStarSystem.factory("kepler_1649")
					.position(ExoStarSystemConfig.POS_KEPLER1649)
					.mainStarProperties(
							new Mass(0.198, UnitType.SOLAR),
							new Radius(0.232, UnitType.SOLAR),
							new Temperature(3240), 
							"M5 V")
					.build();
		}
		
		if(ExoStarSystemConfig.ENABLE_TRAPPIST1.get()) {
			TRAPPIST_1 = ExoStarSystem.factory("trappist_1")
					.position(ExoStarSystemConfig.POS_TRAPPIST1)
					.mainStarProperties(
							new Mass(0.08, UnitType.SOLAR),
							new Radius(0.31, UnitType.SOLAR),
							new Temperature(3345), 
							"M8 V")
					.build();
		}
		
		if(ExoStarSystemConfig.ENABLE_WOLF1061.get()) {
			WOLF_1061 = ExoStarSystem.factory("wolf_1061")
					.position(ExoStarSystemConfig.POS_WOLF1061)
					.mainStarProperties(
							new Mass(0.08, UnitType.SOLAR),
							new Radius(0.31, UnitType.SOLAR),
							new Temperature(3345), 
							"M3.5")
					.build();
		}
		
		if(ExoStarSystemConfig.ENABLE_YZCETI.get()) {
			YZ_CETI = ExoStarSystem.factory("yz_ceti")
					.position(ExoStarSystemConfig.POS_YZCETI)
					.mainStarProperties(
							new Mass(0.08, UnitType.SOLAR),
							new Radius(0.31, UnitType.SOLAR),
							new Temperature(3345), 
							"M4.5")
					.build();
		}
	}

	public static void init() {

		Planets.register(YZCETIB, new TeleportTypeMoon());
		Planets.register(YZCETIC, new TeleportTypeMoon());
		Planets.register(YZCETID, new TeleportTypeMoon());
		Planets.register(TRAPPIST1B);
		Planets.register(TRAPPIST1C, new TeleportTypeMoon());
		Planets.register(TRAPPIST1D, new TeleportTypeMoon());
		Planets.register(TRAPPIST1E, new TeleportTypeMoon());
		Planets.register(TRAPPIST1F);
		Planets.register(TRAPPIST1G);
		Planets.register(TRAPPIST1H);
		Planets.register(WOLF1061B, new TeleportTypeMoon());
		Planets.register(WOLF1061C, new TeleportTypeMoon());
		Planets.register(WOLF1061D, new TeleportTypeWolf1061D());
		Planets.register(KEPLER1649B, new TeleportTypeOverworld());
		Planets.register(KEPLER1649C);
	}

	private void registerPlanets () {
		ExoplanetProperty prop;
		if(KEPLER_1649 != null) {
			prop = ExoplanetProperty.KEPLER1649B;
			KEPLER_1649_B = Exoplanet.factory("KEPLER_1649_B")
					.solarsystem(KEPLER_1649)
					.mass(prop.mass)
					.radius(prop.radius)
					.distanceFromStar(prop.au)
					.worldProvider(prop.provider)
					.dimensionId(ExoplanetConfig.KEPLER1649_B_ID.get())
					.
					.build();
			
		}
		
		if (SolarSystems.buildyzCeti) {
			if(!ConfigPlanets.disable_yz_b) {
				Universe.ExoPlanetBuilder
				.build(YZCETIB)
				.phaseShift(1.24511554657878988F)
				.tier(ConfigSystems.yzceti_tier)
				.biomes(YZCETIB_PLAINS, YZCETIB_HIGHPLAINS)
				.tempWind(4.50f, 2.0f)
				.gasses(NITROGEN, ARGON)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();				
			}
			if(!ConfigPlanets.disable_yz_c) {
				Universe.ExoPlanetBuilder
				.build(YZCETIC)
				.phaseShift(2.04511554657878988F)
				.tier(ConfigSystems.yzceti_tier)
				.biomes(YZCETIC_DUNES, YZCETIC_HIGHLANDS)
				.tempWind(8.0F, 2.0F)
				.gasses(NITROGEN, ARGON)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();				
			}
			if(!ConfigPlanets.disable_yz_d) {
				Universe.ExoPlanetBuilder
				.build(YZCETID)
				.phaseShift(0.014511554657878988F)
				.tier(ConfigSystems.yzceti_tier)
				.biomes(YZCETID_PLAINS, YZCETID_HILLS, YZCETID_CLIFFS, YZCETID_SEA)
				.tempWind(2.0F, 0.0F)
				.gasses(NITROGEN, ARGON)
				.normalOrbit(false)
				.eccentricities(1.0f, 0.0f)
				.orbitOffsets(5.0f, 0.0f)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();				
			}
		}
		if (SolarSystems.buildtrappist1) {
			if(!ConfigPlanets.disable_trap_c) {
				Universe.ExoPlanetBuilder
				.build(TRAPPIST1C)
				.phaseShift(2.24511554657878988F)
				.tier(ConfigSystems.trap_tier)
				.biomes(ACBiome.ACSpace)
				.tempWind(2.0F, 2.0F)
				.gasses(NITROGEN, ARGON)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();				
			}
			if(!ConfigPlanets.disable_trap_d) {
				Universe.ExoPlanetBuilder
				.build(TRAPPIST1D)
				.phaseShift(0.24511554657878988F)
				.tier(ConfigSystems.trap_tier)
				.biomes(ACBiome.ACSpace)
				.tempWind(2.0F, 2.0F)
				.gasses(WATER)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();			
			}
			if(!ConfigPlanets.disable_trap_e) {
				Universe.ExoPlanetBuilder
				.build(TRAPPIST1E)
				.phaseShift(0.6451158F)
				.tier(ConfigSystems.trap_tier)
				.biomes(ACBiome.ACSpace)
				.tempWind(5.0F, 0.0F)
				.gasses(NITROGEN, ARGON)
				.gravity(0.0650)
				.dayLength(24500L)
				.generate();				
			}

			if (!ConfigSystems.hideUnfinishedSystems) {
				if(!ConfigPlanets.disable_trap_b) {
					Universe.ExoPlanetBuilder
					.build(TRAPPIST1B)
					.unreachable(true)
					.phaseShift(3.254752F)
					.generate();
				}
				if(!ConfigPlanets.disable_trap_f) {
					Universe.ExoPlanetBuilder
					.build(TRAPPIST1F)
					.unreachable(true)
					.phaseShift(6.254752F)
					.generate();
				}
				if(!ConfigPlanets.disable_trap_g) {
					Universe.ExoPlanetBuilder
					.build(TRAPPIST1G)
					.unreachable(true)
					.phaseShift(1.54752F)
					.generate();
				}
				if(!ConfigPlanets.disable_trap_h) {
					Universe.ExoPlanetBuilder
					.build(TRAPPIST1H)
					.unreachable(true)
					.phaseShift(0.254752F)
					.generate();
				}
			}
		}

		if (SolarSystems.buildwolf1061) {
			if (!ConfigSystems.hideUnfinishedSystems) {
				if(!ConfigPlanets.disable_wolf_b) {
					Universe.ExoPlanetBuilder
					.build(WOLF1061B)
					.phaseShift(0.6451158F)
					.tier(ConfigSystems.wolf_tier)
					.biomes(WOLF1061B_PLAINS, WOLF1061B_SEA, WOLF1061B_HILLS, WOLF1061B_CLIFFS)
					.tempWind(2.0F, 0.0F)
					.gasses(NITROGEN, ARGON)
					.normalOrbit(false)
					.eccentricities(0.0f, 0.0f)
					.orbitOffsets(0.0f, -4.0f)
					.gravity(0.030)
					.dayLength(24500L)
					.generate();
				}
				if(!ConfigPlanets.disable_wolf_c) {
					Universe.ExoPlanetBuilder
					.build(WOLF1061C)
					.phaseShift(4.254752F)
					.tier(ConfigSystems.wolf_tier)
					.biomes(WOLF1061C_PLAINS)
					.tempWind(2.0F, 0.0F)
					.gasses(NITROGEN, ARGON)
					.normalOrbit(false)
					.eccentricities(0.0f, 0.0f)
					.orbitOffsets(0.0f, -4.5f)
					.gravity(0.030)
					.dayLength(24500L)
					.generate();
				}
				if(!ConfigPlanets.disable_wolf_d) {
					Universe.ExoPlanetBuilder
					.build(WOLF1061D)
					.phaseShift(0.6451158F)
					.tier(ConfigSystems.wolf_tier)
					.biomes(WOLF1061D_ATMOSPHERE)
					.tempWind(2.0F, 0.0F)
					.gasses(NITROGEN, ARGON, HYDROGEN, CO2)
					.normalOrbit(false)
					.eccentricities(0.9f, 1.05f)
					.orbitOffsets(0.0f, -90.0f)
					.gravity(0.030)
					.dayLength(24500L)
					.generate();
					
				}
			}
		}
		if (SolarSystems.buildkepler1649) {
			if (!ConfigSystems.hideUnfinishedSystems) {
				if(!ConfigPlanets.disable_kepler_b) {
					Universe.ExoPlanetBuilder
					.build(KEPLER1649B)
					.phaseShift(0.04511554657878988F)
					.tier(ConfigSystems.k1649_tier)
					.biomes(KEPLER1649B_BOTTOM, KEPLER1649B_CLIFF)
					.tempWind(8.0F, 2.0F)
					.gasses(NITROGEN, ARGON)
					.gravity(0.0650)
					.dayLength(32500L)
					.generate();
				}
				if(!ConfigPlanets.disable_kepler_c) {
					Universe.ExoPlanetBuilder
					.build(KEPLER1649C)
					.unreachable(true)
					.phaseShift(0.932375F)
					.generate();
				}
			}
		}
	}

	private static void register (ExoPlanet planet, ITeleportType type) {
		planets.add(planet);
		Universe.registerPlanet(planet);
		Universe.registerRocketGui(planet.getWorldProvider(), planet.getName());
		Universe.registerTeleportType(planet.getWorldProvider(), type);
	}
	
	private static void register (ExoPlanet planet) {
		planets.add(planet);
		Universe.registerPlanet(planet);
	}


}