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

package net.rom.exoplanets.content.block;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.internal.block.BlockBase;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockDecoration extends BlockBase implements ISortableBlock {

	public BlockDecoration(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	}

	public ItemStack getStack(int count) {
		return new ItemStack(this, count);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this);
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}

}
