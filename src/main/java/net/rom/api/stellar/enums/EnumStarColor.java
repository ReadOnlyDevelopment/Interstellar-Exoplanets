package net.rom.api.stellar.enums;

import micdoodle8.mods.galacticraft.api.vector.Vector3;

public enum EnumStarColor {

	BRIGHT_BLUE(194, 254, 252), WHITE(234, 240, 240), DARK_WHTIE(249, 252, 200), YELLOW(244, 254, 40),
	ORANGE(255, 155, 0), RED(255, 42, 27);

	private Vector3 color;

	EnumStarColor(int r, int g, int b) {
		this.color = new Vector3(r, g, b);
	}

	public Vector3 getColor() {
		return color;
	}
}
