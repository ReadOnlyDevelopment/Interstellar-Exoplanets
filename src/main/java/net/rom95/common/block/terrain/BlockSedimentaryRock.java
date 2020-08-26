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

package net.rom95.common.block.terrain;

import net.rom95.common.block.BlockTerrain;

/**
 * Sedimentary rocks are types of rock that are formed by the accumulation or
 * deposition of small particles and subsequent cementation of mineral or
 * organic particles on the floor of oceans or other bodies of water
 * 
 */
public class BlockSedimentaryRock extends BlockTerrain {
	
	public BlockSedimentaryRock() {
		super();
		this.setResistance(1.0F);
		this.setHardness(2.0F);
		this.setHarvestLevel("pickaxe", 1);
	}
}
