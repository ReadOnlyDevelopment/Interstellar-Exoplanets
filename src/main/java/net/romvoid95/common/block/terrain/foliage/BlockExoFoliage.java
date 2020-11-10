package net.romvoid95.common.block.terrain.foliage;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.client.CreativeExoTabs;
import net.romvoid95.common.block.EnumPlant;
import net.romvoid95.core.initialization.ExoBlocks;

public class BlockExoFoliage extends BlockBush implements IShearable, IPlantable {

	public static final IProperty<EnumPlant> VARIANT = PropertyEnum.create("variant", EnumPlant.class);

	private Block grassBlock;

	public BlockExoFoliage() {
		super(Material.PLANTS);
		this.setTickRandomly(true);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_TAB);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		EnumPlant variant = EnumPlant.values()[MathHelper.clamp(meta, 0, EnumPlant.values().length)];
		return getDefaultState().withProperty(VARIANT, variant);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleUpdate(pos, this, world.rand.nextInt(50) + 20);
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return getDefaultState();
		return state;
	}

	public static boolean canPlaceRootAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.up());
		if (state.getMaterial() == Material.GROUND || state.getMaterial() == Material.GRASS) {
			// can always hang below dirt blocks
			return true;
		} else {
			return (state.getBlock() == ExoBlocks.EXO_PLANT);
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		return (state.getBlock().isAir(state, world, pos) || state.getMaterial().isReplaceable())
				&& canBlockStay(world, pos, state);
	}

	@Override
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.NONE;
	}

	/**
	 * Called when a neighboring block was changed and marks that this state should
	 * perform any checks during a neighbor change. Cases may include when redstone
	 * power is updated, cactus blocks popping off due to a neighboring solid block,
	 * etc.
	 */
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		this.checkAndDropBlock(worldIn, pos, state);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		this.checkAndDropBlock(worldIn, pos, state);
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos, state)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		/*
		 * Comment from superclass: Forge: This function is called during world gen and
		 * placement, before this block is set, so if we are not 'here' then assume it's
		 * the pre-check. Therefore, we just take the OR of all the conditions below as
		 * the most general "can block stay" check
		 */
		if (state.getBlock() != this) {
			return BlockExoFoliage.canPlaceRootAt(world, pos)
					|| soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this)
					|| soil.isSideSolid(world, pos, EnumFacing.UP)
					|| ((world.getLight(pos) >= 3 || world.canSeeSky(pos)) && soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this));
		} else {
			switch (state.getValue(VARIANT)) {
			case FORESTGRASS:
			case DEADBUSH:
				return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this);
			default:
				return (world.getLight(pos) >= 3 || world.canSeeSky(pos)) && soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this);
			}
		}
	}

	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == grassBlock;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
		
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for
	 * render
	 */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = NonNullList.create();

		switch (state.getValue(VARIANT)) {
			case FORESTGRASS:
			case DEADBUSH:
				break;
			default:
				ret.add(new ItemStack(this, 1, damageDropped(state)));
				break;
		}

		return ret;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	/**
	 * Get the geometry of the queried face at the given position and state. This is
	 * used to decide whether things like buttons are allowed to be placed on the
	 * face, or how glass panes connect to the face, among other things.
	 * <p>
	 * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED},
	 * which represents something that does not fit the other descriptions and will
	 * generally cause other things not to connect to the face.
	 * 
	 * @return an approximation of the form of the given face
	 */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		List<ItemStack> ret = NonNullList.create();
		ret.add(new ItemStack(this, 1, damageDropped(world.getBlockState(pos))));
		return ret;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
		// do not call normal harvest if the player is shearing
		if (world.isRemote || stack.getItem() != Items.SHEARS) {
			super.harvestBlock(world, player, pos, state, te, stack);
		}
	}

	@Override
	public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
		int n = EnumPlant.values().length;
		for (int i = 0; i < n; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		IBlockState blockState = world.getBlockState(pos);
		if (blockState.getBlock() == this) {
			switch (blockState.getValue(VARIANT)) {

				default:
					return EnumPlantType.Plains;
			}
		}
		return EnumPlantType.Plains;
	}
	
	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return super.getExtendedState(state, world, pos);
	}
}
