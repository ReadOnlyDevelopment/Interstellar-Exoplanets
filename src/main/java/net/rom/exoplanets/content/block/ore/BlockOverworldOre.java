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

import java.util.Locale;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.content.item.EnumIngots;
import net.rom.exoplanets.internal.IAddRecipe;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.internal.client.ICustomModel;
import net.rom.exoplanets.internal.item.ItemBlockMetaSubtypes;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockOverworldOre extends BlockMetaSubtypes implements ICustomModel, IAddRecipe, ISortableBlock {
	
	public enum Type implements IStringSerializable {
		ORELIMONITE, ORERUTHENIUM, ORERUTILE;
		
        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }

        public int getMetadata() {
            return ordinal();
        }
		
	}
    public static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

	
	public BlockOverworldOre() {
		super(Material.ROCK, Type.values().length);
        setHardness(3.0f);
        setResistance(30.0f);
        setHarvestLevel("pickaxe", 2);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, Type.ORELIMONITE));
		setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
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
        String fullName = ExoInfo.RESOURCE_PREFIX + "overworldore";
        for (Type type : Type.values()) {
            ModelResourceLocation model = new ModelResourceLocation(fullName, "type=" + type.getName());
            ModelLoader.setCustomModelResourceLocation(item, type.ordinal(), model);
        }
    }
    
    @Override
    public void addRecipes(RecipeBuilder recipes) {
    	ItemStack LIMONITE_ORE = new ItemStack(this, 1, 0);
        ItemStack LIMONITE_INGOT = EnumIngots.LIMONITE.getStack();
        recipes.addSmelting(LIMONITE_ORE, LIMONITE_INGOT, 1.0f);
        
    	ItemStack RUTHENIUM_ORE = new ItemStack(this, 1, 1);
        ItemStack RUTHENIUM_INGOT = EnumIngots.LIMONITE.getStack();
        recipes.addSmelting(RUTHENIUM_ORE, RUTHENIUM_INGOT, 1.0f);
        
    	ItemStack RUTILE_ORE = new ItemStack(this, 1, 2);
        ItemStack RUTILE_INGOT = EnumIngots.LIMONITE.getStack();
        recipes.addSmelting(RUTILE_ORE, RUTILE_INGOT, 1.0f);

    }
    
    @Override
    public void addOreDict() {
        OreDictionary.registerOre("oreLimonite", new ItemStack(this, 1, 0));
        OreDictionary.registerOre("oreRuthenium", new ItemStack(this, 1, 1));
        OreDictionary.registerOre("oreRutile", new ItemStack(this, 1, 2));
    }
    
    public static class ItemBlock extends ItemBlockMetaSubtypes {
        public ItemBlock(BlockOverworldOre block) {
            super(block);
        }
    }

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.ORE;
	}

}
