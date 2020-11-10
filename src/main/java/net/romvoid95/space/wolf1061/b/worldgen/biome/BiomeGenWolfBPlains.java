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

package net.romvoid95.space.wolf1061.b.worldgen.biome;

import net.romvoid95.space.astrogeneration.biome.BiomeData.DataValues;
import net.romvoid95.space.wolf1061.Wolf1061Blocks;
import net.romvoid95.space.wolf1061.b.worldgen.WolfBBiomes;

public class BiomeGenWolfBPlains extends WolfBBiomes {

	public BiomeGenWolfBPlains () {
        super(new DataValues("Wolf1061 B Plains")
        		.temperature(0.8F)
        		.baseHeight(0.1F)
        		.heightVariation(0.025F)
        		.finalzie());
		this.topBlock    = Wolf1061Blocks.Wolf1061B.WOLFB_STONE.getDefaultState();
		this.fillerBlock = Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState();
	}
}
