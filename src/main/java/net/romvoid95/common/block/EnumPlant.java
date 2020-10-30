package net.romvoid95.common.block;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum EnumPlant implements IStringSerializable {
	
	FORESTGRASS(true),
	DEADBUSH();
	
	public final boolean isColored;

	EnumPlant() {
		this(false);
	}

	EnumPlant(boolean isColored) {
		this.isColored = isColored;
	}

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}

}
