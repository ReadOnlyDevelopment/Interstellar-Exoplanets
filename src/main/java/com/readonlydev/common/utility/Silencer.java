/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.readonlydev.common.utility;

public class Silencer {
	public static <T> T get (Supplier<T> supplier) {
		try {
			return supplier.get();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static boolean exec (Exec exec) {
		try {
			exec.exec();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public static <T> boolean accept (Consumer<T> consumer, T t) {
		try {
			consumer.accept(t);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public static <T, U> boolean accept (BiConsumer<T, U> biconsumer, T t, U u) {
		try {
			biconsumer.accept(t, u);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public static <T, U> T apply (Function<T, U> function, U u) {
		try {
			return function.apply(u);
		}
		catch (Exception e) {
			return null;
		}
	}

	public static interface Supplier<T> {
		public T get () throws Exception;
	}

	public static interface Exec {
		public void exec () throws Exception;
	}

	public static interface Consumer<T> {
		public void accept (T t) throws Exception;
	}

	public static interface BiConsumer<T, U> {
		public void accept (T t, U u) throws Exception;
	}

	public static interface Function<T, U> {
		public T apply (U u) throws Exception;
	}
}