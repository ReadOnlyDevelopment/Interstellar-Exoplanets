package net.rom.exoplanets.content.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.Item;
import net.rom.exoplanets.content.EnumAlloy;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ItemNuggetAlloy extends ItemBaseMetal {

    public ItemNuggetAlloy() {
        super("nugget", "nugget");
        setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
    }

    @Override
    public List<IMetal> getMetals(Item item) {
    	return Arrays.asList(EnumAlloy.values());
    }
}
