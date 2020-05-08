package net.rom.exoplanets.content.block.terrain;

import net.rom.exoplanets.content.block.BlockTerrain;

public class BlockMetamorphicRock extends BlockTerrain {
	
	public BlockMetamorphicRock() {
		super();
		setHardness(25.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
	}

}
