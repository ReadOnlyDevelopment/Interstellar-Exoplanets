package net.rom.exoplanets.content;

import java.util.Locale;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.content.block.ore.BlockOreMetal;
import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.init.ExoItems;

public enum EnumMetal implements IMetal {
    COPPER(0, "Copper"),
    TIN(1, "Tin"),
    LEAD(2, "Lead"),
    NICKEL(3, "Nickel"),
    PLATINUM(4, "Platinum"),
    ALUMINIUM(5, "Aluminium"),
    TITANIUM(6, "Titanium"),
	TUNGSTEN(7, "Tungsten");

    public final int meta;
    public final int dimension;
    public final String name;

    EnumMetal(int meta, String name) {
        this(meta, name, 0);
    }

    EnumMetal(int meta, String name, int dimension) {
        this.meta = meta;
        this.name = name;
        this.dimension = dimension;
    }

    @Override
    public int getMeta() {
        return meta;
    }

    @Override
    public String getMetalName() {
        return name;
    }

    @Override
    public String[] getMetalNames() {
        if (this == ALUMINIUM)
            return new String[]{"Aluminium", "Aluminum"};
        return new String[]{getMetalName()};
    }

    @Override
    public String getName() {
        return name.toLowerCase(Locale.ROOT);
    }

    public static EnumMetal byMetadata(int meta) {
        if (meta < 0 || meta >= values().length) {
            meta = 0;
        }
        return values()[meta];
    }

    public IBlockState getOre() {
        return ExoBlocks.metalOre.getDefaultState().withProperty(BlockOreMetal.METAL, this);
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public ItemStack getBlock() {
        return new ItemStack(ExoBlocks.metalBlock, 1, meta);
    }

    @Override
    public ItemStack getIngot() {
        return new ItemStack(ExoItems.metalIngot, 1, meta);
    }

    @Override
    public ItemStack getNugget() {
        return new ItemStack(ExoItems.metalNugget, 1, meta);
    }

    @Override
    public ItemStack getDust() {
        if (this == TUNGSTEN) {
        	return new ItemStack(ExoItems.dustByproduct, 1, meta);
        } else {
            return new ItemStack(ExoItems.metalDust, 1, meta);
        }
    }

    @Override
    public boolean isAlloy() {
        return false;
    }

    @Override
    public ItemStack getPlate() {
        return new ItemStack(ExoItems.plateBasic, 1, meta);
    }

    @Override
    public ItemStack getGear() {
        return new ItemStack(ExoItems.gearBasic, 1, meta);
    }

    public ItemStack getBonus() {
        switch (this) {
            case ALUMINIUM:
                return ALUMINIUM.getDust();
            case NICKEL:
                return PLATINUM.getDust();
            case PLATINUM:
                return TITANIUM.getDust();
            case TUNGSTEN:
            	return EnumByproduct.MOLYBDENUM.getDust();
            default:
                return null;
        }
    }
}
