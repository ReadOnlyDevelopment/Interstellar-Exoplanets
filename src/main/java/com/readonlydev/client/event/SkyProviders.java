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

package com.readonlydev.client.event;

import com.readonlydev.ExoInfo;
import com.readonlydev.common.config.ExoplanetConfig;
import com.readonlydev.space.kepler1649.b.sky.SkyProviderKepler1649B;
import com.readonlydev.space.kepler1649.c.SkyProviderKepler1649c;
import com.readonlydev.space.trappist1.c.SkyProviderTrappist1C;
import com.readonlydev.space.trappist1.d.SkyProviderTrappist1D;
import com.readonlydev.space.trappist1.e.SkyProviderTrappist1E;
import com.readonlydev.space.wolf1061.d.SkyProviderWolf1061D;
import com.readonlydev.space.yzceti.b.SkyProviderYzCetiB;
import com.readonlydev.space.yzceti.c.SkyProviderYzCetiC;
import com.readonlydev.space.yzceti.d.SkyProviderYzCetiD;

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

			if (world.provider.getDimensionType().getId() == ExoplanetConfig.KEPLER1649_B_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderKepler1649B());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.KEPLER1649_C_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderKepler1649c());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.TRAPPIST1_B_ID.get()) {
//				if (world.provider.getSkyRenderer() == null) {
//					world.provider.setSkyRenderer(new SkyProviderTrappist1B());
//				}
//
//				if (world.provider.getCloudRenderer() == null) {
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.TRAPPIST1_C_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderTrappist1C());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.TRAPPIST1_D_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderTrappist1D());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.TRAPPIST1_E_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderTrappist1E());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.TRAPPIST1_F_ID.get()) {
//				if (world.provider.getSkyRenderer() == null) {
//					world.provider.setSkyRenderer(new SkyProviderTrappist1F());
//				}
//
//				if (world.provider.getCloudRenderer() == null) {
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.TRAPPIST1_G_ID.get()) {
//				if (world.provider.getSkyRenderer() == null) {
//					world.provider.setSkyRenderer(new SkyProviderTrappist1G());
//				}
//
//				if (world.provider.getCloudRenderer() == null) {
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.TRAPPIST1_H_ID.get()) {
//				if (world.provider.getSkyRenderer() == null) {
//					world.provider.setSkyRenderer(new SkyProviderTrappist1H());
//				}
//
//				if (world.provider.getCloudRenderer() == null) {
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.WOLF1061_B_ID.get()) {
//				if (world.provider.getSkyRenderer() == null) {
//					world.provider.setSkyRenderer(new SkyProviderWolf1061B());
//				}
//
//				if (world.provider.getCloudRenderer() == null) {
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.WOLF1061_C_ID.get()) {
//				if (world.provider.getSkyRenderer() == null) {
//					world.provider.setSkyRenderer(new SkyProviderWolf1061C());
//				}
//
//				if (world.provider.getCloudRenderer() == null) {
//					world.provider.setCloudRenderer(new CloudRenderer());
//				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.WOLF1061_D_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderWolf1061D());
				}

				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.YZCETI_B_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderYzCetiB());
				}
				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.YZCETI_C_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderYzCetiC());
				}
				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
			if (world.provider.getDimensionType().getId() == ExoplanetConfig.YZCETI_D_ID.get()) {
				if (world.provider.getSkyRenderer() == null) {
					world.provider.setSkyRenderer(new SkyProviderYzCetiD());
				}
				if (world.provider.getCloudRenderer() == null) {
					world.provider.setCloudRenderer(new CloudRenderer());
				}
			}
		}
	}
}
