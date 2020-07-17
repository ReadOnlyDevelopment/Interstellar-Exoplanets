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

package net.rom.exoplanets.content.block.machine;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.content.IMetal;
import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.inerf.IAddRecipe;
import net.rom.exoplanets.internal.inerf.ITEBlock;

public class BlockAlloyRefinery extends BlockMachine implements IAddRecipe, ITEBlock {

    public BlockAlloyRefinery() {
        super(Material.IRON);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAlloyRefinery();
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileAlloyRefinery.class;
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {
    	
    	for (IMetal metals : EnumMetal.values()) {

    		ItemStack metalBlocks = metals.getBlock();
            ItemStack result = new ItemStack(this);
            recipes.addShaped("alloy_refinery", result, "iii", "afa", "bab", 'i', "plateTitanium", 'a', "platePlatinum", 'b', metalBlocks, 'f' , ExoBlocks.metalFurnace);
    	}


    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileAlloyRefinery) {
                player.openGui(ExoplanetsMod.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
}
