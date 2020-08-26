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

package net.rom95.api.space.enums;

public enum EnumSpectralClass {

	O("O", 30000),
	B("B", 11000),
	A("A", 7500),
	F("F", 6000),
	G("G", 5000),
	K("K", 3500),
	M("M", 2310);

	private String spectralClass;
	private int    lowestTemp;

	EnumSpectralClass(String spectralClass, int lowestTemp) {
		this.spectralClass = spectralClass;
		this.lowestTemp    = lowestTemp;
	}

	EnumSpectralClass(String spectralClass) {
		this.spectralClass = spectralClass;
	}

	public int getLowestTempBoundry () {
		return lowestTemp;
	}

	//	@Override
	//	public String getName () {
	//		return spectralClass;
	//	}

	public static EnumSpectralClass getClass (int temp) {
		// Gets stellar classification of star based on its temperature value
		if (temp > 25000) {
			return EnumSpectralClass.O;
		}
		else if (temp > 11000) {
			return EnumSpectralClass.B;
		}
		else if (temp > 7500) {
			return EnumSpectralClass.A;
		}
		else if (temp > 6000) {
			return EnumSpectralClass.F;
		}
		else if (temp > 5000) {
			return EnumSpectralClass.G;
		}
		else if (temp > 3500) {
			return EnumSpectralClass.K;
		}
		else {
			return EnumSpectralClass.M;
		}
	}

}
