package net.rom.exoplanets.content.block.machine;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.internal.IAddRecipe;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.block.ITEBlock;

public class BlockMetalFurnace extends BlockMachine implements IAddRecipe, ITEBlock {

	public BlockMetalFurnace() {
		super(Material.IRON);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileMetalFurnace();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileMetalFurnace.class;
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		ItemStack result = new ItemStack(this);

		recipes.addShapedOre("metal_furnace_bronze", result, "aaa", "afa", "bab", 'a', "plateBronze", 'b', Blocks.BRICK_BLOCK, 'f', Blocks.FURNACE);
		recipes.addShapedOre("metal_furnace_brass", result, "aaa", "afa", "bab", 'a', "plateBrass", 'b', Blocks.BRICK_BLOCK, 'f', Blocks.FURNACE);

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileMetalFurnace) {
				player.openGui(ExoplanetsMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
}
