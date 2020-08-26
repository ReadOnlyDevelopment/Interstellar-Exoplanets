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

package net.rom95.core.initialization;

import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.*;
import static net.rom95.api.space.PlanetConstants.*;
import static net.rom95.common.config.SConfigDimensionID.*;
import static net.rom95.common.config.SConfigSystems.*;
import static net.rom95.core.initialization.SolarSystems.*;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import net.rom95.api.space.AstroBuilder;
import net.rom95.api.space.ExoPlanet;
import net.rom95.api.space.Universe;
import net.rom95.api.space.objects.RelayStation;
import net.rom95.common.astronomy.ExoplanetBiomes;
import net.rom95.common.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.rom95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.rom95.common.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.rom95.common.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.rom95.common.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.rom95.common.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.rom95.common.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.rom95.common.config.SConfigSystems;

public class Planets {

	public static ExoPlanet yzcetib;
	public static ExoPlanet yzcetic;
	public static ExoPlanet yzcetid;

	public static Planet wolf1061b;
	public static Planet wolf1061c;
	public static Planet wolf1061d;

	public static Planet    trappistb;
	public static ExoPlanet trappistc;
	public static ExoPlanet trappistd;
	//public static Satellite trappistdStation;
	public static ExoPlanet trappiste;
	public static Planet    trappistf;
	public static Planet    trappistg;
	public static Planet    trappisth;

	public static Planet kepler1649b;
	public static Planet kepler1649c;

	public static RelayStation station;

	static AstroBuilder builder = new AstroBuilder("exoplanets");

	public static void init () {

		Planets.initPlanets();
		Planets.initStation();
		Planets.registerPlanets();

	}

	public static void initPlanets () {

		if (SolarSystems.buildyzCeti) {

			yzcetib = Universe.createPlanet("yzcetib", yzCeti, 0.5F, CETI_B, CETI_B, yzceti_tier);
			Universe.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
			Universe.setExoPlanetData(yzcetib, 50.0f, 0.75f, 0.93f);
			Universe.setNormalOrbit(yzcetib);
			Universe.setAtmosphere(yzcetib, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetib, 0.50, 21500L, ClassBody.SELENA);
			Universe.setProvider(yzcetib, WorldProviderYzCetiB.class, id_yz_b);

			yzcetic = Universe.createPlanet("yzcetic", yzCeti, 0.5F, CETI_C, CETI_C + 1, yzceti_tier);
			Universe.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
			Universe.setExoPlanetData(yzcetic, 26.5f, 0.9f, 1.0f);
			Universe.setNormalOrbit(yzcetic);
			Universe.setAtmosphere(yzcetic, 5.0, 0.0, WATER, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetic, 0.65, 26500L, ClassBody.SELENA);
			Universe.setProvider(yzcetic, WorldProviderYzCetiC.class, id_yz_c);

			yzcetid = Universe.createPlanet("yzcetid", yzCeti, 0.5F, CETI_D, CETI_D + 2, yzceti_tier);
			Universe.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d);
			Universe.setExoPlanetData(yzcetid, 5.0f, 1.14f, 1.05f);
			Universe.setOrbit(yzcetid, 1.0f, 1.0f, 4.3f, 4.3f);
			Universe.setAtmosphere(yzcetid, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetid, 0.75, 32500L, ClassBody.SELENA);
			Universe.setProvider(yzcetid, WorldProviderYzCetiD.class, id_yz_d);

		}

		if (SolarSystems.buildtrappist1) {

			trappistc = Universe.createPlanet("trappist1c", trappist1, 0.5F, TRAPPIST_C, TRAPPIST_C + 1, trap_tier);
			Universe.setBiomes(trappistc, ACBiome.ACSpace);
			Universe.setExoPlanetData(trappistc, 5.0f, 1.14f, 1.05f);
			Universe.setOrbit(trappistc, 1.0f, 1.0f, 4.3f, 4.3f);
			Universe.setAtmosphere(trappistc, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(trappistc, 0.75, 32500L, ClassBody.SELENA);
			Universe.setProvider(trappistc, WorldProviderTrappist1C.class, id_trap_c);

			trappistd = Universe
					.createPlanet("trappist1d", trappist1, 0.5F, TRAPPIST_D, (float) (TRAPPIST_D + 1.5), trap_tier);
			Universe.setBiomes(trappistd, ACBiome.ACSpace);
			Universe.setExoPlanetData(trappistd, 5.0f, 1.14f, 1.05f);
			Universe.setOrbit(trappistd, 1.0f, 1.0f, 4.3f, 4.3f);
			Universe.setAtmosphere(trappistd, 5.0, 0.0, WATER);
			Universe.setSurfaceData(trappistd, 0.75, 32500L, ClassBody.OCEANIDE);
			Universe.setProvider(trappistd, WorldProviderTrappist1D.class, id_trap_d);

			trappiste = Universe.createPlanet("trappist1e", trappist1, 0.5F, TRAPPIST_E, TRAPPIST_E + 2, trap_tier);
			Universe.setBiomes(trappiste, ACBiome.ACSpace);
			Universe.setExoPlanetData(trappiste, 5.0f, 1.14f, 1.05f);
			Universe.setOrbit(trappiste, 1.0f, 1.0f, 4.3f, 4.3f);
			Universe.setAtmosphere(trappiste, 5.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(trappiste, 0.75, 32500L, ClassBody.SELENA);
			Universe.setProvider(trappiste, WorldProviderTrappist1E.class, id_trap_e);

			if (!SConfigSystems.hideUnfinishedSystems) {

				trappistb = builder.buildUnreachablePlanet("trappist1b", trappist1, 3.254752F);
				builder.setData(trappistb, ClassBody.SELENA, TRAPPIST_B, 0.45F, TRAPPIST_B, 0, 24000L);

				trappistf = builder.buildUnreachablePlanet("trappist1f", SolarSystems.trappist1, 0.6451158F);
				builder.setData(trappistf, ClassBody.SELENA, TRAPPIST_F, 0.45F, TRAPPIST_F, 0, 24000L);

				trappistg = builder.buildUnreachablePlanet("trappist1g", SolarSystems.trappist1, 0.6451158F);
				builder.setData(trappistg, ClassBody.SELENA, TRAPPIST_G, 0.45F, TRAPPIST_G, 0, 24000L);

				trappisth = builder.buildUnreachablePlanet("trappist1h", SolarSystems.trappist1, 0.896365F);
				builder.setData(trappisth, ClassBody.SELENA, TRAPPIST_H, 0.45F, TRAPPIST_H, 0, 24000L);
			}
		}

		if (SolarSystems.buildwolf1061) {

			if (!SConfigSystems.hideUnfinishedSystems) {

				wolf1061b = builder.buildUnreachablePlanet("wolf1061b", wolf1061, 2.9495497F);
				builder.setData(wolf1061b, ClassBody.SELENA, WOLF_B, 0.45F, WOLF_B, 0, 24000L);

				wolf1061c = builder.buildUnreachablePlanet("wolf1061c", SolarSystems.wolf1061, 1.7264397F);
				builder.setData(wolf1061c, ClassBody.SELENA, WOLF_C, 0.45F, WOLF_C, 0, 24000L);

				wolf1061d = builder.buildUnreachablePlanet("wolf1061d", SolarSystems.wolf1061, 7.132725F);
				builder.setData(wolf1061d, ClassBody.SELENA, WOLF_D, 0.45F, WOLF_D, 0, 24000L);
			}

		}

		if (SolarSystems.buildkepler1649) {

			if (!SConfigSystems.hideUnfinishedSystems) {

				kepler1649c = builder.buildUnreachablePlanet("kepler1649c", kepler1649, 1.932375F);
				builder.setData(kepler1649c, ClassBody.SELENA, KEPLER_C, 0.55F, KEPLER_C, 0, 24000L);

				kepler1649b = builder.buildUnreachablePlanet("kepler1649b", kepler1649, 1.932375F);
				builder.setData(kepler1649b, ClassBody.SELENA, KEPLER_B, 0.55F, KEPLER_B, 0, 24000L);
			}

		}
	}

	public static void initStation () {

	}

	private static void registerPlanets () {

		Planets.finishRegistry(yzcetib, new TeleportTypeMoon());
		Planets.finishRegistry(yzcetic, new TeleportTypeMoon());
		Planets.finishRegistry(yzcetid, new TeleportTypeMoon());
		Planets.finishRegistry(trappistc, new TeleportTypeMoon());
		Planets.finishRegistry(trappistd, new TeleportTypeMoon());
		Planets.finishRegistry(trappiste, new TeleportTypeMoon());

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