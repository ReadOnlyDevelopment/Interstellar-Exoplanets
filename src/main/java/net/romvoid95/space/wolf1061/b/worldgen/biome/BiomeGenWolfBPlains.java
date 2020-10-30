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

import net.romvoid95.space.astrogeneration.biome.BiomeData;
import net.romvoid95.space.wolf1061.Wolf1061Blocks;
import net.romvoid95.space.wolf1061.b.worldgen.WolfBBiomes;

public class BiomeGenWolfBPlains extends WolfBBiomes {

	public BiomeGenWolfBPlains (String name, float height, float variation) {
		super(new BiomeData.BiomeDataBuilder()
				.biomeName(name)
				.rainfall(0F)
				.rainEnabled(false)
				.baseHeight(height)
				.heightVariation(variation));
		this.topBlock    = Wolf1061Blocks.Wolf1061B.WOLFB_STONE.getDefaultState();
		this.fillerBlock = Wolf1061Blocks.Wolf1061B.WOLFB_COBBLESTONE.getDefaultState();
	}
}
