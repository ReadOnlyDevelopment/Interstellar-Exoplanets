/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.init;

import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_b;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_c;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_d;
import static net.rom.exoplanets.conf.SConfigSystems.yzceti_tier;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.rom.exoplanets.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.rom.exoplanets.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.rom.exoplanets.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.rom.exoplanets.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.rom.exoplanets.conf.SConfigDimensionID;
import net.rom.exoplanets.conf.SConfigSystems;
import net.rom.exoplanets.internal.AstroBuilder;
import net.rom.exoplanets.internal.AstronomicalConstants;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;

public class InitPlanets {

	public static Float[] yzCetiAu = { 0.3F, 0.4f, 0.590f };

	public static ExoPlanet yzcetib;
	public static ExoPlanet yzcetic;
	public static ExoPlanet yzcetid;

	// Wolf 1061IBuild
	private static Float[] wolfAu = { 0.2F, 0.4f, 2.5f };
	public static ExoPlanet wolf1061b;
	public static ExoPlanet wolf1061c;
	public static ExoPlanet wolf1061d;

	// HD 219134
	private static Float[] hdAu = { 0.2F, 0.3f, 0.4f, 0.6f, 0.8f, 2.7f };
	public static ExoPlanet hd219134b;
	public static ExoPlanet hd219134c;
	public static ExoPlanet hd219134d;
	public static ExoPlanet hd219134f;
	public static ExoPlanet hd219134g;
	public static ExoPlanet hd219134h;

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
	
	public static Satellite earth_highorbit;

	static AstroBuilder builder = new AstroBuilder("exoplanets");

	public static void init() {
		InitPlanets.initPlanets();
		InitPlanets.unreachables();
		InitPlanets.registerPlanets();
		InitPlanets.registerTeleportTypes();

	}

	public static void initPlanets() {

		SolarSystem yzCet = IniSystems.yzCeti;
		SolarSystem trap1 = IniSystems.trappist1;

		yzcetib = builder.buildExoPlanet(yzCet, "yz_ceti_b", WorldProviderYzCetiB.class, id_yz_b, yzceti_tier, 0.5F);
		builder.setData(yzcetib, ClassBody.SELENA, yzCetiAu[0], 0.015f, 0.4f, 0, 0L);
		builder.setNormalOrbit(yzcetib);
		builder.setExoData(yzcetib, 50.0f, 0.75f, 0.93f);
		builder.setAtmos(yzcetib, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
		builder.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);

		yzcetic = builder.buildExoPlanet(yzCet, "yz_ceti_c", WorldProviderYzCetiC.class, id_yz_c, yzceti_tier, 0.1F);
		builder.setData(yzcetic, ClassBody.SELENA, yzCetiAu[1], 0.010f, 0.5f, 0, 19500L);
		builder.setNormalOrbit(yzcetic);
		builder.setExoData(yzcetic, 26.5f, 0.9f, 1.0f);
		builder.setAtmos(yzcetic, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
		builder.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);

		yzcetid = builder.buildExoPlanet(yzCet, "yz_ceti_d", WorldProviderYzCetiD.class, id_yz_d, yzceti_tier, 0.9F);
		builder.setData(yzcetid, ClassBody.SELENA, yzCetiAu[2], 0.005f, 0.6f, 0, 26500L);
		builder.setOrbit(yzcetid, 1.0f, 1.0f, 4.3f, 4.3f);
		builder.setExoData(yzcetid, 5.0f, 1.14f, 1.05f);
		builder.setAtmos(yzcetid, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
		builder.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d);
		
		trappistc = builder.buildExoPlanet(trap1, "trappist_c", WorldProviderTrappist1C.class, SConfigDimensionID.id_trap_c, SConfigSystems.trap_tier, (float) Math.PI);
		builder.setData(trappistc, ClassBody.SELENA, trappistAu[1], 0.005f, 0.6f, 0, 26500L);
		builder.setExoData(trappistc, 8.0f, 1.38f, 1.056f);
		builder.setAtmos(trappistc, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
		
		trappiste = builder.buildExoPlanet(trap1, "trappist_e", WorldProviderTrappist1E.class, SConfigDimensionID.id_trap_e, SConfigSystems.trap_tier, (float) Math.PI);
		builder.setData(trappiste, ClassBody.SELENA, trappistAu[3], 0.005f, 0.6f, 0, 26500L);
		builder.setExoData(trappiste, 8.0f, 1.38f, 1.056f);
		builder.setAtmos(trappiste, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.OXYGEN);
		
		
	}

	public static void unreachables() {

		if (!SConfigSystems.hideUnfinishedSystems) {
			// Wolf 1061
			wolf1061b = builder.buildUnreachablePlanet("wolf_1061_b", IniSystems.wolf1061, 2.9495497F);
			builder.setData(wolf1061b, ClassBody.SELENA,wolfAu[0],  0.45F, wolfAu[0], 0, 24000L);

			wolf1061c = builder.buildUnreachablePlanet("wolf_1061_c", IniSystems.wolf1061, 1.7264397F);
			builder.setData(wolf1061c, ClassBody.SELENA,wolfAu[1],  0.45F, wolfAu[1], 0, 24000L);

			wolf1061d = builder.buildUnreachablePlanet("wolf_1061_d", IniSystems.wolf1061, 7.132725F);
			builder.setData(wolf1061d, ClassBody.SELENA,wolfAu[2],  0.45F, wolfAu[2], 0, 24000L);

			// HD 219134
			hd219134b = builder.buildUnreachablePlanet("hd_219134_b", IniSystems.hd219134, 0.292421F);
			builder.setData(hd219134b, ClassBody.SELENA,hdAu[0],  0.45F, hdAu[0], 0, 24000L);

			hd219134c = builder.buildUnreachablePlanet("hd_219134_c", IniSystems.hd219134, 2.9495497F);
			builder.setData(hd219134c, ClassBody.SELENA,hdAu[1],  0.45F, hdAu[1], 0, 24000L);

			hd219134d = builder.buildUnreachablePlanet("hd_219134_d", IniSystems.hd219134, 0.896365F);
			builder.setData(hd219134d, ClassBody.SELENA,hdAu[2],  0.45F, hdAu[2], 0, 24000L);

			hd219134f = builder.buildUnreachablePlanet("hd_219134_f", IniSystems.hd219134, 4.305977F);
			builder.setData(hd219134f, ClassBody.SELENA,hdAu[3],  0.45F, hdAu[3], 0, 24000L);

			hd219134g = builder.buildUnreachablePlanet("hd_219134_g", IniSystems.hd219134, 1.932375F);
			builder.setData(hd219134g, ClassBody.SELENA,hdAu[4],  0.45F, hdAu[4], 0, 24000L);

			hd219134h = builder.buildUnreachablePlanet("hd_219134_h", IniSystems.hd219134, 7.906747F);
			builder.setData(hd219134h, ClassBody.SELENA,hdAu[5],  0.45F, hdAu[5], 0, 24000L);

			// Trappist 1
			trappistb = builder.buildUnreachablePlanet("trappist_b", IniSystems.trappist1, 3.254752F);
			builder.setData(trappistb, ClassBody.SELENA,trappistAu[0],  0.45F, trappistAu[0], 0, 24000L);
			
//			trappistc = builder.buildUnreachablePlanet("trappist_c", IniSystems.trappist1, 0.6451158F, trappistAu[1]);
//			builder.setData(trappistc, ClassBody.SELENA,trappistAu[1],  0.45F, trappistAu[1], 0, 24000L);
			
			trappistd = builder.buildUnreachablePlanet("trappist_d", IniSystems.trappist1, 1.932375F);
			builder.setData(trappistd, ClassBody.SELENA,trappistAu[2],  0.45F, trappistAu[2], 0, 24000L);
			
//			trappiste = builder.buildUnreachablePlanet("trappist_e", IniSystems.trappist1, 4.305977F, trappistAu[3]);
//			builder.setData(trappiste, ClassBody.SELENA,trappistAu[3],  0.45F, trappistAu[3], 0, 24000L);
			
			trappistf = builder.buildUnreachablePlanet("trappist_f", IniSystems.trappist1, 0.6451158F);
			builder.setData(trappistf, ClassBody.SELENA,trappistAu[4],  0.45F, trappistAu[4], 0, 24000L);
			
			trappistg = builder.buildUnreachablePlanet("trappist_g", IniSystems.trappist1, 0.6451158F);
			builder.setData(trappistg, ClassBody.SELENA,trappistAu[5],  0.45F, trappistAu[5], 0, 24000L);
			
			trappisth = builder.buildUnreachablePlanet("trappist_h", IniSystems.trappist1, 0.896365F);
			builder.setData(trappisth, ClassBody.SELENA,trappistAu[6],  0.45F, trappistAu[6], 0, 24000L);
			
			//kepler 1649
			kepler1649b = builder.buildUnreachablePlanet("kepler1649_b", IniSystems.kepler1649, 0.6451158F);
			builder.setData(trappistg, ClassBody.SELENA, kepler1649Au[0],  0.55F, kepler1649Au[0], 0, 24000L);
			
			kepler1649c = builder.buildUnreachablePlanet("kepler1649_c", IniSystems.kepler1649, 1.932375F);
			builder.setData(trappisth, ClassBody.SELENA, kepler1649Au[1],  0.55F, kepler1649Au[1], 0, 24000L);
		}
	}

	public static float calcNew(float si) {
		return (float) (AstronomicalConstants.GRAVITY_FIELD * si / 1000);
	}
	
	public static void registerPlanets() {
		GalaxyRegistry.registerPlanet(yzcetib);
		GalaxyRegistry.registerPlanet(yzcetic);
		GalaxyRegistry.registerPlanet(yzcetid);
		
		GalaxyRegistry.registerPlanet(trappiste);
		GalaxyRegistry.registerPlanet(trappistc);


	}

	public static void registerTeleportTypes() {
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiB.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiC.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiD.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiB.class, new ResourceLocation(ExoInfo.RESOURCE_PREFIX, "textures/gui/rocketgui/yzcetib.png"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiC.class, new ResourceLocation(ExoInfo.RESOURCE_PREFIX, "textures/gui/rocketgui/yzcetic.png"));
		GalacticraftRegistry.registerRocketGui(WorldProviderYzCetiD.class, new ResourceLocation(ExoInfo.RESOURCE_PREFIX, "textures/gui/rocketgui/yzcetid.png"));
		
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1E.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1E.class, new ResourceLocation(ExoInfo.RESOURCE_PREFIX, "textures/gui/rocketgui/yzcetid.png"));
		
		GalacticraftRegistry.registerTeleportType(WorldProviderTrappist1C.class, new TeleportTypeMoon());
		GalacticraftRegistry.registerRocketGui(WorldProviderTrappist1C.class, new ResourceLocation(ExoInfo.RESOURCE_PREFIX, "textures/gui/rocketgui/yzcetid.png"));
	}

	public static float getGravity(ExPlanet planet) {
		return planet.getGravity();
	}

	public static long getDayLength(ExPlanet planet) {
		return planet.getDayLenght();
	}
}