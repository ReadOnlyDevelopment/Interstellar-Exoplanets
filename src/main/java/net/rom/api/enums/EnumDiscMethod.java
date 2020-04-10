package net.rom.api.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumDiscMethod implements IStringSerializable{
	
	TRANSIT("Transit"), RADIAL_VELOCITY("Radial Velocity");
	
	private EnumDiscMethod method;
	private String name;
	
	private EnumDiscMethod(String name) {
		this.name = name;
	}
	
	private EnumDiscMethod(EnumDiscMethod method) {
		this.method = method;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public EnumDiscMethod getMethod() {
		return this.method;
	}

}
