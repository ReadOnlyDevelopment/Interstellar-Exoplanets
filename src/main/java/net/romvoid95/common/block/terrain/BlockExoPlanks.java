package net.romvoid95.common.block.terrain;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockExoPlanks extends BlockPlanks {
	
	public BlockExoPlanks() {
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);	
	}
	
	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 20;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}

}
