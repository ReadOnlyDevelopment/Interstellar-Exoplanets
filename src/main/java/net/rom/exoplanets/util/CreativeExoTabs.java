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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.init.ExoBlocks;

public class CreativeExoTabs {

	public static final CreativeTabs DECO_CREATIVE_TABS = new ExoTab("decoration", "decoration.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ExoBlocks.stand_console);
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return true;
		}
	};
	
	public static final CreativeTabs TERRAIN_CREATIVE_TABS = new ExoTab("terrain", "terrain.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(YzCetiBlocks.CetiB.B_METAMORPHIC);
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return true;
		}
	};
	
	public static final CreativeTabs ITEMS_CREATIVE_TABS = new ExoTab("items", "decoration.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(EnumMetal.PLATINUM.getPlate().getItem());
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return true;
		}
	};

}
