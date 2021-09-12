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
package com.readonlydev.space.yzceti.c.worldgen.biomes;

import com.readonlydev.space.generation.biome.BiomeData.DataBuilder;
import com.readonlydev.space.yzceti.YzCetiBlocks;
import com.readonlydev.space.yzceti.c.worldgen.YzCetiCBiomes;

public class BiomeGenYzCetiC extends YzCetiCBiomes {

	public BiomeGenYzCetiC() {
        super(new DataBuilder("YzCeti C Highlands")
        		.temp(0.2F)
        		.baseHeight(1.1F)
        		.heightVariation(0.765F)
        		.build());
		this.topBlock = YzCetiBlocks.C.YZC_METAMORPHIC.getDefaultState();
		this.fillerBlock = YzCetiBlocks.C.YZC_IGNEOUS.getDefaultState();	
		}

}
