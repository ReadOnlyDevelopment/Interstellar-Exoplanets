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
import net.minecraft.util.IStringSerializable;
import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.init.ExoItems;

public enum EnumAlloy implements IStringSerializable, IMetal {
	BRONZE(0, "Bronze"),
	BRASS(1, "Brass"),
	STEEL(2, "Steel"),
	A2219(3, "A2219");

	private final int    meta;
	private final String name;

	EnumAlloy(int meta, String name) {
		this.meta = meta;
		this.name = name;
	}

	public int getMeta () {
		return meta;
	}

	@Override
	public String getMetalName () {
		return name;
	}

	@Override
	public String getName () {
		return name.toLowerCase(Locale.ROOT);
	}

	public static EnumAlloy byMetadata (int meta) {
		if (meta < 0 || meta >= values().length) {
			meta = 0;
		}
		return values()[meta];
	}

	@Override
	public boolean isAlloy () {
		return true;
	}

	@Override
	public boolean isClad () {
		return false;
	}

	public ItemStack getBlock () {
		return new ItemStack(ExoBlocks.alloyBlock, 1, meta);
	}

	public ItemStack getIngot () {
		return new ItemStack(ExoItems.alloyIngot, 1, meta);
	}

	public ItemStack getDust () {
		return new ItemStack(ExoItems.alloyDust, 1, meta);
	}

	@Override
	public ItemStack getSheet () {
		return new ItemStack(ExoItems.sheetAlloy, 1, meta);
	}

	@Override
	public ItemStack getGear () {
		return new ItemStack(ExoItems.gearAlloy, 1, meta);
	}

	@Override
	public ItemStack getPlate () {
		return null;
	}

	@Override
	public ItemStack getPile () {
		return null;
	}
}
