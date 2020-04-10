package net.rom.exoplanets.internal.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockMetaSubtypes extends Block{
	
	private final int subtypeCount;

	public BlockMetaSubtypes(Material materialIn, int subtypeCount) {
		super(materialIn);
		this.subtypeCount = subtypeCount;
	}
	
    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

	public int getSubtypeCount() {
		return subtypeCount;
	}

}
