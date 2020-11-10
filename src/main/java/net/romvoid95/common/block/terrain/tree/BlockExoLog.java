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
package net.romvoid95.common.block.terrain.tree;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.romvoid95.api.enums.WoodType;
import net.romvoid95.client.CreativeExoTabs;
import net.romvoid95.client.model.ModelUtil;
import net.romvoid95.common.lib.block.ICustomModel;

public class BlockExoLog extends BlockLog implements ICustomModel{
	
	public static final PropertyEnum<WoodType> WOOD_TYPE = PropertyEnum.create("type", WoodType.class);

	public BlockExoLog() {
		super();
		this.setHarvestLevel("axe", 2);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_TAB);
		this.setHardness(2.0F);
		this.setSoundType(SoundType.WOOD);
		this.setDefaultState(this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.Y));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState().withProperty(WOOD_TYPE, WoodType.values()[meta & 3]);

		switch (meta & 0b1100) {
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
				break;
			case 8:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return iblockstate;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = state.getValue(WOOD_TYPE).ordinal();

		switch (state.getValue(LOG_AXIS)) {
			case X:
				i |= 4;
				break;
			case Y:
				i |= 0;
				break;
			case Z:
				i |= 8;
				break;
			case NONE:
				i |= 12;
		}

		return i;
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(WOOD_TYPE).ordinal();
	}
	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getValue(WOOD_TYPE).getMapColor();
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, WOOD_TYPE, LOG_AXIS);
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}
	
	@Override
	public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
		list.add(new ItemStack(this, 1, 3));
	}

	@Override
	public int getFireSpreadSpeed (IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}

	@Override
	public int getFlammability (IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}

	@Override
	public void registerModels() {
		ModelUtil.registerToStateSingleVariant(this, WOOD_TYPE);
		
	}
}
