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

package com.readonlydev.common;

import javax.annotation.Nullable;

import com.readonlydev.api.proxy.IProxy;
import com.readonlydev.api.proxy.IProxy.WrongSideException;
import com.readonlydev.lib.registry.InterstellarRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ExoCommonProxy implements IProxy {

	@Override
	public void preInit(InterstellarRegistry registry, FMLPreInitializationEvent event) {
		registry.preInit(event);
	}

	@Override
	public void init(InterstellarRegistry registry, FMLInitializationEvent event) {
		registry.init(event);
	}

	@Override
	public void postInit(InterstellarRegistry registry, FMLPostInitializationEvent event) {
		registry.postInit(event);
	}

	@Nullable
	@Override
	public EntityPlayer getClientPlayer() {
		throw new WrongSideException("Tried to get the client player on the dedicated server");
	}

	@Nullable
	@Override
	public World getClientWorld() {
		throw new WrongSideException("Tried to get the client world on the dedicated server");
	}

	@Override
	public IThreadListener getThreadListener(final MessageContext context) {
		if (context.side.isServer()) {
			return context.getServerHandler().player.mcServer;
		} else {
			throw new WrongSideException(
					"Tried to get the IThreadListener from a client-side MessageContext on the dedicated server");
		}
	}

	@Override
	public EntityPlayer getPlayer(final MessageContext context) {
		if (context.side.isServer()) {
			return context.getServerHandler().player;
		} else {
			throw new WrongSideException(
					"Tried to get the player from a client-side MessageContext on the dedicated server");
		}
	}
}
