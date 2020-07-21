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
package net.rom.exoplanets.crafting;

import edu.umd.cs.findbugs.annotations.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class IngredientFluidStack extends Ingredient {
	private final FluidStack fluid;

	public IngredientFluidStack(FluidStack fluid) {
		super(0);
		this.fluid = fluid;
	}

	public IngredientFluidStack(Fluid fluid, int amount) {
		this(new FluidStack(fluid, amount));
	}

	public FluidStack getFluid() {
		return fluid;
	}

	ItemStack[] cachedStacks;

	@Override
	public ItemStack[] getMatchingStacks() {
		if (cachedStacks == null) {
			cachedStacks = new ItemStack[] { FluidUtil.getFilledBucket(fluid) };
		}
		return this.cachedStacks;
	}

	@Override
	public boolean apply(@Nullable ItemStack stack) {
		if (stack == null) {
			return false;
		} else {
			FluidStack fs = FluidUtil.getFluidContained(stack);
			return fs == null && this.fluid == null || fs != null && fs.containsFluid(fluid);
		}
	}
}
