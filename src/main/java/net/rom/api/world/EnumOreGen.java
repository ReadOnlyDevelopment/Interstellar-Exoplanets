package net.rom.api.world;

public enum EnumOreGen {

	// Block , genCount, blockCount, centerHeight, spread
	COAL(17, 20, 0, 128), 
	IRON(9, 20, 0, 64), 
	GOLD(9, 2, 0, 32), 
	REDSTONE(8, 8, 0, 16), 
	LAPIS(7, 1, 16, 16),
	DIAMOND(8, 1, 0, 16), 
	DIRT(33, 10, 0, 256),
	GRAVEL(33, 8, 0, 256);

	private final int genCount;
	private final int blockCount;
	private final int minHeight;
	private final int maxHeight;

	private EnumOreGen(int genCount, int blockCount, int minHeight, int maxHeight) {
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

}
