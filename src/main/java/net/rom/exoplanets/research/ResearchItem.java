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

package net.rom.exoplanets.research;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.rom.exoplanets.research.utils.JsonUtil;

public class ResearchItem {
    ItemStack itemStack;
    int itemAmount;
    int itemDamage;
    String name;
    String mod;
    NBTTagCompound nbtTagCompound;
    boolean ignoreDamage;
    boolean ignoreNBT;

    public ResearchItem(JsonObject object) {
        name = JsonUtil.getString(object, "id");
        itemAmount = JsonUtil.getInt(object, "count", 1);
        itemDamage = JsonUtil.getInt(object, "damage", 0);
        mod = JsonUtil.getString(object, "mod", null);
        nbtTagCompound = JsonUtil.getNbt(object, "nbt", null);
        ignoreDamage = JsonUtil.getBool(object, "ignore_damage", false);
        ignoreNBT = JsonUtil.getBool(object, "ignore_nbt", false);
    }

    public ResearchItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ResearchItem(String name, String mod) {
        this(name, mod, 1, 0, null);
    }

    public ResearchItem(String name, String mod, int itemAmount) {
        this(name, mod, itemAmount, 0, null);
    }

    public ResearchItem(String name, String mod, int itemAmount, int itemDamage) {
        this(name, mod, itemAmount, itemDamage, null);
    }

    public ResearchItem(String name, String mod, int itemAmount, int itemDamage, NBTTagCompound tagCompound) {
        this.name = name;
        this.mod = mod;
        this.itemAmount = itemAmount;
        this.itemDamage = itemDamage;
        this.nbtTagCompound = tagCompound;
    }

    public static ResearchItem fromItemStack(ItemStack itemStack) {
        return new ResearchItem(itemStack);
    }

    public boolean isModded() {
        return mod != null && !mod.isEmpty();
    }

    public boolean isModPresent() {
        return Loader.isModLoaded(mod);
    }

    public boolean canItemExist() {
        if (isModded()) {
            return isModPresent();
        }
        return true;
    }

    public ItemStack getItemStack() {
        if (isModded() || itemStack == null) {
            Item item = Item.getByNameOrId(name);
            if (item != null) {
                ItemStack itemStack = new ItemStack(item, itemAmount, itemDamage);
                itemStack.setTagCompound(nbtTagCompound);
                return itemStack;
            }

        } else {
            return itemStack;
        }
        return null;
    }

    public boolean matches(ItemStack itemStack) {
        if (this.itemStack != null) {
            return itemStack.getItem().equals(this.itemStack.getItem()) && (ignoreDamage || itemStack.getItemDamage() == this.itemStack.getItemDamage()) && (ignoreNBT || ItemStack.areItemStackTagsEqual(itemStack, this.itemStack));
        } else {
            return itemStack.getItem().getRegistryName().toString().equals(name) && (ignoreDamage || itemDamage == itemStack.getItemDamage()) && (ignoreNBT || (nbtTagCompound == null || nbtTagCompound.equals(itemStack.getTagCompound())));
        }
    }
}
