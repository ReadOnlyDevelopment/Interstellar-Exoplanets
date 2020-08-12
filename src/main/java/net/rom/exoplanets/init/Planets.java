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

import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.ARGON;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.CO2;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.NITROGEN;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.OXYGEN;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.WATER;
import static net.rom.exoplanets.PlanetConstants.CETI_B;
import static net.rom.exoplanets.PlanetConstants.CETI_C;
import static net.rom.exoplanets.PlanetConstants.CETI_D;
import static net.rom.exoplanets.PlanetConstants.KEPLER_B;
import static net.rom.exoplanets.PlanetConstants.KEPLER_C;
import static net.rom.exoplanets.PlanetConstants.TRAPPIST_B;
import static net.rom.exoplanets.PlanetConstants.TRAPPIST_C;
import static net.rom.exoplanets.PlanetConstants.TRAPPIST_D;
import static net.rom.exoplanets.PlanetConstants.TRAPPIST_E;
import static net.rom.exoplanets.PlanetConstants.TRAPPIST_F;
import static net.rom.exoplanets.PlanetConstants.TRAPPIST_G;
import static net.rom.exoplanets.PlanetConstants.TRAPPIST_H;
import static net.rom.exoplanets.PlanetConstants.WOLF_B;
import static net.rom.exoplanets.PlanetConstants.WOLF_C;
import static net.rom.exoplanets.PlanetConstants.WOLF_D;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_kepler_c;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_trap_c;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_trap_d;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_trap_e;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_b;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_c;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_d;
import static net.rom.exoplanets.conf.SConfigSystems.k1649_tier;
import static net.rom.exoplanets.conf.SConfigSystems.trap_tier;
import static net.rom.exoplanets.conf.SConfigSystems.yzceti_tier;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeOrbit;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
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

public class Planets {

	public static Planet yzcetib;
	public static Planet yzcetic;
	public static Planet yzcetid;

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
		Planets.registerTeleportTypes();

	}

	public static void initPlanets () {

		if (SolarSystems.buildyzCeti) {

			yzcetib = builder
					.buildExoPlanet(SolarSystems.yzCeti, "yzcetib", WorldProviderYzCetiB.class, id_yz_b, yzceti_tier, 0.5F, CETI_B);
			builder.setData(yzcetib, ClassBody.SELENA, CETI_B, 0.015f, 0.4f, 0, 0L);
			builder.setNormalOrbit(yzcetib);
			builder.setExoData(yzcetib, 50.0f, 0.75f, 0.93f);
			builder.setAtmos(yzcetib, NITROGEN, ARGON);
			builder.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
			AstroBuilder.registerExPlanet(SolarSystems.yzCeti, "yzcetib", CETI_B, ClassBody.SELENA);
			GalaxyRegistry.registerPlanet(yzcetib);

			yzcetic = builder
					.buildExoPlanet(SolarSystems.yzCeti, "yzcetic", WorldProviderYzCetiC.class, id_yz_c, yzceti_tier, 0.1F, CETI_C);
			builder.setData(yzcetic, ClassBody.SELENA, CETI_C, 0.010f, 0.5f, 0, 19500L);
			builder.setNormalOrbit(yzcetic);
			builder.setExoData(yzcetic, 26.5f, 0.9f, 1.0f);
			builder.setAtmos(yzcetic, WATER, NITROGEN, ARGON);
			builder.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
			AstroBuilder.registerExPlanet(SolarSystems.yzCeti, "yzcetic", CETI_C, ClassBody.SELENA);
			GalaxyRegistry.registerPlanet(yzcetic);

			yzcetid = builder
					.buildExoPlanet(SolarSystems.yzCeti, "yzcetid", WorldProviderYzCetiD.class, id_yz_d, yzceti_tier, 0.9F, CETI_D);
			builder.setData(yzcetid, ClassBody.SELENA, CETI_D, 0.005f, 0.6f, 0, 26500L);
			builder.setOrbit(yzcetid, 1.0f, 1.0f, 4.3f, 4.3f);
			builder.setExoData(yzcetid, 5.0f, 1.14f, 1.05f);
			builder.setAtmos(yzcetid, NITROGEN, ARGON);
			builder.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d);
			AstroBuilder.registerExPlanet(SolarSystems.yzCeti, "yzcetid", CETI_D, ClassBody.SELENA);
			GalaxyRegistry.registerPlanet(yzcetid);
		}

		if (SolarSystems.buildtrappist1) {

			trappistc = AstroBuilder
					.registerExPlanet(SolarSystems.trappist1, "trappist1c", TRAPPIST_C, ClassBody.SELENA);
			BodiesRegistry.setOrbitData(trappistc, (float) Math.PI / 4, 1.25F, 1.0F);
			BodiesRegistry.setPlanetData(trappistc, 3, 36000L, BodiesRegistry.calculateGravity(7.8F), false);
			BodiesRegistry
					.setProviderData(trappistc, WorldProviderTrappist1C.class, id_trap_c, trap_tier, ACBiome.ACSpace);
			GalaxyRegistry.registerPlanet(trappistc);

			trappistd = AstroBuilder
					.registerExPlanet(SolarSystems.trappist1, "trappist1d", TRAPPIST_D, ClassBody.OCEANIDE);
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

				trappistb = builder.buildUnreachablePlanet("trappist1b", SolarSystems.trappist1, 3.254752F);
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

				wolf1061b = builder.buildUnreachablePlanet("wolf1061b", SolarSystems.wolf1061, 2.9495497F);
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

				kepler1649b = builder.buildUnreachablePlanet("kepler1649b", SolarSystems.kepler1649, 1.932375F);
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
		//		final HashMap<Object, Integer> spaceStationRequirements = new HashMap<Object, Integer>(5, 1.0F);
		//		spaceStationRequirements.put("sheetTitanium", 64);
		//		spaceStationRequirements.put("sheetSteel", 64);
		//		spaceStationRequirements.put(new ItemStack(GCItems.basicItem, 1, ItemBasic.WAFER_ADVANCED), 1);
		//		spaceStationRequirements.put(Items.IRON_INGOT, 24);
		//		spaceStationRequirements.put(new ItemStack(ExoBlocks.stand_console, 1), 10);
		//
		//		trappistdStation                              = BodiesRegistry
		//				.registerSatellite(trappistd, ExoInfo.MODID, OrbitalStationProvider.class, -4513, -4514, 2.0F, 0.2667F, 5.5F, 20.0F);
		//		TrappistDimensions.TRAPPIST_1D_STATION        = GalacticraftRegistry
		//				.registerDimension("Trappist Orbital Station", "_trappist_orbit", -4513, OrbitalStationProvider.class, false);
		//		TrappistDimensions.TRAPPIST_1D_STATION_STATIC = GalacticraftRegistry
		//				.registerDimension("Trappist Orbital Station", "_trappist_orbit", -4514, OrbitalStationProvider.class, true);
		//		GalaxyRegistry.registerSatellite(trappistdStation);
		//		GalacticraftRegistry
		//				.registerSpaceStation(new SpaceStationType(-4514, id_trap_d, new SpaceStationRecipe(spaceStationRequirements)));
	}

	public static void registerTeleportTypes () {

		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiB.class, new TeleportTypeOrbit());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiC.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiD.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerTeleportType(WorldProviderKepler1649c.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiB.class, getWorldGui("yzcetib"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiC.class, getWorldGui("yzcetic"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiD.class, getWorldGui("yzcetid"));
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1E.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1E.class, getWorldGui("trappist1e"));
		//		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1D.class, new TeleportTypeOrbit());
		//		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1D.class, getWorldGui("trappist1d"));
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1C.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1C.class, getWorldGui("trappist1c"));
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