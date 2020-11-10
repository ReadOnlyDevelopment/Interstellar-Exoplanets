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
package net.romvoid95.space.yzceti.b.worldgen.biomes;

import net.romvoid95.space.astrogeneration.biome.BiomeData.DataValues;
import net.romvoid95.space.yzceti.YzCetiBlocks;
import net.romvoid95.space.yzceti.b.worldgen.YzCetiBBiomes;

public class BiomeCetiBPlains extends YzCetiBBiomes {

	public BiomeCetiBPlains() {
		super(new DataValues("YzCeti B Plains")
        		.temperature(0.8F)
        		.baseHeight(0.125F)
        		.heightVariation(0.5F)
        		.finalzie());
		this.topBlock = YzCetiBlocks.B.YZB_METAMORPHIC.getDefaultState();
		this.fillerBlock = YzCetiBlocks.B.YZB_IGNEOUS.getDefaultState();
		}

}
