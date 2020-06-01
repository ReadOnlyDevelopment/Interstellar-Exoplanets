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
import net.rom.exoplanets.init.ExoItems;

public enum EnumByproduct implements IMetal {
	MOLYBDENUM(15, "Molybdenum");
	
    public final int meta;
    public final String name;

    EnumByproduct(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }
    
    public static EnumByproduct byMetadata(int meta) {
        if (meta < 0 || meta >= values().length) {
            meta = 0;
        }
        return values()[meta];
    }

	@Override
	public String getName() {
		return name.toLowerCase(Locale.ROOT);
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
        return new String[]{getMetalName()};
    }

	@Override
	public boolean isAlloy() {
		return false;
	}

	@Override
	public ItemStack getBlock() {
		return null;
	}

	@Override
	public ItemStack getIngot() {
		return null;
	}

	@Override
	public ItemStack getNugget() {
		return null;
	}

	@Override
	public ItemStack getDust() {
		return new ItemStack(ExoItems.metalDust, 1, meta);
	}
	

	public ItemStack getDust(int amount) {
		return new ItemStack(ExoItems.metalDust, amount, meta);
	}

	@Override
	public ItemStack getPlate() {
		return null;
	}

	@Override
	public ItemStack getGear() {
		return null;
	}

}
