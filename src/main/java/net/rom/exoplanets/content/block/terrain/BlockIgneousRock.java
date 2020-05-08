package net.rom.exoplanets.content.block.terrain;

import net.rom.exoplanets.content.block.BlockTerrain;

/**
 * Igneous rock, or magmatic rock, is one of the three main rock types, the
 * others being sedimentary and metamorphic. Igneous rock is formed through the
 * cooling and solidification of magma or lava. The magma can be derived from
 * partial melts of existing rocks in either a planet's mantle or crust.
 * <br><br>
 * (jeez sounds JUST like Obsidian DONT IT, Because Obsidian is a Igneous Rock, Thanks Wiki)
 * 
 *
 */
public class BlockIgneousRock extends BlockTerrain {

	public BlockIgneousRock() {
		super();
		setResistance(20.0F);
		setHardness(30.0F);
		setHarvestLevel("pickaxe", 3);
	}
}
