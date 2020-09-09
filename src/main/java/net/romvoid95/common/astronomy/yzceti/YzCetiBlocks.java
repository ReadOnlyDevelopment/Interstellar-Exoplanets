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

package net.romvoid95.common.astronomy.yzceti;

import net.minecraft.block.Block;
import net.romvoid95.common.block.terrain.BlockGravel;
import net.romvoid95.common.block.terrain.BlockIgneousRock;
import net.romvoid95.common.block.terrain.BlockMetamorphicRock;
import net.romvoid95.common.block.terrain.BlockSediment;
import net.romvoid95.common.block.terrain.BlockSedimentaryRock;
import net.romvoid95.core.initialization.ExoBlocks;

public class YzCetiBlocks {

	public static void registerAll () {

		// CetiB
		ExoBlocks.register(YzCetiB.YZB_LOOSE_SEDIMENT, "yzb_loose_sediment");
		ExoBlocks.register(YzCetiB.YZB_DARK_LOOSE_SEDIMENT, "yzb_dark_loose_sediment");
		ExoBlocks.register(YzCetiB.YZB_GRAVEL, "yzb_gravel");
		ExoBlocks.register(YzCetiB.YZB_IGNEOUS, "yzb_igneous");
		ExoBlocks.register(YzCetiB.YZB_METAMORPHIC, "yzb_metamorphic");
		ExoBlocks.register(YzCetiB.YZB_SEDIMENTARYROCK, "yzb_sedimentary_rock");
		// CetiC
		ExoBlocks.register(YzCetiC.YZC_LOOSE_SEDIMENT, "yzc_loose_sediment");
		ExoBlocks.register(YzCetiC.YZC_DARK_LOOSE_SEDIMENT, "yzc_dark_loose_sediment");
		ExoBlocks.register(YzCetiC.YZC_GRAVEL, "yzc_gravel");
		ExoBlocks.register(YzCetiC.YZC_IGNEOUS, "yzc_igneous");
		ExoBlocks.register(YzCetiC.YZC_METAMORPHIC, "yzc_metamorphic");
		ExoBlocks.register(YzCetiC.YZC_SEDIMENTARYROCK, "yzc_sedimentary_rock");
		// CetiD
		ExoBlocks.register(YzCetiD.YZD_LOOSE_SEDIMENT, "yzd_loose_sediment");
		ExoBlocks.register(YzCetiD.YZD_DARK_LOOSE_SEDIMENT, "yzd_dark_loose_sediment");
		ExoBlocks.register(YzCetiD.YZD_GRAVEL, "yzd_gravel");
		ExoBlocks.register(YzCetiD.YZD_IGNEOUS, "yzd_igneous");
		ExoBlocks.register(YzCetiD.YZD_METAMORPHIC, "yzd_metamorphic");
		ExoBlocks.register(YzCetiD.YZD_SEDIMENTARYROCK, "yzd_sedimentary_rock");
	}

	public static class YzCetiB {

		public static Block YZB_LOOSE_SEDIMENT      = new BlockSediment();
		public static Block YZB_DARK_LOOSE_SEDIMENT = new BlockSediment();
		public static Block YZB_GRAVEL              = new BlockGravel();
		public static Block YZB_IGNEOUS             = new BlockIgneousRock();
		public static Block YZB_METAMORPHIC         = new BlockMetamorphicRock();
		public static Block YZB_SEDIMENTARYROCK     = new BlockSedimentaryRock();

	}

	public static class YzCetiC {

		public static Block YZC_LOOSE_SEDIMENT      = new BlockSediment();
		public static Block YZC_DARK_LOOSE_SEDIMENT = new BlockSediment();
		public static Block YZC_GRAVEL              = new BlockGravel();
		public static Block YZC_IGNEOUS             = new BlockIgneousRock();
		public static Block YZC_METAMORPHIC         = new BlockMetamorphicRock();
		public static Block YZC_SEDIMENTARYROCK     = new BlockSedimentaryRock();
	}

	public static class YzCetiD {

		public static Block YZD_LOOSE_SEDIMENT      = new BlockSediment();
		public static Block YZD_DARK_LOOSE_SEDIMENT = new BlockSediment();
		public static Block YZD_GRAVEL              = new BlockGravel();
		public static Block YZD_IGNEOUS             = new BlockIgneousRock();
		public static Block YZD_METAMORPHIC         = new BlockMetamorphicRock();
		public static Block YZD_SEDIMENTARYROCK     = new BlockSedimentaryRock();
	}

}
