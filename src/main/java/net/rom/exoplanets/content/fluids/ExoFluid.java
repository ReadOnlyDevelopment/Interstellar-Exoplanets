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
