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

package net.rom.exoplanets.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rom.exoplanets.Assets;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.client.render.BlockHandler;
import net.rom.exoplanets.client.render.RocketRenderer;
import net.rom.exoplanets.content.entity.EntityTwoPlayerRocket;
import net.rom.exoplanets.events.BetaGuiHandler;
import net.rom.exoplanets.events.ClientHandler;
import net.rom.exoplanets.events.SkyProviders;
import net.rom.exoplanets.init.ExoFluids;
import net.rom.exoplanets.init.ExoItems;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.internal.client.ExoModelLoader;

public class ExoClientProxy extends ExoCommonProxy {

	@Override
	public void preInit(StellarRegistry registry, FMLPreInitializationEvent event) {
		super.preInit(registry, event);
		ModelLoaderRegistry.registerLoader(ExoModelLoader.instance);
		ExoModelLoader.instance.addDomain(ExoInfo.MODID);
		registerEventHandler(this);

		registerVarients();
		RenderingRegistry.registerEntityRenderingHandler(EntityTwoPlayerRocket.class,
				(RenderManager manager) -> new RocketRenderer(manager));
		registerEventHandler(new BetaGuiHandler());
		registerEventHandler(new ClientHandler());
		registerEventHandler(new BlockHandler());
		
		registry.clientPreInit(event);
		ExoFluids.bakeModels();

	}

	@Override
	public void init(StellarRegistry registry, FMLInitializationEvent event) {
		super.init(registry, event);
		registerEventHandler(new SkyProviders());
		registry.clientInit(event);
	}

	@Override
	public void postInit(StellarRegistry registry, FMLPostInitializationEvent event) {
		super.postInit(registry, event);
		registerTextureAssets();
		registry.clientPostInit(event);
	}

	@Override
	public void registerTextureAssets() {
		Assets.addTexture("GuiDiscordButton", "textures/gui/discord.png");
		Assets.addTexture("GuiBetaBackground", "textures/gui/teleport.png");
		Assets.addTexture("tdeco", "textures/gui/container/tab_decoration.png");
		Assets.addTexture("tterrain", "textures/gui/container/tab_terrain.png");
		Assets.addTexture("titems", "textures/gui/container/tab_decoration.png");
	}

	public World getWorld() {
		return Minecraft.getMinecraft().world;
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}

	public static void registerEventHandler(Object handler) {
		MinecraftForge.EVENT_BUS.register(handler);
	}

	public void registerVarients() {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation("exoplanets:twopersonrocket",
				"inventory");
		for (int i = 0; i < 5; ++i) {
			ModelLoader.setCustomModelResourceLocation(ExoItems.passengerRocket, i, modelResourceLocation);
		}
	}
}
