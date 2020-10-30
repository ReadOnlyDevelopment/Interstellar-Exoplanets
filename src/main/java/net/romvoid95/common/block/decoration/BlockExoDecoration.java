package net.romvoid95.common.block.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockExoDecoration extends Block {

	// constructor
	public BlockExoDecoration() {
		this(Material.PLANTS);
	}

	public BlockExoDecoration(Material material) {
		super(material);

		// set some defaults
		this.setTickRandomly(true);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);

		this.setDefaultState(this.blockState.getBaseState());
	}

	// no collision box - you can walk straight through them
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return NULL_AABB;
	}

	// add a canBlockStay() check before placing this block
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		if (world.getBlockState(pos).getBlock().isReplaceable(world, pos)
				&& this.canBlockStay(world, pos, this.getStateFromMeta(meta))) {
			return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		}

		return world.getBlockState(pos);
	}

	// check this block is still able to remain after neighbor change
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock,
			BlockPos neighborPos) {
		this.checkAndDropBlock(world, pos, state);
	}

	// check this block is still able to remain on update ticks
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		this.checkAndDropBlock(worldIn, pos, state);
	}

	// our blocks usually drop their current state as opposed to a single 'default'
	// state
	@Override
	public int damageDropped(IBlockState state) {
		return this.getMetaFromState(state);
	}

	// drop block as item if it cannot remain here - return whether on not it could
	// stay
	protected boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (this.canBlockStay(worldIn, pos, state)) {
			return true;
		} else {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
			return false;
		}
	}

	// child classes should override this with their own rules about where the block
	// can be placed
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		return true;
	}

	// not opaque
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	// not full cube
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	// decoration should be randomly offset by default
	@Override
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.XZ;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing) {
		return BlockFaceShape.UNDEFINED;
	}
}
