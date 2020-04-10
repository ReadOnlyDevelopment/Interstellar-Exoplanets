package net.rom.exoplanets;

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
import net.rom.api.IMod;
import net.rom.exoplanets.astronomy.ExoDimensions;
import net.rom.exoplanets.astronomy.biomes.ExoplanetBiomes;
import net.rom.exoplanets.conf.SConfigCore;
import net.rom.exoplanets.conf.SConfigSystems;
import net.rom.exoplanets.event.HabitableZoneClientHandler;
import net.rom.exoplanets.init.BlocksRegister;
import net.rom.exoplanets.init.PlanetsRegister;
import net.rom.exoplanets.init.SystemRegister;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.internal.network.NHandler;
import net.rom.exoplanets.proxy.ExoCommonProxy;


@Mod(modid = Exoplanets.MODID, name = Exoplanets.NAME, version = Exoplanets.VERSION, dependencies = Exoplanets.DEPENDENCIES_MODS, acceptedMinecraftVersions = Exoplanets.ACCEPTED_MC_VERSION, certificateFingerprint = "@FINGERPRINT@")
public class Exoplanets implements IMod {

	public static final String MODID = "exoplanets";
	public static final String NAME = "Interstellar: Exoplanets";
	public static final String VERSION = "${version}";
	public static final String ACCEPTED_MC_VERSIONS = "[1.12.2]";
	public static final String ACCEPTED_MC_VERSION = ForgeVersion.mcVersion;
	public static final String DEPENDENCIES_MODS = "required-after:galacticraftcore; required-after:galacticraftplanets;";
	public static final String RESOURCE_PREFIX = MODID + ":";
	public static final Logger LOGGER = LogManager.getLogger(Exoplanets.MODID);
	public static final StellarRegistry REGISTRY = new StellarRegistry();
	@Instance(MODID)
	public static Exoplanets instance;
	public static NHandler network;

	@SidedProxy(clientSide = "net.rom.exoplanets.proxy.ExoClientProxy", serverSide = "net.rom.exoplanets.proxy.ExoCommonProxy")
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
		REGISTRY.addRegistrationHandler(BlocksRegister::registerAll, Block.class);
		ExoplanetBiomes.init();
		SystemRegister.init();
		PlanetsRegister.init();
		sounds = new InterstellarSounds();
        HabitableZoneClientHandler clientEventHandler = new HabitableZoneClientHandler();
        MinecraftForge.EVENT_BUS.register(clientEventHandler);
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
