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

package net.rom.exoplanets.astronomy.yzceti;

import net.minecraft.block.Block;
import net.rom.exoplanets.content.block.terrain.BlockGravel;
import net.rom.exoplanets.content.block.terrain.BlockIgneousRock;
import net.rom.exoplanets.content.block.terrain.BlockMetamorphicRock;
import net.rom.exoplanets.content.block.terrain.BlockSediment;
import net.rom.exoplanets.content.block.terrain.BlockSedimentaryRock;
import net.rom.exoplanets.internal.StellarRegistry;

public class YzCetiBlocks {

	public static void registerAll (StellarRegistry r) {
		// CetiB
		r.registerBlock(CetiB.B_LOOSE_SEDIMENT, "yzb_loose_sediment");
		r.registerBlock(CetiB.B_DARK_LOOSE_SEDIMENT, "yzb_dark_loose_sediment");
		r.registerBlock(CetiB.B_GRAVEL, "yzb_gravel");
		r.registerBlock(CetiB.B_IGNEOUS, "yzb_igneous");
		r.registerBlock(CetiB.B_METAMORPHIC, "yzb_metamorphic");
		r.registerBlock(CetiB.B_SEDIMENTARYROCK, "yzb_sedimentary_rock");
		// CetiC
		r.registerBlock(CetiC.C_LOOSE_SEDIMENT, "yzc_loose_sediment");
		r.registerBlock(CetiC.C_DARK_LOOSE_SEDIMENT, "yzc_dark_loose_sediment");
		r.registerBlock(CetiC.C_GRAVEL, "yzc_gravel");
		r.registerBlock(CetiC.C_IGNEOUS, "yzc_igneous");
		r.registerBlock(CetiC.C_METAMORPHIC, "yzc_metamorphic");
		r.registerBlock(CetiC.C_SEDIMENTARYROCK, "yzc_sedimentary_rock");
		// CetiD
		r.registerBlock(CetiD.D_LOOSE_SEDIMENT, "yzd_loose_sediment");
		r.registerBlock(CetiD.D_DARK_LOOSE_SEDIMENT, "yzd_dark_loose_sediment");
		r.registerBlock(CetiD.D_GRAVEL, "yzd_gravel");
		r.registerBlock(CetiD.D_IGNEOUS, "yzd_igneous");
		r.registerBlock(CetiD.D_METAMORPHIC, "yzd_metamorphic");
		r.registerBlock(CetiD.D_SEDIMENTARYROCK, "yzd_sedimentary_rock");
	}

	public static class CetiB {

		public static Block                B_LOOSE_SEDIMENT      = new BlockSediment();
		public static Block                B_DARK_LOOSE_SEDIMENT = new BlockSediment();
		public static BlockGravel          B_GRAVEL              = new BlockGravel();
		public static BlockIgneousRock     B_IGNEOUS             = new BlockIgneousRock();
		public static BlockMetamorphicRock B_METAMORPHIC         = new BlockMetamorphicRock();
		public static BlockSedimentaryRock B_SEDIMENTARYROCK     = new BlockSedimentaryRock();

	}

	public static class CetiC {

		public static Block                C_LOOSE_SEDIMENT      = new BlockSediment();
		public static Block                C_DARK_LOOSE_SEDIMENT = new BlockSediment();
		public static BlockGravel          C_GRAVEL              = new BlockGravel();
		public static BlockIgneousRock     C_IGNEOUS             = new BlockIgneousRock();
		public static BlockMetamorphicRock C_METAMORPHIC         = new BlockMetamorphicRock();
		public static BlockSedimentaryRock C_SEDIMENTARYROCK     = new BlockSedimentaryRock();
	}

	public static class CetiD {

		public static Block                D_LOOSE_SEDIMENT      = new BlockSediment();
		public static Block                D_DARK_LOOSE_SEDIMENT = new BlockSediment();
		public static BlockGravel          D_GRAVEL              = new BlockGravel();
		public static BlockIgneousRock     D_IGNEOUS             = new BlockIgneousRock();
		public static BlockMetamorphicRock D_METAMORPHIC         = new BlockMetamorphicRock();
		public static BlockSedimentaryRock D_SEDIMENTARYROCK     = new BlockSedimentaryRock();
	}

}
