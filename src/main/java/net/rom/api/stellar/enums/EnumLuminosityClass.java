package net.rom.api.stellar.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumLuminosityClass implements IStringSerializable {

	EXT_LUMINOUS_SUPERGIANTS("Ia-O"),
	LUMINOUS_SUPERGIANTS("Ia"),
	LESS_LUMINOUS_SUPERGIANTS("Ib"),
	BRIGHT_GIANTS("II"),
	GIANTS("III"),
	SUB_GIANTS("IV"),
	MAIN_SEQUENCE("V"),
	SUB_DWARF("VI"),
	SUPERGIANTS_I("D");

	private String clazz;
	
	EnumLuminosityClass(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getName() {
		return this.clazz;
	}


}
