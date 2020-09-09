/**
 * Exoplanets
 * Copyright (C) 2020
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
package net.romvoid95.api;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

public class ApiUtil {

	public static boolean compareToOreName (ItemStack stack, String oreName) {
		if (!isExistingOreName(oreName))
			return false;
		List<ItemStack> s = OreDictionary.getOres(oreName);
		for (ItemStack st : s)
			if (OreDictionary.itemMatches(st, stack, false))
				return true;
		return false;
	}

	public static boolean isExistingOreName (String name) {
		if (!OreDictionary.doesOreNameExist(name))
			return false;
		else
			return !OreDictionary.getOres(name).isEmpty();
	}

	public static boolean isMetalComponent (ItemStack stack, String componentType) {
		return getMetalComponentType(stack, componentType) != null;
	}

	public static String getMetalComponentType (ItemStack stack, String... componentTypes) {
		int[]    ids      = OreDictionary.getOreIDs(stack);
		String[] oreNames = OreDictionary.getOreNames();
		for (int id : ids) {
			String oreName = oreNames[id];
			for (String componentType : componentTypes)
				if (oreName.startsWith(componentType))
					return componentType;
		}
		return null;
	}

	public static String[] getMetalComponentTypeAndMetal (ItemStack stack, String... componentTypes) {
		int[]    ids      = OreDictionary.getOreIDs(stack);
		String[] oreNames = OreDictionary.getOreNames();
		for (int id : ids) {
			String oreName = oreNames[id];
			for (String componentType : componentTypes) {
				if (oreName.startsWith(componentType))
					return new String[] { componentType, oreName.substring(componentType.length()) };
			}
		}
		return null;
	}

	public static boolean isIngot (ItemStack stack) {
		return isMetalComponent(stack, "ingot");
	}

	public static boolean isSheet (ItemStack stack) {
		return isMetalComponent(stack, "sheet");
	}

	public static BlockPos toBlockPos (Object object) {
		if (object instanceof BlockPos)
			return (BlockPos) object;
		if (object instanceof TileEntity)
			return ((TileEntity) object).getPos();
		return null;
	}

	public static Ingredient createIngredientFromList (List<ItemStack> list) {
		return Ingredient.fromStacks(list.toArray(new ItemStack[list.size()]));
	}

	public static ItemStack copyStackWithAmount (ItemStack stack, int amount) {
		if (stack.isEmpty())
			return ItemStack.EMPTY;
		ItemStack s2 = stack.copy();
		s2.setCount(amount);
		return s2;
	}

}
