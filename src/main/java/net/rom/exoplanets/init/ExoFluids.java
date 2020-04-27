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
import net.rom.exoplanets.Exoplanets;
import net.rom.exoplanets.block.fluid.BlockMoltenFluid;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class ExoFluids {

    public static Fluid fluidmantle;
    public static BlockFluidBase fluidBlockMantle;

    private static final Map<Fluid, BlockFluidBase> fluidBlocks = new HashMap<>();
    private static final Map<BlockFluidBase, String> fluidBlockNames = new HashMap<>();

    public static void init() {
        fluidmantle = newFluid("mantle", 4000, 20000, 5000, 200);

        fluidBlockMantle = registerFluidBlock(fluidmantle, new BlockMoltenFluid(fluidmantle), "mantle");
    }

    private static Fluid newFluid(String name, int density, int viscosity, int temperature, int luminosity) {
        Fluid fluid = new Fluid(name, new ResourceLocation(ExoInfo.MODID + ":blocks/molten_" + name), new ResourceLocation(ExoInfo.MODID + ":blocks/molten_" + name + "_flow")) {

            @Override
            public String getLocalizedName(FluidStack stack) {
                return Exoplanets.i18n.translate(this.unlocalizedName);
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
        Exoplanets.REGISTRY.registerBlock(block, blockName);
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
