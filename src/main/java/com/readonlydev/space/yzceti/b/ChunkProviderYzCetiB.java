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

package com.readonlydev.space.yzceti.b;

import java.util.Random;

import com.readonlydev.space.generation.chunkgen.ExoChunkProvider;
import com.readonlydev.space.yzceti.YzCetiBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkProviderYzCetiB extends ExoChunkProvider {

	public ChunkProviderYzCetiB (World world, long seed, boolean flag) {
		super(world, seed, flag);
	}

	@Override
	protected void decoratePlanet(World world, Random rand, int x, int z) {}

	@Override
	public void onChunkProvide (int cX, int cZ, ChunkPrimer primer) {
	}

	@Override
	protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z) {}

	@Override
	protected int getCraterProbability() {
		return 0;
	}

	@Override
	public void onPopulate(int cX, int cZ) {}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {}

	@Override
	protected IBlockState getStoneBlock() {
		return YzCetiBlocks.B.YZB_METAMORPHIC.getDefaultState();
	}


}