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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rom.exoplanets.event.client.handlers.GuiScreenHandler;
import net.rom.exoplanets.event.client.handlers.SkyProviders;
import net.rom.exoplanets.init.ExoFluids;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoClientProxy extends ExoCommonProxy {


	@Override
	public void preInit(StellarRegistry registry, FMLPreInitializationEvent event) {
		super.preInit(registry, event);

		ExoFluids.bakeModels();
		registerEventHandler(new GuiScreenHandler());

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

		registry.clientPostInit(event);
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
}
