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
package com.readonlydev.space.wolf1061.c.worldgen;

import com.readonlydev.space.generation.biome.BiomeData.DataBuilder;

public class BiomeGenWolf1061C extends Wolf1061Biomes {

	public BiomeGenWolf1061C() {
        super(new DataBuilder("Wolf1061 C Plains")
        		.temp(0.2F)
        		.baseHeight(2.5F)
        		.heightVariation(0.645F)
        		.build());
	}

}
