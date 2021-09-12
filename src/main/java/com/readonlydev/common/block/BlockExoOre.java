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

package com.readonlydev.common.block;

import com.readonlydev.api.repcie.IRecipeProcessor;
import com.readonlydev.lib.block.BlockSubtype;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public abstract class BlockExoOre extends BlockSubtype implements IRecipeProcessor {

	public final int maxMeta;

	public BlockExoOre(int subBlockCount) {
		super(Material.ROCK, subBlockCount);
		this.maxMeta = subBlockCount;
		this.setSoundType(SoundType.STONE);
	}

}
