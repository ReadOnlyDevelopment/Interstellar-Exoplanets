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

package net.rom.exoplanets.content.block.machine;

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
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.inerf.IAddRecipe;
import net.rom.exoplanets.internal.inerf.ITEBlock;

public class BlockMetalFurnace extends BlockMachine implements IAddRecipe, ITEBlock {

	public BlockMetalFurnace() {
		super(Material.IRON);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileMetalFurnace();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileMetalFurnace.class;
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		ItemStack result = new ItemStack(this);

		recipes.addShaped("metal_furnace_bronze", result, "aaa", "afa", "bab", 'a', "plateBronze", 'b', Blocks.BRICK_BLOCK, 'f', Blocks.FURNACE);
		recipes.addShaped("metal_furnace_brass", result, "aaa", "afa", "bab", 'a', "plateBrass", 'b', Blocks.BRICK_BLOCK, 'f', Blocks.FURNACE);

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileMetalFurnace) {
				player.openGui(ExoplanetsMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
}
