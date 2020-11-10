package net.romvoid95.api.enums;

import java.util.Locale;

import com.google.common.base.Predicate;

import net.minecraft.util.IStringSerializable;

public enum TreeType implements IStringSerializable {

	SWAMP, 
	FALLEN, 
	FIR, 
	SEMPER, 
	FLAT, 
	WILLOW;

	@Override
	public String getName() {
		return this.name().toLowerCase(Locale.ROOT);
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public boolean hasSapling() {
		switch (this) {
		case FALLEN:
		case SEMPER:
			return false;
		default:
			return true;
		}
	}

	public static Predicate withSaplings = new Predicate<TreeType>() {
		@Override
		public boolean apply(TreeType tree) {
			return tree.hasSapling();
		}
	};
}
