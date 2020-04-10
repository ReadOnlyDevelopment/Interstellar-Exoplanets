package net.rom.stellar.init;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.minecraftforge.fml.common.Loader;
import net.rom.core.space.AstroBuilder;
import net.rom.core.space.enums.EnumClass;
import net.rom.core.space.implemtations.planet.ExoPlanet;
import net.rom.stellar.Exoplanets;
import net.rom.stellar.astronomy.biomes.ExoplanetBiomes;
import net.rom.stellar.astronomy.worldproviders.WorldProviderYzCetiB;
import net.rom.stellar.astronomy.worldproviders.WorldProviderYzCetiC;
import net.rom.stellar.conf.SConfigSystems;

public class PlanetsRegister {

	public static ExoPlanet yzcetib = new ExoPlanet("yzcetib");
	public static ExoPlanet yzcetic = new ExoPlanet("yzcetic");
	public static ExoPlanet yzcetid = new ExoPlanet("yzcetid");
	static AstroBuilder builder = new AstroBuilder(Exoplanets.MODID);

	public static void init() {
		PlanetsRegister.initPlanets();
		PlanetsRegister.registerPlanets();
		PlanetsRegister.registerTeleportTypes();
	}

	public static void initPlanets() {

		yzcetib = builder.buildExoPlanet(SystemRegister.YZCETI, "yzcetib", WorldProviderYzCetiB.class,
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

		yzcetic = builder.buildExoPlanet(SystemRegister.YZCETI, "yzcetic", WorldProviderYzCetiC.class,
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
		yzcetic.setBiomeInfo(ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
		yzcetic.addChecklistKeys("equipOxygenSuit");
		
		yzcetid = builder.buildUnreachablePlanet(SystemRegister.YZCETI, "yzcetid");
		yzcetid.setDistanceFromCenter(0.5F);
		yzcetid.setRingColorRGB(0.8F, 0.0F, 0.0F);
		
		if(Loader.isModLoaded("asmodeuscore")) {
			BodiesData data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.052F), 0, 22000L, false);
			BodiesHelper.registerBody(yzcetib, data, SConfigSystems.disable_yzceti_system);
			
			data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.014F), 0, 10000L, false);
			BodiesHelper.registerBody(yzcetic, data, SConfigSystems.disable_yzceti_system);
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
}