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

import com.readonlydev.common.block.ore.BlockOreMetal;
import com.readonlydev.core.registries.ExoplanetBlocks;
import com.readonlydev.core.registries.ExoplanetItems;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public enum EnumMetal implements IMetal {
	COPPER(0, "Copper"),
	TIN(1, "Tin"),
	LEAD(2, "Lead"),
	NICKEL(3, "Nickel"),
	PLATINUM(4, "Platinum"),
	ALUMINIUM(5, "Aluminium"),
	TITANIUM(6, "Titanium"),
	TUNGSTEN(7, "Tungsten"),
	SILVER(8, "Silver"),
	ZINC(9, "Zinc");

	public final int    meta;
	public final int    dimension;
	public final String name;

	EnumMetal(int meta, String name) {
		this(meta, name, 0);
	}

	EnumMetal(int meta, String name, int dimension) {
		this.meta      = meta;
		this.name      = name;
		this.dimension = dimension;
	}

	@Override
	public int getMeta () {
		return meta;
	}

	@Override
	public String getMetalName () {
		return name;
	}

	@Override
	public String[] getMetalNames () {
		if (this == ALUMINIUM)
			return new String[] { "Aluminium", "Aluminum" };
		return new String[] { getMetalName() };
	}

	@Override
	public String getName () {
		return name.toLowerCase(Locale.ROOT);
	}

	public static EnumMetal byMetadata (int meta) {
		if (meta < 0 || meta >= values().length) {
			meta = 0;
		}
		return values()[meta];
	}

	public IBlockState getOre () {
		return ExoplanetBlocks.METALORE.getDefaultState().withProperty(BlockOreMetal.METAL, this);
	}

	public IBlockState getOre (Block ore) {
		return ore.getDefaultState().withProperty(BlockOreMetal.METAL, this);
	}

	public int getDimension () {
		return dimension;
	}

	@Override
	public boolean isAlloy () {
		return false;
	}

	@Override
	public boolean isClad () {
		return false;
	}

	@Override
	public ItemStack getBlock () {
		return new ItemStack(ExoplanetBlocks.METALBLOCK, 1, meta);
	}

	@Override
	public ItemStack getIngot () {
		return new ItemStack(ExoplanetItems.METAL_INGOT, 1, meta);
	}

	@Override
	public ItemStack getDust () {
		return new ItemStack(ExoplanetItems.METAL_DUST, 1, meta);
	}

	@Override
	public ItemStack getSheet () {
		return new ItemStack(ExoplanetItems.METAL_SHEET, 1, meta);
	}

	@Override
	public ItemStack getGear () {
		return new ItemStack(ExoplanetItems.METAL_GEAR, 1, meta);
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
