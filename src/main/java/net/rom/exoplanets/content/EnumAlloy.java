package net.rom.exoplanets.content;

import java.util.Locale;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.init.ExoItems;

public enum EnumAlloy implements IStringSerializable, IMetal {
    BRONZE(0, "Bronze"),
    BRASS(1, "Brass"),
    STEEL(2, "Steel");
	

    private final int meta;
    private final String name;

    EnumAlloy(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public int getMeta() {
        return meta;
    }

    @Override
    public String getMetalName() {
        return name;
    }

    @Override
    public String getName() {
        return name.toLowerCase(Locale.ROOT);
    }

    public static EnumAlloy byMetadata(int meta) {
        if (meta < 0 || meta >= values().length) {
            meta = 0;
        }
        return values()[meta];
    }

    public ItemStack getBlock() {
        return new ItemStack(ExoBlocks.alloyBlock, 1, meta);
    }

    public ItemStack getIngot() {
        return new ItemStack(ExoItems.alloyIngot, 1, meta);
    }

    public ItemStack getNugget() {
        return new ItemStack(ExoItems.alloyNugget, 1, meta);
    }

    public ItemStack getDust() {
        return new ItemStack(ExoItems.alloyDust, 1, meta);
    }

    @Override
    public boolean isAlloy() {
        return true;
    }

    @Override
    public ItemStack getPlate() {
        return new ItemStack(ExoItems.plateAlloy, 1, meta);
    }

    @Override
    public ItemStack getGear() {
        return new ItemStack(ExoItems.gearAlloy, 1, meta);
    }
}
