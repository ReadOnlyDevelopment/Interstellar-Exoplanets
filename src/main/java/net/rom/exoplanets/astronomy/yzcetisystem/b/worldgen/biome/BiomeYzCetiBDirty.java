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

package net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome;

import net.rom.api.stellar.enums.EnumBiomeType;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;

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
		this.topBlock = YzCetiBlocks.CetiB.B_LOOSE_SEDIMENT.getDefaultState();
		this.fillerBlock = YzCetiBlocks.CetiB.B_SEDIMENTARYROCK.getDefaultState();
		this.stoneBlock = YzCetiBlocks.CetiB.B_METAMORPHIC;
	}
}