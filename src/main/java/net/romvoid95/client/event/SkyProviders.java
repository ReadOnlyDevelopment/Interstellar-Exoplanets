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

package net.romvoid95.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.core.client.CloudRenderer;

import net.romvoid95.common.astronomy.kepler1649.c.SkyProviderKepler1649c;
import net.romvoid95.common.astronomy.kepler1649.c.WorldProviderKepler1649c;
import net.romvoid95.common.astronomy.trappist1.c.SkyProviderTrappist1C;
import net.romvoid95.common.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.romvoid95.common.astronomy.trappist1.d.SkyProviderTrappist1D;
import net.romvoid95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.romvoid95.common.astronomy.trappist1.d.client.CloudProviderTrappist1D;
import net.romvoid95.common.astronomy.trappist1.d.client.WeatherRendererTrappistD;
import net.romvoid95.common.astronomy.trappist1.e.SkyProviderTrappist1E;
import net.romvoid95.common.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.romvoid95.common.astronomy.wolf1061.d.*;
import net.romvoid95.common.astronomy.yzceti.b.SkyProviderB;
import net.romvoid95.common.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.romvoid95.common.astronomy.yzceti.c.SkyProviderC;
import net.romvoid95.common.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.romvoid95.common.astronomy.yzceti.d.SkyProviderD;
import net.romvoid95.common.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.romvoid95.common.utility.mc.MCUtil;
import net.romvoid95.core.ExoInfo;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public class SkyProviders {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientTick (ClientTickEvent event) {
		final Minecraft      minecraft = MCUtil.getClient();
		final WorldClient    world     = minecraft.world;
		final EntityPlayerSP player    = minecraft.player;

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
			if (world.provider instanceof WorldProviderTrappist1D) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderTrappist1D());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudProviderTrappist1D());
				}

				if (world.provider.getWeatherRenderer() == null) {
					world.provider.setWeatherRenderer(new WeatherRendererTrappistD());
				}
			}
			if (world.provider instanceof WorldProviderWolf1061D) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderWolf1061D());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudProviderWolf1061D());
				}
			}
		}
	}
}
