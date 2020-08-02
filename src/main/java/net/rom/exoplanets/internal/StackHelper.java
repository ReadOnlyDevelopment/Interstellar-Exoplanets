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

package net.rom.exoplanets.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class StackHelper {
	private StackHelper() {
		throw new IllegalAccessError("Utility class");
	}

	public static ItemStack fromBlockOrItem (IForgeRegistryEntry<?> blockOrItem) {
		//noinspection ChainOfInstanceofChecks
		if (blockOrItem instanceof Block)
			return new ItemStack((Block) blockOrItem);
		else if (blockOrItem instanceof Item)
			return new ItemStack((Item) blockOrItem);
		return ItemStack.EMPTY;
	}

	@Nonnull
	public static ItemStack loadFromNBT (@Nullable NBTTagCompound tags) {
		return tags != null ? new ItemStack(tags) : ItemStack.EMPTY;
	}

	@Nullable
	public static NBTTagCompound getTagCompound (ItemStack stack, boolean createIfNull) {
		if (stack.isEmpty())
			return null;
		if (!stack.hasTagCompound() && createIfNull)
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}

	public static NBTTagCompound getOrCreateTagCompound (ItemStack stack) {
		if (stack.isEmpty())
			return new NBTTagCompound();
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		//noinspection ConstantConditions - guaranteed not null
		return stack.getTagCompound();
	}

	public static List<ItemStack> getOres (String oreDictKey) {
		return OreDictionary.getOres(oreDictKey);
	}

	public static List<ItemStack> getOres (String oreDictKey, boolean alwaysCreateEntry) {
		return OreDictionary.getOres(oreDictKey, alwaysCreateEntry);
	}

	public static List<String> getOreNames (ItemStack stack) {
		List<String> list = new ArrayList<>();
		if (stack.isEmpty())
			return list;

		for (int id : OreDictionary.getOreIDs(stack))
			list.add(OreDictionary.getOreName(id));
		return list;
	}

	public static boolean matchesOreDict (ItemStack stack, String oreDictKey) {
		if (stack.isEmpty())
			return false;

		for (int id : OreDictionary.getOreIDs(stack))
			if (OreDictionary.getOreName(id).equals(oreDictKey))
				return true;

		return false;
	}

	@Deprecated
	public static StackList getNonEmptyStacks (IInventory inv) {
		StackList list = StackList.of();
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			// StackList automatically filters empty stacks
			list.add(inv.getStackInSlot(i));
		}
		return list;
	}

	public static ItemStack firstMatch (IInventory inv, Predicate<ItemStack> predicate) {
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() && predicate.test(stack))
				return stack;
		}
		return ItemStack.EMPTY;
	}
}
