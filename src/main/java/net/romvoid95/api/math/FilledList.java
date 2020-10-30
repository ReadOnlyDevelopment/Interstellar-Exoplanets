package net.romvoid95.api.math;

import java.util.*;

import javax.annotation.Nullable;

import org.apache.commons.lang3.Validate;

public class FilledList<Var> extends AbstractList<Var> {

	private final List<Var>	var;
	private final Var		defaultVar;

	public static <Var> FilledList<Var> make() {
		return new FilledList<>();
	}

	public static <Var> FilledList<Var> withSize(int size, Var fill) {
		Validate.notNull(fill);
		Object[] aobject = new Object[size];
		Arrays.fill(aobject, fill);
		return new FilledList<>(Arrays.asList((Var[]) aobject), fill);
	}

	public static <Var> FilledList<Var> from(Var defaultVar, Var... elements) {
		return new FilledList<>(Arrays.asList(elements), defaultVar);
	}

	protected FilledList () {
		this(new ArrayList(), null);
	}

	protected FilledList (List<Var> varIn, @Nullable Var listType) {
		this.var = varIn;
		this.defaultVar = listType;
	}

	@Override
	public Var get(int index) {
		return this.var.get(index);
	}

	@Override
	public Var set(int index, Var var) {
		Validate.notNull(index);
		return this.var.set(index, var);
	}

	@Override
	public void add(int index, Var var) {
		Validate.notNull(var);
		this.var.add(index, var);
	}

	@Override
	public Var remove(int index) {
		return this.var.remove(index);
	}

	@Override
	public int size() {
		return this.var.size();
	}

	@Override
	public void clear() {
		if (this.defaultVar == null) {
			super.clear();
		} else {
			for (int i = 0; i < this.size(); ++i) {
				this.set(i, this.defaultVar);
			}
		}
	}
}
