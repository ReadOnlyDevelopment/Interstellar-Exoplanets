/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
