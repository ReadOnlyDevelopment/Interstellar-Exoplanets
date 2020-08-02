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
package net.rom.exoplanets.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper.UnableToFindFieldException;

public class ReflectionHelper {

	/**
	 * Searches for a field and makes it accessible.
	 * 
	 * @return The field or null if the field couldn't be found.
	 */
	public static Field getDeclaredField (Class<?> c, String fieldname) {
		try {
			Field f = c.getDeclaredField(fieldname);
			f.setAccessible(true);
			return f;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Method getPrivateMethod (Class<?> c, String methodName) {
		try {
			Method m = c.getDeclaredMethod(methodName, c);
			m.setAccessible(true);
			return m;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return True if the field value was set correctly.
	 */
	public static boolean setField (Field f, Object instance, Object value) {
		try {
			f.set(instance, value);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @return The old value.
	 */
	public static Object setStaticFinalField (Field f, Class<?> c, Object value) {
		Object o = null;
		try {
			f.setAccessible(true);
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			o = f.get(c);
			f.set(null, value);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	@Nonnull
	public static Field findField (@Nonnull Class<?> clazz, @Nonnull String fieldName, @Nullable String fieldObfName) {
		Preconditions.checkNotNull(clazz);
		Preconditions.checkArgument(StringUtils.isNotEmpty(fieldName), "Field name cannot be empty");

		String nameToFind = FMLLaunchHandler.isDeobfuscatedEnvironment() ? fieldName
				: MoreObjects.firstNonNull(fieldObfName, fieldName);

		try {
			Field f = clazz.getDeclaredField(nameToFind);
			f.setAccessible(true);
			return f;
		}
		catch (Exception e) {
			throw new UnableToFindFieldException(e);
		}
	}
}
