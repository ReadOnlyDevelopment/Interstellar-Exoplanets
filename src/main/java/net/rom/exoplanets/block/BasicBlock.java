package net.rom.exoplanets.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.internal.block.BlockBase;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BasicBlock extends BlockBase {

	public BasicBlock() {
		super(Material.ROCK);
		setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
	}
	
	public ItemStack getStack(int count) {
		return new ItemStack(this, count);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this);
	}
}
