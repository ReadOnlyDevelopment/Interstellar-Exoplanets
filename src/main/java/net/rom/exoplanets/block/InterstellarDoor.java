package net.rom.exoplanets.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class InterstellarDoor extends BlockDoor {

	protected InterstellarDoor() {
		super(Material.IRON);
		setHardness(3);
		setResistance(20);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (blockMaterial == Material.IRON)
			return false; // Allow items to interact with the door
		else {
			BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
			IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() != this)
				return false;
			else {
				state = iblockstate.cycleProperty(OPEN);
				worldIn.setBlockState(blockpos, state, 10);
				worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
				worldIn.playSound(null, pos, getDoorSoundEvent(state), SoundCategory.BLOCKS, 1.0F,
						(worldIn.rand.nextFloat() * 0.1F) + 0.9F);
				return true;
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
			BlockPos blockpos = pos.down();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() != this) {
				worldIn.setBlockToAir(pos);
			} else if (blockIn != this) {
				iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
			}
		} else {
			boolean flag1 = false;
			BlockPos blockpos1 = pos.up();
			IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

			if (iblockstate1.getBlock() != this) {
				worldIn.setBlockToAir(pos);
				flag1 = true;
			}

			if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
				worldIn.setBlockToAir(pos);
				flag1 = true;

				if (iblockstate1.getBlock() == this) {
					worldIn.setBlockToAir(blockpos1);
				}
			}

			if (flag1) {
				if (!worldIn.isRemote) {
					dropBlockAsItem(worldIn, pos, state, 0);
				}
			} else {
				boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos1);

				if ((blockIn != this) && (flag || blockIn.getDefaultState().canProvidePower())
						&& (flag != iblockstate1.getValue(POWERED).booleanValue())) {
					worldIn.setBlockState(blockpos1, iblockstate1.withProperty(POWERED, Boolean.valueOf(flag)), 2);

					if (flag != state.getValue(OPEN).booleanValue()) {
						worldIn.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
						worldIn.markBlockRangeForRenderUpdate(pos, pos);
						worldIn.playSound(null, pos, getDoorSoundEvent(iblockstate1), SoundCategory.BLOCKS, 1.0F,
								(worldIn.rand.nextFloat() * 0.1F) + 0.9F);
					}
				}
			}
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos,
			EntityPlayer player) {
		return getItem(worldIn, pos, state);
	}

	protected SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_WOODEN_DOOR_CLOSE;
	}

	protected SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_WOODEN_DOOR_CLOSE;
	}

	private SoundEvent getDoorSoundEvent(IBlockState state) {
		return (state.getValue(OPEN)).booleanValue() ? getOpenSound() : getCloseSound();
	}

}
