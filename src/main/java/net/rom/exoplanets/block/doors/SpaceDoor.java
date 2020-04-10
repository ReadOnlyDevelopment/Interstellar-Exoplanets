package net.rom.exoplanets.block.doors;

import net.minecraft.block.SoundType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.InterstellarSounds;
import net.rom.exoplanets.block.InterstellarDoor;

public class SpaceDoor extends InterstellarDoor {

	public SpaceDoor() {
		super();
		setHardness(3);
		setResistance(20);
		blockSoundType = SoundType.GLASS;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	protected SoundEvent getOpenSound() {
		return InterstellarSounds.SPACEDOOR_OPEN;
	}

	@Override
	protected SoundEvent getCloseSound() {
		return InterstellarSounds.SPACEDOOR_OPEN;
	}
}
