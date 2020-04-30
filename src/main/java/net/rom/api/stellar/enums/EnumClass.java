package net.rom.api.stellar.enums;

public enum EnumClass {

	D("D"), H("H"), J("J"), K("K"), L("L"), M("M"), N("N"), R("R"), T("T"), Y("Y");

	private EnumClass planetClass;
	private String planetClassStr;

	private EnumClass(String strClass) {
		this.planetClassStr = strClass;
	}

	private EnumClass(EnumClass pClass) {
		this.planetClass = pClass;
	}

	public void setPlanetStrClass(String strClass) {
		this.planetClassStr = strClass;
	}

	public void setPlanetClass(EnumClass pClass) {
		this.planetClass = pClass;
	}

	public String getPlanetStrClass() {
		return this.planetClassStr;
	}

	public EnumClass getPlanetClass() {
		return this.planetClass;
	}

}
