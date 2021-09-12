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

package com.readonlydev;

import javax.annotation.ParametersAreNonnullByDefault;

import com.readonlydev.api.log.ILog;
import com.readonlydev.api.space.enums.EnumAtmosphereContent;
import com.readonlydev.common.ExoCommonProxy;
import com.readonlydev.common.command.CommandDownloadUpdate;
import com.readonlydev.common.command.CommandFindBiome;
import com.readonlydev.common.config.ExoConfigs;
import com.readonlydev.common.config.ExoStarSystemConfig;
import com.readonlydev.common.config.ExoplanetConfig;
import com.readonlydev.common.event.GuiHandlerExo;
import com.readonlydev.common.network.NetworkPipeline;
import com.readonlydev.common.utility.TranslateUtil;
import com.readonlydev.common.world.OverworldOreGen;
import com.readonlydev.core.ExoFluids;
import com.readonlydev.core.ExoRecipes;
import com.readonlydev.core.Planets;
import com.readonlydev.core.registries.SolarSystems;
import com.readonlydev.lib.base.InterstellarMod;
import com.readonlydev.lib.exception.InvalidFingerprintException;
import com.readonlydev.lib.utils.system.Log;
import com.readonlydev.space.CelestialAssets;

import mcp.MethodsReturnNonnullByDefault;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.util.EnumHelper;
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

@Mod(
		modid = ExoInfo.MODID,
		name = ExoInfo.NAME,
		version = ExoInfo.FULL_VERSION,
		dependencies = ExoInfo.DEPENDENCIES_MODS,
		acceptedMinecraftVersions = ExoInfo.ACCEPTED_MC_VERSION,
		guiFactory = "net.romvoid95.client.gui.screen.ExoplanetsConfigGuiFactory",
		certificateFingerprint = ExoInfo.FINGERPRINT,
		useMetadata = true)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Exoplanets extends InterstellarMod {

	@Mod.Instance(owner = ExoInfo.MODID)
	public static Exoplanets   _instance;
	public static ILog log;
	public static TranslateUtil   translate = new TranslateUtil(ExoInfo.MODID);
	@SidedProxy(clientSide = "net.romvoid95.client.ExoClientProxy", serverSide = "net.rom.common.ExoCommonProxy")
	public static ExoCommonProxy  proxy;
	public static NetworkPipeline network;
	
	public Exoplanets() {
		super(Exoplanets.class);
		log = new Log(this, Integer.valueOf(ExoInfo.BUILD));
		FluidRegistry.enableUniversalBucket();
		configFiles(new ExoplanetConfig(), new ExoStarSystemConfig());
	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		if ((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
			log.info("Ignoring fingerprint signing since we are in a Development Environment");
			return;
		} else {
			throw new InvalidFingerprintException(event);
		}
	}
	
	@EventHandler
	@Override
	public void onPreInit(FMLPreInitializationEvent event) {
		super.onPreInit(event);
		
		proxy.preInit(registry, event);
	}

	@EventHandler
	public void preInit (FMLPreInitializationEvent event) {
		exoPlanetsDirectory = event.getModConfigurationDirectory() + "/Exoplanets/";
		registry.setMod(this);
		registry.getRecipeBuilder();
		
		// CONFIG
		ExoConfigs.init();
		handlers.forEach(handler -> handler.preInit(event));
		
		ExoFluids.initFluids();
		CelestialAssets.init();
		SolarSystems.init();
		Planets.init();

		// GUI STUFF
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerExo());
		proxy.preInit(REGISTRY, event);
	}

	@EventHandler
	public static void init (FMLInitializationEvent event) {
		
		// OVERWORLD ORE GEN
		GameRegistry.registerWorldGenerator(new OverworldOreGen(), 0);

		proxy.registerRender();
		
		handlers.forEach(handler -> handler.init(event));
		
		//ExoVillagerHandler.initVillageAstronomerHouse();
		//ExoVillagerHandler.initAstronomerVillagerTrades();
		proxy.init(REGISTRY, event);
	}

	@EventHandler
	public static void postInit (FMLPostInitializationEvent event) {
		Exoplanets.addEnums();
		ExoRecipes.alloySmelterRecipes();
		
		handlers.forEach(handler -> handler.postInit(event));

		proxy.postInit(REGISTRY, event);
	}

	@EventHandler
	public void onServerStarting (FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandDownloadUpdate());
		event.registerServerCommand(new CommandFindBiome());
	}

	private static void addEnums () {
		for (EnumAtmosphereContent e : EnumAtmosphereContent.values()) {
			if (e.add()) {
				EnumHelper.addEnum(EnumAtmosphericGas.class, e.getName(), new Class<?>[] {});
			}
		}
	}

	public boolean isDev () {
		return isDevBuild;
	}

	public String getModId () {
		return ExoInfo.MODID;
	}

	public String getModName () {
		return ExoInfo.NAME;
	}

}
