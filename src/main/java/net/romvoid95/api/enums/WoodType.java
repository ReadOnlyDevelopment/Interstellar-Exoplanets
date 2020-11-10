package net.romvoid95.api.enums;

import java.util.Locale;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum WoodType implements IStringSerializable {
	
	LIGHT(MapColor.SILVER),
	DARK(MapColor.OBSIDIAN),
	ROUGH(MapColor.SILVER),
	MOLD(MapColor.GREEN);
	
	private final MapColor color;
	
	WoodType(MapColor color) {
		this.color = color;
	}
	
	public int getMeta() {
		return this.ordinal();
	}
	
	public MapColor getMapColor() {
		return color;
	}

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}

}
