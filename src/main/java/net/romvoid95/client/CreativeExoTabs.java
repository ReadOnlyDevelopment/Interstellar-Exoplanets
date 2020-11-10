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

package net.romvoid95.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.common.utility.mc.FluidUtility;
import net.romvoid95.core.initialization.ExoBlocks;
import net.romvoid95.core.initialization.ExoFluids;
import net.romvoid95.space.trappist1.TrappistBlocks;

public class CreativeExoTabs {

	public static final CreativeTabs DECORATION_TAB = new Tab("tabDecoration", "tabDeco") {
		@Override
		public ItemStack getTabIconItem () {
			return new ItemStack(ExoBlocks.ROOF_SLANTED);
		}
	};

	public static final CreativeTabs TERRAIN_TAB = new Tab("tabTerrain", "tabTerrain") {
		@Override
		public ItemStack getTabIconItem () {
			return new ItemStack(TrappistBlocks.TrappistE.TRAP1E_GRASS);
		}
	};

	public static final CreativeTabs ITEMS_TABS = new Tab("tabItems", "tabItems") {
		@Override
		public ItemStack getTabIconItem () {
			return new ItemStack(Blocks.FIRE);
		}
	};

	public static final CreativeTabs FLUIDS_TABS = new Tab("tabFluids", "tabFluids") {
		@Override
		public ItemStack getTabIconItem () {
			return new ItemStack(FluidUtility.getBucket(ExoFluids.PRESSURED_WATER_FLUID).getItem());
		}
	};

	static class Tab extends CreativeTabs {

		private String assetName;

		public Tab(String label, String assetName) {
			super(label);
			setBackgroundImageName(assetName);
		}

		@Override
		public Tab setBackgroundImageName (String assetName) {
			this.assetName = assetName;
			return this;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public ResourceLocation getBackgroundImage () {
			return Assets.getTexture(assetName);
		}

		@Override
		public boolean hasSearchBar () {
			return true;
		}

		@Override
		public ItemStack getTabIconItem () {
			return null;
		}
	}

}
