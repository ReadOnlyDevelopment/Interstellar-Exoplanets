package net.romvoid95.common.world.cave.bedrock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

public class FlattenBedrock {
	private static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();

	public static void flattenBedrock(ChunkPrimer primer, int bedrockLayerWidth) {
		IBlockState replacementBlock = Blocks.STONE.getDefaultState();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 1; y < 5; y++) {
					if (primer.getBlockState(x, y, z) == BEDROCK) {
						primer.setBlockState(x, y, z, replacementBlock);
					}
				}
			}
		}

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 1; y < bedrockLayerWidth; y++) {
					primer.setBlockState(x, y, z, BEDROCK);
				}
			}
		}
	}
}
