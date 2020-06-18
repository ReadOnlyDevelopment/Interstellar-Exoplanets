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
import net.rom.exoplanets.astronomy.CelestialAssets;
import net.rom.exoplanets.client.render.BlockHandler;
import net.rom.exoplanets.client.render.RocketRenderer;
import net.rom.exoplanets.content.entity.EntityTwoPlayerRocket;
import net.rom.exoplanets.events.ClientHandler;
import net.rom.exoplanets.events.GuiScreenHandler;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityTwoPlayerRocket.class, (RenderManager manager) -> new RocketRenderer(manager));
		ExoFluids.bakeModels();
		registerEventHandler(new GuiScreenHandler());
		registerEventHandler(new ClientHandler());
		registerEventHandler(new BlockHandler());
		registry.clientPreInit(event);

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
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation("exoplanets:twopersonrocket", "inventory");
        for (int i = 0; i < 5; ++i)
        {
            ModelLoader.setCustomModelResourceLocation(ExoItems.passengerRocket, i, modelResourceLocation);
        }
    }
}
