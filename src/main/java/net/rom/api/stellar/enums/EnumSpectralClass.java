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
