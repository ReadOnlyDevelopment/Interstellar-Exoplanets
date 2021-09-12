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

package com.readonlydev.common.lib;

import java.util.Locale;

import com.readonlydev.core.registries.ExoplanetBlocks;
import com.readonlydev.core.registries.ExoplanetItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

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
		return new ItemStack(ExoplanetBlocks.ALLOYBLOCK, 1, meta);
	}

	public ItemStack getIngot () {
		return new ItemStack(ExoplanetItems.ALLOY_INGOT, 1, meta);
	}

	public ItemStack getDust () {
		return new ItemStack(ExoplanetItems.ALLOY_DUST, 1, meta);
	}

	@Override
	public ItemStack getSheet () {
		return new ItemStack(ExoplanetItems.ALLOY_SHEET, 1, meta);
	}

	@Override
	public ItemStack getGear () {
		return new ItemStack(ExoplanetItems.ALLOY_GEAR, 1, meta);
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
