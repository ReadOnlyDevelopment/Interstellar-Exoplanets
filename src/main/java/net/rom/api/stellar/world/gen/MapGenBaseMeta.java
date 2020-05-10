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

package net.rom.api.stellar.world.gen;

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
