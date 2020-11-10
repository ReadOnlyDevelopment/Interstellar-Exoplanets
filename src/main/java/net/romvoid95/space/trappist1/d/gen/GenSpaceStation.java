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
package net.romvoid95.space.trappist1.d.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.romvoid95.ExoplanetsMod;

public class GenSpaceStation extends WorldGenerator {

	private IBlockState block;

	public GenSpaceStation(Block block) {
		this.block = block.getDefaultState();
	}

	@Override
	public boolean generate (World world, Random rand, BlockPos position) {
//		if (GenUtility.checkValidSpawn(world, position, 10) == false)
			return false;
//		else {
//			ExoplanetsMod.logger.info("Spawning at {}", position.toString());
//			generateStructure(world, rand, position);
//		}
//		return true;
	}

	public boolean generateStructure (World world, Random rand, BlockPos position) {
		int                       size = 20;
		int                       x    = position.getX();
		int                       y    = position.getY();
		int                       z    = position.getZ();
		Iterable<MutableBlockPos> area = BlockPos.getAllInBoxMutable(x, y, z, x + size, y, z - size);

		for (BlockPos pos : area) {
			ExoplanetsMod.logger.info("Position: {}", pos.toString());
			int xx = pos.getX();
			int yy = pos.getY();
			int zz = pos.getZ();
			world.setBlockState(new BlockPos(xx, yy, zz), this.block, 3);
			return true;
		}
		return true;
	}

}
