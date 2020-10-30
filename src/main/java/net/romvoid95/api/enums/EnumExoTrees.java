package net.romvoid95.api.enums;

import java.util.Locale;

import com.google.common.base.Predicate;

import net.minecraft.util.IStringSerializable;
import net.romvoid95.common.utility.mc.IPagedVar;

public enum EnumExoTrees implements IStringSerializable, IPagedVar {

	SWAMP, FALLEN, FIR, SEMPER, FLAT, WILLOW;

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

	public static Predicate withSaplings = new Predicate<EnumExoTrees>() {
		@Override
		public boolean apply(EnumExoTrees tree) {
			return tree.hasSapling();
		}
	};
}
