package net.rom.exoplanets.content.item;

import net.minecraft.item.Item;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class ItemBase extends Item {

    private String name;

    public ItemBase() {
        setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
    }

    public ItemBase(String name) {
        this.setUnlocalizedName(name);
    }

    public String getName() {
        return this.name;
    }
}
