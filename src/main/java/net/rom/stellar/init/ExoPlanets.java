package net.rom.stellar.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.rom.core.space.AstroBuilder;
import net.rom.core.space.enums.EnumClass;
import net.rom.core.space.implemtations.planet.ExoPlanet;
import net.rom.stellar.Exoplanets;
import net.rom.stellar.astronomy.biomes.ExoplanetBiomes;
import net.rom.stellar.astronomy.worldproviders.WorldProviderYzCetiB;
import net.rom.stellar.astronomy.worldproviders.WorldProviderYzCetiC;
import net.rom.stellar.conf.SConfigSystems;

public class ExoPlanets {

	public static ExoPlanet yzcetib = new ExoPlanet("yzcetib");
	public static ExoPlanet yzcetic = new ExoPlanet("yzcetic");
	public static ExoPlanet yzcetid = new ExoPlanet("yzcetid");
	static AstroBuilder builder = new AstroBuilder(Exoplanets.MODID);

	public static void init() {
		ExoPlanets.initPlanets();
		ExoPlanets.registerPlanets();
		ExoPlanets.registerTeleportTypes();
	}

	public static void initPlanets() {

		yzcetib = builder.buildExoPlanet(ExoStarSystem.YZCETI, "yzcetib", WorldProviderYzCetiB.class,
				SConfigSystems.id_yz_b, 3, 0.0F, 0.2F, 0.4F);

		yzcetib.setExoClass(EnumClass.D);
		yzcetib.setRingColorRGB(0.1F, 0.9F, 2.6F);
		yzcetib.setRelativeSize(40.0F);
		yzcetib.setPlanetTemp(-54.5F);
		yzcetib.setBaseToxicity(15.2F);
		yzcetib.setBaseRadiation(2.2F);
		yzcetib.setPlanetDensity(5);
		yzcetib.setAtmosGasses(EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN,
				EnumAtmosphericGas.ARGON);
		yzcetib.setAtmos();
		yzcetib.setBiomeInfo(ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
		yzcetib.addChecklistKeys("equipOxygenSuit");
		yzcetib.setDimensionInfo(SConfigSystems.id_yz_b, WorldProviderYzCetiB.class);

		yzcetic = builder.buildExoPlanet(ExoStarSystem.YZCETI, "yzcetic", WorldProviderYzCetiB.class,
				SConfigSystems.id_yz_c, 3, 0.0F, 0.4F, 0.9F);

		yzcetic.setExoClass(EnumClass.D);
		yzcetic.setRingColorRGB(0.1F, 0.9F, 2.6F);
		yzcetic.setRelativeSize(40.0F);
		yzcetic.setPlanetTemp(-54.5F);
		yzcetic.setBaseToxicity(15.2F);
		yzcetic.setBaseRadiation(2.2F);
		yzcetic.setPlanetDensity(5);
		yzcetic.setAtmosGasses(EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN,
				EnumAtmosphericGas.ARGON);
		yzcetic.setAtmos();
		yzcetic.setBiomeInfo(ExoplanetBiomes.CETIC_BASE);
		yzcetic.addChecklistKeys("equipOxygenSuit");
		yzcetic.setDimensionInfo(SConfigSystems.id_yz_c, WorldProviderYzCetiC.class);
		
		yzcetid = builder.buildUnreachablePlanet(ExoStarSystem.YZCETI, "yzcetid");
		yzcetid.setDistanceFromCenter(0.5F);
		yzcetid.setRingColorRGB(0.8F, 0.0F, 0.0F);

	}

	public static void registerPlanets() {
		GalaxyRegistry.registerPlanet(yzcetib);
		GalaxyRegistry.registerPlanet(yzcetic);
	}

	public static void registerTeleportTypes() {
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiB.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiC.class, new TeleportTypeVenus());
	}
}