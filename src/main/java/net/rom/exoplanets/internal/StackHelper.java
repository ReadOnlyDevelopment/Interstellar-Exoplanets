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

    public static ItemStack fromBlockOrItem(IForgeRegistryEntry<?> blockOrItem) {
        //noinspection ChainOfInstanceofChecks
        if (blockOrItem instanceof Block)
            return new ItemStack((Block) blockOrItem);
        else if (blockOrItem instanceof Item)
            return new ItemStack((Item) blockOrItem);
        return ItemStack.EMPTY;
    }

    @Nonnull
    public static ItemStack loadFromNBT(@Nullable NBTTagCompound tags) {
        return tags != null ? new ItemStack(tags) : ItemStack.EMPTY;
    }

    @Nullable
    public static NBTTagCompound getTagCompound(ItemStack stack, boolean createIfNull) {
        if (stack.isEmpty())
            return null;
        if (!stack.hasTagCompound() && createIfNull)
            stack.setTagCompound(new NBTTagCompound());
        return stack.getTagCompound();
    }

    public static NBTTagCompound getOrCreateTagCompound(ItemStack stack) {
        if (stack.isEmpty()) return new NBTTagCompound();
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        //noinspection ConstantConditions - guaranteed not null
        return stack.getTagCompound();
    }

    public static List<ItemStack> getOres(String oreDictKey) {
        return OreDictionary.getOres(oreDictKey);
    }

    public static List<ItemStack> getOres(String oreDictKey, boolean alwaysCreateEntry) {
        return OreDictionary.getOres(oreDictKey, alwaysCreateEntry);
    }

    public static List<String> getOreNames(ItemStack stack) {
        List<String> list = new ArrayList<>();
        if (stack.isEmpty())
            return list;

        for (int id : OreDictionary.getOreIDs(stack))
            list.add(OreDictionary.getOreName(id));
        return list;
    }

    public static boolean matchesOreDict(ItemStack stack, String oreDictKey) {
        if (stack.isEmpty())
            return false;

        for (int id : OreDictionary.getOreIDs(stack))
            if (OreDictionary.getOreName(id).equals(oreDictKey))
                return true;

        return false;
    }

    @Deprecated
    public static StackList getNonEmptyStacks(IInventory inv) {
        StackList list = StackList.of();
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            // StackList automatically filters empty stacks
            list.add(inv.getStackInSlot(i));
        }
        return list;
    }

    public static ItemStack firstMatch(IInventory inv, Predicate<ItemStack> predicate) {
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && predicate.test(stack))
                return stack;
        }
        return ItemStack.EMPTY;
    }
}
