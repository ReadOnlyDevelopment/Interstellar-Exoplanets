package net.rom95.common.block.terrain;

import net.rom95.common.block.BlockTerrain;

public class BlockExoDirt extends BlockTerrain {
	
	public BlockExoDirt() {
		super();
		this.setHarvestLevel("shovel", 1);
		this.setDefaultState(this.getDefaultState());
	}
}
