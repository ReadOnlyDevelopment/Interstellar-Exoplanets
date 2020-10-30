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
package net.romvoid95.common.tile;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.romvoid95.common.lib.tile.TileEntityExo;
import net.romvoid95.core.initialization.ExoBlocks;

public class TileEntityLaunchFacilitySingle extends TileEntityExo implements ITickable {
	
	private int corner = 0;

	@Override
	public void tick() {
		if (!this.world.isRemote && this.corner == 0) {
			final ArrayList<TileEntity> attachedLaunchPads = new ArrayList<TileEntity>();

			for (int x = this.getPos().getX() - 2; x < this.getPos().getX() + 3; x++) {
				for (int z = this.getPos().getZ() - 2; z < this.getPos().getZ() + 3; z++) {
					final TileEntity tile = this.world.getTileEntity(new BlockPos(x, this.getPos().getY(), z));

					if (tile instanceof TileEntityLaunchFacilitySingle && !tile.isInvalid() && ((TileEntityLaunchFacilitySingle) tile).corner == 0) {
						attachedLaunchPads.add(tile);
					}
				}
			}

			if (attachedLaunchPads.size() == 25) {
				for (final TileEntity tile : attachedLaunchPads) {
					this.world.markTileEntityForRemoval(tile);
					((TileEntityLaunchFacilitySingle) tile).corner = 1;
				}

				this.getPos().south(1);
				this.getPos().east(1);

				this.world.setBlockState(this.getPos(), ExoBlocks.launch_facility_full.getDefaultState(), 2);
			}
		}
	}

}
