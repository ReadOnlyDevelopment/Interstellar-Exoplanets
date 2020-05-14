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

package net.rom.exoplanets.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.content.item.EnumIngots;
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
			return false;
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
			return false;
		}
	};
	
	public static final CreativeTabs ITEMS_CREATIVE_TABS = new ExoTab("items", "decoration.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(EnumIngots.RUTILE.getItem());
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return false;
		}
	};

}
