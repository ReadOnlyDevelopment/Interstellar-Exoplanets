package net.romvoid95.core.initialization;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.romvoid95.client.CreativeExoTabs;
import net.romvoid95.common.block.fluid.BlockFluid;
import net.romvoid95.common.lib.fluid.ExoMaterial;
import net.romvoid95.core.ExoInfo;

public class ExoFluids {

	public static Block PRESSURED_WATER;
	public static Fluid PRESSURED_WATER_FLUID;

	public static Material pressurizedMaterial = new ExoMaterial(MapColor.GREEN_STAINED_HARDENED_CLAY);

	public static final Map<Fluid, IFluidBlock>  fluidBlocks     = new HashMap<>();
	public static final Map<IFluidBlock, String> fluidBlockNames = new HashMap<>();

	public static void initFluids () {

		PRESSURED_WATER_FLUID = createFluid("pressured_water_fluid", PRESSURED_WATER);
		PRESSURED_WATER_FLUID.setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(PRESSURED_WATER_FLUID);
		FluidRegistry.addBucketForFluid(PRESSURED_WATER_FLUID);

	}

	public static void init () {
		PRESSURED_WATER = new BlockFluid(PRESSURED_WATER_FLUID, pressurizedMaterial);
		registerFluidBlock(PRESSURED_WATER, PRESSURED_WATER_FLUID, "pressured_water");

	}

	private static Fluid createFluid (String name, Block fluidBlock) {
		String           n       = name.replace("_FLUID", "");
		ResourceLocation still   = new ResourceLocation(ExoInfo.RESOURCE_PREFIX + "blocks/fluids/" + n + "_still");
		ResourceLocation flow    = new ResourceLocation(ExoInfo.RESOURCE_PREFIX + "blocks/fluids/" + n + "_flow");
		ResourceLocation overlay = new ResourceLocation(ExoInfo.RESOURCE_PREFIX + "misc/under_" + n);
		Fluid            fluid   = new Fluid(name, still, flow, overlay);
		fluid.setBlock(fluidBlock);
		return fluid;
	}

	private static void registerFluidBlock (Block block, Fluid fluid, String name) {
		ExoBlocks.blocksList.add(block);
		ExoBlocks.register(block, name);
		block.setCreativeTab(CreativeExoTabs.FLUIDS_TABS);
		fluidBlocks.put(fluid, (IFluidBlock) block);
		fluidBlockNames.put((IFluidBlock) block, name);
	}

}
