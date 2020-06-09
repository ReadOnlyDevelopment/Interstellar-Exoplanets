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

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.api.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.exoplanets.content.EnumAlloy;
import net.rom.exoplanets.content.EnumByproduct;

public class ExoRecipes {

	public static void alloySmelterRecipes() {

		AlloyRefineryRecipe.addRecipe(EnumAlloy.BRONZE.getIngot(), 200, 1.2f, "ingotCopper*3", "ingotTin*1");

		AlloyRefineryRecipe.addRecipe(EnumAlloy.BRONZE.getIngot(), 200, 1.2f, "ingotCopper*3", "ingotTin*1");

		AlloyRefineryRecipe.addRecipe(EnumAlloy.BRASS.getIngot(), 200, 1.0f, "ingotCopper*3", "ingotZinc*1");

		ItemStack coal = new ItemStack(Items.COAL, 2);
		AlloyRefineryRecipe.addRecipe(EnumAlloy.STEEL.getIngot(), 800, 2.5f, "ingotIron*1", coal);
		
		ItemStack molybdenumDust = EnumByproduct.MOLYBDENUM.getDust(2);
		AlloyRefineryRecipe.addRecipe(molybdenumDust, 200, 0.5f, "dustTungsten*3", coal);

	}

}
