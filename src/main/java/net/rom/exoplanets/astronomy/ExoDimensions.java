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

package net.rom.exoplanets.astronomy;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.rom.exoplanets.astronomy.trappist1.TrappistDimensions;
import net.rom.exoplanets.astronomy.yzceti.YzCetiDimensions;
import net.rom.exoplanets.conf.SConfigDimensionID;

public class ExoDimensions {

	//public static DimensionType HIGHORBIT;

	public static void init() {
		YzCetiDimensions.YZCETIB = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_b);
		YzCetiDimensions.YZCETIC = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_c);
		YzCetiDimensions.YZCETID = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_d);
		
		TrappistDimensions.TRAPPIST_1B = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_b);
		TrappistDimensions.TRAPPIST_1C = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_c);
		TrappistDimensions.TRAPPIST_1D = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_d);
		TrappistDimensions.TRAPPIST_1E = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_e);
		TrappistDimensions.TRAPPIST_1F = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_f);
		TrappistDimensions.TRAPPIST_1G = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_g);
		TrappistDimensions.TRAPPIST_1H = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_h);
		

	}
}