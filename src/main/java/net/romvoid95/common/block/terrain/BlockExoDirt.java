package net.romvoid95.common.block.terrain;

import net.romvoid95.common.block.BlockTerrain;

public class BlockExoDirt extends BlockTerrain {
	
	public BlockExoDirt() {
		super();
		this.setHarvestLevel("shovel", 1);
		this.setDefaultState(this.getDefaultState());
	}
}
