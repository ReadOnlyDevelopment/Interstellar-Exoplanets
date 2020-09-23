package net.romvoid95.common.lib.block;

import net.minecraft.block.material.Material;

public enum EnumPlantType {
	BUSH(Material.VINE, true, true),
	FLOWER(Material.PLANTS, false, false);

	private final Material material;
	private final boolean  shearable;
	private final boolean  replacable;

	EnumPlantType(Material material, boolean shearable, boolean replacable) {
		this.material   = material;
		this.shearable  = shearable;
		this.replacable = replacable;
	}

	public Material getMaterial () {
		return this.material;
	}

	public boolean isShearable () {
		return this.shearable;
	}

	public boolean isReplacable () {
		return this.replacable;
	}

}
