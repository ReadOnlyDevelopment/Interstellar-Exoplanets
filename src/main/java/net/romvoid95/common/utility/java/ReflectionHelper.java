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
package net.romvoid95.common.utility.java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.romvoid95.common.utility.mc.MCUtil;
import net.romvoid95.core.ExoplanetsMod;

@UtilityClass
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
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * Finds the method with the given name. If multiple methods with the same name exist, the first one will be returned
	 *
	 * @param clazz the class
	 * @param name the method name to search for
	 * @return the first method with the given name or null if no such method is found
	 */
	public static MethodNode findMethod (ClassNode clazz, String name) {
		for (MethodNode method : clazz.methods) {
			if (method.name.equals(name)) {
				return method;
			}
		}
		return null;
	}

	/**
	 * Changes the access level for the specified field for a class.
	 *
	 * @param clazz the clazz
	 * @param fieldName the field name
	 * @return the field
	 */
	public static Field changeFieldAccess (Class<?> clazz, String fieldName) {
		return changeFieldAccess(clazz, fieldName, fieldName, false);
	}

	/**
	 * Changes the access level for the specified field for a class.
	 *
	 * @param clazz the clazz
	 * @param fieldName the field name
	 * @param srgName the srg name
	 * @return the field
	 */
	public static Field changeFieldAccess (Class<?> clazz, String fieldName, String srgName) {
		return changeFieldAccess(clazz, fieldName, srgName, false);
	}

	/**
	 * Changes the access level for the specified field for a class.
	 *
	 * @param clazz the clazz
	 * @param fieldName the field name
	 * @param srgName the srg name
	 * @param silenced the silenced
	 * @return the field
	 */
	public static Field changeFieldAccess (Class<?> clazz, String fieldName, String srgName, boolean silenced) {
		try {
			Field f = clazz.getDeclaredField(MCUtil.isDeobfuscated() ? srgName : fieldName);
			f.setAccessible(true);
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(f, f.getModifiers() & ~Modifier.FINAL);

			return f;
		}
		catch (ReflectiveOperationException e) {
			if (!silenced)
				ExoplanetsMod.logger.error("Could not change access for field " + clazz.getSimpleName() + "."
						+ (MCUtil.isDeobfuscated() ? srgName : fieldName), e);
			return null;
		}

	}

	/**
	 * Changes the access level for the specified method for a class.
	 *
	 * @param clazz the clazz
	 * @param methodName the field name
	 * @return the field
	 */
	public static Method changeMethodAccess (Class<?> clazz, String methodName, Class<?>... params) {
		return changeMethodAccess(clazz, methodName, methodName, false, params);
	}

	/**
	 * Changes the access level for the specified method for a class.
	 *
	 * @param clazz the clazz
	 * @param methodName the method name
	 * @param srgName the srg name
	 * @param params the params
	 * @return the method
	 */
	public static Method changeMethodAccess (Class<?> clazz, String methodName, String srgName, Class<?>... params) {
		return changeMethodAccess(clazz, methodName, srgName, false, params);
	}

	/**
	 * Changes the access level for the specified method for a class.
	 *
	 * @param clazz the clazz
	 * @param methodName the field name
	 * @param srgName the srg name
	 * @param silenced the silenced
	 * @param params the params
	 * @return the field
	 */
	public static Method changeMethodAccess (Class<?> clazz, String methodName, String srgName, boolean silenced, Class<?>... params) {
		try {
			Method m = clazz.getDeclaredMethod(MCUtil.isDeobfuscated() ? srgName : methodName, params);
			m.setAccessible(true);
			return m;
		}
		catch (ReflectiveOperationException e) {
			ExoplanetsMod.logger.error("Could not change access for method " + clazz.getSimpleName() + "."
					+ (MCUtil.isDeobfuscated() ? srgName : methodName), e);
		}

		return null;
	}
}
