package net.rom.exoplanets.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.internal.block.BlockBase;

public class BlockDecoration extends BlockBase {

	public BlockDecoration(Material materialIn) {
		super(materialIn);
		setDefaultState(blockState.getBaseState());
	}
	
    public ItemStack getStack(int count) {
        return new ItemStack(this, count);
    }
    
    public Block getBlockFromState(IBlockState state) {
    	return state.getBlock();
    }

}
