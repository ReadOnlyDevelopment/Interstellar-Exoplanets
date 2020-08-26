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

import java.util.Random;

import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.rom95.api.crafting.RecipeBuilder;
import net.rom95.common.block.BlockGeneral;

public class DenseMeteoricIron extends BlockGeneral {


	public DenseMeteoricIron(Material materialIn) {
		super(Material.IRON);
		this.setHardness(3.0f);
		this.setResistance(15.0f);
		this.setSoundType(SoundType.ANVIL);

	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		ItemStack ore   = new ItemStack(this, 1);
		ItemStack ingot = null;  //metal.getIngot();

		// Vanilla smelting
		recipes.addSmelting(ore, ingot, 0.5f);
	}

	@Override
	public void addOreDict() {
		ItemStack stack = new ItemStack(this, 1);
		OreDictionary.registerOre("oreMeteoricIron", stack);

	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return GCItems.meteorChunk;
	}
}
