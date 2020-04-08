package net.rom.stellar;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rom.core.autoreg.StellarRegistry;
import net.rom.core.base.IMod;
import net.rom.stellar.astronomy.ExoDimensions;
import net.rom.stellar.astronomy.biomes.ExoplanetBiomes;
import net.rom.stellar.conf.SConfigCore;
import net.rom.stellar.conf.SConfigSystems;
import net.rom.stellar.init.ExoPlanets;
import net.rom.stellar.init.ExoStarSystem;
import net.rom.stellar.init.ExoplanetsBlocks;
import net.rom.stellar.proxy.ExoCommonProxy;


@Mod(modid = Exoplanets.MODID, name = Exoplanets.NAME, version = Exoplanets.VERSION, dependencies = Exoplanets.DEPENDENCIES_MODS, acceptedMinecraftVersions = Exoplanets.ACCEPTED_MC_VERSION, certificateFingerprint = "@FINGERPRINT@")
public class Exoplanets implements IMod {

	public static final String MODID = "exoplanets";
	public static final String NAME = "Interstellar: Exoplanets";
	public static final String VERSION = "${version}";
	public static final String ACCEPTED_MC_VERSIONS = "[1.12.2]";
	public static final String ACCEPTED_MC_VERSION = ForgeVersion.mcVersion;
	public static final String DEPENDENCIES_MODS = "required-after:readonlycore; required-after:galacticraftcore; required-after:galacticraftplanets;";
	public static final String RESOURCE_PREFIX = MODID + ":";
	public static final Logger LOGGER = LogManager.getLogger(Exoplanets.MODID);
	public static final StellarRegistry REGISTRY = new StellarRegistry();
	@Instance(MODID)
	public static Exoplanets instance;


	@SidedProxy(clientSide = "net.rom.stellar.proxy.ExoClientProxy", serverSide = "net.rom.stellar.proxy.ExoCommonProxy")
	public static ExoCommonProxy proxy;
	
	private InterstellarSounds sounds;


	@EventHandler
	public static void onFingerprintViolation(final FMLFingerprintViolationEvent event) {
		LOGGER.warn("Invalid Fingerprint");
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		REGISTRY.setMod(this);
		new SConfigSystems(new File(event.getModConfigurationDirectory(), "Interstellar/dimensions.cfg"));
		new SConfigCore(new File(event.getModConfigurationDirectory(), "Interstellar/core.cfg"));
		REGISTRY.addRegistrationHandler(ExoplanetsBlocks::registerAll, Block.class);
		ExoplanetBiomes.init();
		ExoStarSystem.init();
		ExoPlanets.init();
		sounds = new InterstellarSounds();
		
		MinecraftForge.EVENT_BUS.register(sounds);
		proxy.preInit(REGISTRY, event);
	}

    @EventHandler
	public static void init(FMLInitializationEvent event) {
    	
		for (BiomeGenBaseGC biome : ExoplanetBiomes.biomeList) {
			biome.registerTypes(biome);
		}
		proxy.init(REGISTRY, event);
	}

    @EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
    	
    	ExoDimensions.init();
		proxy.postInit(REGISTRY, event);
	}

	@Override
	public String getModId() {
		return MODID;
	}

	@Override
	public String getModName() {
		return NAME;
	}

	@Override
	public String getVersion() {
		return VERSION;
	}

	@Override
	public int getBuildNum() {
		return 0;
	}
}
