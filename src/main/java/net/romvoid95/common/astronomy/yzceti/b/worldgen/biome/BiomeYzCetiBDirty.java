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

package net.romvoid95.common.astronomy.yzceti.b.worldgen.biome;

import net.romvoid95.common.world.helpers.EnumBiomeType;
import net.romvoid95.core.ExoBlock;

public class BiomeYzCetiBDirty extends BiomeYzCetiBBase {

	public static int grassFoilageColorMultiplier = 0x000000;

	public BiomeYzCetiBDirty(BiomeProperties props) {
		super("dirty", props);
		props.setRainDisabled();
		props.setBaseHeight(2.0F);
		props.setHeightVariation(1.2F);
		props.setTemperature(2.0F);
		this.setTemp(2F);
		this.setBiomeHeight(66);
		this.setBiomeType(EnumBiomeType.ABANDONED);
		this.topBlock    = ExoBlock.YZB_LOOSE_SEDIMENT;
		this.fillerBlock = ExoBlock.YZB_SEDIMENTARYROCK;
		this.stoneBlock  = ExoBlock.YZB_METAMORPHIC.getBlock();
	}
}