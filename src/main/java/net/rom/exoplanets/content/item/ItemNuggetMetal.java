package net.rom.exoplanets.content.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.Item;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ItemNuggetMetal extends ItemBaseMetal {

    public ItemNuggetMetal() {
        super("nugget", "nugget");
        setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
    }

    @Override
    public List<IMetal> getMetals(Item item) {
        return Arrays.asList(EnumMetal.values());
    }
}
