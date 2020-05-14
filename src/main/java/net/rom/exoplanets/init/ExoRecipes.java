package net.rom.exoplanets.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipe;
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
