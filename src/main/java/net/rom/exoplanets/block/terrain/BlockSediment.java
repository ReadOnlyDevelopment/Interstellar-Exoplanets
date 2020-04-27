package net.rom.exoplanets.block.terrain;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockSediment extends BlockFalling {
	public BlockSediment() {
		super(Material.SAND);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
	}
}
