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

package net.romvoid95.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.client.event.ClientEventHandler;
import net.romvoid95.client.event.SkyProviders;
import net.romvoid95.client.model.RocketModelLoader;
import net.romvoid95.client.render.RocketRenderer;
import net.romvoid95.common.ExoCommonProxy;
import net.romvoid95.common.entity.EntityTwoPlayerRocket;
import net.romvoid95.common.utility.mc.FluidUtility;
import net.romvoid95.common.version.VersionChecker;
import net.romvoid95.core.ExoInfo;
import net.romvoid95.core.initialization.ExoFluids;
import net.romvoid95.core.initialization.ExoItems;

public class ExoClientProxy extends ExoCommonProxy {

	public static double	playerPosX;
	public static double	playerPosY;
	public static double	playerPosZ;

	@Override
	public void preInit(ExoRegistry registry, FMLPreInitializationEvent event) {
		super.preInit(registry, event);
		ModelLoaderRegistry.registerLoader(RocketModelLoader.instance);
		RocketModelLoader.instance.addDomain(ExoInfo.MODID);
		register_event(this);

		RenderingRegistry.registerEntityRenderingHandler(EntityTwoPlayerRocket.class,
				(RenderManager manager) -> new RocketRenderer(manager));
		register_event(new ClientEventHandler());

		registry.clientPreInit(event);

	}

	@Override
	public void init(ExoRegistry registry, FMLInitializationEvent event) {
		super.init(registry, event);
		register_event(new SkyProviders());
		// register_event(new TickHandlerClientTrappistD());
		VersionChecker.init();
		registry.clientInit(event);
	}

	@Override
	public void postInit(ExoRegistry registry, FMLPostInitializationEvent event) {
		super.postInit(registry, event);
		registerTextureAssets();
		registry.clientPostInit(event);
	}

	@Override
	public void registerTextureAssets() {
		Assets.addTexture("GuiDiscordButton", "textures/gui/discord.png");
		Assets.addTexture("GuiBetaBackground", "textures/gui/teleport.png");
		Assets.addTexture("tabDeco", "textures/gui/container/tab_decoration.png");
		Assets.addTexture("tabTerrain", "textures/gui/container/tab_terrain.png");
		Assets.addTexture("tabItems", "textures/gui/container/tab_decoration.png");
		Assets.addTexture("tabFluids", "textures/gui/container/tab_decoration.png");
		Assets.addTexture("heavyrain", "textures/enviroment/heavyrain.png");
	}

	public World getWorld() {
		return Minecraft.getMinecraft().world;
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}

	@Override
	public void registerFluidVariants() {
		FluidUtility.registerFluidVariant(ExoInfo.RESOURCE_PREFIX + "pressured_water", ExoFluids.PRESSURED_WATER);
	}

	@Override
	public void register_event(Object obj) {
		MinecraftForge.EVENT_BUS.register(obj);
	}

	@Override
	public void registerVariants() {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation("exoplanets:twopersonrocket", "inventory");
		ModelLoader.setCustomModelResourceLocation(ExoItems.passengerRocket, 0, modelResourceLocation);

	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		registerFluidVariants();
		registerVariants();
	}

	public static class EventSpecialRender extends Event {
		public final float partialTicks;

		public EventSpecialRender (float partialTicks) {
			this.partialTicks = partialTicks;
		}
	}
}
