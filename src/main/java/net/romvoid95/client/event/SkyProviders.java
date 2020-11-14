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

import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.ExoInfo;
import net.romvoid95.api.world.ExoRainRenderer;
import net.romvoid95.common.config.ConfigPlanets;
import net.romvoid95.space.kepler1649.b.SkyProviderKepler1649B;
import net.romvoid95.space.kepler1649.b.WorldProviderKepler1649B;
import net.romvoid95.space.kepler1649.c.SkyProviderKepler1649c;
import net.romvoid95.space.trappist1.c.SkyProviderTrappist1C;
import net.romvoid95.space.trappist1.d.SkyProviderTrappist1D;
import net.romvoid95.space.trappist1.d.client.CloudProviderTrappist1D;
import net.romvoid95.space.trappist1.e.SkyProviderTrappist1E;
import net.romvoid95.space.wolf1061.d.CloudProviderWolf1061D;
import net.romvoid95.space.wolf1061.d.SkyProviderWolf1061D;
import net.romvoid95.space.yzceti.b.SkyProviderYzCetiB;
import net.romvoid95.space.yzceti.c.SkyProviderYzCetiC;
import net.romvoid95.space.yzceti.d.SkyProviderYzCetiD;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public class SkyProviders {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event) {
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final WorldClient world = minecraft.world;
		final EntityPlayerSP player = minecraft.player;

		if (event.phase == Phase.START && player != null && world != null) {
			// Planets

			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_yz_b) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderYzCetiB());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_yz_c) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderYzCetiC());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_yz_d) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderYzCetiD());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_trap_e) {
				
					world.provider.setSkyRenderer(new SkyProviderTrappist1E());
				

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}

			}
			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_trap_c) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderTrappist1C());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_kepler_c) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderKepler1649c());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_trap_d) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderTrappist1D());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudProviderTrappist1D());
				}
				if(world.provider.getWeatherRenderer() == null) {
					world.provider.setWeatherRenderer(new ExoRainRenderer());
				}

			}
			if (world.provider.getDimensionType().getId() == ConfigPlanets.id_wolf_d) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderWolf1061D());
				}
				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudProviderWolf1061D());
				}
			}
			
			if (world.provider instanceof WorldProviderKepler1649B) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderKepler1649B());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
		}
	}

}
