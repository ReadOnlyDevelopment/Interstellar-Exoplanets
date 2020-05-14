package net.rom.exoplanets.internal.tile;

import net.minecraft.item.ItemStack;

public interface IFuelBurner {
    boolean feedFuel(ItemStack stack);

    boolean hasFuel();

    boolean tickFuel(boolean isProcessing);
}
