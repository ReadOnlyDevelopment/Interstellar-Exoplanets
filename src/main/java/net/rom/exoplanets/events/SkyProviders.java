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

package net.rom.exoplanets.events;

import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.kepler1649.c.SkyProviderKepler1649c;
import net.rom.exoplanets.astronomy.kepler1649.c.WorldProviderKepler1649c;
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
            if (world.provider instanceof WorldProviderKepler1649c) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderKepler1649c());
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
        }
    }
}
