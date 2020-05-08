package net.rom.exoplanets.content.block.decoration;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.rom.exoplanets.content.block.BlockDecoration;

public class BlockSpotLamp extends BlockDecoration {

	protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.0D, 0.85D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockSpotLamp() {
		super(Material.GLASS);
		setHardness(2F);
		setResistance(1F);
		setLightLevel(1F);
		setLightOpacity(50);
		setHarvestLevel("axe", 1);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_UP;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

}
