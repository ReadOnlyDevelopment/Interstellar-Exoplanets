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

package net.romvoid95.common.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import net.romvoid95.common.lib.fluid.FluidBlockBase;
import net.romvoid95.core.initialization.ExoFluids;

public class BlockFluid extends FluidBlockBase implements IFluidBlock {

	public BlockFluid(Fluid fluid, Material material) {
		super(fluid, material);

	}

	@Override
	public void onEntityCollidedWithBlock (World world, BlockPos pos, IBlockState state, Entity entity) {
		if (state.getBlock() == ExoFluids.PRESSURED_WATER) {
			if ((entity instanceof EntityLivingBase)) {
				if (!((EntityLivingBase) entity).isEntityUndead()) {
					entity.motionX *= 0.1D;
					entity.motionY  = -0.1D;
					entity.motionZ *= 0.1D;
				}
			}
		}
	}
}
