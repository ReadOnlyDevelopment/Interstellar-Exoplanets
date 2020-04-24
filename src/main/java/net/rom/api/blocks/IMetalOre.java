package net.rom.api.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public interface IMetalOre extends IStringSerializable {
    int getMeta();

    String getMetalName();

    default String[] getMetalNames() {

        return new String[]{getMetalName()};
    }

    boolean isAlloy();

    ItemStack getBlock();

    ItemStack getIngot();
}
