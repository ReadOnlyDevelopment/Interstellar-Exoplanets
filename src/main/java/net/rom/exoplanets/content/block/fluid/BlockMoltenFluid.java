package net.rom.exoplanets.content.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.rom.exoplanets.internal.block.FluidBlockBase;

public class BlockMoltenFluid extends FluidBlockBase {

    public BlockMoltenFluid(Fluid fluid) {
        super(fluid, Material.LAVA);
    }
    
    @Override
    public String getUnlocalizedName() {
        Fluid fluid = FluidRegistry.getFluid(fluidName);
        return fluid != null ? fluid.getUnlocalizedName() : super.getUnlocalizedName();
    }

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {		
	}

}
