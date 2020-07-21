package net.rom.exoplanets.content.block.terrain;

import net.rom.exoplanets.content.block.BlockTerrain;

public class BlockExoDirt extends BlockTerrain {
	
	public BlockExoDirt() {
		super();
		this.setHarvestLevel("shovel", 1);
		this.setDefaultState(this.getDefaultState());
	}
}
