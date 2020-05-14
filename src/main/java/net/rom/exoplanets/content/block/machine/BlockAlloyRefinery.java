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

public class BlockAlloyRefinery extends BlockMachine implements IAddRecipe, ITEBlock {

    public BlockAlloyRefinery() {
        super(Material.IRON);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAlloyRefinery();
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileAlloyRefinery.class;
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {
        ItemStack result = new ItemStack(this);
            recipes.addShapedOre("alloy_refinery_1", result, "iii", "a a", "bab", 'i', "plateIron", 'a', "plateAluminum", 'b', Blocks.BRICK_BLOCK);
            recipes.addShapedOre("alloy_refinery_2", result, "iii", "a a", "bab", 'i', "plateIron", 'a', "plateAluminium", 'b', Blocks.BRICK_BLOCK);

    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileAlloyRefinery) {
                player.openGui(ExoplanetsMod.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
}
