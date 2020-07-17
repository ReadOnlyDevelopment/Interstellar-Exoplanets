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

package net.rom.exoplanets.content.block.generic;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.internal.inerf.IAddRecipe;
import net.rom.exoplanets.internal.inerf.ICustomModel;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockMetal extends BlockMetaSubtypes implements IAddRecipe, ICustomModel {
    private static final PropertyEnum<EnumMetal> METAL = PropertyEnum.create("metal", EnumMetal.class);

    public BlockMetal() {
        super(Material.IRON, EnumMetal.values().length);
        this.setHardness(3.0f);
        this.setResistance(30.0f);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
    }

    @Override
    public void addOreDict() {
        for (EnumMetal metal : EnumMetal.values())
                OreDictionary.registerOre("block" + metal.getMetalName(), metal.getBlock());

        // Alternative spelling of aluminium

            OreDictionary.registerOre("blockAluminum", EnumMetal.ALUMINIUM.getBlock());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(METAL).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for (ItemStack stack : getSubItems(Item.getItemFromBlock(this))) {
        	list.add(stack);
        }
    }

    public List<ItemStack> getSubItems(Item item) {
        List<ItemStack> ret = Lists.newArrayList();
        for (IMetal metal : EnumMetal.values()) {
        	ret.add(new ItemStack(item, 1, metal.getMeta()));
        }
        return ret;
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
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return true;
    }

    @Override
    public void registerModels() {
        Item item = Item.getItemFromBlock(this);
        for (EnumMetal metal : EnumMetal.values()) {
            ModelResourceLocation model = new ModelResourceLocation(ExoInfo.RESOURCE_PREFIX + "metalblock", "metal=" + metal.getName());
            ModelLoader.setCustomModelResourceLocation(item, metal.getMeta(), model);
        }
    }
}
