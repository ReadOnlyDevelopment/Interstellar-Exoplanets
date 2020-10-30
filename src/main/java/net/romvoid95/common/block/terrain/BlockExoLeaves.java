package net.romvoid95.common.block.terrain;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.romvoid95.api.enums.EnumExoTrees;
import net.romvoid95.common.config.ConfigCore;
import net.romvoid95.common.utility.mc.VarPagingHelper;
import net.romvoid95.core.initialization.ExoBlocks;

public class BlockExoLeaves extends BlockLeaves {

	public static VarPagingHelper<BlockExoLeaves, EnumExoTrees> paging = new VarPagingHelper<BlockExoLeaves, EnumExoTrees>(
			4, EnumExoTrees.class);

	private static IProperty currentVariantProperty;

	// Create an instance for each page
	public static void createAllPages() {
		int numPages = paging.getNumPages();
		for (int i = 0; i < numPages; ++i) {
			currentVariantProperty = paging.getVariantProperty(i);
			paging.addBlock(i, new BlockExoLeaves());
		}

	}

	// Each instance has a reference to its own variant property
	public IProperty variantProperty;

	@Override
	protected BlockStateContainer createBlockState() {
		this.variantProperty = currentVariantProperty; // get from static variable
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE, this.variantProperty);
	}

	@Override
	public String getStateName(IBlockState state) {
		EnumExoTrees tree = ((EnumExoTrees) state.getValue(this.variantProperty));
		switch (tree) {
		default:
			return tree.getName() + "_leaves";
		}
	}

	public static final IProperty<LeavesVariant> VARIANT = PropertyEnum.create("variant", LeavesVariant.class);

	protected BlockExoLeaves() {
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true))
				.withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	@Override
	public int getLightOpacity(IBlockState state) {
		return ConfigCore.leavesLightOpacity;
	}

	// map from meta to state and vice verca. Use the same scheme as for the vanilla
	// leaf blocks
	// highest bit is for CHECK_DECAY true=>1 false=>0
	// next bit is for DECAYABLE true=>0 false=>1 (other way round this time! cheers
	// Mojang)
	// low 2 bits for VARIANT
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta & 3))
				.withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
				.withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		BOPTrees tree = (BOPTrees) state.getValue(this.variantProperty);
		int meta = paging.getIndex(tree);
		if (!state.getValue(DECAYABLE).booleanValue()) {
			meta |= 4;
		}
		if (state.getValue(CHECK_DECAY).booleanValue()) {
			meta |= 8;
		}
		return meta;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = state.getValue(VARIANT).ordinal();

		if (!state.getValue(DECAYABLE)) {
			i |= 4;
		}

		if (state.getValue(CHECK_DECAY)) {
			i |= 8;
		}

		return i;
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int meta) {
		return BlockPlanks.EnumType.OAK;
	}

	@Override
	public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
		list.add(new ItemStack(this, 1, 3));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return Item.getItemFromBlock(ExoBlocks.EXO_SAPLING);
	}

	@Override
	public int damageDropped(IBlockState state) {
		LeavesVariant leafType = state.getValue(VARIANT);
		return leafType == LeavesVariant.SWAMP ? 9 : leafType.ordinal();
	}

	@Override
	public ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this, 1, state.getValue(VARIANT).ordinal());
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this, 1, state.getValue(VARIANT).ordinal());
	}

	@Override
	public int getSaplingDropChance(IBlockState state) {
		return state.getValue(VARIANT) == LeavesVariant.CANOPY ? 20 : 40;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal()));
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 30;
	}

	public enum LeavesVariant implements IStringSerializable {

		SWAMP, CANOPY, FADED;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ROOT);
		}
	}

}
