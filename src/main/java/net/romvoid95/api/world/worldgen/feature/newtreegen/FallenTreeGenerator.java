package net.romvoid95.api.world.worldgen.feature.newtreegen;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.romvoid95.api.enums.LeafType;
import net.romvoid95.api.enums.WoodType;
import net.romvoid95.common.block.terrain.tree.BlockExoLeaves;
import net.romvoid95.common.block.terrain.tree.BlockExoLog;
import net.romvoid95.core.initialization.ExoBlocks;

public class FallenTreeGenerator extends ExoTreeGenerator {

    private final int minTreeLength = 11;
    private IBlockState stateWood = ExoBlocks.EXO_LOG.getDefaultState().withProperty(BlockExoLog.WOOD_TYPE, WoodType.LIGHT);
    private IBlockState stateLeaves = ExoBlocks.EXO_LEAVES.getDefaultState().withProperty(BlockExoLeaves.VARIANT, LeafType.LIGHT).withProperty(BlockExoLeaves.CHECK_DECAY, false);
    
    public FallenTreeGenerator() {
    	this(false);
    }
    
    public FallenTreeGenerator(boolean notify) {
    	super(notify);
    }

    @Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
        int num = rand.nextInt(5);
        EnumFacing orientation;
        if (num == 0) {
            orientation = EnumFacing.EAST;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
        } else if (num == 1) {
            orientation = EnumFacing.WEST;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
        } else if (num == 1) {
            orientation = EnumFacing.SOUTH;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
        } else {
            orientation = EnumFacing.NORTH;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
        }
        int i = rand.nextInt(2) + this.minTreeLength;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;

                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < worldIn.getHeight()) {
                            if (!this.isReplaceable(worldIn, mutablePos.setPos(l, j, i1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                IBlockState state = worldIn.getBlockState(position.down());

                if (state.getBlock().canSustainPlant(state, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) Blocks.SAPLING) && position.getY() < worldIn.getHeight() - i - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, position.down(), position);

                    for (int j3 = 0; j3 < i; ++j3) {
                        BlockPos offsetPos = position.offset(orientation, j3);
                        state = worldIn.getBlockState(offsetPos);

                        if (state.getBlock().isAir(state, worldIn, offsetPos) || state.getBlock().isLeaves(state, worldIn, offsetPos) || state.getMaterial() == Material.VINE) {
                            this.setBlockAndNotifyAdequately(worldIn, position.offset(orientation, j3), this.stateWood);
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}