package net.rom.exoplanets.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.exoplanets.client.gui.GuiAlloyRefinery;
import net.rom.exoplanets.compat.jei.alloyrefinery.AlloyRefineryRecipeCategory;
import net.rom.exoplanets.compat.jei.alloyrefinery.AlloyRefineryRecipeMaker;
import net.rom.exoplanets.compat.jei.alloyrefinery.AlloyRefineryRecipeWrapper;
import net.rom.exoplanets.init.ExoBlocks;

@JEIPlugin
public class ExoplanetsPlugin implements IModPlugin {
	
    @Override
    public void register(IModRegistry reg) {
    	
    	reg.handleRecipes(AlloyRefineryRecipe.class, AlloyRefineryRecipeWrapper::new, AlloyRefineryRecipeCategory.UID);
    	reg.addRecipes(AlloyRefineryRecipeMaker.getRecipes(), AlloyRefineryRecipeCategory.UID);
    	
    	reg.addRecipeClickArea(GuiAlloyRefinery.class, 80, 34, 25, 16, AlloyRefineryRecipeCategory.UID);
    	
        ItemStack metalFurnace = new ItemStack(ExoBlocks.metalFurnace);
        ItemStack alloySmelter = new ItemStack(ExoBlocks.alloyRefinery);
        
        reg.addRecipeCatalyst(metalFurnace, VanillaRecipeCategoryUid.FUEL, VanillaRecipeCategoryUid.SMELTING);
        reg.addRecipeCatalyst(alloySmelter, AlloyRefineryRecipeCategory.UID);
        
        // Description pages
        String descPrefix = "jei.exoplanets.desc.";
        reg.addIngredientInfo(metalFurnace, ItemStack.class, descPrefix + "metal_furnace");
        reg.addIngredientInfo(alloySmelter, ItemStack.class, descPrefix + "alloy_refinery");
        
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
    	 IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
    	 registry.addRecipeCategories(new AlloyRefineryRecipeCategory(guiHelper));
    }

}
