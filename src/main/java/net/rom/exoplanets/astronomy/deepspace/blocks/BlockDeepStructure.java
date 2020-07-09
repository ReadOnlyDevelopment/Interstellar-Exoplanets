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
package net.rom.exoplanets.astronomy.deepspace.blocks;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import micdoodle8.mods.galacticraft.core.util.PropertyObject;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.deepspace.tile.TileEntityDeepStructure;

public class BlockDeepStructure extends Block implements ISortableBlock, ITileEntityProvider, IPartialSealableBlock {
	public static final PropertyInteger H = PropertyInteger.create("h", 0, 15);
	public static final PropertyObject<IBlockState> BASE_STATE = new PropertyObject<>("held", IBlockState.class);
	protected static final AxisAlignedBB BOUNDING_BOX[] = new AxisAlignedBB[16];
	protected static final AxisAlignedBB BBWHOLE = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 1D);
	private final boolean inv;

	static {
		for (int y = 0; y < 8; y++) {
			BOUNDING_BOX[y] = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, (y + 1) / 8D);
		}
		for (int y = 8; y < 16; y++) {
			BOUNDING_BOX[y] = new AxisAlignedBB(0.0D, 0.0D, (15 - y) / 8D, 1.0D, 1.0D, 1.0D);
		}
	}

	public enum WallType implements IStringSerializable {
		SOLID("solid"), ARCH("arch");
		// IF ADDING TO THIS ENUM, MAKE SURE TO CHANGE DEFINITION OF
		// PacketSimple.C_UPDATE_STATS!!!!!!

		private final String name;

		private WallType(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
	}

	public BlockDeepStructure(boolean invert) {
		super(Material.IRON);
		this.inv = invert;
		this.setHardness(1.0F);
		this.setSoundType(SoundType.METAL);
		this.setDefaultState(this.blockState.getBaseState());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new ExtendedBlockState(this, new IProperty[] { H }, new IUnlistedProperty[] { BASE_STATE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		super.getSubBlocks(itemIn, items);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		result.add(new ItemStack(Blocks.AIR));
		return result;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		int meta2 = (pos.getY() % 8);
		int z = pos.getZ() % 16;
		if (z < 0)
			z += 16;
		if (z >= 8)
			meta += 8;
		return this.getStateFromMeta(15 - meta2);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityDeepStructure(meta);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return GalacticraftCore.galacticraftBlocksTab;
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		int h = this.getMetaFromState(state);
		if (side == EnumFacing.NORTH && h >= 8)
			return true;
		if (side == EnumFacing.SOUTH && h < 8)
			return true;
		IBlockState bs = blockAccess.getBlockState(pos.offset(side));
		if (bs.getBlock() == this) {
			if (side == EnumFacing.EAST || side == EnumFacing.WEST || side == EnumFacing.DOWN)
				return false;
			if (side == EnumFacing.UP)
				return (h & 7) == 7;
		}
		return !bs.doesSideBlockRendering(blockAccess, pos.offset(side), side.getOpposite());
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.SOUTH) {
			return this.getMetaFromState(state) >= 8;
		}
		if (side == EnumFacing.NORTH) {
			return this.getMetaFromState(state) < 8;
		}
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta >= 16)
			meta = 0;
		return this.getDefaultState().withProperty(H, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockDeepStructure.H).intValue();
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileEntityDeepStructure) {
			state = ((IExtendedBlockState) state).withProperty(BASE_STATE,
					((TileEntityDeepStructure) tile).getBaseBlock());
		}

		int meta = (pos.getY() % 8);
		int z = pos.getZ() % 16;
		if (z < 0)
			z += 16;
		if (z >= 8)
			meta += 8;
		return state.withProperty(H, 15 - meta); // this.inv ? 15 - meta : meta);
	}

	@SideOnly(value = Side.CLIENT)
	public static void updateClient(int type, IBlockState state) {
	}

	@SideOnly(value = Side.CLIENT)
	public static void updateClient(List<Object> data) {
	}

	public static void getNetworkedData(Object[] result, IBlockState[] state) {
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
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		int z = pos.getZ() % 16;
		if (z < 0)
			z += 16;
		if (z < 8)
			return side == EnumFacing.SOUTH;
		return side == EnumFacing.NORTH;
	}

	@Override
	public boolean isSealed(World world, BlockPos pos, EnumFacing direction) {
		return direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BOUNDING_BOX[this.getMetaFromState(state)];
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return BBWHOLE;
	}
}
