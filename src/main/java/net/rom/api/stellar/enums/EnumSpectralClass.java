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

package net.rom.api.stellar.enums;

import net.rom.api.stellar.interfaces.ISpectral;

public enum EnumSpectralClass implements ISpectral {
	
	O("O", 30000), B("B", 11000), A("A", 7500), F("F", 6000), G("G", 5000), K("K", 3500), M("M", 2310);

	private String spectralClass;
	private int lowestTemp;

	EnumSpectralClass(String spectralClass, int lowestTemp) {
		this.spectralClass = spectralClass;
		this.lowestTemp = lowestTemp;
	}
	
	EnumSpectralClass(String spectralClass) {
		this.spectralClass = spectralClass;
	}

	public int getLowestTempBoundry() {
		return lowestTemp;
	}

	@Override
	public String getName() {
		return spectralClass;
	}
	
	public static EnumSpectralClass getClass(int temp) {
		// Gets stellar classification of star based on its temperature value
		if (temp > 25000) {
			return EnumSpectralClass.O;
		} else if (temp > 11000) {
			return EnumSpectralClass.B;
		} else if (temp > 7500) {
			return EnumSpectralClass.A;
		} else if (temp > 6000) {
			return EnumSpectralClass.F;
		} else if (temp > 5000) {
			return EnumSpectralClass.G;
		} else if (temp > 3500) {
			return EnumSpectralClass.K;
		} else {
			return EnumSpectralClass.M;
		}
	}

}
