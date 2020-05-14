package net.rom.exoplanets.content.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.content.EnumByproduct;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ItemDustMetal extends ItemBaseMetal {

    public ItemDustMetal() {
        super("dust", "dust");
        setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
    }

    @Override
    public List<IMetal> getMetals(Item item) {
		List<IMetal> metals = Lists.newArrayList();
		metals.addAll(Arrays.asList(EnumMetal.values()));
    	return metals;
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {
        for (IMetal metal : getMetals(this)) {
            ItemStack dust = metal.getDust();
            ItemStack ingot = metal.getIngot();
                recipes.addSmelting(dust, ingot, 0.6f);
        }
    }
}
