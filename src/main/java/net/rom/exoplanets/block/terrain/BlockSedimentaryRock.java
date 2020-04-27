package net.rom.exoplanets.block.terrain;

import net.rom.exoplanets.block.BasicBlock;

/**
 * Sedimentary rocks are types of rock that are formed by the accumulation or
 * deposition of small particles and subsequent cementation of mineral or
 * organic particles on the floor of oceans or other bodies of water
 * 
 */
public class BlockSedimentaryRock extends BasicBlock{
	
	public BlockSedimentaryRock() {
		super();
		setResistance(1.0F);
		setHardness(2.0F);
		setHarvestLevel("pickaxe", 1);
	}
}
