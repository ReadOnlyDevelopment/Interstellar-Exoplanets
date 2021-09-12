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

import java.util.Arrays;
import java.util.List;

import com.readonlydev.client.CreativeExoTabs;
import com.readonlydev.common.lib.EnumAlloy;
import com.readonlydev.common.lib.IMetal;
import com.readonlydev.lib.recipe.RecipeBuilder;

import net.minecraft.item.Item;

public class ItemIngotAlloy extends ItemBaseMetal {

    public ItemIngotAlloy() {
        super("ingot", "ingot");
        setCreativeTab(CreativeExoTabs.ITEMS_TABS);
    }

    @Override
    public List<IMetal> getMetals(Item item) {
    	return Arrays.asList(EnumAlloy.values());
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {
        for (EnumAlloy metal : EnumAlloy.values()) {

            // Ingots <--> Blocks
                recipes.addCompression("block_" + metal.getMetalName(), metal.getIngot(), metal.getBlock(), 9);
        }
    }
}
