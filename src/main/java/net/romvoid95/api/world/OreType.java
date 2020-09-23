package net.romvoid95.api.world;

import net.minecraft.block.Block;
import net.romvoid95.common.lib.EnumMetal;

public enum OreType {

	COPPER("copper", EnumMetal.COPPER.getOre().getBlock()),
	TIN("tin", EnumMetal.TIN.getOre().getBlock()),
	LEAD("lead", EnumMetal.LEAD.getOre().getBlock()),
	NICKEL("nickel", EnumMetal.NICKEL.getOre().getBlock()),
	PLATINUM("platinum", EnumMetal.PLATINUM.getOre().getBlock()),
	ALUMINIUM("aluminium", EnumMetal.ALUMINIUM.getOre().getBlock()),
	TITANIUM("titanium", EnumMetal.TITANIUM.getOre().getBlock()),
	TUNGSTEN("tungsten", EnumMetal.TUNGSTEN.getOre().getBlock()),
	SILVER("silver", EnumMetal.SILVER.getOre().getBlock()),
	ZINC("zinc", EnumMetal.ZINC.getOre().getBlock());

	private final String name;
	private final Block  block;

	OreType(String name, Block block) {
		this.name  = name;
		this.block = block;
	}

	public String getName () {
		return this.name;
	}

	public Block getBlock () {
		return this.block;
	}
}
