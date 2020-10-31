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

package net.rom.exoplanets.init;

import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import net.rom.api.space.ExoPlanet;
import net.rom.api.space.RelayStation;
import net.rom.api.space.Universe;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.rom.exoplanets.conf.ConfigPlanets;
import net.rom.exoplanets.conf.ConfigSystems;
import net.rom.exoplanets.internal.Exoplanets;

public class Planets {

	public static List<ExoPlanet> planets = new ArrayList<>();

	public static ExoPlanet YZCETIB;
	public static ExoPlanet YZCETIC;
	public static ExoPlanet YZCETID;
	public static ExoPlanet WOLF1061B;
	public static ExoPlanet WOLF1061C;
	public static ExoPlanet WOLF1061D;
	public static ExoPlanet TRAPPIST1B;
	public static ExoPlanet TRAPPIST1C;
	public static ExoPlanet TRAPPIST1D;
	public static ExoPlanet TRAPPIST1E;
	public static ExoPlanet TRAPPIST1F;
	public static ExoPlanet TRAPPIST1G;
	public static ExoPlanet TRAPPIST1H;
	public static ExoPlanet KEPLER1649B;
	public static ExoPlanet KEPLER1649C;
	public static RelayStation station;

	public static void init() {

		
		YZCETIB = Exoplanets.YZCETIB.getExoPlanet();
		YZCETIC = Exoplanets.YZCETIC.getExoPlanet();
		YZCETID = Exoplanets.YZCETID.getExoPlanet();
		TRAPPIST1B = Exoplanets.TRAPPIST1B.get();
		TRAPPIST1C = Exoplanets.TRAPPIST1C.getExoPlanet();
		TRAPPIST1D = Exoplanets.TRAPPIST1D.get();
		TRAPPIST1E = Exoplanets.TRAPPIST1E.getExoPlanet();
		TRAPPIST1F = Exoplanets.TRAPPIST1F.get();
		TRAPPIST1G = Exoplanets.TRAPPIST1G.get();
		TRAPPIST1H = Exoplanets.TRAPPIST1H.get();
		WOLF1061D = Exoplanets.WOLF1061D.get();
		WOLF1061C = Exoplanets.WOLF1061C.get();
		WOLF1061B = Exoplanets.WOLF1061B.get();
		KEPLER1649B = Exoplanets.KEPLER1649B.get();
		KEPLER1649C = Exoplanets.KEPLER1649C.get();
		
		Planets.initPlanets();
		
		Planets.register(YZCETIB, new TeleportTypeMoon());
		Planets.register(YZCETIC, new TeleportTypeMoon());
		Planets.register(YZCETID, new TeleportTypeMoon());
		Planets.register(TRAPPIST1B);
		Planets.register(TRAPPIST1C, new TeleportTypeMoon());
		Planets.register(TRAPPIST1D);
		Planets.register(TRAPPIST1E, new TeleportTypeMoon());
		Planets.register(TRAPPIST1F);
		Planets.register(TRAPPIST1G);
		Planets.register(TRAPPIST1H);
		Planets.register(WOLF1061B);
		Planets.register(WOLF1061C);
		Planets.register(WOLF1061D);
		Planets.register(KEPLER1649B);
		Planets.register(KEPLER1649C);
		
		createDebugFile();
	}

	private static void initPlanets () {
		if (SolarSystems.buildyzCeti) {
			if(!ConfigPlanets.disable_yz_b) {
				Universe.ExoPlanetBuilder
				.build(YZCETIB)
				.phaseShift(1.24511554657878988F)
				.tier(ConfigSystems.yzceti_tier)
				.biomes(ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY)
				.tempWind(4.50f, 2.0f)
				.gasses(NITROGEN, ARGON)
				.clazz(ClassBody.SELENA)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();				
			}
			if(!ConfigPlanets.disable_yz_c) {
				Universe.ExoPlanetBuilder
				.build(YZCETIC)
				.phaseShift(2.04511554657878988F)
				.tier(ConfigSystems.yzceti_tier)
				.biomes(ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON)
				.tempWind(8.0F, 2.0F)
				.gasses(NITROGEN, ARGON)
				.clazz(ClassBody.SELENA)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();				
			}
			if(!ConfigPlanets.disable_yz_d) {
				Universe.ExoPlanetBuilder
				.build(YZCETID)
				.phaseShift(0.014511554657878988F)
				.tier(ConfigSystems.yzceti_tier)
				.biomes(YzCetiDBiomes.yz_ceti_d)
				.tempWind(2.0F, 0.0F)
				.gasses(NITROGEN, ARGON)
				.clazz(ClassBody.SELENA)
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
				.clazz(ClassBody.SELENA)
				.gravity(0.0650)
				.dayLength(32500L)
				.generate();				
			}
			if(!ConfigPlanets.disable_trap_d) {
				Universe.ExoPlanetBuilder
				.build(TRAPPIST1D)
				.unreachable(true)
				.phaseShift(3.254752F)
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
				.clazz(ClassBody.SELENA)
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
					.unreachable(true)
					.phaseShift(0.254752F)
					.generate();
				}
				if(!ConfigPlanets.disable_wolf_c) {
					Universe.ExoPlanetBuilder
					.build(WOLF1061C)
					.unreachable(true)
					.phaseShift(0.254752F)
					.generate();
				}
				if(!ConfigPlanets.disable_wolf_d) {
					Universe.ExoPlanetBuilder
					.build(WOLF1061D)
					.unreachable(true)
					.phaseShift(0.254752F)
					.generate();
					
				}
			}
		}
		if (SolarSystems.buildkepler1649) {
			if (!ConfigSystems.hideUnfinishedSystems) {
				if(!ConfigPlanets.disable_kepler_b) {
					Universe.ExoPlanetBuilder
					.build(KEPLER1649B)
					.unreachable(true)
					.phaseShift(1.932375F)
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
	
	private static boolean createDebugFile() {
		File debugFile = new File("planets.txt");
		if (debugFile.exists()) {
			debugFile.delete();
		}
		try {
			debugFile.createNewFile();
			PrintWriter print_line = new PrintWriter(new FileWriter(debugFile));
			for(ExoPlanet p : planets) {
				if(p.equals(WOLF1061D)) {
					print_line.println(p.toString());
				}
			}
            print_line.flush();
            print_line.close();
            return true;
		} catch (IOException localIOException) {
            return false;
        }
	}
}