package net.rom.exoplanets.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockElectronic extends ItemBlock {

    public ItemBlockElectronic(Block block) {
	super(block);
	this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName() {
	return this.getBlock().getUnlocalizedName() + ".0";
    }

}
