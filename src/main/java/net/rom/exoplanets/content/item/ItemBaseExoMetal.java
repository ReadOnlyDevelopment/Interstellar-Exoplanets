package net.rom.exoplanets.content.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.rom.exoplanets.internal.client.ICustomModel;

public abstract class ItemBaseExoMetal extends Item implements IExoMetal, ICustomModel {
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (!this.isInCreativeTab(tab)) return;
        for (ItemStack stack : getSubItems(this))
                list.add(stack);
    }
}
