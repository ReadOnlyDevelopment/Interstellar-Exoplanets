package net.romvoid95.common.block.terrain;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.enums.EnumExoTrees;
import net.romvoid95.common.block.decoration.BlockExoDecoration;
import net.romvoid95.common.utility.mc.VarPagingHelper;

public class BlockExoSapling extends BlockExoDecoration implements IGrowable, IPlantable {

	protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.09999999403953552D, 0.0D,
			0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	private final WorldGenerator treeGenerator = new WorldGenBirchTree(true, true);
	// setup paged variant property

	// STAGE requires one bit, so we have 3 bits left for the VARIANT which means we
	// can have eight per instance
	public static VarPagingHelper<BlockExoSapling, EnumExoTrees> paging = new VarPagingHelper<BlockExoSapling, EnumExoTrees>(
			8, EnumExoTrees.class, EnumExoTrees.withSaplings);

	// Slightly naughty hackery here
	// The constructor of Block() calls createBlockState() which needs to know the
	// particular instance's variant property
	// There is no way to set the individual block instance's variant property
	// before this, because the super() has to be first
	// So, we use the static variable currentVariantProperty to provide each
	// instance access to its variant property during creation
	private static IProperty currentVariantProperty;

	// Each instance has a reference to its own variant property
	public IProperty variantProperty;

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	@Override
	protected BlockStateContainer createBlockState() {
		this.variantProperty = currentVariantProperty; // get from static variable
		return new BlockStateContainer(this, STAGE, this.variantProperty);
	}

	public BlockExoSapling() {
		super();
		this.setSoundType(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
	}

	// map from meta to state and vice verca. Use the highest bit for STAGE and the
	// other 3 for VARIANT (so we can have 8 per instance)
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta & 7))
				.withProperty(STAGE, Integer.valueOf(meta >> 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumExoTrees tree = (EnumExoTrees) state.getValue(this.variantProperty);
		return state.getValue(STAGE).intValue() * 8 + paging.getIndex(tree);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);
			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, rand, pos, state);
			}
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		// We don't want item drops to have metadata for the stage
		return this.getMetaFromState(state) & 7;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.NONE;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return (double) worldIn.rand.nextFloat() < 0.45D;
	}

	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((Integer) state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos))
			return;

		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

		if (!treeGenerator.generate(worldIn, rand, pos)) {
			worldIn.setBlockState(pos, state, 4);
		}
	}

	public boolean thisSaplingHere(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == this;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return getDefaultState();
		return state;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub

	}
}
