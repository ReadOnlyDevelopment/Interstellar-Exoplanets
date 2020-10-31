package net.rom.exoplanets.content.block.terrain;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockExoLog extends BlockLog {

	public BlockExoLog() {
		super();
		this.setHarvestLevel("axe", 2);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
		this.setHardness(2.0F);
		this.setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		for (EnumAxis axis : EnumAxis.values()) {
			if (axis.ordinal() == meta) {
				return getDefaultState().withProperty(LOG_AXIS, axis);
			}
		}
		return getDefaultState();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int getMetaFromState (IBlockState state) {
		return state.getValue(LOG_AXIS).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{LOG_AXIS});
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
