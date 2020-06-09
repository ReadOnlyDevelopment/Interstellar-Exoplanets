package net.rom.exoplanets.content.block.stairs;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;

public class BlockStairsVitalliumStairs extends BlockStairs {
	
	public BlockStairsVitalliumStairs() {
		super(Blocks.IRON_BLOCK.getDefaultState());
		setHardness(2F);
		setResistance(5F);
		setHarvestLevel("axe", 2);
		setSoundType(SoundType.METAL);
	}
}
