package net.rom.api.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public interface IOre extends IStringSerializable {
	
	IBlockState getOre();

	int getDimension();
}
