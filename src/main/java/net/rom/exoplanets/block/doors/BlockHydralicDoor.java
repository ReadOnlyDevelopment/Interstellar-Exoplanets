package net.rom.exoplanets.block.doors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rom.exoplanets.ExoplanetsCustomSounds;

public class BlockHydralicDoor extends BlockCustomDoor {

	public BlockHydralicDoor() {
		super();
		setHardness(3);
		setResistance(20);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	protected SoundEvent getOpenSound() {
		return ExoplanetsCustomSounds.HYDOOR_OPEN;
	}

	@Override
	protected SoundEvent getCloseSound() {
		return ExoplanetsCustomSounds.HYDOOR_CLOSE;
	}
}
