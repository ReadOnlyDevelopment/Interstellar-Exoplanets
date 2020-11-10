package net.romvoid95.common.block.terrain.tree;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.enums.LeafType;
import net.romvoid95.api.math.FilledList;
import net.romvoid95.client.CreativeExoTabs;
import net.romvoid95.client.model.ModelUtil;
import net.romvoid95.common.config.ConfigCore;
import net.romvoid95.common.lib.block.ICustomModel;
import net.romvoid95.core.initialization.ExoBlocks;

public class BlockExoLeaves extends BlockLeaves implements ICustomModel {

	public static final PropertyEnum<LeafType> VARIANT = PropertyEnum.create("variant", LeafType.class);

	public BlockExoLeaves() {
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_TAB);
		this.setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	@Override
	public int getLightOpacity(IBlockState state) {
		return ConfigCore.leavesLightOpacity;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE, VARIANT);
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		int variant = meta & 4;
		final LeafType[] values = LeafType.values();

		return getDefaultState()
				.withProperty(VARIANT, values[variant % values.length])
				.withProperty(DECAYABLE, (meta & 4) == 0)
				.withProperty(CHECK_DECAY, (meta & 8) > 0);
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
		return null;
	}

	@Override
	public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return Item.getItemFromBlock(ExoBlocks.EXO_SAPLING);
	}

	@Override
	public int damageDropped(IBlockState state) {
		LeafType leafType = state.getValue(VARIANT);
		return leafType.ordinal();
	}

	@Override
	public ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this, 1, state.getValue(VARIANT).ordinal());
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, state.getValue(VARIANT).ordinal());
	}

	@Override
	public int getSaplingDropChance(IBlockState state) {
		return state.getValue(VARIANT).getSaplingDropChance();
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return FilledList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal()));
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 30;
	}
	

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return Blocks.LEAVES.isOpaqueCube(state);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return !this.leavesFancy && blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(state, blockAccess, pos, side);
	}

	@Override
	public void registerModels() {
		IStateMapper stateMapper = new StateMap.Builder().ignore(CHECK_DECAY, DECAYABLE).build();
		ModelLoader.setCustomStateMapper(this, stateMapper);
		ModelUtil.registerToStateSingleVariant(this, VARIANT, stateMapper);
	}

}
