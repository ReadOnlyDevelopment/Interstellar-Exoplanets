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

package net.rom.exoplanets.content.block.decoration;

import java.util.Locale;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.BlockYellowFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.EnumAlloy;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.internal.inerf.IAddRecipe;
import net.rom.exoplanets.internal.inerf.ICustomModel;
import net.rom.exoplanets.internal.inerf.item.ItemBlockMetaSubtypes;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockMetalDecoration extends BlockMetaSubtypes implements ICustomModel, IAddRecipe, ISortableBlock {
	
	public enum Type implements IStringSerializable {
		GRATING, 
		GRATING_STRIPE, 
		HEAVY_BORDER_METAL, 
		LIGHT_BORDER_METAL, 
		PATTERN_METAL,
		PATTERN_METAL_1,
		RIVET_METAL,
		METAL_FAN;
		
        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }

        public int getMetadata() {
            return ordinal();
        }
		
	}
    public static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

	
	public BlockMetalDecoration() {
		super(Material.ROCK, Type.values().length);
        setHardness(3.0f);
        setResistance(30.0f);
        setHarvestLevel("pickaxe", 2);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, Type.GRATING));
		setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	}
	
    public ItemStack getStack(Type type, int count) {
        return new ItemStack(this, count, type.ordinal());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {

        meta = MathHelper.clamp(meta, 0, Type.values().length - 1);
        return this.getDefaultState().withProperty(VARIANT, Type.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public void registerModels() {
        Item item = Item.getItemFromBlock(this);
        String fullName = ExoInfo.RESOURCE_PREFIX + "metaldecoration";
        for (Type type : Type.values()) {
            ModelResourceLocation model = new ModelResourceLocation(fullName, "type=" + type.getName());
            ModelLoader.setCustomModelResourceLocation(item, type.ordinal(), model);
        }
    }
    
    @Override
    public void addRecipes(RecipeBuilder recipes) {
    	for(IMetal metals : EnumMetal.values()) {
    		ItemStack plates = metals.getPlate();
    		ItemStack ingots = metals.getIngot();
    	
    	ItemStack grating = new ItemStack(this, 4, 0);
		recipes.addShaped("grating", grating, "a a", "bbb", "   ", 'a', Items.IRON_INGOT, 'b', plates);

    	ItemStack grating_stripe = new ItemStack(this, 4, 1);
		recipes.addShaped("grating_stripe", grating_stripe, "aca", "bbb", "   ", 'a', Items.IRON_INGOT, 'b', plates, 'c', Blocks.WOOL);

    	ItemStack heavy_border = new ItemStack(this, 2, 2);
    	recipes.addSurround("heavy_border", heavy_border, EnumAlloy.STEEL.getPlate(), Items.IRON_INGOT);

    	ItemStack light_border = new ItemStack(this, 2, 3);
    	recipes.addSurround("light_border", light_border, EnumMetal.ALUMINIUM.getPlate(), Items.IRON_INGOT);

    	ItemStack pattern_m1 = new ItemStack(this, 4, 4);
    	recipes.addShaped("pattern_m1", pattern_m1, "aa ", "bc ", "   ", 'a', plates, 'b', ingots, 'c', Items.IRON_INGOT);
    	ItemStack pattern_m2 = new ItemStack(this, 4, 5);
    	recipes.addShaped("pattern_m2", pattern_m2, "aa ", "bb ", "   ", 'a', plates, 'b', ingots);

    	ItemStack rivet = new ItemStack(this, 4, 6);
		recipes.addShaped("rivet", rivet, "a a", "   ", "a a", 'a', plates);

    	ItemStack fan = new ItemStack(this, 4, 7);
		recipes.addShaped("fan", fan, " a ", "aba", " a ", 'a', EnumAlloy.STEEL.getPlate(), 'b', EnumAlloy.STEEL.getGear());
    	}
    }

    public static class ItemBlock extends ItemBlockMetaSubtypes {
        public ItemBlock(BlockMetalDecoration block) {
            super(block);
        }
    }

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}

}
