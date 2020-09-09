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

package net.romvoid95.common.block.machine;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.romvoid95.api.crafting.RecipeBuilder;
import net.romvoid95.common.lib.interfaces.IAddRecipe;
import net.romvoid95.common.lib.interfaces.IBlockTileEntity;
import net.romvoid95.common.tile.TileEntityMetalFurnace;
import net.romvoid95.core.ExoplanetsMod;

public class BlockMetalFurnace extends BlockMachine implements IAddRecipe, IBlockTileEntity {

	public BlockMetalFurnace() {
		super(Material.IRON);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMetalFurnace();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityMetalFurnace.class;
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		ItemStack result = new ItemStack(this);

		recipes.addShaped("metal_furnace_bronze", result, "aaa", "afa", "bab", 'a', "sheetBronze", 'b', Blocks.BRICK_BLOCK, 'f', Blocks.FURNACE);
		recipes.addShaped("metal_furnace_brass", result, "aaa", "afa", "bab", 'a', "sheetBrass", 'b', Blocks.BRICK_BLOCK, 'f', Blocks.FURNACE);

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileEntityMetalFurnace) {
				player.openGui(ExoplanetsMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
}
