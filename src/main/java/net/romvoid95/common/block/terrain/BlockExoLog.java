/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid95.common.block.terrain;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import net.romvoid95.client.CreativeExoTabs;

public class BlockExoLog extends BlockLog {

	public BlockExoLog() {
		super();
		this.setHarvestLevel("axe", 2);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_TAB);
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
