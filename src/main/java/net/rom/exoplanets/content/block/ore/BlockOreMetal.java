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

package net.rom.exoplanets.content.block.ore;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.block.BlockExoOre;
import net.rom.exoplanets.content.block.decoration.BlockMetalDecoration;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.inerf.ICustomModel;
import net.rom.exoplanets.internal.inerf.item.ItemBlockMetaSubtypes;

public class BlockOreMetal extends BlockExoOre implements ICustomModel {
    public static final PropertyEnum<EnumMetal> METAL = PropertyEnum.create("metal", EnumMetal.class);

    public BlockOreMetal() {
        super(EnumMetal.values().length);
        setHardness(3.0f);
        setResistance(15.0f);
        setSoundType(SoundType.STONE);

        for (EnumMetal metal : EnumMetal.values()) {
            if (metal == EnumMetal.COPPER || metal == EnumMetal.TIN || metal == EnumMetal.ALUMINIUM) {
                setHarvestLevel("pickaxe", 1, getDefaultState().withProperty(METAL, metal));
            } else {
                setHarvestLevel("pickaxe", 2, getDefaultState().withProperty(METAL, metal));
            }
        }
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {
        for (EnumMetal metal : EnumMetal.values()) {
            ItemStack ore = new ItemStack(this, 1, metal.meta);
                ItemStack ingot = metal.getIngot();

                // Vanilla smelting
                    recipes.addSmelting(ore, ingot, 0.5f);
        }
    }

    @Override
    public void addOreDict() {
        for (EnumMetal metal : EnumMetal.values()) {
            ItemStack stack = new ItemStack(this, 1, metal.getMeta());
                OreDictionary.registerOre("ore" + metal.getMetalName(), stack);
                // Alternative spelling of aluminium
                if (metal == EnumMetal.ALUMINIUM)
                    OreDictionary.registerOre("oreAluminum", stack);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(METAL).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        Item item = Item.getItemFromBlock(this);
        for (EnumMetal metal : EnumMetal.values()) {
            ItemStack stack = new ItemStack(item, 1, metal.meta);
                list.add(stack);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(METAL, EnumMetal.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(METAL).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, METAL);
    }

    @Override
    public void registerModels() {
        Item item = Item.getItemFromBlock(this);
        for (EnumMetal metal : EnumMetal.values()) {
            ModelResourceLocation model = new ModelResourceLocation(ExoInfo.RESOURCE_PREFIX + "metalore", "metal=" + metal.getName());
            ModelLoader.setCustomModelResourceLocation(item, metal.getMeta(), model);
        }
    }
    
    public static class ItemBlock extends ItemBlockMetaSubtypes {
        public ItemBlock(BlockOreMetal block) {
            super(block);
        }
    }
    
    
}
