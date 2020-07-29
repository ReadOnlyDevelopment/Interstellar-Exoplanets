package net.rom.exoplanets.content.fluids;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.ExoInfo;

public class ExoFluid extends BlockFluidClassic implements IExoFluid {

	protected String nameString;

	public ExoFluid(String fluidName, Fluid fluid, Material material) {
		super(fluid, material);
		this.make(fluidName);
	}

	public ExoFluid(String fluidName, Fluid fluid, Material material, MapColor mapColor) {
		super(fluid, material);
		this.make(fluidName);
	}

	protected void make(String fluidName) {
		this.nameString = fluidName;
		this.setUnlocalizedName("fluid_" + fluidName);
		this.setRegistryName("fluid_" + fluidName);
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos,
			EntityLiving.SpawnPlacementType type) {
		return false;
	}

	@Override
	public boolean canDrain(World worldIn, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		} else {
			return super.canDisplace(world, pos);
		}
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		Item item = Item.getItemFromBlock(this);
		MapUtil mapper = new MapUtil(ExoInfo.MODID, "fluid", nameString);

		ModelBakery.registerItemVariants(item);
		ModelLoader.setCustomMeshDefinition(item, mapper);

		ModelLoader.setCustomStateMapper(this, mapper);
	}
	
	public class MapUtil extends StateMapperBase implements ItemMeshDefinition {
		
		public final ModelResourceLocation location;
		
		public MapUtil(String modName, String fileName, String modelName) {
			this.location = new ModelResourceLocation(modName + ":" + fileName, modelName);
		}
		
		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return location;
		}
		
		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack) {
			return location;
		}
	}

}
