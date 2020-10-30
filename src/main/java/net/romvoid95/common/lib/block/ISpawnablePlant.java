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
package net.romvoid95.common.lib.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public interface ISpawnablePlant extends IPlantable {
	/**
	 * @param world
	 * @param pos
	 * @param state
	 * @return
	 */
	default boolean canGeneratePlant (World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this);
	}

	/**
	 * @param world
	 * @param pos
	 * @param state
	 * @return
	 */
	static boolean canGenerate (World world, BlockPos pos, IBlockState state) {
		if (state.getBlock() instanceof ISpawnablePlant) {
			return ((ISpawnablePlant) state.getBlock()).canGeneratePlant(world, pos, state);
		}
		return false;
	}
}
