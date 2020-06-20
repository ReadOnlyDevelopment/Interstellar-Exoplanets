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

package net.rom.exoplanets.init;

import java.util.LinkedList;

import net.minecraft.item.Item;
import net.rom.exoplanets.content.item.ItemCraftingItem;
import net.rom.exoplanets.content.item.ItemDustAlloy;
import net.rom.exoplanets.content.item.ItemDustByproduct;
import net.rom.exoplanets.content.item.ItemDustMetal;
import net.rom.exoplanets.content.item.ItemIngotAlloy;
import net.rom.exoplanets.content.item.ItemIngotMetal;
import net.rom.exoplanets.content.item.ItemNuggetAlloy;
import net.rom.exoplanets.content.item.ItemNuggetMetal;
import net.rom.exoplanets.content.item.ItemTwoPlayerRocket;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoItems {

	public static final Item passengerRocket = new ItemTwoPlayerRocket();
    public static final Item metalIngot = new ItemIngotMetal();
    public static final Item metalNugget = new ItemNuggetMetal();
    public static final Item metalDust = new ItemDustMetal();
    public static final Item alloyIngot = new ItemIngotAlloy();
    public static final Item alloyNugget = new ItemNuggetAlloy();
    public static final Item alloyDust = new ItemDustAlloy();
    public static final Item dustByproduct = new ItemDustByproduct();
    public static final Item plateBasic = new ItemCraftingItem("plate", false);
    public static final Item plateAlloy = new ItemCraftingItem("plate", true);
    public static final Item gearBasic = new ItemCraftingItem("gear", false);
    public static final Item gearAlloy = new ItemCraftingItem("gear", true);

    public static LinkedList<Item> itemList = new LinkedList<>();
	private static StellarRegistry reg;


	public static void registerAll(StellarRegistry reg) {
		setReg(reg);

		register(passengerRocket, "twopersonrocket");

		register(metalIngot, "metalingot");
		register(metalNugget, "metalnugget");
		register(metalDust, "metaldust");
		register(alloyIngot, "alloyingot");
		register(alloyNugget, "alloynugget");
		register(alloyDust, "alloydust");
		register(dustByproduct, "byproductdust");
		register(plateBasic, "metalplate");
		register(plateAlloy, "alloyplate");
		register(gearBasic, "metalgear");
		register(gearAlloy, "alloygear");

	}

	public static void register(Item item, String itemName) {
		itemList.add(item);
		reg.registerItem(item, itemName);
	}

	public static void setReg(StellarRegistry reg) {
		ExoItems.reg = reg;
	}

}
