package net.romvoid95.common.block.terrain.tree;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.romvoid95.api.enums.TreeType;
import net.romvoid95.api.world.worldgen.feature.newtreegen.FallenTreeGenerator;
import net.romvoid95.api.world.worldgen.feature.newtreegen.FirTreeGenerator;
import net.romvoid95.api.world.worldgen.feature.newtreegen.FlatTopTreeGenerator;
import net.romvoid95.api.world.worldgen.feature.newtreegen.SwampTreeGenerator;
import net.romvoid95.api.world.worldgen.feature.newtreegen.WillowTreeGenerator;

public class BlockExoSapling extends BlockBush implements IGrowable {

	public static final IProperty<TreeType> TREE_TYPE = PropertyEnum.create("tree_type", TreeType.class);
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	public BlockExoSapling() {
		this.setSoundType(SoundType.PLANT);
		this.setDefaultState(blockState.getBaseState()
				.withProperty(TREE_TYPE, TreeType.SWAMP));
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TREE_TYPE);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		WorldGenerator treeGenerator = null;

		switch (state.getValue(TREE_TYPE)) {
			case SWAMP:
				treeGenerator = new SwampTreeGenerator(true);
				break;
			case FALLEN:
				treeGenerator = new FallenTreeGenerator(true);
				break;
			case FIR:
				treeGenerator = new FirTreeGenerator(true);
				break;
			case SEMPER:
				//treeGenerator = new Semper(true);
				break;
			case FLAT:
				treeGenerator = new FlatTopTreeGenerator(true);
				break;
			default:
				treeGenerator = new WillowTreeGenerator(true);
				break;
		}

		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

		if (!treeGenerator.generate(world, rand, pos)) {
			world.setBlockState(pos, state, 4);
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
		list.add(new ItemStack(this, 1, 3));
		list.add(new ItemStack(this, 1, 4));
		list.add(new ItemStack(this, 1, 5));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SAPLING_AABB;
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
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return (double) worldIn.rand.nextFloat() < 0.45D;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TREE_TYPE).ordinal();
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TREE_TYPE, TreeType.values()[meta % TreeType.values().length]);
	}
}
