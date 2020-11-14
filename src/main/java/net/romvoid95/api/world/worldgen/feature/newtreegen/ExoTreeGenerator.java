package net.romvoid95.api.world.worldgen.feature.newtreegen;

import java.util.ArrayList;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.romvoid95.api.content.ExoMaterial;
import net.romvoid95.api.enums.LeafType;
import net.romvoid95.api.enums.WoodType;
import net.romvoid95.common.block.terrain.tree.BlockExoLeaves;
import net.romvoid95.common.block.terrain.tree.BlockExoLog;
import net.romvoid95.core.ExoBlocks;

public abstract class ExoTreeGenerator extends WorldGenAbstractTree implements IBlockSettable {
	
	protected IBlockState treeState = ExoBlocks.EXO_LOG.getDefaultState();
	protected IBlockState branchState = ExoBlocks.EXO_LOG.getDefaultState().withProperty(BlockExoLog.LOG_AXIS, BlockLog.EnumAxis.NONE).withProperty(BlockExoLog.WOOD_TYPE, WoodType.LIGHT);
	protected IBlockState leafState = ExoBlocks.EXO_LEAVES.getDefaultState().withProperty(BlockExoLeaves.VARIANT, LeafType.LIGHT);
	protected IPlantable source = ExoBlocks.EXO_SAPLING;
	
	protected int generateFlag = 2;
	
	protected Set<ExoMaterial> validGroundMaterials = Sets.newHashSet(ExoMaterial.BIO, ExoMaterial.SLUDGE);

	public ExoTreeGenerator() {
		this(false);
	}

	public ExoTreeGenerator(boolean notify) {
		super(notify);
	}

	@Override
	public final void setBlockAndNotify(World world, BlockPos pos, IBlockState state) {
		setBlockAndNotifyAdequately(world, pos, state);
	}

	@Override
	protected boolean canGrowInto(Block blockType) {
		Material material = blockType.getDefaultState().getMaterial();
        return material == Material.AIR;
	}
	
    protected void placeLogBlock(World world, BlockPos pos, IBlockState logBlock, int generateFlag) {

        if (this.isReplaceable(world, pos)) {
            world.setBlockState(pos, logBlock, generateFlag);
        }
    }

    protected void placeLeavesBlock(World world, BlockPos pos, IBlockState leavesBlock, int generateFlag) {

        if (world.isAirBlock(pos)) {
            world.setBlockState(pos, leavesBlock, generateFlag);
        }
    }
    
    protected boolean isGroundValid(World world, BlockPos trunkPos) {
		return isGroundValid(world, trunkPos, false);
	}
    
    protected boolean isGroundValid(World world, BlockPos trunkPos, boolean sandAllowed) {

        IBlockState g = world.getBlockState(new BlockPos(trunkPos.getX(), trunkPos.getY() - 1, trunkPos.getZ()));

        return valid(g);
    }

    protected boolean isGroundValid(World world, ArrayList<BlockPos> trunkPos) {

        if (trunkPos.isEmpty()) {
            throw new RuntimeException("Unable to determine if ground is valid. No trunks.");
        }

        for (int i = 0; i < trunkPos.size(); i++) {
            if (!this.isGroundValid(world, trunkPos.get(i), false)) {
                return false;
            }
        }

        return true;
    }
    
    protected boolean valid(IBlockState state) {
    	return state.getMaterial() == validGroundMaterials;
    }
}
