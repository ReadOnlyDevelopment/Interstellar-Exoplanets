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

import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.*;
import static net.romvoid95.api.space.PlanetConstants.AU.*;
import static net.romvoid95.api.space.PlanetConstants.Orbit.*;
import static net.romvoid95.common.config.SConfigDimensionID.*;
import static net.romvoid95.common.config.SConfigSystems.*;
import static net.romvoid95.core.initialization.SolarSystems.*;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeOverworld;
import net.romvoid95.api.space.ExoPlanet;
import net.romvoid95.api.space.Universe;
import net.romvoid95.api.space.objects.RelayStation;
import net.romvoid95.common.astronomy.ExoplanetBiomes;
import net.romvoid95.common.astronomy.kepler1649.c.WorldProviderKepler1649c;
import net.romvoid95.common.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.romvoid95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.romvoid95.common.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.romvoid95.common.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.romvoid95.common.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.romvoid95.common.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.romvoid95.common.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.romvoid95.common.config.SConfigSystems;

public class Planets {

	public static ExoPlanet yzcetib;
	public static ExoPlanet yzcetic;
	public static ExoPlanet yzcetid;

	public static ExoPlanet wolf1061b;
	public static ExoPlanet wolf1061c;
	public static ExoPlanet wolf1061d;

	public static ExoPlanet trappistb;
	public static ExoPlanet trappistc;
	public static ExoPlanet trappistd;
	//public static Satellite trappistdStation;
	public static ExoPlanet trappiste;
	public static ExoPlanet trappistf;
	public static ExoPlanet trappistg;
	public static ExoPlanet trappisth;

	public static ExoPlanet kepler1649b;
	public static ExoPlanet kepler1649c;

	public static RelayStation station;

	public static void init () {

		Planets.initPlanets();
		Planets.initStation();
		Planets.registerPlanets();

	}

	public static void initPlanets () {

		if (SolarSystems.buildyzCeti) {

			yzcetib = Universe.planet("yzcetib", yzCeti, 0.5F, CETI_B_AU, CETI_B_ORBIT, yzceti_tier);
			Universe.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
			Universe.setExoPlanetData(yzcetib, 50.0f, 0.75f, 0.93f);
			Universe.setNormalOrbit(yzcetib);
			Universe.setAtmosphere(yzcetib, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetib, 0.50, 21500L, ClassBody.SELENA);
			Universe.setProvider(yzcetib, WorldProviderYzCetiB.class, id_yz_b);

			yzcetic = Universe.planet("yzcetic", yzCeti, 0.5F, CETI_C_AU, CETI_C_ORBIT, yzceti_tier);
			Universe.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
			Universe.setExoPlanetData(yzcetic, 26.5f, 0.9f, 1.0f);
			Universe.setNormalOrbit(yzcetic);
			Universe.setAtmosphere(yzcetic, 5.0, 0.0, WATER, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetic, 0.65, 26500L, ClassBody.SELENA);
			Universe.setProvider(yzcetic, WorldProviderYzCetiC.class, id_yz_c);

			yzcetid = Universe.planet("yzcetid", yzCeti, 0.5F, CETI_D_AU, CETI_D_ORBIT, yzceti_tier);
			Universe.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d);
			Universe.setExoPlanetData(yzcetid, 5.0f, 1.14f, 1.05f);
			Universe.setNormalOrbit(yzcetib);
			Universe.setAtmosphere(yzcetid, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetid, 0.75, 32500L, ClassBody.SELENA);
			Universe.setProvider(yzcetid, WorldProviderYzCetiD.class, id_yz_d);

		}

		if (SolarSystems.buildtrappist1) {

			trappistc = Universe.planet("trappist1c", trappist1, 0.5F, TRAPPIST_C_AU, TRAPPIST_C_ORBIT, trap_tier);
			Universe.setBiomes(trappistc, ACBiome.ACSpace);
			Universe.setExoPlanetData(trappistc, 5.0f, 1.14f, 1.05f);
			Universe.setNormalOrbit(trappistc);
			Universe.setAtmosphere(trappistc, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(trappistc, 0.75, 32500L, ClassBody.SELENA);
			Universe.setProvider(trappistc, WorldProviderTrappist1C.class, id_trap_c);

			trappistd = Universe.planet("trappist1d", trappist1, 0.5F, TRAPPIST_D_AU, TRAPPIST_D_ORBIT, trap_tier);
			Universe.setBiomes(trappistd, ACBiome.ACSpace);
			Universe.setExoPlanetData(trappistd, 5.0f, 1.14f, 1.05f);
			Universe.setNormalOrbit(trappistd);
			Universe.setAtmosphere(trappistd, 5.0, 0.0, WATER);
			Universe.setSurfaceData(trappistd, 0.75, 32500L, ClassBody.OCEANIDE);
			Universe.setProvider(trappistd, WorldProviderTrappist1D.class, id_trap_d);

			trappiste = Universe.planet("trappist1e", trappist1, 0.5F, TRAPPIST_E_AU, TRAPPIST_E_ORBIT, trap_tier);
			Universe.setBiomes(trappiste, ACBiome.ACSpace);
			Universe.setExoPlanetData(trappiste, 5.0f, 1.14f, 1.05f);
			Universe.setNormalOrbit(trappiste);
			Universe.setAtmosphere(trappiste, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(trappiste, 0.75, 32500L, ClassBody.SELENA);
			Universe.setProvider(trappiste, WorldProviderTrappist1E.class, id_trap_e);

			if (!SConfigSystems.hideUnfinishedSystems) {

				trappistb = Universe.unreachable("trappist1b", trappist1, 3.254752F, TRAPPIST_B_AU, TRAPPIST_B_ORBIT);

				trappistf = Universe.unreachable("trappist1f", trappist1, 0.6451158F, TRAPPIST_F_AU, TRAPPIST_F_ORBIT);

				trappistg = Universe.unreachable("trappist1g", trappist1, 0.6451158F, TRAPPIST_G_AU, TRAPPIST_G_ORBIT);

				trappisth = Universe.unreachable("trappist1h", trappist1, 0.896365F, TRAPPIST_H_AU, TRAPPIST_H_ORBIT);
			}
		}

		if (SolarSystems.buildwolf1061) {

			if (!SConfigSystems.hideUnfinishedSystems) {

				wolf1061b = Universe.unreachable("wolf1061b", wolf1061, 2.9495497F, WOLF_B_AU, WOLF_B_ORBIT);

				wolf1061c = Universe.unreachable("wolf1061c", wolf1061, 1.7264397F, WOLF_C_AU, WOLF_C_ORBIT);

				wolf1061d = Universe.unreachable("wolf1061d", wolf1061, 7.132725F, WOLF_D_AU, WOLF_D_ORBIT);
			}

		}

		if (SolarSystems.buildkepler1649) {

			if (!SConfigSystems.hideUnfinishedSystems) {

				kepler1649c = Universe.planet("kepler1649c", kepler1649, 0.5F, KEPLER_C_AU, KEPLER_C_ORBIT, k1649_tier);
				Universe.setBiomes(kepler1649c, ACBiome.ACSpace);
				Universe.setExoPlanetData(kepler1649c, 5.0f, 1.14f, 1.05f);
				Universe.setNormalOrbit(kepler1649c);
				Universe.setAtmosphere(kepler1649c, 5.0, 0.0, NITROGEN, ARGON);
				Universe.setSurfaceData(kepler1649c, 0.75, 32500L, ClassBody.SELENA);
				Universe.setProvider(kepler1649c, WorldProviderKepler1649c.class, id_kepler_c);

				kepler1649b = Universe.unreachable("kepler1649b", kepler1649, 1.932375F, KEPLER_B_AU, KEPLER_B_ORBIT);
			}

		}
	}

	public static void initStation () {

		//		station = new RelayStation("relaystation1", GalacticraftCore.solarSystemSol
		//				.getMainStar(), GalacticraftCore.solarSystemSol);
		//		station.setRelativeDistanceFromCenter(20.0f);
		//		station.setRelativeOrbitTime(11.5F);
		//		station.setPhaseShift(1.65F);
		//		station.setRelativeSize(1.0f);
		//		station.setRingColorRGB(0.0F, 0.4F, 0.9F);
		//		station.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/space_station.png"));
		//		SpaceData.registerCelestialBody(station);
		//		ExoSpaceRegistry.registerRelayStation(station);

	}

	private static void registerPlanets () {

		Planets.finishRegistry(yzcetib, new TeleportTypeMoon());
		Planets.finishRegistry(yzcetic, new TeleportTypeMoon());
		Planets.finishRegistry(yzcetid, new TeleportTypeMoon());
		Planets.finishRegistry(trappistc, new TeleportTypeMoon());
		Planets.finishRegistry(trappistd, new TeleportTypeMoon());
		Planets.finishRegistry(trappiste, new TeleportTypeMoon());
		Planets.finishRegistry(kepler1649c, new TeleportTypeOverworld());

	}

	private static void finishRegistry (ExoPlanet planet, ITeleportType type) {
		Universe.registerPlanet(planet);
		Universe.registerRocketGui(planet.getProvider(), planet.getPlanetName());
		Universe.registerTeleportType(planet.getProvider(), type);
	}

	public static float getGravity (ExPlanet planet) {
		return planet.getGravity();
	}

	public static long getDayLength (ExPlanet planet) {
		return planet.getDayLength();
	}
}