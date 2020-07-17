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

package net.rom.exoplanets.content.fluids;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.rom.exoplanets.ExoInfo;

public class ExoFluid {

	public static ExoFluidBase mantle;
	public static final Material MANTLE = (new MaterialLiquid(MapColor.RED_STAINED_HARDENED_CLAY));


	public static void init() {
		register(mantle = (ExoFluidBase) new ExoFluidBase("mantle", new ResourceLocation(ExoInfo.MODID, "blocks/liquid/mantle_still"), new ResourceLocation(ExoInfo.MODID, "blocks/liquid/mantle_flow")).setHasBucket(true).setDensity(40).setGaseous(false).setLuminosity(12).setViscosity(1000).setTemperature(500));
	}

	public static void register(ExoFluidBase fluid) {
		FluidRegistry.registerFluid(fluid);

		if (fluid.isBucketEnabled())
			FluidRegistry.addBucketForFluid(fluid);
	}

}
