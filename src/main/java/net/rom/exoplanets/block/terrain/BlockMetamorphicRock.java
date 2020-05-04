package net.rom.exoplanets.block.terrain;

import net.rom.exoplanets.block.BlockTerrain;

public class BlockMetamorphicRock extends BlockTerrain {
	
	public BlockMetamorphicRock() {
		super();
		setHardness(25.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
	}

}
