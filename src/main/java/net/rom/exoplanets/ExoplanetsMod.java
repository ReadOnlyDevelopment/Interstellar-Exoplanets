/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
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

package net.rom.exoplanets;

import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;

import asmodeuscore.core.astronomy.BodiesRegistry;
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
import net.rom.exoplanets.init.ExoFluids;
import net.rom.exoplanets.init.ExoRecipes;
import net.rom.exoplanets.init.Planets;
import net.rom.exoplanets.init.RegistrationHandler;
import net.rom.exoplanets.init.Researching;
import net.rom.exoplanets.init.SolarSystems;
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
    
    public static final boolean isDevBuild = true;
    
	public static final Researches RESEARCH;

    static {
    	FluidRegistry.enableUniversalBucket();
    	RESEARCH = new Researches();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        REGISTRY.setMod(this);
        REGISTRY.getRecipeMaker();
        BodiesRegistry.setMaxTier(3);
        
        // CONFIGS
        InitConfigFiles.init(event);
        ExoFluids.init();
        // BLOCKS, ITEMS, ENTITIES, ETC
        RegistrationHandler.init(REGISTRY);
        
        // TEXTURES
        CelestialAssets.init();
        
        // OVERWORLD ORE GEN
        GameRegistry.registerWorldGenerator(new OverworldOreGen(), 0);
        
        // FLUIDS , NEED TO REDO THIS
        //ExoFluids.init();
        
        ExoplanetBiomes.init();
        SolarSystems.init();
        Planets.init();	
        
        // PLANETS
        ExoplanetBiomes.init();

        
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
