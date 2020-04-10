package net.rom.exoplanets.util;

public enum EnumOreGen {
	
	// vanilla blocks / other mod ores
	COAL(15, 25, 0, 256),
	IRON(21, 20, 0, 256),
	GOLD(16, 6, 0, 41),
	REDSTONE(10, 15, 0, 26),
	LAPIS(7, 7, 16, 26),
	DIAMOND(8, 4, 0, 16),
	EMERALD(4, 6, 0, 12),
	DESH(4, 15, 0, 32),
	METEORIC_IRON(4, 13, 0, 32),
	DIRT(20, 20, 0, 256),
	PACKED_ICE(53, 20, 0, 256),
	MAGMA(20, 15, 0, 128),
	BLAZE(16, 5, 0, 56),
	GRAVEL(33, 8, 0, 256),
	SILICON(7, 3, 0, 25),
	ALUMINUM(11, 18, 0, 70),
	TIN(11, 22, 0, 70),
	COPPER(11, 24, 0, 75),
	SILVER(5, 16, 0, 42),
	LEAD(6, 20, 0, 36),
	NICKEL(8, 14, 0, 36),
	COBALT(5, 14, 0, 24),
	STEEL(9, 15, 0, 32),
	BRONZE(6, 6, 0, 24),
	OBSIDIAN(20, 25, 0, 45),
	ZINC(25, 6, 0, 26);
	
	private int genCount;
	private int blockCount;
	private int minHeight;
	private int maxHeight;
	
	private EnumOreGen(int genCount, int blockCount, int minHeight, int maxHeight) {
		this.genCount = genCount;
		this.blockCount = blockCount;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}
	
	public EnumOreGen createOreGen(int genCount, int blockCount, int minHeight, int maxHeight) {
		this.setGenCount(genCount);
		this.setBlockCount(blockCount);
		this.setMinHeight(minHeight);
		this.setMaxHeight(maxHeight);
		return this;
	}
	
	public EnumOreGen createOreGen(int genCount, int blockCount, int minMaxHeight) {
		this.setGenCount(genCount);
		this.setBlockCount(blockCount);
		this.setMinMaxHeight(minMaxHeight);
		return this;
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
	
	public int setGenCount(int genCount) {
		this.blockCount = genCount;
		return genCount;
	}
	
	public int setMinHeight(int minHeight) {
		this.minHeight = minHeight;
		return minHeight;
	}
	
	public int setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
		return maxHeight;
	}
	
	public void setMinMaxHeight(int minHeight, int maxHeight) {
		this.setMaxHeight(maxHeight);
		this.setMinHeight(minHeight);
	}
	
	public void setMinMaxHeight(int height) {
		this.setMinMaxHeight(height);
	}
	
	public int setBlockCount(int countBlock) {
		this.blockCount = countBlock;
		return blockCount;
	}
	
	public EnumOreGen instance() {
		return this;
	}
}
