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

package net.rom.exoplanets.content.block.decoration;

import java.util.Locale;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
        this.setHardness(3.0f);
        this.setResistance(30.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, Type.GRATING));
		this.setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
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
