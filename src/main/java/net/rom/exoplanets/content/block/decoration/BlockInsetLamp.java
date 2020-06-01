/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.content.block.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.rom.exoplanets.content.block.BlockDecoration;
import net.rom.exoplanets.init.ExoBlocks;

public class BlockInsetLamp extends BlockDecoration {
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.20D, 0.0D, 0.15D, 0.80D, 0.80D, 0.80D);

	private final boolean isOn;

	public BlockInsetLamp(boolean isOn) {
		super(Material.REDSTONE_LIGHT);
		this.isOn = isOn;

		if (isOn) {
			setLightLevel(1.0F);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			if (isOn && !worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.inset_lamp.getDefaultState(), 2);
			} else if (!isOn && worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.inset_lamp_lit.getDefaultState(), 2);
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			if (isOn && !worldIn.isBlockPowered(pos)) {
				worldIn.scheduleUpdate(pos, this, 4);
			} else if (!isOn && worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.inset_lamp_lit.getDefaultState(), 2);
			}
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (isOn && !worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, ExoBlocks.inset_lamp.getDefaultState(), 2);
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ExoBlocks.inset_lamp);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ExoBlocks.inset_lamp);
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(ExoBlocks.inset_lamp);
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
}
