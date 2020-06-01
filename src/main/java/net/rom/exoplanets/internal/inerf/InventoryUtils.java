/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal.inerf;

import com.google.common.collect.ImmutableList;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.internal.MathUtils;

import java.util.Collection;

public final class InventoryUtils {
	private InventoryUtils() {
		throw new IllegalAccessError("Utility class");
	}

	public static boolean canItemsStack(ItemStack a, ItemStack b) {
		if (a.isEmpty() || !a.isItemEqual(b) || a.hasTagCompound() != b.hasTagCompound())
			return false;
		// noinspection ConstantConditions
		return (!a.hasTagCompound() || a.getTagCompound().equals(b.getTagCompound())) && a.areCapsCompatible(b);
	}

	public static ItemStack mergeItem(IInventory inventory, int slotStart, int slotEndExclusive, ItemStack stack) {
		if (inventory == null || stack.isEmpty())
			return stack;

		// Merge into non-empty slots first
		for (int i = slotStart; i < slotEndExclusive && !stack.isEmpty(); ++i) {
			ItemStack inSlot = inventory.getStackInSlot(i);
			if (canItemsStack(inSlot, stack)) {
				int amountCanFit = MathUtils.min(inSlot.getMaxStackSize() - inSlot.getCount(), stack.getCount(), inventory.getInventoryStackLimit());
				inSlot.grow(amountCanFit);
				stack.shrink(amountCanFit);
				inventory.setInventorySlotContents(i, inSlot);
			}
		}

		// Fill empty slots next
		for (int i = slotStart; i < slotEndExclusive && !stack.isEmpty(); ++i) {
			if (inventory.getStackInSlot(i).isEmpty()) {
				int amountCanFit = MathUtils.min(stack.getCount(), inventory.getInventoryStackLimit());
				ItemStack toInsert = stack.copy();
				toInsert.setCount(amountCanFit);
				stack.shrink(amountCanFit);
				inventory.setInventorySlotContents(i, toInsert);
			}
		}

		return stack;
	}

	public static Collection<ItemStack> mergeItems(IInventory inventory, int slotStart, int slotEndExclusive, Collection<ItemStack> stacks) {
		if (inventory == null && stacks.isEmpty())
			return ImmutableList.of();

		ImmutableList.Builder<ItemStack> leftovers = ImmutableList.builder();

		for (ItemStack stack : stacks) {
			stack = mergeItem(inventory, slotStart, slotEndExclusive, stack);

			// Failed to merge?
			if (!stack.isEmpty()) {
				leftovers.add(stack);
			}
		}

		return leftovers.build();
	}
}
