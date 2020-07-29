package net.rom.exoplanets.init;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.material.Material;
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
import net.rom.exoplanets.content.block.fluid.BlockFluid;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ExoFluids {

    public static Fluid fluidpressurized;
    public static BlockFluidBase fluidBlockMantle;

    private static final Map<Fluid, BlockFluidBase> fluidBlocks = new HashMap<>();
    private static final Map<BlockFluidBase, String> fluidBlockNames = new HashMap<>();

	public static void init () {
		fluidpressurized = newFluid("pressurizedfluid", 100, 500, 80, 0, 0xadff2f96);

        fluidBlockMantle = registerFluidBlock(fluidpressurized, new BlockFluid(fluidpressurized, Material.WATER), "pressurizedfluid");
	}
	
    private static Fluid newFluid(String name, int density, int viscosity, int temperature, int luminosity, int tintColor) {
        Fluid fluid = new Fluid(name, new ResourceLocation(ExoInfo.MODID + ":blocks/" + name + "_still"), new ResourceLocation(ExoInfo.MODID + ":blocks/" + name + "_flow")) {
            @Override
            public int getColor() {
                return tintColor;
            }
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
        String blockName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
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
