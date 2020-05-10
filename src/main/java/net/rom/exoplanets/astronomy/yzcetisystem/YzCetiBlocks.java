/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.astronomy.yzcetisystem;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.rom.exoplanets.content.block.terrain.BlockGravel;
import net.rom.exoplanets.content.block.terrain.BlockIgneousRock;
import net.rom.exoplanets.content.block.terrain.BlockMetamorphicRock;
import net.rom.exoplanets.content.block.terrain.BlockSediment;
import net.rom.exoplanets.content.block.terrain.BlockSedimentaryRock;
import net.rom.exoplanets.internal.StellarRegistry;
import static net.rom.exoplanets.ExoInfo.blocks;

public class YzCetiBlocks {

    public static void registerAll(StellarRegistry r) {
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

        public static Block B_LOOSE_SEDIMENT = new BlockSediment();
        public static Block B_DARK_LOOSE_SEDIMENT = new BlockSediment();
        public static BlockGravel B_GRAVEL = new BlockGravel();
        public static BlockIgneousRock B_IGNEOUS = new BlockIgneousRock();
        public static BlockMetamorphicRock B_METAMORPHIC = new BlockMetamorphicRock();
        public static BlockSedimentaryRock B_SEDIMENTARYROCK = new BlockSedimentaryRock();

    }

    public static class CetiC {

        public static Block C_LOOSE_SEDIMENT = new BlockSediment();
        public static Block C_DARK_LOOSE_SEDIMENT = new BlockSediment();
        public static BlockGravel C_GRAVEL = new BlockGravel();
        public static BlockIgneousRock C_IGNEOUS = new BlockIgneousRock();
        public static BlockMetamorphicRock C_METAMORPHIC = new BlockMetamorphicRock();
        public static BlockSedimentaryRock C_SEDIMENTARYROCK = new BlockSedimentaryRock();
    }

    public static class CetiD {

        public static Block D_LOOSE_SEDIMENT = new BlockSediment();
        public static Block D_DARK_LOOSE_SEDIMENT = new BlockSediment();
        public static BlockGravel D_GRAVEL = new BlockGravel();
        public static BlockIgneousRock D_IGNEOUS = new BlockIgneousRock();
        public static BlockMetamorphicRock D_METAMORPHIC = new BlockMetamorphicRock();
        public static BlockSedimentaryRock D_SEDIMENTARYROCK = new BlockSedimentaryRock();
    }

}
