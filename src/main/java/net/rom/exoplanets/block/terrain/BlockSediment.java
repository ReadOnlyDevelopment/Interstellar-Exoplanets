package net.rom.exoplanets.block.terrain;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockSediment extends BlockFalling implements ISortableBlock {
	
	public BlockSediment() {
		super(Material.SAND);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);

	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}
}
