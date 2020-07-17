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

package net.rom.exoplanets.content.block.terrain;

import net.minecraft.block.SoundType;
import net.rom.exoplanets.content.block.BlockTerrain;

/**
 * Igneous rock, or magmatic rock, is one of the three main rock types, the
 * others being sedimentary and metamorphic. Igneous rock is formed through the
 * cooling and solidification of magma or lava. The magma can be derived from
 * partial melts of existing rocks in either a planet's mantle or crust.
 * <br><br>
 * (jeez sounds JUST like Obsidian DONT IT, Because Obsidian is a Igneous Rock, Thanks Wiki)
 * 
 *
 */
public class BlockIgneousRock extends BlockTerrain {

	public BlockIgneousRock() {
		super();
		this.setResistance(20.0F);
		this.setHardness(30.0F);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 3);
	}
}
