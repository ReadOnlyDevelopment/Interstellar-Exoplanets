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

package net.rom.exoplanets.init;

import java.util.LinkedList;

import net.minecraft.item.Item;
import net.rom.exoplanets.content.item.ItemCraftingItem;
import net.rom.exoplanets.content.item.ItemDustAlloy;
import net.rom.exoplanets.content.item.ItemDustMetal;
import net.rom.exoplanets.content.item.ItemIngotAlloy;
import net.rom.exoplanets.content.item.ItemIngotMetal;
import net.rom.exoplanets.content.item.ItemTwoPlayerRocket;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoItems {

	public static final Item passengerRocket = new ItemTwoPlayerRocket();
	public static final Item metalIngot      = new ItemIngotMetal();
	public static final Item metalDust       = new ItemDustMetal();
	public static final Item alloyIngot      = new ItemIngotAlloy();
	public static final Item alloyDust       = new ItemDustAlloy();
	public static final Item sheetBasic      = new ItemCraftingItem("sheet", false);
	public static final Item sheetAlloy      = new ItemCraftingItem("sheet", true);
	public static final Item gearBasic       = new ItemCraftingItem("gear", false);
	public static final Item gearAlloy       = new ItemCraftingItem("gear", true);

	public static LinkedList<Item> itemList = new LinkedList<>();
	private static StellarRegistry reg;

	public static void registerAll (StellarRegistry reg) {
		setReg(reg);

		register(passengerRocket, "twopersonrocket");

		register(metalIngot, "metalingot");
		register(metalDust, "metaldust");
		register(alloyIngot, "alloyingot");
		register(alloyDust, "alloydust");
		register(sheetBasic, "metalsheet");
		register(sheetAlloy, "alloysheet");
		register(gearBasic, "metalgear");
		register(gearAlloy, "alloygear");

	}

	public static void register (Item item, String itemName) {
		itemList.add(item);
		reg.registerItem(item, itemName);
	}

	public static void setReg (StellarRegistry reg) {
		ExoItems.reg = reg;
	}

}
