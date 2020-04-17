package net.rom.exoplanets.init;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.minecraftforge.fml.common.Loader;
import net.rom.api.AstroBuilder;
import net.rom.api.enums.EnumClass;
import net.rom.api.implemtations.planet.ExoPlanet;
import net.rom.exoplanets.Exoplanets;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.yzcetisystem.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzcetisystem.c.WorldProviderYzCetiC;
import net.rom.exoplanets.conf.SConfigDimensionID;
import net.rom.exoplanets.conf.SConfigSystems;

public class PlanetsRegister {

	// Yz Ceti
	private static Float[] yzCetiAu = { 0.3F, 0.4f, 0.6f };
	public static ExoPlanet yzcetib;
	public static ExoPlanet yzcetic;
	public static ExoPlanet yzcetid;

	// Wolf 1061
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

	static AstroBuilder builder = new AstroBuilder(Exoplanets.MODID);

	public static void init() {
		PlanetsRegister.initPlanets();
		PlanetsRegister.registerPlanets();
		PlanetsRegister.registerPlanetData();
		PlanetsRegister.buildUnreachable();
		PlanetsRegister.registerTeleportTypes();
	}

	public static void initPlanets() {

		yzcetib = builder.buildExoPlanet(SystemRegister.yzCeti, "yz_ceti_b", WorldProviderYzCetiB.class,
				SConfigDimensionID.id_yz_b, 3, 4.50F);

		yzcetic = builder.buildExoPlanet(SystemRegister.yzCeti, "yz_ceti_c", WorldProviderYzCetiC.class,
				SConfigDimensionID.id_yz_c, 3, 1.5F);

		if (Loader.isModLoaded("asmodeuscore")) {
			asmodeusData();
		}
	}

	public static void registerPlanetData() {
		yzcetib.setPlanetGravity(0.35F);
		yzcetib.setDistanceFromCenter(yzCetiAu[0]);
		yzcetib.setRelativeOrbitTime(yzCetiAu[0] + 0.5F);
		yzcetib.setDayLength(0.93F);
		yzcetib.setExoClass(EnumClass.D);
		yzcetib.setPlanetTemp(-54.5F);
		yzcetib.setBaseToxicity(15.2F);
		yzcetib.setBaseRadiation(2.2F);
		yzcetib.setPlanetDensity(5);
		yzcetib.setAtmosGasses(EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN,
				EnumAtmosphericGas.ARGON);
		yzcetib.setBiomeInfo(ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
		yzcetib.addChecklistKeys("equipOxygenSuit");

		yzcetib.setPlanetGravity(0.42F);
		yzcetic.setDistanceFromCenter(yzCetiAu[1]);
		yzcetic.setRelativeOrbitTime(yzCetiAu[1] + 0.5F);
		yzcetib.setDayLength(1.0F);
		yzcetic.setExoClass(EnumClass.D);
		yzcetic.setPlanetTemp(-54.5F);
		yzcetic.setBaseToxicity(15.2F);
		yzcetic.setBaseRadiation(2.2F);
		yzcetic.setPlanetDensity(5);
		yzcetic.setAtmosGasses(EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN,
				EnumAtmosphericGas.ARGON);
		yzcetic.setBiomeInfo(ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
		yzcetic.addChecklistKeys("equipOxygenSuit");
	}

	public static void asmodeusData() {
		// Yz Ceti
		BodiesData data = new BodiesData(ClassBody.DESERT, BodiesHelper.calculateGravity(getGravity(yzcetib)), 0,
				getDayLength(yzcetib), false);
		BodiesHelper.registerBody(yzcetib, data, SConfigSystems.disable_yzceti_system);
		data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(getGravity(yzcetic)), 0,
				getDayLength(yzcetic), false);
		BodiesHelper.registerBody(yzcetic, data, false);

		if (!SConfigSystems.hideUnfinishedSystems) {
			data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(yzcetid, data, false);

			// Wolf 1061
			data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(wolf1061b, data, false);
			data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(wolf1061c, data, false);
			data = new BodiesData(ClassBody.GASGIANT, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(wolf1061d, data, false);

			// HD 219134
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(hd219134b, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(hd219134c, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(hd219134d, data, false);
			data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(hd219134f, data, false);
			data = new BodiesData(ClassBody.GASGIANT, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(hd219134g, data, false);
			data = new BodiesData(ClassBody.GASGIANT, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(hd219134h, data, false);
			
			// Trappist 1
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(trappistb, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(trappistc, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(trappistd, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(trappiste, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(trappistf, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(trappistg, data, false);
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
			BodiesHelper.registerBody(trappisth, data, false);
		}
	}

	public static void buildUnreachable() {

		if (!SConfigSystems.hideUnfinishedSystems) {
			yzcetid = builder.buildUnreachablePlanet("yz_ceti_d", SystemRegister.yzCeti, 4.793016F, yzCetiAu[2]);

			// Wolf 1061
			wolf1061b = builder.buildUnreachablePlanet("wolf_1061_b", SystemRegister.wolf1061, 2.9495497F, wolfAu[0]);
			wolf1061c = builder.buildUnreachablePlanet("wolf_1061_c", SystemRegister.wolf1061, 1.7264397F, wolfAu[1]);
			wolf1061d = builder.buildUnreachablePlanet("wolf_1061_d", SystemRegister.wolf1061, 7.132725F, wolfAu[2]);

			// HD 219134
			hd219134b = builder.buildUnreachablePlanet("hd_219134_b", SystemRegister.hd219134, 0.292421F, hdAu[0]);
			hd219134c = builder.buildUnreachablePlanet("hd_219134_c", SystemRegister.hd219134, 2.9495497F, hdAu[1]);
			hd219134d = builder.buildUnreachablePlanet("hd_219134_d", SystemRegister.hd219134, 0.896365F, hdAu[2]);
			hd219134f = builder.buildUnreachablePlanet("hd_219134_f", SystemRegister.hd219134, 4.305977F, hdAu[3]);
			hd219134g = builder.buildUnreachablePlanet("hd_219134_g", SystemRegister.hd219134, 1.932375F, hdAu[4]);
			hd219134h = builder.buildUnreachablePlanet("hd_219134_h", SystemRegister.hd219134, 7.906747F, hdAu[5]);

			// Trappist 1
			trappistb = builder.buildUnreachablePlanet("trappist_b", SystemRegister.trappist1, 3.254752F, trappistAu[0]);
			trappistc = builder.buildUnreachablePlanet("trappist_c", SystemRegister.trappist1, 0.6451158F, trappistAu[1]);
			trappistd = builder.buildUnreachablePlanet("trappist_d", SystemRegister.trappist1, 5.6336107F, trappistAu[2]);
			trappiste = builder.buildUnreachablePlanet("trappist_e", SystemRegister.trappist1, 0.6451158F, trappistAu[3]);
			trappistf = builder.buildUnreachablePlanet("trappist_f", SystemRegister.trappist1, 5.396859F, trappistAu[4]);
			trappistg = builder.buildUnreachablePlanet("trappist_g", SystemRegister.trappist1, 7.957024F, trappistAu[5]);
			trappisth = builder.buildUnreachablePlanet("trappist_h", SystemRegister.trappist1, 2.711277F, trappistAu[6]);
		}
	}

	public static void registerPlanets() {
		GalaxyRegistry.registerPlanet(yzcetib);
		GalaxyRegistry.registerPlanet(yzcetic);
	}

	public static void registerTeleportTypes() {
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiB.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiC.class, new TeleportTypeVenus());
	}

	public static float getGravity(ExoPlanet planet) {
		return planet.getGravity();
	}

	public static long getDayLength(ExoPlanet planet) {
		return planet.getDayLength();
	}
}