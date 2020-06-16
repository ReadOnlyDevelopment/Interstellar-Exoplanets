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

package net.rom.exoplanets.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.client.gui.GuiAlloyRefinery;
import net.rom.exoplanets.client.gui.GuiMetalFurnace;
import net.rom.exoplanets.content.block.machine.TileAlloyRefinery;
import net.rom.exoplanets.content.block.machine.TileMetalFurnace;
import net.rom.exoplanets.content.block.machine.container.ContainerAlloyRefinery;
import net.rom.exoplanets.content.block.machine.container.ContainerMetalFurnace;

public class GuiHandlerExo implements IGuiHandler {
	public static final int ID_METAL_FURNACE = 0;
	public static final int ID_ALLOY_SMELTER = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
			case ID_METAL_FURNACE:
				if (tile instanceof TileMetalFurnace) {
					TileMetalFurnace tileFurnace = (TileMetalFurnace) tile;
					return new ContainerMetalFurnace(player.inventory, tileFurnace);
				}
				return null;
			case ID_ALLOY_SMELTER:
				if (tile instanceof TileAlloyRefinery) {
					TileAlloyRefinery tileSmelter = (TileAlloyRefinery) tile;
					return new ContainerAlloyRefinery(player.inventory, tileSmelter);
				}
				return null;
			default:
				ExoplanetsMod.logger.formatted_Warn("No GUI with ID {}!", ID);
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
			case ID_METAL_FURNACE:
				if (tile instanceof TileMetalFurnace) {
					TileMetalFurnace tileFurnace = (TileMetalFurnace) tile;
					return new GuiMetalFurnace(player.inventory, tileFurnace);
				}
				return null;
			case ID_ALLOY_SMELTER:
				if (tile instanceof TileAlloyRefinery) {
					TileAlloyRefinery tileSmelter = (TileAlloyRefinery) tile;
					return new GuiAlloyRefinery(player.inventory, tileSmelter);
				}
				return null;
			default:
				ExoplanetsMod.logger.formatted_Warn("No GUI with ID {}!", ID);
				return null;
		}
	}

}
