package net.rom.exoplanets.content.block.stairs;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpaceQuartzStair extends BlockStairs {
	
	public BlockSpaceQuartzStair() {
		super(Blocks.STONE_STAIRS.getDefaultState());
		this.setHardness(2F);
		this.setResistance(2F);
		this.setHarvestLevel("axe", 1);
		this.setLightOpacity(1);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}
}
