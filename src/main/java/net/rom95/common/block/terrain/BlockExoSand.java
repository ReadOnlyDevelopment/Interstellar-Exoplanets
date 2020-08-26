package net.rom95.common.block.terrain;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.rom95.client.CreativeExoTabs;

public class BlockExoSand extends BlockFalling {
	
	public BlockExoSand() {
		super(Material.SAND);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
	}
}