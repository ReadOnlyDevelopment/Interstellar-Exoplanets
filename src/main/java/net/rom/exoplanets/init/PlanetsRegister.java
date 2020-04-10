package net.rom.exoplanets.init;

import java.util.Random;

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
import net.rom.exoplanets.astronomy.biomes.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.worldproviders.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.worldproviders.WorldProviderYzCetiC;
import net.rom.exoplanets.conf.SConfigDimensionID;
import net.rom.exoplanets.conf.SConfigSystems;

public class PlanetsRegister {

	static Random random = new Random();
	static float randomf = (float) (random.nextDouble() * (8.0 - 0.0));

	// Yz Ceti
	private static Float[] yzCetiAu = { 0.3F, 0.4f, 0.6f };
	public static ExoPlanet yzcetib = new ExoPlanet("yzcetib").setAUFromStar(yzCetiAu[0]);
	public static ExoPlanet yzcetic = new ExoPlanet("yzcetic").setAUFromStar(yzCetiAu[1]);
	public static ExoPlanet yzcetid = new ExoPlanet("yzcetid").setAUFromStar(yzCetiAu[2]);

	// Wolf 1061
	private static Float[] wolfAu = { 0.2F, 0.4f, 1.0f };
	public static ExoPlanet wolf1061b = new ExoPlanet("wolf1061b").setAUFromStar(wolfAu[0]);
	public static ExoPlanet wolf1061c = new ExoPlanet("wolf1061c").setAUFromStar(wolfAu[1]);
	public static ExoPlanet wolf1061d = new ExoPlanet("wolf1061d").setAUFromStar(wolfAu[2]);

	// HD 219134
	private static Float[] hdAu = { 0.2F, 0.3f, 0.5f, 0.8f, 1.1f, 1.6f };
	public static ExoPlanet hd219134b = new ExoPlanet("hd219134b").setAUFromStar(hdAu[0]);
	public static ExoPlanet hd219134c = new ExoPlanet("hd219134c").setAUFromStar(hdAu[1]);
	public static ExoPlanet hd219134d = new ExoPlanet("hd219134d").setAUFromStar(hdAu[2]);
	public static ExoPlanet hd219134f = new ExoPlanet("hd219134f").setAUFromStar(hdAu[3]);
	public static ExoPlanet hd219134g = new ExoPlanet("hd219134g").setAUFromStar(hdAu[4]);
	public static ExoPlanet hd219134h = new ExoPlanet("hd219134h").setAUFromStar(hdAu[5]);

	// Trappist 1
	private static Float[] trappistAu = { 0.2F, 0.3f, 0.5f, 0.8f, 1.1f, 1.6f, 1.8f };
	public static ExoPlanet trappistb = new ExoPlanet("trappistb").setAUFromStar(trappistAu[0]);
	public static ExoPlanet trappistc = new ExoPlanet("trappistc").setAUFromStar(trappistAu[1]);
	public static ExoPlanet trappistd = new ExoPlanet("trappistd").setAUFromStar(trappistAu[2]);
	public static ExoPlanet trappiste = new ExoPlanet("trappiste").setAUFromStar(trappistAu[3]);
	public static ExoPlanet trappistf = new ExoPlanet("trappistf").setAUFromStar(trappistAu[4]);
	public static ExoPlanet trappistg = new ExoPlanet("trappistg").setAUFromStar(trappistAu[5]);
	public static ExoPlanet trappisth = new ExoPlanet("trappisth").setAUFromStar(trappistAu[6]);

	static AstroBuilder builder = new AstroBuilder(Exoplanets.MODID);

	public static void init() {
		PlanetsRegister.initPlanets();
		PlanetsRegister.registerPlanets();
		PlanetsRegister.registerPlanetData();
		PlanetsRegister.buildUnreachable();
		PlanetsRegister.registerTeleportTypes();
	}

	public static void initPlanets() {

		yzcetib = builder.buildExoPlanet(SystemRegister.yzCeti, "yzcetib", WorldProviderYzCetiB.class,
				SConfigDimensionID.id_yz_b, 3, randomf);

		yzcetic = builder.buildExoPlanet(SystemRegister.yzCeti, "yzcetic", WorldProviderYzCetiC.class,
				SConfigDimensionID.id_hd_c, 3, randomf);

		if (Loader.isModLoaded("asmodeuscore")) {
			asmodeusData();
		}
	}

	public static void registerPlanetData() {
		yzcetib.setPlanetMass(0.75F);
		yzcetib.setExoClass(EnumClass.D);
		yzcetib.setPlanetTemp(-54.5F);
		yzcetib.setBaseToxicity(15.2F);
		yzcetib.setBaseRadiation(2.2F);
		yzcetib.setPlanetDensity(5);
		yzcetib.setAtmosGasses(EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN,
				EnumAtmosphericGas.ARGON);
		yzcetib.setBiomeInfo(ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
		yzcetib.addChecklistKeys("equipOxygenSuit");

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
			yzcetid = builder.buildUnreachablePlanet(yzcetid, SystemRegister.yzCeti, randomf);

			// Wolf 1061
			wolf1061b = builder.buildUnreachablePlanet(wolf1061b, SystemRegister.wolf1061, randomf);
			wolf1061c = builder.buildUnreachablePlanet(wolf1061c, SystemRegister.wolf1061, randomf);
			wolf1061d = builder.buildUnreachablePlanet(wolf1061d, SystemRegister.wolf1061, randomf);

			// HD 219134
			hd219134b = builder.buildUnreachablePlanet(hd219134b, SystemRegister.hd219134, randomf);
			hd219134c = builder.buildUnreachablePlanet(hd219134c, SystemRegister.hd219134, randomf);
			hd219134d = builder.buildUnreachablePlanet(hd219134d, SystemRegister.hd219134, randomf);
			hd219134f = builder.buildUnreachablePlanet(hd219134f, SystemRegister.hd219134, randomf);
			hd219134g = builder.buildUnreachablePlanet(hd219134g, SystemRegister.hd219134, randomf);
			hd219134h = builder.buildUnreachablePlanet(hd219134h, SystemRegister.hd219134, randomf);

			// Trappist 1
			trappistb = builder.buildUnreachablePlanet(trappistb, SystemRegister.trappist1, randomf);
			trappistc = builder.buildUnreachablePlanet(trappistc, SystemRegister.trappist1, randomf);
			trappistd = builder.buildUnreachablePlanet(trappistd, SystemRegister.trappist1, randomf);
			trappiste = builder.buildUnreachablePlanet(trappiste, SystemRegister.trappist1, randomf);
			trappistf = builder.buildUnreachablePlanet(trappistf, SystemRegister.trappist1, randomf);
			trappistg = builder.buildUnreachablePlanet(trappistg, SystemRegister.trappist1, randomf);
			trappisth = builder.buildUnreachablePlanet(trappisth, SystemRegister.trappist1, randomf);
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
		return planet.getExoPlanetProvider().getGravity();
	}

	public static long getDayLength(ExoPlanet planet) {
		return planet.getExoPlanetProvider().getDayLength();
	}
}