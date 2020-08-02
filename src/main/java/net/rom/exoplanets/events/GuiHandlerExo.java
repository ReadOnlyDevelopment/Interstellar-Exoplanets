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

import micdoodle8.mods.galacticraft.core.tile.TileEntityAirLockController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.client.gui.GuiAlloyRefinery;
import net.rom.exoplanets.client.gui.GuiMetalFurnace;
import net.rom.exoplanets.content.block.machine.container.ContainerAlloyRefinery;
import net.rom.exoplanets.content.block.machine.container.ContainerMetalFurnace;
import net.rom.exoplanets.content.tile.TileEntityAlloyRefinery;
import net.rom.exoplanets.content.tile.TileEntityMetalFurnace;

public class GuiHandlerExo implements IGuiHandler {
	public static final int ID_METAL_FURNACE = 0;
	public static final int ID_ALLOY_SMELTER = 1;
	public static final int ID_AVD_AIR_LOCK  = 2;

	@Override
	public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos   pos  = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case ID_METAL_FURNACE:
			if (tile instanceof TileEntityMetalFurnace) {
				TileEntityMetalFurnace tileFurnace = (TileEntityMetalFurnace) tile;
				return new ContainerMetalFurnace(player.inventory, tileFurnace);
			}
			return null;
		case ID_ALLOY_SMELTER:
			if (tile instanceof TileEntityAlloyRefinery) {
				TileEntityAlloyRefinery tileSmelter = (TileEntityAlloyRefinery) tile;
				return new ContainerAlloyRefinery(player.inventory, tileSmelter);
			}
			return null;
		case ID_AVD_AIR_LOCK:
			if (tile instanceof TileEntityAirLockController) {
				return new TileEntityAirLockController();
			}
			return null;
		default:
			ExoplanetsMod.logger.warn("No GUI with ID {}!", ID);
			return null;
		}
	}

	@Override
	public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos   pos  = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case ID_METAL_FURNACE:
			if (tile instanceof TileEntityMetalFurnace) {
				TileEntityMetalFurnace tileFurnace = (TileEntityMetalFurnace) tile;
				return new GuiMetalFurnace(player.inventory, tileFurnace);
			}
			return null;
		case ID_ALLOY_SMELTER:
			if (tile instanceof TileEntityAlloyRefinery) {
				TileEntityAlloyRefinery tileSmelter = (TileEntityAlloyRefinery) tile;
				return new GuiAlloyRefinery(player.inventory, tileSmelter);
			}
			return null;
		case ID_AVD_AIR_LOCK:
			if (tile instanceof TileEntityAirLockController) {
				return new TileEntityAirLockController();
			}
			return null;
		default:
			ExoplanetsMod.logger.warn("No GUI with ID {}!", ID);
			return null;
		}
	}

}
