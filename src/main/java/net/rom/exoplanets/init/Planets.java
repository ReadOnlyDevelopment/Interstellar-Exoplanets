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
import static net.rom.exoplanets.PlanetConstants.*;
import static net.rom.exoplanets.conf.SConfigDimensionID.*;
import static net.rom.exoplanets.conf.SConfigSystems.*;
import static net.rom.exoplanets.init.SolarSystems.*;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import net.minecraft.util.ResourceLocation;
import net.rom.api.space.ExoRegistry;
import net.rom.api.space.ExoStar;
import net.rom.api.space.RelayStation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.kepler1649.c.WorldProviderKepler1649c;
import net.rom.exoplanets.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.rom.exoplanets.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.rom.exoplanets.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.rom.exoplanets.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.rom.exoplanets.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.rom.exoplanets.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.rom.exoplanets.conf.SConfigSystems;
import net.rom.exoplanets.internal.AstroBuilder;
import net.rom.exoplanets.internal.Universe;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;

public class Planets {

	public static ExoPlanet yzcetib;
	public static ExoPlanet yzcetic;
	public static ExoPlanet yzcetid;

	public static Planet wolf1061b;
	public static Planet wolf1061c;
	public static Planet wolf1061d;

	public static Planet trappistb;
	public static Planet trappistc;
	public static Planet trappistd;
	//public static Satellite trappistdStation;
	public static Planet trappiste;
	public static Planet trappistf;
	public static Planet trappistg;
	public static Planet trappisth;

	public static Planet kepler1649b;
	public static Planet kepler1649c;

	public static RelayStation station;

	static AstroBuilder builder = new AstroBuilder("exoplanets");

	public static void init () {

		Planets.initPlanets();
		Planets.initStation();
		Planets.registerPlanets();
		Planets.registerTeleportTypes();

	}

	public static void initPlanets () {

		if (SolarSystems.buildyzCeti) {

			yzcetib = Universe.createPlanet("yzcetib", yzCeti, 0.5F, CETI_B, CETI_B, yzceti_tier);
			Universe.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
			Universe.setExoPlanetData(yzcetib, 50.0f, 0.75f, 0.93f);
			Universe.setNormalOrbit(yzcetib);
			Universe.setAtmosphere(yzcetib, 25.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetib, 0.50, 21500L, ClassBody.SELENA);
			Universe.setProvider(yzcetib, WorldProviderYzCetiB.class, id_yz_b);

			yzcetic = Universe.createPlanet("yzcetic", yzCeti, 0.5F, CETI_C, CETI_C + 1, yzceti_tier);
			Universe.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
			Universe.setExoPlanetData(yzcetic, 26.5f, 0.9f, 1.0f);
			Universe.setNormalOrbit(yzcetic);
			Universe.setAtmosphere(yzcetic, 25.0, 0.0, WATER, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetic, 0.65, 26500L, ClassBody.SELENA);
			Universe.setProvider(yzcetic, WorldProviderYzCetiC.class, id_yz_c);

			yzcetid = Universe.createPlanet("yzcetid", yzCeti, 0.5F, CETI_D, CETI_D + 2, yzceti_tier);
			Universe.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d);
			Universe.setExoPlanetData(yzcetid, 5.0f, 1.14f, 1.05f);
			Universe.setOrbit(yzcetid, 1.0f, 1.0f, 4.3f, 4.3f);
			Universe.setAtmosphere(yzcetid, 25.0, 0.0, NITROGEN, ARGON);
			Universe.setSurfaceData(yzcetid, 0.75, 32500L, ClassBody.SELENA);
			Universe.setProvider(yzcetid, WorldProviderYzCetiD.class, id_yz_d);

		}

		if (SolarSystems.buildtrappist1) {

			trappistc = AstroBuilder.registerExPlanet(trappist1, "trappist1c", TRAPPIST_C, ClassBody.SELENA);
			BodiesRegistry.setOrbitData(trappistc, (float) Math.PI / 4, 1.25F, 1.0F);
			BodiesRegistry.setPlanetData(trappistc, 3, 36000L, BodiesRegistry.calculateGravity(7.8F), false);
			BodiesRegistry
					.setProviderData(trappistc, WorldProviderTrappist1C.class, id_trap_c, trap_tier, ACBiome.ACSpace);
			GalaxyRegistry.registerPlanet(trappistc);

			trappistd = AstroBuilder.registerExPlanet(trappist1, "trappist1d", TRAPPIST_D, ClassBody.OCEANIDE);
			BodiesRegistry.setOrbitData(trappistd, (float) Math.PI * 2, 1.25F, 3.0F);
			BodiesRegistry.setPlanetData(trappistd, 5, 31000L, BodiesRegistry.calculateGravity(5.8F), true);
			BodiesRegistry
					.setProviderData(trappistd, WorldProviderTrappist1D.class, id_trap_d, trap_tier, ACBiome.ACSpace);
			AstroBuilder.setAtmosphere(trappistd, OXYGEN, CO2);
			trappistd.setUnreachable();
			GalaxyRegistry.registerPlanet(trappistd);

			trappiste = AstroBuilder
					.registerExPlanet(SolarSystems.trappist1, "trappist1e", TRAPPIST_E, ClassBody.TERRA);
			BodiesRegistry.setOrbitData(trappiste, (float) Math.PI / 2, 1.25F, 6.0F);
			BodiesRegistry.setPlanetData(trappiste, 3, 36000L, BodiesRegistry.calculateGravity(7.8F), false);
			BodiesRegistry
					.setProviderData(trappiste, WorldProviderTrappist1E.class, id_trap_e, trap_tier, ACBiome.ACSpace);
			AstroBuilder.setAtmosphere(trappiste, OXYGEN, CO2);
			GalaxyRegistry.registerPlanet(trappiste);

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
			kepler1649c = builder
					.buildExoPlanet(SolarSystems.kepler1649, "kepler1649c", WorldProviderKepler1649c.class, id_kepler_c, k1649_tier, 0.9F, KEPLER_C);
			builder.setData(kepler1649c, ClassBody.SELENA, KEPLER_C, 0.005f, 0.5f, 0, 26500L);
			builder.setExoData(kepler1649c, 5.0f, 1.2f, 1.06f);
			builder.setAtmos(kepler1649c, NITROGEN, ARGON);
			BodiesRegistry.setPlanetData(kepler1649c, 2, 35050, BodiesRegistry.calculateGravity(8.0F), true);
			GalaxyRegistry.registerPlanet(kepler1649c);

			if (!SConfigSystems.hideUnfinishedSystems) {

				kepler1649b = builder.buildUnreachablePlanet("kepler1649b", kepler1649, 1.932375F);
				builder.setData(kepler1649b, ClassBody.SELENA, KEPLER_B, 0.55F, KEPLER_B, 0, 24000L);
			}

		}
	}

	public static void initStation () {

		ExoStar exStar = (ExoStar) GalacticraftCore.solarSystemSol.getMainStar();

		station = new RelayStation("relaystation1");
		station.setParent(exStar);
		station.setRelativeDistanceFromCenter(20.0f);
		station.setRelativeOrbitTime(11.5F);
		station.setPhaseShift(1.65F);
		station.setRelativeSize(1.0f);
		station.setRingColorRGB(0.0F, 0.4F, 0.9F);
		station.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/space_station.png"));
		BodiesData data = new BodiesData(BodiesRegistry.registerType("orbital_station"), BodiesRegistry
				.registerClass("orbital_station"));
		BodiesRegistry.registerBodyData(station, data);
		ExoRegistry.registerRelayStation(station);

	}

	private static void registerPlanets () {

		Planets.finishRegistry(yzcetib, new TeleportTypeMoon());
		Planets.finishRegistry(yzcetic, new TeleportTypeMoon());
		Planets.finishRegistry(yzcetid, new TeleportTypeMoon());

	}

	public static void registerTeleportTypes () {

		GalacticraftRegistry.registerTeleportType(WorldProviderKepler1649c.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1E.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1E.class, getWorldGui("trappist1e"));

		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1C.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1C.class, getWorldGui("trappist1c"));
	}

	private static void finishRegistry (ExoPlanet planet, ITeleportType type) {
		Universe.registerPlanet(planet);
		Universe.registerRocketGui(planet.getProvider(), planet.getPlanetName());
		Universe.registerTeleportType(planet.getProvider(), type);
	}

	public static float getGravity (ExPlanet planet) {
		return planet.getGravity();
	}

	private static ResourceLocation getWorldGui (String planetString) {
		return new ResourceLocation(ExoInfo.MODID, "textures/gui/rocketgui/" + planetString + ".png");
	}

	public static long getDayLength (ExPlanet planet) {
		return planet.getDayLength();
	}
}