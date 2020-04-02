package net.rom.stellar.block;

import net.minecraft.block.material.Material;
import net.rom.core.block.BlockBaseRO;
import net.rom.stellar.tabs.CreativeExoTabs;

public class BasicBlock extends BlockBaseRO {

	public BasicBlock(Material materialIn) {
		super(materialIn);
		setCreativeTab(CreativeExoTabs.TERRAIN);
	}

}
