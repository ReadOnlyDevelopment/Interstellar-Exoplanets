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

package net.rom.exoplanets.astronomy.yzceti.b.worldgen.biome;

import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.internal.enums.EnumBiomeType;


public class BiomeYzCetiB extends BiomeYzCetiBBase {
	
	public BiomeYzCetiB(BiomeProperties props) {
		super("yzb", props);
		props.setRainDisabled();
		props.setBaseHeight(0.6F);
		props.setHeightVariation(1.2F);
		props.setTemperature(5.0F);
		this.setTemp(5F);
		this.setBiomeHeight(65);
		this.setBiomeType(EnumBiomeType.DARK);
		this.topBlock = YzCetiBlocks.CetiB.B_DARK_LOOSE_SEDIMENT.getDefaultState();
		this.fillerBlock = YzCetiBlocks.CetiB.B_METAMORPHIC.getDefaultState();
		this.stoneBlock = YzCetiBlocks.CetiB.B_IGNEOUS;
	}
}