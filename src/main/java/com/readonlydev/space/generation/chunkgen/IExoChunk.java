package com.readonlydev.space.generation.chunkgen;

import net.minecraft.world.chunk.ChunkPrimer;

public interface IExoChunk {

	public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer);

}
