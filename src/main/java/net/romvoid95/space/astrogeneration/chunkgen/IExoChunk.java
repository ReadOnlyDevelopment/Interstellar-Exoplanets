package net.romvoid95.space.astrogeneration.chunkgen;

import net.minecraft.world.chunk.ChunkPrimer;

public interface IExoChunk {
	
	public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer);

}
