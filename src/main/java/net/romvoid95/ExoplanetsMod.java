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

package net.romvoid95;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;

import asmodeuscore.core.astronomy.BodiesRegistry;
import mcp.MethodsReturnNonnullByDefault;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
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
import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.api.registry.IInitialize;
import net.romvoid95.api.space.enums.EnumAtmosphereContent;
import net.romvoid95.common.ExoCommonProxy;
import net.romvoid95.common.command.CommandDownloadUpdate;
import net.romvoid95.common.command.CommandFindBiome;
import net.romvoid95.common.config.ExoConfigs;
import net.romvoid95.common.event.GuiHandlerExo;
import net.romvoid95.common.network.NetworkPipeline;
import net.romvoid95.common.utility.mc.MCUtil;
import net.romvoid95.common.utility.system.TranslateUtil;
import net.romvoid95.common.world.OverworldOreGen;
import net.romvoid95.core.ExoInfo;
import net.romvoid95.core.Logging;
import net.romvoid95.core.initialization.ExoFluids;
import net.romvoid95.core.initialization.ExoHandler;
import net.romvoid95.core.initialization.ExoRecipes;
import net.romvoid95.core.initialization.Planets;
import net.romvoid95.core.initialization.SolarSystems;
import net.romvoid95.core.initialization.WorldHandler;
import net.romvoid95.space.CelestialAssets;

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
public class ExoplanetsMod {

	@Instance(ExoInfo.MODID)
	public static ExoplanetsMod   instance;
	public static ExoRegistry     REGISTRY  = new ExoRegistry();
	public static TranslateUtil   translate = new TranslateUtil(ExoInfo.MODID);
	public static Logging         logger    = new Logging();
	@SidedProxy(clientSide = "net.romvoid95.client.ExoClientProxy", serverSide = "net.rom.common.ExoCommonProxy")
	public static ExoCommonProxy  proxy;
	public static Random          random    = new Random();
	public static NetworkPipeline network;
	public static String exoPlanetsDirectory;

	public static final boolean isDevBuild = MCUtil.isDeobfuscated();
	
	private static List<IInitialize> handlers = new ArrayList<IInitialize>() {{
		add(ExoHandler.INSTANCE);
		add(new WorldHandler());
	}};

	static {
		FluidRegistry.enableUniversalBucket();
		BodiesRegistry.setMaxTier(3);
	}

	@EventHandler
	public void preInit (FMLPreInitializationEvent event) {
		exoPlanetsDirectory = event.getModConfigurationDirectory() + "/Exoplanets/";
		REGISTRY.setMod(this);
		REGISTRY.getRecipeMaker();
		
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
		ExoplanetsMod.addEnums();
		ExoRecipes.alloySmelterRecipes();
		
		handlers.forEach(handler -> handler.postInit(event));

		proxy.postInit(REGISTRY, event);
	}

	@EventHandler
	public void onServerStarting (FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandDownloadUpdate());
		event.registerServerCommand(new CommandFindBiome());
	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		if (!MCUtil.isDeobfuscated()) {
			logger.warn("Invalid fingerprint detected! The file " + event.getSource().getName()
					+ " may have been tampered with. This version will NOT be supported by the author!");
		} else {
			logger.info("Ignoring fingerprint signing since we are in a Development Environment");
		}
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
