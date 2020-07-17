/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
