package net.rom.exoplanets.content.item;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.rom.exoplanets.content.EnumByproduct;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ItemDustByproduct extends ItemBaseMetal {

    public ItemDustByproduct() {
        super("dust", "dust");
        setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
    }

    @Override
    public List<IMetal> getMetals(Item item) {
		List<IMetal> metals = Lists.newArrayList();
		metals.addAll(Arrays.asList(EnumByproduct.values()));
    	return metals;
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {

    }
}
