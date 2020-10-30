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
package net.romvoid95.common.lib.block;

import net.minecraft.block.material.Material;

public enum EnumPlantType {
	BUSH(Material.VINE, true, true),
	FLOWER(Material.PLANTS, false, false);

	private final Material material;
	private final boolean  shearable;
	private final boolean  replacable;

	EnumPlantType(Material material, boolean shearable, boolean replacable) {
		this.material   = material;
		this.shearable  = shearable;
		this.replacable = replacable;
	}

	public Material getMaterial () {
		return this.material;
	}

	public boolean isShearable () {
		return this.shearable;
	}

	public boolean isReplacable () {
		return this.replacable;
	}

}
