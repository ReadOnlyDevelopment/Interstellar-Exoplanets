package net.rom.exoplanets.block;

import net.minecraft.block.material.Material;
import net.rom.exoplanets.internal.block.BlockBase;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BasicBlock extends BlockBase {

	public BasicBlock(Material materialIn) {
		super(materialIn);
		setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
	}

}
