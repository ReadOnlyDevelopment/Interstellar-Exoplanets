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
package net.romvoid95.common.block.terrain;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.WorldProvider;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.romvoid95.api.space.prefab.WorldProviderWE_ExoPlanet;
import net.romvoid95.client.CreativeExoTabs;

public class BlockExoTallGrass extends BlockTallGrass {

	private WorldProvider provider;

	public BlockExoTallGrass(WorldProvider provider) {
		super();
		setCreativeTab(CreativeExoTabs.TERRAIN_TAB);
		setSoundType(SoundType.PLANT);
		useNeighborBrightness = true;
		this.provider = provider;
	}

	@Override
	public boolean canSustainBush(IBlockState state) {
		if(((WorldProviderWE_ExoPlanet)provider).getPlanetGrassBlock() != null) {
			return (state.getBlock() == ((WorldProviderWE_ExoPlanet)provider).getPlanetGrassBlock().getDefaultState());
		} else {
			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
}
