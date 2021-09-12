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

package com.readonlydev.common.item;

import com.readonlydev.client.CreativeExoTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBase extends Item {

    private String name;

    public ItemBase() {
        setCreativeTab(CreativeExoTabs.ITEMS_TABS);
    }

    public ItemBase(String name) {
        this.setUnlocalizedName(name);
    }

    public String getName() {
        return this.name;
    }
    
    public void InitTagCompount(ItemStack stack) {
        stack.setTagCompound(new NBTTagCompound());
    }

    public void TagCompountCheck(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            InitTagCompount(stack);
        }
    }
}
