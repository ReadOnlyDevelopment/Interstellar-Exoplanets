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

package net.rom.exoplanets.client;

import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.trappist1.c.SkyProviderTrappist1C;
import net.rom.exoplanets.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.rom.exoplanets.astronomy.trappist1.e.SkyProviderTrappist1E;
import net.rom.exoplanets.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.rom.exoplanets.astronomy.yzceti.b.SkyProviderB;
import net.rom.exoplanets.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzceti.c.SkyProviderC;
import net.rom.exoplanets.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.rom.exoplanets.astronomy.yzceti.d.SkyProviderD;
import net.rom.exoplanets.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.rom.exoplanets.internal.MCUtil;

public class SkyProviders {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        final Minecraft minecraft = MCUtil.getClient();
        final WorldClient world = minecraft.world;
        final EntityPlayerSP player = minecraft.player;

        if (event.phase == Phase.START && player != null && world != null) {
            // Planets
            if (world.provider instanceof WorldProviderYzCetiB) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderB());
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            if (world.provider instanceof WorldProviderYzCetiC) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderC());
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            if (world.provider instanceof WorldProviderYzCetiD) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderD());
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            if (world.provider instanceof WorldProviderTrappist1E) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderTrappist1E());
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            if (world.provider instanceof WorldProviderTrappist1C) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderTrappist1C());
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
        }
    }
}
