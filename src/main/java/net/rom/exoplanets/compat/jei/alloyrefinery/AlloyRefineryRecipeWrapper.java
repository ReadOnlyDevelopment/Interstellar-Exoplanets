package net.rom.exoplanets.compat.jei.alloyrefinery;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipeObject;

public class AlloyRefineryRecipeWrapper  implements IRecipeWrapper {
    private final AlloyRefineryRecipe recipe;

    public AlloyRefineryRecipeWrapper(@Nonnull AlloyRefineryRecipe recipe) {
        this.recipe = recipe;
    }

    List<AlloyRefineryRecipeObject> getInputObjects() {
        return Arrays.asList(recipe.getInputs());
    }

    private List<ItemStack> getInputs() {
        List<ItemStack> list = Lists.newArrayList();
        for (AlloyRefineryRecipeObject recipeObject : recipe.getInputs()) {
            list.addAll(recipeObject.getPossibleItemStacks());
        }
        return list;
    }

    List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getOutput());
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, this.getInputs());
        ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        FontRenderer fontRender = minecraft.fontRenderer;
        String str = String.format("%.1f XP", recipe.getExperience());
        fontRender.drawStringWithShadow(str, 63, 0, 0xFFFFFF);
        str = (recipe.getCookTime() / 20) + "s";
        fontRender.drawStringWithShadow(str, 66, 28, 0xFFFFFF);
    }
}
