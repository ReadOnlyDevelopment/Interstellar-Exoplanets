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

package net.rom.exoplanets;

import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;

import asmodeuscore.core.astronomy.BodiesHelper;
import mcp.MethodsReturnNonnullByDefault;
import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.rom.api.research.Researches;
import net.rom.exoplanets.astronomy.CelestialAssets;
import net.rom.exoplanets.astronomy.ExoDimensions;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.command.CommandData;
import net.rom.exoplanets.conf.InitConfigFiles;
import net.rom.exoplanets.events.GuiHandlerExo;
import net.rom.exoplanets.events.HabitableZoneClientHandler;
import net.rom.exoplanets.init.ExoRecipes;
import net.rom.exoplanets.init.IniSystems;
import net.rom.exoplanets.init.InitPlanets;
import net.rom.exoplanets.init.RegistrationHandler;
import net.rom.exoplanets.init.Researching;
import net.rom.exoplanets.internal.LogHelper;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.internal.inerf.IMod;
import net.rom.exoplanets.proxy.ExoCommonProxy;
import net.rom.exoplanets.util.TranslateUtil;
import net.rom.exoplanets.world.OverworldOreGen;

@Mod(modid = ExoInfo.MODID, name = ExoInfo.NAME, version = ExoInfo.FULL_VERSION, dependencies = ExoInfo.DEPENDENCIES_MODS, acceptedMinecraftVersions = ExoInfo.ACCEPTED_MC_VERSION, guiFactory = "net.rom.exoplanets.client.screen.ExoplanetsConfigGuiFactory", certificateFingerprint = "@FINGERPRINT@")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExoplanetsMod implements IMod {

    @Instance(ExoInfo.MODID)
    public static ExoplanetsMod instance;
    public static StellarRegistry REGISTRY = new StellarRegistry();
    public static TranslateUtil translate = new TranslateUtil(ExoInfo.MODID);
    public static LogHelper logger = new LogHelper();
    public static ResourceLocation location = new ResourceLocation(ExoInfo.MODID);
    @SidedProxy(clientSide = "net.rom.exoplanets.proxy.ExoClientProxy", serverSide = "net.rom.exoplanets.proxy.ExoCommonProxy")
    public static ExoCommonProxy proxy;
    public static Random random = new Random();
    
	public static final Researches RESEARCH;

    static {
    	FluidRegistry.enableUniversalBucket();
    	RESEARCH = new Researches();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        REGISTRY.setMod(this);
        REGISTRY.getRecipeMaker();
        BodiesHelper.max_tier = 3;

        // CONFIGS
        InitConfigFiles.init(event);
        
        // BLOCKS, ITEMS, ENTITIES, ETC
        RegistrationHandler.init(REGISTRY);
        
        // TEXTURES
        CelestialAssets.init();
        
        // OVERWORLD ORE GEN
        GameRegistry.registerWorldGenerator(new OverworldOreGen(), 0);
        
        // FLUIDS , NEED TO REDO THIS
        //ExoFluids.init();
        
        // PLANETS
        ExoplanetBiomes.init();
        IniSystems.init();
        InitPlanets.init();
        
        // RESEARCH SYSTEM
        Researching.register(RESEARCH);
        
        // GUI STUFF
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerExo());
        MinecraftForge.EVENT_BUS.register(new HabitableZoneClientHandler());
        proxy.preInit(REGISTRY, event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
    	
    	proxy.registerRender();
    	//ExoVillagerHandler.initVillageAstronomerHouse();
    	//ExoVillagerHandler.initAstronomerVillagerTrades();
        for (BiomeGenBaseGC biome : ExoplanetBiomes.biomeList) {
            biome.registerTypes(biome);
        }
        proxy.init(REGISTRY, event);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        ExoDimensions.init();
        ExoRecipes.alloySmelterRecipes();
        //proxy.registerTextureAssets();
        proxy.postInit(REGISTRY, event);
    }
    
    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandData());
    }
    
    @EventHandler
    public void onFingerprintViolation (FMLFingerprintViolationEvent event) {

        logger.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
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
    public String getBuildNum() {
        return ExoInfo.BUILD;
    }

}
