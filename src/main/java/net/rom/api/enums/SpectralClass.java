package net.rom.api.enums;

import net.rom.api.interfaces.ISpectral;

public enum SpectralClass implements ISpectral {
	
	O("O", 30000), B("B", 11000), A("A", 7500), F("F", 6000), G("G", 5000), K("K", 3500), M("M", 2310);

	private String spectralClass;
	private int lowestTemp;

	SpectralClass(String spectralClass, int lowestTemp) {
		this.spectralClass = spectralClass;
		this.lowestTemp = lowestTemp;
	}
	
	SpectralClass(String spectralClass) {
		this.spectralClass = spectralClass;
	}

	public int getLowestTempBoundry() {
		return lowestTemp;
	}

	@Override
	public String getName() {
		return spectralClass;
	}
	
	public static SpectralClass getClass(int temp) {
		// Gets stellar classification of star based on its temperature value
		if (temp > 25000) {
			return SpectralClass.O;
		} else if (temp > 11000) {
			return SpectralClass.B;
		} else if (temp > 7500) {
			return SpectralClass.A;
		} else if (temp > 6000) {
			return SpectralClass.F;
		} else if (temp > 5000) {
			return SpectralClass.G;
		} else if (temp > 3500) {
			return SpectralClass.K;
		} else {
			return SpectralClass.M;
		}
	}

}
