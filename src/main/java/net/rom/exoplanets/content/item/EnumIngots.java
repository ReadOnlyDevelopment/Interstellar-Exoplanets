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

package net.rom.exoplanets.content.item;

import java.util.Locale;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.rom.exoplanets.internal.item.IEnumItems;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public enum EnumIngots implements IStringSerializable, IEnumItems<EnumIngots, EnumIngots.ItemIngot> {

	RUTILE("Rutile"), LIMONITE("Limonite"), RUTHENIUM("Ruthenium");

	private ItemIngot item;
	private String name;

	EnumIngots(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name.toLowerCase(Locale.ROOT);
	}

	@Override
	public EnumIngots getEnum() {
		return this;
	}

	@Override
	public ItemIngot getItem() {
		if (item == null)
			item = new ItemIngot();
		return item;
	}

	public class ItemIngot extends Item {
		public ItemIngot() {
			setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
		}
	}
}
