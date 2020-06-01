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
	
	public static final ItemTwoPlayerRocket passengerRocket = new ItemTwoPlayerRocket();
    public static final ItemIngotMetal metalIngot = new ItemIngotMetal();
    public static final ItemNuggetMetal metalNugget = new ItemNuggetMetal();
    public static final ItemDustMetal metalDust = new ItemDustMetal();
    public static final ItemIngotAlloy alloyIngot = new ItemIngotAlloy();
    public static final ItemNuggetAlloy alloyNugget = new ItemNuggetAlloy();
    public static final ItemDustAlloy alloyDust = new ItemDustAlloy();
    public static final ItemDustByproduct dustByproduct = new ItemDustByproduct();
    public static final ItemCraftingItem plateBasic = new ItemCraftingItem("plate", false);
    public static final ItemCraftingItem plateAlloy = new ItemCraftingItem("plate", true);
    public static final ItemCraftingItem gearBasic = new ItemCraftingItem("gear", false);
    public static final ItemCraftingItem gearAlloy = new ItemCraftingItem("gear", true);

	public static void registerAll(StellarRegistry registry) {
		
		registry.registerItem(passengerRocket, "twopersonrocket");

		registry.registerItem(metalIngot, "metalingot");
		registry.registerItem(metalNugget, "metalnugget");
		registry.registerItem(metalDust, "metaldust");
		registry.registerItem(alloyIngot, "alloyingot");
		registry.registerItem(alloyNugget, "alloynugget");
		registry.registerItem(alloyDust, "alloydust");
		registry.registerItem(dustByproduct, "byproductdust");
		registry.registerItem(plateBasic, "metalplate");
		registry.registerItem(plateAlloy, "alloyplate");
		registry.registerItem(gearBasic, "metalgear");
		registry.registerItem(gearAlloy, "alloygear");

	}

}
