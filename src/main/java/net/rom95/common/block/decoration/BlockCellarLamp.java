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

package net.rom95.common.block.decoration;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.rom95.common.block.BlockDecoration;
import net.rom95.core.initialization.ExoBlocks;

public class BlockCellarLamp extends BlockDecoration {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.25D, 0.25D, 0.80D, 0.75D, 0.90D, 1.0D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.90D, 0.20D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.80D, 0.25D, 0.25D, 1.0D, 0.90D, 0.75D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.20D, 0.90D, 0.75D);

	private final boolean isOn;

	public BlockCellarLamp(boolean isOn) {
		super(Material.REDSTONE_LIGHT);
		this.setHardness(2F);
		this.setResistance(5F);
		this.setHarvestLevel("axe", 2);
		this.setSoundType(SoundType.GLASS);
		this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.isOn = isOn;

		if (isOn) {
			setLightLevel(1.0F);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(FACING)) {
		case NORTH:
		default:
			return AABB_NORTH;
		case SOUTH:
			return AABB_SOUTH;
		case WEST:
			return AABB_WEST;
		case EAST:
			return AABB_EAST;
		}
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumFacing = EnumFacing.getFront(meta);

		if (enumFacing.getAxis() == EnumFacing.Axis.Y) {
			enumFacing = EnumFacing.NORTH;
		}
		return getDefaultState().withProperty(FACING, enumFacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		if (canPlaceAt(worldIn, pos, facing)) {
            return getDefaultState().withProperty(FACING, facing);
        } else {
			for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
				if (canPlaceAt(worldIn, pos, enumfacing)) {
                    return getDefaultState().withProperty(FACING, enumfacing);
                }
			}

			return getDefaultState();
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			if (!checkForDrop(worldIn, pos, state)) {
                return;
            }

			if (isOn && !worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.cellar_lamp.getDefaultState().withProperty(FACING,
						worldIn.getBlockState(pos).getValue(FACING)), 2);
			} else if (!isOn && worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.cellar_lamp_lit.getDefaultState().withProperty(FACING,
						worldIn.getBlockState(pos).getValue(FACING)), 2);
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			if (!checkForDrop(worldIn, pos, state)) {
                return;
            }

			if (isOn && !worldIn.isBlockPowered(pos)) {
				worldIn.scheduleUpdate(pos, this, 4);
			} else if (!isOn && worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.cellar_lamp_lit.getDefaultState().withProperty(FACING,
						worldIn.getBlockState(pos).getValue(FACING)), 2);
			}
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (isOn && !worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.cellar_lamp.getDefaultState().withProperty(FACING,
						worldIn.getBlockState(pos).getValue(FACING)), 2);
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ExoBlocks.cellar_lamp);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ExoBlocks.cellar_lamp);
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(ExoBlocks.cellar_lamp);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	private boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
		BlockPos blockpos = pos.offset(facing.getOpposite());
		return facing.getAxis().isHorizontal() && worldIn.isSideSolid(blockpos, facing, true);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		for (EnumFacing enumfacing : FACING.getAllowedValues()) {
			if (canPlaceAt(worldIn, pos, enumfacing)) {
                return true;
            }
		}

		return false;
	}

	protected boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if ((state.getBlock() == this) && canPlaceAt(worldIn, pos, state.getValue(FACING))) {
            return true;
        } else {
			if (worldIn.getBlockState(pos).getBlock() == this) {
				dropBlockAsItem(worldIn, pos, ExoBlocks.cellar_lamp.getDefaultState(), 0);
				worldIn.setBlockToAir(pos);
			}

			return false;
		}
	}

}
