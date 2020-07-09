/**
 * Copyright (C) 2020 Interstellar: Exoplanets
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
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_d;
import static net.rom.exoplanets.conf.SConfigSystems.yzceti_tier;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeSpaceStation;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.deepspace.WorldProviderDeepSpace;
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
import net.rom.exoplanets.internal.world.star.ExoStar;

public class ExoUniverse {

	public static ExoStar yzCetiStar;
	public static ExoStar wolf1061Star;
	public static ExoStar trappist1Star;
	public static ExoStar kepler1649star;

	public static SolarSystem yzCeti;
	public static SolarSystem wolf1061;
	public static SolarSystem trappist1;
	public static SolarSystem kepler1649;

	public static Float[] yzCetiAu = { 0.3F, 0.4f, 0.590f };
	public static ExoPlanet yzcetib;
	public static ExoPlanet yzcetic;
	public static ExoPlanet yzcetid;

	// Wolf 1061IBuild
	private static Float[] wolfAu = { 0.2F, 0.4f, 2.5f };
	public static ExoPlanet wolf1061b;
	public static ExoPlanet wolf1061c;
	public static ExoPlanet wolf1061d;

	// Trappist 1
	private static Float[] trappistAu = { 0.3F, 0.4f, 0.6f, 0.8f, 1.0f, 1.2f, 1.6f };
	public static ExoPlanet trappistb;
	public static ExoPlanet trappistc;
	public static ExoPlanet trappistd;
	public static ExoPlanet trappiste;
	public static ExoPlanet trappistf;
	public static ExoPlanet trappistg;
	public static ExoPlanet trappisth;

	public static float[] kepler1649Au = { 0.4f, 0.7f };
	public static ExoPlanet kepler1649b;
	public static ExoPlanet kepler1649c;
	
	public static Planet planetJupiter = GalacticraftCore.planetJupiter;
	
	static SolarSystem sol = GalacticraftCore.solarSystemSol;

	public static Moon stationDeepSpace;

	static AstroBuilder builder = new AstroBuilder("exoplanets");
	
	public static void bigBangEvent() {
		ExoStars.build();
		ExoSystems.build();
		Exoplanets.build();
		//ExoMoons.build();
		registerTeleportTypes();
	}
	
	private static void registerTeleportTypes() {
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiB.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiC.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiD.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiB.class, getWorldGui("yzcetib"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiC.class, getWorldGui("yzcetic"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiD.class, getWorldGui("yzcetid"));
		
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1E.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1E.class, getWorldGui("trappist1e"));
		
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1C.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1C.class, getWorldGui("trappist1c"));
		
        GalacticraftRegistry.registerTeleportType(WorldProviderDeepSpace.class, new TeleportTypeSpaceStation());
        GalacticraftRegistry.registerRocketGui(WorldProviderDeepSpace.class, new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/overworld_rocket_gui.png"));
	}
	
	private static ResourceLocation getWorldGui(String planetString) {
		return new ResourceLocation(ExoInfo.MODID, "textures/gui/rocketgui/" + planetString + ".png");
	}
	
	public static class ExoStars {
		
		private static void build() {
			yzCetiStar = builder.buildExoStar("yzcetistar", 3058, 0.130D, 0.168D);
			wolf1061Star = builder.buildExoStar("wolf1061star", 3342, 0.294D, 0.307D);
			trappist1Star = builder.buildExoStar("trappist1star", 2511, 0.089D, 0.121D);
			kepler1649star = builder.buildExoStar("kepler1649star", 2150, 0.02D, 0.230D);
		}
	}
	
	public static class ExoSystems {
		
		private static void build() {
			yzCeti = builder.buildSolarSystem("yzceti", yzPos(), yzCetiStar);
			BodiesData data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesHelper.registerBodyData(yzCeti.getMainStar(), data);

			wolf1061 = builder.buildSolarSystem("wolf1061", wolfPos(), wolf1061Star);
			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesHelper.registerBodyData(wolf1061.getMainStar(), data);

			trappist1 = builder.buildSolarSystem("trappist1", trapPos(), trappist1Star);
			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesHelper.registerBodyData(trappist1.getMainStar(), data);

			kepler1649 = builder.buildSolarSystem("kepler1649", k1649Pos(), kepler1649star);
			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesHelper.registerBodyData(kepler1649.getMainStar(), data);
		}
		
		public static Vector3 yzPos() {
			return new Vector3(SConfigSystems.yzceti_x, SConfigSystems.yzceti_y, 0.0F);
		}

		public static Vector3 wolfPos() {
			return new Vector3(SConfigSystems.wolf_x, SConfigSystems.wolf_y, 0.0F);
		}

		public static Vector3 trapPos() {
			return new Vector3(SConfigSystems.trap_x, SConfigSystems.trap_y, 0.0F);
		}

		public static Vector3 k1649Pos() {
			return new Vector3(SConfigSystems.k1649_x, SConfigSystems.k1649_y, 0.0F);
		}
	}

	public static class Exoplanets {
		
		private static void build() {
			
			planetJupiter = BodiesHelper.registerExPlanet(sol, "jupiter", Constants.ASSET_PREFIX, 2.0F);
			BodiesHelper.setOrbitData(planetJupiter, (float) Math.PI, 2.0F, 11.86F);
			GalaxyRegistry.registerPlanet(planetJupiter);
			
			yzcetib = builder.buildExoPlanet(yzCeti, "yzcetib", WorldProviderYzCetiB.class, id_yz_b, yzceti_tier, 0.5F);
			builder.setData(yzcetib, ClassBody.SELENA, yzCetiAu[0], 0.015f, 0.4f, 0, 0L);
			builder.setNormalOrbit(yzcetib);
			builder.setExoData(yzcetib, 50.0f, 0.75f, 0.93f);
			builder.setAtmos(yzcetib, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			builder.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);

			yzcetic = builder.buildExoPlanet(yzCeti, "yzcetic", WorldProviderYzCetiC.class, id_yz_c, yzceti_tier, 0.1F);
			builder.setData(yzcetic, ClassBody.SELENA, yzCetiAu[1], 0.010f, 0.5f, 0, 19500L);
			builder.setNormalOrbit(yzcetic);
			builder.setExoData(yzcetic, 26.5f, 0.9f, 1.0f);
			builder.setAtmos(yzcetic, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			builder.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);

			yzcetid = builder.buildExoPlanet(yzCeti, "yzcetid", WorldProviderYzCetiD.class, id_yz_d, yzceti_tier, 0.9F);
			builder.setData(yzcetid, ClassBody.SELENA, yzCetiAu[2], 0.005f, 0.6f, 0, 26500L);
			builder.setOrbit(yzcetid, 1.0f, 1.0f, 4.3f, 4.3f);
			builder.setExoData(yzcetid, 5.0f, 1.14f, 1.05f);
			builder.setAtmos(yzcetid, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			builder.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d);
			
			trappistc = builder.buildExoPlanet(trappist1, "trappist1c", WorldProviderTrappist1C.class, SConfigDimensionID.id_trap_c, SConfigSystems.trap_tier, (float) Math.PI);
			builder.setData(trappistc, ClassBody.SELENA, trappistAu[1], 0.005f, 0.6f, 0, 26500L);
			builder.setExoData(trappistc, 8.0f, 1.38f, 1.056f);
			builder.setAtmos(trappistc, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
			
			trappiste = builder.buildExoPlanet(trappist1, "trappist1e", WorldProviderTrappist1E.class, SConfigDimensionID.id_trap_e, SConfigSystems.trap_tier, (float) Math.PI);
			builder.setData(trappiste, ClassBody.SELENA, trappistAu[3], 0.005f, 0.6f, 0, 26500L);
			builder.setExoData(trappiste, 8.0f, 1.38f, 1.056f);
			builder.setAtmos(trappiste, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.OXYGEN);
			
			if (!SConfigSystems.hideUnfinishedSystems) {
				// Wolf 1061
				wolf1061b = builder.buildUnreachablePlanet("wolf1061b", wolf1061, 2.9495497F);
				builder.setData(wolf1061b, ClassBody.SELENA,wolfAu[0],  0.45F, wolfAu[0], 0, 24000L);

				wolf1061c = builder.buildUnreachablePlanet("wolf1061c", wolf1061, 1.7264397F);
				builder.setData(wolf1061c, ClassBody.SELENA,wolfAu[1],  0.45F, wolfAu[1], 0, 24000L);

				wolf1061d = builder.buildUnreachablePlanet("wolf1061d", wolf1061, 7.132725F);
				builder.setData(wolf1061d, ClassBody.SELENA,wolfAu[2],  0.45F, wolfAu[2], 0, 24000L);

				// Trappist 1
				trappistb = builder.buildUnreachablePlanet("trappist1b", trappist1, 3.254752F);
				builder.setData(trappistb, ClassBody.SELENA,trappistAu[0],  0.45F, trappistAu[0], 0, 24000L);

				trappistd = builder.buildUnreachablePlanet("trappist1d", trappist1, 1.932375F);
				builder.setData(trappistd, ClassBody.SELENA,trappistAu[2],  0.45F, trappistAu[2], 0, 24000L);
				
				trappistf = builder.buildUnreachablePlanet("trappist1f", trappist1, 0.6451158F);
				builder.setData(trappistf, ClassBody.SELENA,trappistAu[4],  0.45F, trappistAu[4], 0, 24000L);
				
				trappistg = builder.buildUnreachablePlanet("trappist1g", trappist1, 0.6451158F);
				builder.setData(trappistg, ClassBody.SELENA,trappistAu[5],  0.45F, trappistAu[5], 0, 24000L);
				
				trappisth = builder.buildUnreachablePlanet("trappist1h", trappist1, 0.896365F);
				builder.setData(trappisth, ClassBody.SELENA,trappistAu[6],  0.45F, trappistAu[6], 0, 24000L);
				
				//kepler 1649
				kepler1649b = builder.buildUnreachablePlanet("kepler1649b", kepler1649, 0.6451158F);
				builder.setData(kepler1649b, ClassBody.SELENA, kepler1649Au[0],  0.55F, kepler1649Au[0], 0, 24000L);
				
				kepler1649c = builder.buildUnreachablePlanet("kepler1649c", kepler1649, 1.932375F);
				builder.setData(kepler1649c, ClassBody.SELENA, kepler1649Au[1],  0.55F, kepler1649Au[1], 0, 24000L);
			}
		}
	}
	
	public static class ExoMoons {
		
		private static void build() {
			stationDeepSpace = builder.buildMoon(planetJupiter, "deep_space", 3, 0.2667F, 23.5F, 1 / 0.02F);
			stationDeepSpace.setDimensionInfo(-65, WorldProviderDeepSpace.class);
			stationDeepSpace.setAtmosphere(new AtmosphereInfo(false, false, false, 0.0F, 0.1F, 0.02F));
		}
		
	}
}
