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

import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_b;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_c;
import static net.rom.exoplanets.conf.SConfigDimensionID.*;
import static net.rom.exoplanets.conf.SConfigSystems.*;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeOverworld;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.kepler1649.c.WorldProviderKepler1649c;
import net.rom.exoplanets.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.rom.exoplanets.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.rom.exoplanets.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.rom.exoplanets.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.rom.exoplanets.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.rom.exoplanets.conf.SConfigDimensionID;
import net.rom.exoplanets.conf.SConfigSystems;
import net.rom.exoplanets.internal.AstroBuilder;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;

public class InitPlanets {

	public static Float[]	yzCetiAu	= { 0.3F, 0.4f, 0.590f };
	public static ExoPlanet	yzcetib;
	public static ExoPlanet	yzcetic;
	public static ExoPlanet	yzcetid;

	private static Float[]	wolfAu	= { 0.2F, 0.4f, 2.5f };
	public static ExoPlanet	wolf1061b;
	public static ExoPlanet	wolf1061c;
	public static ExoPlanet	wolf1061d;

	private static Float[]	trappistAu	= { 0.3F, 0.4f, 0.6f, 0.8f, 1.0f, 1.2f, 1.6f };
	public static ExoPlanet	trappistb;
	public static ExoPlanet	trappistc;
	public static ExoPlanet	trappistd;
	public static ExoPlanet	trappiste;
	public static ExoPlanet	trappistf;
	public static ExoPlanet	trappistg;
	public static ExoPlanet	trappisth;

	public static float[]	kepler1649Au	= { 0.4f, 0.7f };
	public static ExoPlanet	kepler1649b;
	public static ExoPlanet	kepler1649c;

	static AstroBuilder builder = new AstroBuilder("exoplanets");

	public static void init() {

		InitPlanets.initPlanets();
		InitPlanets.registerTeleportTypes();

	}

	public static void initPlanets() {

		if (IniSystems.buildyzCeti) {

			yzcetib = builder.buildExoPlanet(IniSystems.yzCeti, "yzcetib", WorldProviderYzCetiB.class, id_yz_b, yzceti_tier, 0.5F);
			builder.setData(yzcetib, ClassBody.SELENA, yzCetiAu[0], 0.015f, 0.4f, 0, 0L);
			builder.setNormalOrbit(yzcetib);
			builder.setExoData(yzcetib, 50.0f, 0.75f, 0.93f);
			builder.setAtmos(yzcetib, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			builder.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
			GalaxyRegistry.registerPlanet(yzcetib);

			yzcetic = builder.buildExoPlanet(IniSystems.yzCeti, "yzcetic", WorldProviderYzCetiC.class, id_yz_c, yzceti_tier, 0.1F);
			builder.setData(yzcetic, ClassBody.SELENA, yzCetiAu[1], 0.010f, 0.5f, 0, 19500L);
			builder.setNormalOrbit(yzcetic);
			builder.setExoData(yzcetic, 26.5f, 0.9f, 1.0f);
			builder.setAtmos(yzcetic, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			builder.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
			GalaxyRegistry.registerPlanet(yzcetic);

			yzcetid = builder.buildExoPlanet(IniSystems.yzCeti, "yzcetid", WorldProviderYzCetiD.class, id_yz_d, yzceti_tier, 0.9F);
			builder.setData(yzcetid, ClassBody.SELENA, yzCetiAu[2], 0.005f, 0.6f, 0, 26500L);
			builder.setOrbit(yzcetid, 1.0f, 1.0f, 4.3f, 4.3f);
			builder.setExoData(yzcetid, 5.0f, 1.14f, 1.05f);
			builder.setAtmos(yzcetid, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			builder.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d);
			GalaxyRegistry.registerPlanet(yzcetid);
		}

		if (IniSystems.buildtrappist1) {

			trappistc = builder.buildExoPlanet(IniSystems.trappist1, "trappist1c", WorldProviderTrappist1C.class, SConfigDimensionID.id_trap_c, SConfigSystems.trap_tier, (float) Math.PI);
			builder.setData(trappistc, ClassBody.SELENA, trappistAu[1], 0.005f, 0.6f, 0, 26500L);
			builder.setExoData(trappistc, 8.0f, 1.38f, 1.056f);
			builder.setAtmos(trappistc, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			GalaxyRegistry.registerPlanet(trappistc);

			trappiste = builder.buildExoPlanet(IniSystems.trappist1, "trappist1e", WorldProviderTrappist1E.class, SConfigDimensionID.id_trap_e, SConfigSystems.trap_tier, (float) Math.PI);
			builder.setData(trappiste, ClassBody.SELENA, trappistAu[3], 0.005f, 0.6f, 0, 26500L);
			builder.setExoData(trappiste, 8.0f, 1.38f, 1.056f);
			builder.setAtmos(trappiste, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.OXYGEN);
			GalaxyRegistry.registerPlanet(trappiste);

			if (!SConfigSystems.hideUnfinishedSystems) {

				trappistb = builder.buildUnreachablePlanet("trappist1b", IniSystems.trappist1, 3.254752F);
				builder.setData(trappistb, ClassBody.SELENA, trappistAu[0], 0.45F, trappistAu[0], 0, 24000L);

				trappistd = builder.buildUnreachablePlanet("trappist1d", IniSystems.trappist1, 1.932375F);
				builder.setData(trappistd, ClassBody.SELENA, trappistAu[2], 0.45F, trappistAu[2], 0, 24000L);

				trappistf = builder.buildUnreachablePlanet("trappist1f", IniSystems.trappist1, 0.6451158F);
				builder.setData(trappistf, ClassBody.SELENA, trappistAu[4], 0.45F, trappistAu[4], 0, 24000L);

				trappistg = builder.buildUnreachablePlanet("trappist1g", IniSystems.trappist1, 0.6451158F);
				builder.setData(trappistg, ClassBody.SELENA, trappistAu[5], 0.45F, trappistAu[5], 0, 24000L);

				trappisth = builder.buildUnreachablePlanet("trappist1h", IniSystems.trappist1, 0.896365F);
				builder.setData(trappisth, ClassBody.SELENA, trappistAu[6], 0.45F, trappistAu[6], 0, 24000L);
			}
		}

		if (IniSystems.buildwolf1061) {

			if (!SConfigSystems.hideUnfinishedSystems) {

				wolf1061b = builder.buildUnreachablePlanet("wolf1061b", IniSystems.wolf1061, 2.9495497F);
				builder.setData(wolf1061b, ClassBody.SELENA, wolfAu[0], 0.45F, wolfAu[0], 0, 24000L);

				wolf1061c = builder.buildUnreachablePlanet("wolf1061c", IniSystems.wolf1061, 1.7264397F);
				builder.setData(wolf1061c, ClassBody.SELENA, wolfAu[1], 0.45F, wolfAu[1], 0, 24000L);

				wolf1061d = builder.buildUnreachablePlanet("wolf1061d", IniSystems.wolf1061, 7.132725F);
				builder.setData(wolf1061d, ClassBody.SELENA, wolfAu[2], 0.45F, wolfAu[2], 0, 24000L);
			}

		}

		if (IniSystems.buildkepler1649) {
			
			kepler1649c = builder.buildExoPlanet(IniSystems.kepler1649, "kepler1649c", WorldProviderKepler1649c.class, id_kepler_c, k1649_tier, 0.9F);
			builder.setData(kepler1649c, ClassBody.SELENA, kepler1649Au[1], 0.005f, 0.5f, 0, 26500L);
			builder.setExoData(kepler1649c, 5.0f, 1.2f, 1.06f);
			builder.setAtmos(kepler1649c, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			GalaxyRegistry.registerPlanet(kepler1649c);

			if (!SConfigSystems.hideUnfinishedSystems) {
				
				kepler1649b = builder.buildUnreachablePlanet("kepler1649b", IniSystems.kepler1649, 1.932375F);
				builder.setData(kepler1649b, ClassBody.SELENA, kepler1649Au[0], 0.55F, kepler1649Au[1], 0, 24000L);
			}

		}
	}

	public static void registerTeleportTypes() {

		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiB.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiC.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiD.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerTeleportType(WorldProviderKepler1649c.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiB.class, getWorldGui("yzcetib"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiC.class, getWorldGui("yzcetic"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiD.class, getWorldGui("yzcetid"));
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1E.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1E.class, getWorldGui("trappist1e"));
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1C.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1C.class, getWorldGui("trappist1c"));
	}

	public static float getGravity(ExPlanet planet) {
		return planet.getGravity();
	}

	private static ResourceLocation getWorldGui(String planetString) {
		return new ResourceLocation(ExoInfo.MODID, "textures/gui/rocketgui/" + planetString + ".png");
	}

	public static long getDayLength(ExPlanet planet) {
		return planet.getDayLength();
	}
}