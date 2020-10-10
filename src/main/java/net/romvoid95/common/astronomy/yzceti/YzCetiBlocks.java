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

import net.romvoid95.common.block.terrain.*;
import net.romvoid95.core.initialization.ExoBlocks;

public class YzCetiBlocks {

	public static void registerAll() {

		// CetiB
		ExoBlocks.register(B.YZB_LOOSE_SEDIMENT, "yzb_loose_sediment");
		ExoBlocks.register(B.YZB_DARK_LOOSE_SEDIMENT, "yzb_dark_loose_sediment");
		ExoBlocks.register(B.YZB_GRAVEL, "yzb_gravel");
		ExoBlocks.register(B.YZB_IGNEOUS, "yzb_igneous");
		ExoBlocks.register(B.YZB_METAMORPHIC, "yzb_metamorphic");
		ExoBlocks.register(B.YZB_SEDIMENTARYROCK, "yzb_sedimentary_rock");
		// CetiC
		ExoBlocks.register(C.YZC_LOOSE_SEDIMENT, "yzc_loose_sediment");
		ExoBlocks.register(C.YZC_DARK_LOOSE_SEDIMENT, "yzc_dark_loose_sediment");
		ExoBlocks.register(C.YZC_GRAVEL, "yzc_gravel");
		ExoBlocks.register(C.YZC_IGNEOUS, "yzc_igneous");
		ExoBlocks.register(C.YZC_METAMORPHIC, "yzc_metamorphic");
		ExoBlocks.register(C.YZC_SEDIMENTARYROCK, "yzc_sedimentary_rock");
		// CetiD
		ExoBlocks.register(D.YZD_LOOSE_SEDIMENT, "yzd_loose_sediment");
		ExoBlocks.register(D.YZD_DARK_LOOSE_SEDIMENT, "yzd_dark_loose_sediment");
		ExoBlocks.register(D.YZD_GRAVEL, "yzd_gravel");
		ExoBlocks.register(D.YZD_IGNEOUS, "yzd_igneous");
		ExoBlocks.register(D.YZD_METAMORPHIC, "yzd_metamorphic");
		ExoBlocks.register(D.YZD_SEDIMENTARYROCK, "yzd_sedimentary_rock");
		ExoBlocks.register(D.YZD_MNT1, "yzd_mnt1");
		ExoBlocks.register(D.YZD_MNT2, "yzd_mnt2");
		ExoBlocks.register(D.YZD_STONE, "yzd_stone");
	}

	public static class B {

		public static final Block	YZB_LOOSE_SEDIMENT		= new BlockSediment();
		public static final Block	YZB_DARK_LOOSE_SEDIMENT	= new BlockSediment();
		public static final Block	YZB_GRAVEL				= new BlockGravel();
		public static final Block	YZB_IGNEOUS				= new BlockIgneousRock();
		public static final Block	YZB_METAMORPHIC			= new BlockMetamorphicRock();
		public static final Block	YZB_SEDIMENTARYROCK		= new BlockSedimentaryRock();
	}

	public static class C {

		public static final Block	YZC_LOOSE_SEDIMENT		= new BlockSediment();
		public static final Block	YZC_DARK_LOOSE_SEDIMENT	= new BlockSediment();
		public static final Block	YZC_GRAVEL				= new BlockGravel();
		public static final Block	YZC_IGNEOUS				= new BlockIgneousRock();
		public static final Block	YZC_METAMORPHIC			= new BlockMetamorphicRock();
		public static final Block	YZC_SEDIMENTARYROCK		= new BlockSedimentaryRock();
	}

	public static class D {

		public static final Block	YZD_LOOSE_SEDIMENT		= new BlockSediment();
		public static final Block	YZD_DARK_LOOSE_SEDIMENT	= new BlockSediment();
		public static final Block	YZD_GRAVEL				= new BlockGravel();
		public static final Block	YZD_IGNEOUS				= new BlockIgneousRock();
		public static final Block	YZD_METAMORPHIC			= new BlockMetamorphicRock();
		public static final Block	YZD_SEDIMENTARYROCK		= new BlockSedimentaryRock();
		public static final Block	YZD_MNT1				= new BlockMetamorphicRock();
		public static final Block	YZD_MNT2				= new BlockMetamorphicRock();
		public static final Block   YZD_STONE				= new BlockMetamorphicRock();
	}

}
