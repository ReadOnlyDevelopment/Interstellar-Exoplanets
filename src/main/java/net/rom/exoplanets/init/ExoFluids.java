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

package net.rom.exoplanets.init;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.content.block.fluid.BlockMoltenFluid;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ExoFluids {

    public static Fluid fluidmantle;
    public static BlockFluidBase fluidBlockMantle;

    private static final Map<Fluid, BlockFluidBase> fluidBlocks = new HashMap<>();
    private static final Map<BlockFluidBase, String> fluidBlockNames = new HashMap<>();

    public static void init() {
        fluidmantle = newFluid("mantle", 100, 500, 2500, 5);

        fluidBlockMantle = registerFluidBlock(fluidmantle, new BlockMoltenFluid(fluidmantle), "mantle");
    }

    private static Fluid newFluid(String name, int density, int viscosity, int temperature, int luminosity) {
        Fluid fluid = new Fluid(name, new ResourceLocation(ExoInfo.MODID + ":blocks/molten_" + name), new ResourceLocation(ExoInfo.MODID + ":blocks/molten_" + name + "_flow")) {

            @Override
            public String getLocalizedName(FluidStack stack) {
                return ExoplanetsMod.translate.translate(this.unlocalizedName);
            }
        };

        fluid.setDensity(density);
        fluid.setViscosity(viscosity);
        fluid.setTemperature(temperature);
        fluid.setLuminosity(luminosity);
        fluid.setUnlocalizedName(ExoInfo.MODID + "." + name);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
    }

    private static BlockFluidClassic registerFluidBlock(Fluid fluid, BlockFluidClassic block, String name) {
        String blockName = "Molten" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        ExoplanetsMod.REGISTRY.registerBlock(block, blockName);
        block.setUnlocalizedName(ExoInfo.RESOURCE_PREFIX + blockName);
        block.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
        fluidBlocks.put(fluid, block);
        fluidBlockNames.put(block, name);
        return block;
    }

    @SideOnly(Side.CLIENT)
    public static void bakeModels() {
        for (Fluid fluid : fluidBlocks.keySet()) {
            BlockFluidBase block = fluidBlocks.get(fluid);
            Item item = Item.getItemFromBlock(block);
            String name = fluidBlockNames.get(block);
            name = "Molten" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
            final ModelResourceLocation fluidModelLocation = new ModelResourceLocation(
            		ExoInfo.RESOURCE_PREFIX + name, "fluid");
            ModelBakery.registerItemVariants(item);
            ModelLoader.setCustomMeshDefinition(item, stack -> fluidModelLocation);
            ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {

                    return fluidModelLocation;
                }
            });
        }
    }
}
