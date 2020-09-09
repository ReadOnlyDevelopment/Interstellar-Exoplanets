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

package net.romvoid95.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.common.lib.interfaces.IProxy;

public class ExoCommonProxy implements IProxy {

	@Override
	public void preInit (ExoRegistry registry, FMLPreInitializationEvent event) {
		registry.preInit(event);
	}

	@Override
	public void init (ExoRegistry registry, FMLInitializationEvent event) {
		registry.init(event);

	}

	@Override
	public void postInit (ExoRegistry registry, FMLPostInitializationEvent event) {
		registry.postInit(event);

	}

	@Override
	public EntityPlayer getClientPlayer () {
		return null;
	}

	public void register_event (Object obj) {
		MinecraftForge.EVENT_BUS.register(obj);
	}

	public EntityPlayer getPlayerFromNetHandler (INetHandler handler) {
		if (handler instanceof NetHandlerPlayServer) {
			return ((NetHandlerPlayServer) handler).player;
		}
		else {
			return null;
		}
	}

	public void registerRender () {}

	public void registerFluidVariants () {}

	public void registerTexture (Pre event, String texture) {}

	public void registerItemRenderer (Item item, int meta, String id) {}

	public void registerVariants () {}

	public void registerModels () {}

	public void registerTextureAssets () {}

}
