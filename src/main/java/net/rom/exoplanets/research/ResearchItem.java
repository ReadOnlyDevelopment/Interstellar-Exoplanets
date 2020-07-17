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
