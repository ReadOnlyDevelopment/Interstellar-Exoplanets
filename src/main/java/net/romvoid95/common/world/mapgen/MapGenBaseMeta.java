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

package net.romvoid95.common.world.mapgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

/*
 * Class from Galacticraft Core
 */
public abstract class MapGenBaseMeta {
	
	/**
	 * The number of Chunks to gen-check in any given direction.
	 */
	protected int range = 8;

	/**
	 * The RNG used by the MapGen classes.
	 */
	protected Random rand = new Random();

	/**
	 * This world object.
	 */
	protected World worldObj;

	public void generate(World world, int chunkX, int chunkZ, ChunkPrimer primer) {
		this.worldObj = world;
		this.rand.setSeed(world.getSeed());
		final long r0 = this.rand.nextLong();
		final long r1 = this.rand.nextLong();

		for (int x0 = chunkX - this.range; x0 <= chunkX + this.range; ++x0) {
			for (int y0 = chunkZ - this.range; y0 <= chunkZ + this.range; ++y0) {
				final long randX = x0 * r0;
				final long randZ = y0 * r1;
				this.rand.setSeed(randX ^ randZ ^ world.getSeed());
				this.recursiveGenerate(world, x0, y0, chunkX, chunkZ, primer);
			}
		}
	}

	/**
	 * Recursively called by generate() (generate) and optionally by itself.
	 */
	protected void recursiveGenerate(World world, int xChunkCoord, int zChunkCoord, int origXChunkCoord, int origZChunkCoord, ChunkPrimer primer) {
	}

}
