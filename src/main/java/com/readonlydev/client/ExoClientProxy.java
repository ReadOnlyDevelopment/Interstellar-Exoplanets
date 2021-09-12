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

package com.readonlydev.client;

import javax.annotation.Nullable;

import com.readonlydev.ExoInfo;
import com.readonlydev.api.proxy.IProxy;
import com.readonlydev.client.event.ClientEventHandler;
import com.readonlydev.client.event.SkyProviders;
import com.readonlydev.client.model.RocketModelLoader;
import com.readonlydev.client.render.RocketRenderer;
import com.readonlydev.common.entity.EntityTwoPlayerRocket;
import com.readonlydev.common.version.VersionChecker;
import com.readonlydev.core.ExoItems;
import com.readonlydev.lib.registry.InterstellarRegistry;
import com.readonlydev.space.trappist1.d.client.TickHandlerClientTrappistD;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ExoClientProxy implements IProxy {
	
	private final Minecraft MINECRAFT = Minecraft.getMinecraft();

	@Override
	public void preInit(InterstellarRegistry registry, FMLPreInitializationEvent event) {
		registry.clientPreInit(event);
		
		ModelLoaderRegistry.registerLoader(RocketModelLoader.instance);
		RocketModelLoader.instance.addDomain(ExoInfo.MODID);
		register_event(this);

		RenderingRegistry.registerEntityRenderingHandler(EntityTwoPlayerRocket.class, RocketRenderer::new);
		registerTextureAssets();
		register_event(new ClientEventHandler());
		register_event(new SkyProviders());
		register_event(new TickHandlerClientTrappistD());
	}

	@Override
	public void init(InterstellarRegistry registry, FMLInitializationEvent event) {
		registry.clientInit(event);
		VersionChecker.init();
	}

	@Override
	public void postInit(InterstellarRegistry registry, FMLPostInitializationEvent event) {		
		registry.clientPostInit(event);
	}

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

	public void register_event(Object obj) {
		MinecraftForge.EVENT_BUS.register(obj);
	}

	public void registerVariants() {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation("exoplanets:twopersonrocket", "inventory");
		ModelLoader.setCustomModelResourceLocation(ExoItems.passengerRocket, 0, modelResourceLocation);

	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		registerVariants();
	}

	@Nullable
	@Override
	public EntityPlayer getClientPlayer() {
		return MINECRAFT.player;
	}

	@Nullable
	@Override
	public World getClientWorld() {
		return MINECRAFT.world;
	}

	@Override
	public IThreadListener getThreadListener(final MessageContext context) {
		if (context.side.isClient()) {
			return MINECRAFT;
		} else {
			return context.getServerHandler().player.mcServer;
		}
	}

	@Override
	public EntityPlayer getPlayer(final MessageContext context) {
		if (context.side.isClient()) {
			return MINECRAFT.player;
		} else {
			return context.getServerHandler().player;
		}
	}
}
