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

package net.romvoid95.common.block.terrain;

import net.minecraft.block.SoundType;
import net.romvoid95.common.block.BlockTerrain;

public class BlockMetamorphicRock extends BlockTerrain {
	
	public BlockMetamorphicRock() {
		super();
		this.setHardness(25.0F);
		this.setResistance(15.0F);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 2);
	}

}
