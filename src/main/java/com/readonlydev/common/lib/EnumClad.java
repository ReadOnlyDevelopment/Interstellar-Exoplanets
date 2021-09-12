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

import net.minecraft.item.ItemStack;

public enum EnumClad implements IMetal {
	CCA(0, "Copper-Clad Aluminium ");

	private final int    meta;
	private final String name;

	EnumClad(int meta, String name) {
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

	public static EnumClad byMetadata (int meta) {
		if (meta < 0 || meta >= values().length) {
			meta = 0;
		}
		return values()[meta];
	}

	@Override
	public boolean isAlloy () {
		return false;
	}

	@Override
	public boolean isClad () {
		return true;
	}

	@Override
	public ItemStack getBlock () {
		return null;
	}

	@Override
	public ItemStack getIngot () {
		return null;
	}

	@Override
	public ItemStack getDust () {
		return null;
	}

	@Override
	public ItemStack getSheet () {
		return null;
	}

	@Override
	public ItemStack getPlate () {
		return null;
	}

	@Override
	public ItemStack getGear () {
		return null;
	}

	@Override
	public ItemStack getPile () {
		return null;
	}

}
