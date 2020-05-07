package net.rom.exoplanets;

import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.rom.api.IMod;
import net.rom.exoplanets.astronomy.ExoDimensions;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.conf.InitConfigFiles;
import net.rom.exoplanets.event.HabitableZoneClientHandler;
import net.rom.exoplanets.init.ExoFluids;
import net.rom.exoplanets.init.InitPlanets;
import net.rom.exoplanets.init.InitSolarSystems;
import net.rom.exoplanets.init.RegistrationHandler;
import net.rom.exoplanets.internal.LogHelper;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.proxy.ExoCommonProxy;
import net.rom.exoplanets.util.BiomeDebug;
import net.rom.exoplanets.util.Deobf;
import net.rom.exoplanets.util.LangFileHelper;
import net.rom.exoplanets.util.TranslateUtil;
import net.rom.exoplanets.world.OverworldOreGen;

@Mod(modid = ExoInfo.MODID, name = ExoInfo.NAME, version = ExoInfo.VERSION, dependencies = ExoInfo.DEPENDENCIES_MODS, acceptedMinecraftVersions = ExoInfo.ACCEPTED_MC_VERSION, certificateFingerprint = "0030a289fad85affe4a366ee6009b0b35d478f63", guiFactory = "net.rom.exoplanets.client.ExoplanetsConfigGuiFactory")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExoplanetsMod implements IMod {
	
    ///////////////////////// DEV ONLY /////////////////////////////

    private static boolean biomeDebug = true;
    private static boolean langHelper = true;

    /////////////////////////////////////////////////////////////////

    @Instance(ExoInfo.MODID)
    public static ExoplanetsMod instance;
    public static StellarRegistry REGISTRY = new StellarRegistry();
    public static TranslateUtil i18n = new TranslateUtil(ExoInfo.MODID);
    public static LogHelper logger = new LogHelper();

    @SidedProxy(clientSide = "net.rom.exoplanets.proxy.ExoClientProxy", serverSide = "net.rom.exoplanets.proxy.ExoCommonProxy")
    public static ExoCommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        REGISTRY.setMod(this);

        InitConfigFiles.init(event);
        RegistrationHandler.init(REGISTRY);
        GameRegistry.registerWorldGenerator(new OverworldOreGen(), 0);
        ExoFluids.init();
        ExoplanetBiomes.init();
        InitSolarSystems.init();
        InitPlanets.init();

        MinecraftForge.EVENT_BUS.register(new HabitableZoneClientHandler());
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

        if (!(Deobf.isDeobfuscated()) && (biomeDebug || langHelper)) {
            BiomeDebug.createFile();
            LangFileHelper.createFile();
        }

        proxy.postInit(REGISTRY, event);
    }

    @Override
    public String getModId() {
        return ExoInfo.MODID;
    }

    @Override
    public String getModName() {
        return ExoInfo.NAME;
    }

    @Override
    public String getVersion() {
        return ExoInfo.VERSION;
    }

    @Override
    public int getBuildNum() {
        return ExoInfo.BUILD;
    }

}
