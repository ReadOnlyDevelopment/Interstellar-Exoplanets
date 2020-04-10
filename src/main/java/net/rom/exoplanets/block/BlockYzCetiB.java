package net.rom.exoplanets.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockYzCetiB extends BasicBlock {

	public BlockYzCetiB() {
		super(Material.ROCK);
		setHardness(3.0f);
		setResistance(30.0f);
		setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	}

	public ItemStack getStack(int count) {
		return new ItemStack(this, count);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this);
	}
}
