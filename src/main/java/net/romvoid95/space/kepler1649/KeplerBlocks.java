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
package net.romvoid95.space.kepler1649;

import net.minecraft.block.Block;
import net.romvoid95.common.block.terrain.BlockCrust;
import net.romvoid95.common.block.terrain.BlockCrust.RockType;
import net.romvoid95.common.block.terrain.BlockExoSand;
import net.romvoid95.common.block.terrain.foliage.BlockExoFoliage;
import net.romvoid95.core.initialization.ExoBlocks;

public class KeplerBlocks {

	public static void registerAll() {

		ExoBlocks.register(Kepler1649C.kepler_sand, "kepler_sand");
		ExoBlocks.register(Kepler1649B.KEPLERB_STONE, "keplerb_stone");
		ExoBlocks.register(Kepler1649B.KEPLERB_CLIFF_STONE, "keplerb_cliff_stone");
		ExoBlocks.register(Kepler1649B.KEPLERB_FOLIAGE_TALL, "keplerb_foliage_tall");
		

	}

	public static class Kepler1649B {
		public static final Block KEPLERB_STONE = new BlockCrust(RockType.METAMORPHIC);
		public static final Block KEPLERB_CLIFF_STONE = new BlockCrust(RockType.METAMORPHIC);
		public static final Block KEPLERB_FOLIAGE_TALL = new BlockExoFoliage();
		
	}

	public static class Kepler1649C {

		public static final Block kepler_sand = new BlockExoSand();


	}

}
