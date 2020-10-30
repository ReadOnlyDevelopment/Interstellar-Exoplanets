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

package net.romvoid95.common.block.decoration;

import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;

import net.romvoid95.api.crafting.RecipeBuilder;
import net.romvoid95.client.CreativeExoTabs;
import net.romvoid95.common.block.BlockGeneral;
import net.romvoid95.common.lib.EnumAlloy;
import net.romvoid95.common.lib.EnumMetal;
import net.romvoid95.common.lib.interfaces.IAddRecipe;
import net.romvoid95.common.lib.interfaces.IMetal;
import net.romvoid95.core.initialization.ExoBlocks;
import net.romvoid95.core.initialization.ExoMaterial;

public class BlockMetalDecoration extends BlockGeneral implements IAddRecipe, ISortableBlock {

	public BlockMetalDecoration() {
		super(ExoMaterial.METAL);
        this.setHardness(3.0f);
        this.setResistance(30.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setSoundType(SoundType.METAL);
		this.setCreativeTab(CreativeExoTabs.DECORATION_TAB);
	}

    @Override
    public void addRecipes(RecipeBuilder recipes) {
    	for(IMetal metals : EnumMetal.values()) {
    		ItemStack sheets = metals.getSheet();
    		ItemStack ingots = metals.getIngot();
    	
    	ItemStack grating = new ItemStack(ExoBlocks.GRATING, 4);
		recipes.addShaped("grating", grating, "a a", "bbb", "   ", 'a', Items.IRON_INGOT, 'b', sheets);

    	ItemStack grating_stripe = new ItemStack(ExoBlocks.GRATING_STRIPE, 4);
		recipes.addShaped("grating_stripe", grating_stripe, "aca", "bbb", "   ", 'a', Items.IRON_INGOT, 'b', sheets, 'c', Blocks.WOOL);

    	ItemStack heavy_border = new ItemStack(ExoBlocks.HEAVY_BORDER_METAL, 2);
    	recipes.addSurround("heavy_border", heavy_border, EnumAlloy.STEEL.getSheet(), Items.IRON_INGOT);

    	ItemStack light_border = new ItemStack(ExoBlocks.LIGHT_BORDER_METAL, 2);
    	recipes.addSurround("light_border", light_border, EnumMetal.ALUMINIUM.getSheet(), Items.IRON_INGOT);

    	ItemStack pattern_m1 = new ItemStack(ExoBlocks.PATTERN_METAL, 4);
    	recipes.addShaped("pattern_m1", pattern_m1, "aa ", "bc ", "   ", 'a', sheets, 'b', ingots, 'c', Items.IRON_INGOT);
    	ItemStack pattern_m2 = new ItemStack(ExoBlocks.PATTERN_METAL_1, 4);
    	recipes.addShaped("pattern_m2", pattern_m2, "aa ", "bb ", "   ", 'a', sheets, 'b', ingots);

    	ItemStack rivet = new ItemStack(ExoBlocks.RIVET_METAL, 4);
		recipes.addShaped("rivet", rivet, "a a", "   ", "a a", 'a', sheets);

    	ItemStack fan = new ItemStack(ExoBlocks.METAL_FAN, 4, 7);
		recipes.addShaped("fan", fan, " a ", "aba", " a ", 'a', EnumAlloy.STEEL.getSheet(), 'b', EnumAlloy.STEEL.getGear());
    	}
    }

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}

}
