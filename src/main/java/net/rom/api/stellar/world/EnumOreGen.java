package net.rom.api.stellar.world;

import net.minecraft.util.IStringSerializable;

public enum EnumOreGen implements IStringSerializable {
	
	RUTILE("rutile", 13, 10, 1, 45),
	LIMONITE("limonite", 9, 20, 0, 64),
	RUTHENIUM("ruthenium", 8, 1, 0, 16);

	private final String name;
	private final int genCount;
	private final int blockCount;
	private final int minHeight;
	private final int maxHeight;

	private EnumOreGen(String name, int genCount, int blockCount, int minHeight, int maxHeight) {
		this.name = name;
		this.genCount = genCount;
		this.blockCount = blockCount;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}

	public int getGenCount() {
		return this.genCount;
	}

	public int getBlockCount() {
		return this.blockCount;
	}

	public int getMinHeight() {
		return this.minHeight;
	}

	public int getMaxHeight() {
		return this.maxHeight;
	}
	
	public EnumOreGen instance() {
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
