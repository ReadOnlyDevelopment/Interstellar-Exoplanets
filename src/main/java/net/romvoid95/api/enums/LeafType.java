package net.romvoid95.api.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum LeafType implements IStringSerializable {
	
	LIGHT(0, 40),
	DARK(1, 25),
	MOLD(2, 75);
	
	private static final LeafType[] META_MAP = new LeafType[values().length];
	private final int meta;
	private final int dropChance;
	
	LeafType(int meta, int dropChance) {
		this.meta = meta;
		this.dropChance = dropChance;
	}
	
	public int getMetadata() {
		return this.meta;
	}
	
	public int getSaplingDropChance() {
		return dropChance;
	}
	
	public static LeafType byMetadata(int meta) {
		if (meta < 0 || meta >= META_MAP.length) {
			meta = 0;
		}

		return META_MAP[meta];
	}
	
	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}
	
	static {
		for (LeafType blockleafs$enumtype : values()) {
			META_MAP[blockleafs$enumtype.getMetadata()] = blockleafs$enumtype;
		}
	}

}
