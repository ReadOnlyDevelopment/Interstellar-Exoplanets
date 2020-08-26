/**
 * Exoplanets
 * Copyright (C) 2020
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
package net.rom95.common.block;

import asmodeuscore.api.item.IShiftDescription;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom95.common.lib.block.BlockExoTile;
import net.rom95.core.initialization.ExoBlocks;

public class BlockLaunchFacility extends BlockExoTile implements IPartialSealableBlock, IShiftDescription, ISortableBlock {
	
	public static final PropertyEnum<EnumLandingPadType> PAD_TYPE = PropertyEnum.create("type", EnumLandingPadType.class);
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.1875, 1.0);

	public enum EnumLandingPadType implements IStringSerializable {
		LAUNCH_FACILITY(0, "launch_facility");

		private final int meta;
		private final String name;

		EnumLandingPadType(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		public int getMeta() {
			return this.meta;
		}

		public static EnumLandingPadType byMetadata(int meta) {
			return values()[meta];
		}

		@Override
		public String getName() {
			return this.name;
		}
	}
	
	public BlockLaunchFacility() {
		super(Material.IRON);
		this.setHardness(1.0F);
		this.setResistance(10.0F);
		this.setSoundType(SoundType.METAL);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int i = 0; i < 4; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	private boolean checkAxis(World world, BlockPos pos, Block block, EnumFacing facing) {
		int sameCount = 0;
		for (int i = 1; i <= 5; i++) {
			if (world.getBlockState(pos.offset(facing, i)).getBlock() == block) {
				sameCount++;
			}
		}

		return sameCount < 5;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		final Block id = ExoBlocks.launch_facility;

		if (!checkAxis(world, pos, id, EnumFacing.EAST) || !checkAxis(world, pos, id, EnumFacing.WEST) || !checkAxis(world, pos, id, EnumFacing.NORTH) || !checkAxis(world, pos, id, EnumFacing.SOUTH)) {
			return false;
		}

		if (world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() == GCBlocks.landingPad && side == EnumFacing.UP) {
			return false;
		} else {
			return this.canPlaceBlockAt(world, pos);
		}
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
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			//return new TileEntityTier2LandingPadSingle();
		default:
			return null;
		}
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return null;
	}

	@Override
	public String getShiftDescription(int meta) {
		return null;
	}

	@Override
	public String getDescription(int meta) {
		return null;
	}

	@Override
	public boolean showDescription(int meta) {
		return false;
	}

	@Override
	public boolean isSealed(World world, BlockPos pos, EnumFacing direction) {
		return false;
	}

}
