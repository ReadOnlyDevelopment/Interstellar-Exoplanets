package net.romvoid95.common.block.terrain;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.romvoid95.client.CreativeExoTabs;

public class ExoLog extends BlockLog {

	public ExoLog() {
		super();
		this.setHarvestLevel("axe", 2);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_TAB);
		this.setHardness(2.0F);
		this.setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	@Override
	public IBlockState getStateFromMeta (int meta) {
		EnumAxis[] values = EnumAxis.values();
		return this.getDefaultState().withProperty(LOG_AXIS, values[meta % values.length]);
	}

	@Override
	public int getMetaFromState (IBlockState state) {
		return state.getValue(LOG_AXIS).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState () {
		return new BlockStateContainer(this, LOG_AXIS);
	}

	@Override
	public int getFireSpreadSpeed (IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}

	@Override
	public int getFlammability (IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}

}
