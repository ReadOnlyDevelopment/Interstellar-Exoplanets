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

package net.rom95.common.block.ore;

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
import net.rom95.api.crafting.RecipeBuilder;
import net.rom95.client.CreativeExoTabs;
import net.rom95.common.block.BlockExoOre;
import net.rom95.common.lib.EnumMetal;
import net.rom95.common.lib.interfaces.ICustomModel;
import net.rom95.common.lib.interfaces.item.ItemBlockMetaSubtypes;
import net.rom95.core.ExoInfo;

public class BlockOreMetal extends BlockExoOre implements ICustomModel {
	public static final PropertyEnum<EnumMetal> METAL = PropertyEnum.create("metal", EnumMetal.class);

	private String name;

	public BlockOreMetal(String name, boolean showInTab) {
		super(EnumMetal.values().length);
		this.setHardness(3.0f);
		this.setResistance(15.0f);
		this.setSoundType(SoundType.STONE);
		this.name = name;
		if (showInTab)
			this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);

		for (EnumMetal metal : EnumMetal.values()) {
			if (metal == EnumMetal.COPPER || metal == EnumMetal.TIN || metal == EnumMetal.ALUMINIUM) {
				this.setHarvestLevel("pickaxe", 1, this.getDefaultState().withProperty(METAL, metal));
			}
			else {
				this.setHarvestLevel("pickaxe", 2, this.getDefaultState().withProperty(METAL, metal));
			}
		}
	}

	@Override
	public void addRecipes (RecipeBuilder recipes) {
		for (EnumMetal metal : EnumMetal.values()) {
			ItemStack ore   = new ItemStack(this, 1, metal.meta);
			ItemStack ingot = metal.getIngot();

			// Vanilla smelting
			recipes.addSmelting(ore, ingot, 0.5f);
		}
	}

	@Override
	public void addOreDict () {
		for (EnumMetal metal : EnumMetal.values()) {
			ItemStack stack = new ItemStack(this, 1, metal.getMeta());
			OreDictionary.registerOre("ore" + metal.getMetalName(), stack);
			// Alternative spelling of aluminium
			if (metal == EnumMetal.ALUMINIUM)
				OreDictionary.registerOre("oreAluminum", stack);
		}
	}

	@Override
	public int damageDropped (IBlockState state) {
		return state.getValue(METAL).getMeta();
	}

	@Override
	public void getSubBlocks (CreativeTabs tab, NonNullList<ItemStack> list) {
		Item item = Item.getItemFromBlock(this);
		for (EnumMetal metal : EnumMetal.values()) {
			ItemStack stack = new ItemStack(item, 1, metal.meta);
			list.add(stack);
		}
	}

	@Override
	public IBlockState getStateFromMeta (int meta) {
		return this.getDefaultState().withProperty(METAL, EnumMetal.byMetadata(meta));
	}

	@Override
	public int getMetaFromState (IBlockState state) {
		return state.getValue(METAL).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState () {
		return new BlockStateContainer(this, METAL);
	}

	@Override
	public void registerModels () {
		Item item = Item.getItemFromBlock(this);
		for (EnumMetal metal : EnumMetal.values()) {
			ModelResourceLocation model = new ModelResourceLocation(ExoInfo.RESOURCE_PREFIX + this.name, "metal="
					+ metal.getName());
			ModelLoader.setCustomModelResourceLocation(item, metal.getMeta(), model);
		}
	}

	public static class ItemBlock extends ItemBlockMetaSubtypes {
		public ItemBlock(BlockOreMetal block) {
			super(block);
		}
	}

}
