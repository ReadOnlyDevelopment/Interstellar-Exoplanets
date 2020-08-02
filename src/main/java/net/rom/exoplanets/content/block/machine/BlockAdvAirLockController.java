package net.rom.exoplanets.content.block.machine;

import micdoodle8.mods.galacticraft.core.blocks.BlockAdvancedTile;
import micdoodle8.mods.galacticraft.core.items.IShiftDescription;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.content.tile.TileEntityAdvAirLockController;
import net.rom.exoplanets.internal.inerf.IAddRecipe;

public class BlockAdvAirLockController extends BlockAdvancedTile implements IAddRecipe, IShiftDescription {

	public BlockAdvAirLockController() {
		super(Material.ROCK);
	}

	@Override
	public boolean canPlaceBlockAt (World worldIn, BlockPos pos) {
		return true;
	}

	@Override
	public void onBlockPlacedBy (World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

	}

	@Override
	public TileEntity createTileEntity (World world, IBlockState state) {

		return new TileEntityAdvAirLockController();

	}

	@Override
	public boolean canConnectRedstone (IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public boolean onMachineActivated (World world, BlockPos pos, IBlockState state, EntityPlayer entityPlayer, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileEntityAdvAirLockController) {
			entityPlayer.openGui(ExoplanetsMod.instance, 2, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}

		return false;
	}

	@Override
	public void breakBlock (World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tile = worldIn.getTileEntity(pos);

		if (tile instanceof TileEntityAdvAirLockController) {
			((TileEntityAdvAirLockController) tile).unsealAirLock();
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public String getShiftDescription (int itemDamage) {
		return GCCoreUtil.translate(this.getUnlocalizedName() + ".description");
	}

	@Override
	public boolean showDescription (int itemDamage) {
		return true;
	}

}
