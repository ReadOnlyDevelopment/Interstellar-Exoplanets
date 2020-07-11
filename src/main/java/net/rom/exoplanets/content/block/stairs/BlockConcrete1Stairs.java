package net.rom.exoplanets.content.block.stairs;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;

public class BlockConcrete1Stairs extends BlockStairs {

	public BlockConcrete1Stairs() {
		super(Blocks.IRON_BLOCK.getDefaultState());
		this.setHardness(2F);
		this.setResistance(5F);
		this.setHarvestLevel("pickaxe", 2);
		this.setSoundType(SoundType.STONE);
	}

}
