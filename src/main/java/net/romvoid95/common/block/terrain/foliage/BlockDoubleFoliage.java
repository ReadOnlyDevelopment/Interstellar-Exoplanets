package net.romvoid95.common.block.terrain.foliage;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.romvoid95.common.block.terrain.IDoubleGrass;

public class BlockDoubleFoliage extends BlockExoFoliage implements IDoubleGrass {

	public enum HALF implements IStringSerializable {
		LOW, HIGH;

		@Override
		public String toString() {
			return this.getName();
		}

		@Override
		public String getName() {
			return this == HIGH ? "high" : "low";
		}
	}

	public static final PropertyEnum<HALF> PART = PropertyEnum.create("part", HALF.class);
	
	protected static final AxisAlignedBB PLANT_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 1.0D, 0.9D);

	public float radius;
	public float height;
	public boolean fromTop;

	public BlockDoubleFoliage() {
		super();
		this.radius = 0.5F;
		this.height = 1.0F;
		this.fromTop = false;
		this.setDefaultState(this.blockState.getBaseState().withProperty(PART, HALF.LOW));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean isUpper = (meta & 1) == 1;
		return this.getDefaultState().withProperty(PART, isUpper ? HALF.HIGH : HALF.LOW);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		boolean isUpper = state.getValue(PART) == BlockDoubleFoliage.HALF.HIGH;
		int meta = isUpper ? 1 : 0;
		return meta;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {PART});
	}

	// utility functions
	public BlockPos getLowerPos(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this) {
			return pos;
		}
		return world.getBlockState(pos).getValue(PART) == HALF.HIGH ? pos.down() : pos;
	}

	public BlockPos getUpperPos(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this) {
			return pos.up();
		}
		return world.getBlockState(pos).getValue(PART) == HALF.HIGH ? pos : pos.up();
	}

	public IBlockState getLowerState(IBlockAccess world, BlockPos pos) {
		return world.getBlockState(this.getLowerPos(world, pos));
	}

	public IBlockState getUpperState(IBlockAccess world, BlockPos pos) {
		return world.getBlockState(this.getUpperPos(world, pos));
	}

	public boolean isValidDoubleBlock(IBlockAccess world, BlockPos pos) {
		IBlockState lowerState = this.getLowerState(world, pos);
		IBlockState upperState = this.getUpperState(world, pos);
		Block lowerBlock = lowerState.getBlock();
		Block upperBlock = upperState.getBlock();
		return (lowerBlock instanceof BlockDoubleFoliage) && (upperBlock instanceof BlockDoubleFoliage)
				&& (lowerBlock == upperBlock) && lowerState.getValue(PART) == HALF.LOW
				&& upperState.getValue(PART) == HALF.HIGH;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return PLANT_AABB;
	}

	/**
	 * Checks if this block can be placed exactly at the given position.
	 */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos, state)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	// This DoubleDecoration can replace the block at pos if both the block and the
	// one above it are replaceable and the environment is suitable (canBlockStay)
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		if (world.getBlockState(pos).getBlock().isReplaceable(world, pos)
				&& world.getBlockState(pos.up()).getBlock().isReplaceable(world, pos.up())
				&& this.canBlockStay(world, pos, this.getStateFromMeta(meta))) {
			return this.getStateFromMeta(meta).withProperty(PART, HALF.LOW);
		}

		return world.getBlockState(pos);
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() != this)
			return super.canBlockStay(worldIn, pos, state); // Forge: This function is called during world gen and
															// placement, before this
		// block is set, so if we are not 'here' then assume it's the pre-check.
		if (state.getValue(PART) == BlockDoubleFoliage.HALF.HIGH) {
			return worldIn.getBlockState(pos.down()).getBlock() == this;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos.up());
			return iblockstate.getBlock() == this && super.canBlockStay(worldIn, pos, iblockstate);
		}
	}

	public void placeAt(World worldIn, BlockPos lowerPos, int flags) {
		worldIn.setBlockState(lowerPos, this.getDefaultState().withProperty(PART, BlockDoubleFoliage.HALF.LOW), flags);
		worldIn.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(PART, BlockDoubleFoliage.HALF.HIGH),
				flags);
	}

	// Called by ItemBlock after the (lower) block has been placed
	// Use it to add the top half of the block
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos.up(), this.getStateFromMeta(stack.getMetadata()).withProperty(PART, HALF.HIGH), 3);
	}

	/**
	 * Get the OffsetType for this Block. Determines if the model is rendered
	 * slightly offset.
	 */
	@Override
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.XZ;
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	// handle drops from UPPER and LOWER separately
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		// note it is important to use the state as provided and NOT
		// world.getBlockState(pos)
		// because when this function is called, the block may have already been turned
		// to air
		// the state provided is the state before the block was destroyed
		if (state.getValue(PART) == HALF.HIGH) {
			return this.getUpperDrops(world, pos, state, fortune);
		} else {
			return this.getLowerDrops(world, pos, state, fortune);
		}
	}

	// default behavior is that UPPER drops nothing, and LOWER drops the default
	// item
	public List<ItemStack> getUpperDrops(IBlockAccess world, BlockPos upperPos, IBlockState upperState, int fortune) {
		return new java.util.ArrayList<ItemStack>();
	}

	public List<ItemStack> getLowerDrops(IBlockAccess world, BlockPos lowerPos, IBlockState lowerState, int fortune) {
		return super.getDrops(world, lowerPos, lowerState, fortune);
	}

	// if a child class chooses to implement IShearable make shearing the upper or
	// lower block act as shearing both
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		List<ItemStack> drops = new java.util.ArrayList<ItemStack>();
		if (!this.isValidDoubleBlock(world, pos)) {
			return drops;
		}
		drops.addAll(this.getUpperShearDrops(item, world, this.getUpperPos(world, pos), this.getUpperState(world, pos),
				fortune));
		drops.addAll(this.getLowerShearDrops(item, world, this.getLowerPos(world, pos), this.getLowerState(world, pos),
				fortune));
		return drops;
	}

	// default behavior is that UPPER drops nothing, and LOWER drops the default
	// item
	public List<ItemStack> getUpperShearDrops(ItemStack item, IBlockAccess world, BlockPos upperPos,
			IBlockState upperState, int fortune) {
		return new java.util.ArrayList<ItemStack>();
	}

	public List<ItemStack> getLowerShearDrops(ItemStack item, IBlockAccess world, BlockPos lowerPos,
			IBlockState lowerState, int fortune) {
		return super.getDrops(world, lowerPos, lowerState, fortune);
	}

	// discard the HALF info in the items dropped - make them all Half.LOWER so that
	// they can stack - keep other state info such as VARIANT
	@Override
	public int damageDropped(IBlockState state) {
		return this.getMetaFromState(state.withProperty(PART, HALF.LOW));
	}
}
