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

import java.util.ArrayList;
import java.util.List;

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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.EnumAlloy;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.internal.inerf.IAddRecipe;
import net.rom.exoplanets.internal.inerf.ICustomModel;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockAlloy extends BlockMetaSubtypes implements IAddRecipe, ICustomModel {
	private static final PropertyEnum<EnumAlloy> ALLOY = PropertyEnum.create("alloy", EnumAlloy.class);

	public BlockAlloy() {
		super(Material.IRON, EnumAlloy.values().length);
		this.setHardness(3.0f);
		this.setResistance(30.0f);
		this.setSoundType(SoundType.METAL);
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
	}

	@Override
	public void addOreDict() {
		for (EnumAlloy alloy : EnumAlloy.values())
			OreDictionary.registerOre("block" + alloy.getMetalName(), alloy.getBlock());
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(ALLOY).getMeta();
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (ItemStack stack : getSubItems(Item.getItemFromBlock(this))) {
			list.add(stack);
		}
	}

	public List<ItemStack> getSubItems(Item item) {
		List<ItemStack> ret = new ArrayList<>();
		for (IMetal metal : EnumAlloy.values()) {
			ret.add(new ItemStack(item, 1, metal.getMeta()));
		}
		return ret;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(ALLOY, EnumAlloy.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ALLOY).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, ALLOY);
	}

	@Override
	public void registerModels() {
		Item item = Item.getItemFromBlock(this);
		for (EnumAlloy alloy : EnumAlloy.values()) {
			ModelResourceLocation model = new ModelResourceLocation(ExoInfo.RESOURCE_PREFIX + "alloyblock", "alloy=" + alloy.getName());
			ModelLoader.setCustomModelResourceLocation(item, alloy.getMeta(), model);
		}
	}
}
