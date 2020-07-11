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

package net.rom.exoplanets.content.block.machine;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rom.exoplanets.content.EnumMachineState;
import net.rom.exoplanets.content.item.IExoMetal;


public class BlockMachine extends BlockContainer implements IExoMetal {
    public static final PropertyEnum<EnumMachineState> FACING = PropertyEnum.create("facing", EnumMachineState.class);

    BlockMachine(Material materialIn) {
        super(materialIn);
        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumMachineState.NORTH_OFF));

        this.setHardness(4.0f);
        this.setResistance(6000.0f);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public List<ItemStack> getSubItems(Item item) {
        return ImmutableList.of(new ItemStack(item));
    }

    public EnumMachineState getMachineState(World world, BlockPos pos) {
        return getMachineState(world.getBlockState(pos));
    }

    public EnumMachineState getMachineState(IBlockState state) {
        return state.getValue(FACING);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public int getLightValue(IBlockState state) {
        return getMachineState(state).isOn ? 15 : 0;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            Block block = worldIn.getBlockState(pos.north()).getBlock();
            Block block1 = worldIn.getBlockState(pos.south()).getBlock();
            Block block2 = worldIn.getBlockState(pos.west()).getBlock();
            Block block3 = worldIn.getBlockState(pos.east()).getBlock();

            EnumMachineState machineState = state.getValue(FACING);

            if (machineState == EnumMachineState.NORTH_OFF && block.isFullBlock(state) && !block1.isFullBlock(state)) {
                machineState = EnumMachineState.SOUTH_OFF;
            } else if (machineState == EnumMachineState.SOUTH_OFF && block1.isFullBlock(state) && !block.isFullBlock(state)) {
                machineState = EnumMachineState.NORTH_OFF;
            } else if (machineState == EnumMachineState.WEST_OFF && block2.isFullBlock(state) && !block3.isFullBlock(state)) {
                machineState = EnumMachineState.EAST_OFF;
            } else if (machineState == EnumMachineState.EAST_OFF && block3.isFullBlock(state) && !block2.isFullBlock(state)) {
                machineState = EnumMachineState.WEST_OFF;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, machineState), 2);
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumMachineState machineState = EnumMachineState.fromEnumFacing(placer.getHorizontalFacing().getOpposite());
        return this.getDefaultState().withProperty(FACING, machineState);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        EnumMachineState machineState = EnumMachineState.fromEnumFacing(placer.getHorizontalFacing().getOpposite());
        world.setBlockState(pos, state.withProperty(FACING, machineState), 2);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IInventory) {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
            world.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
        return Container.calcRedstone(world.getTileEntity(pos));
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public IBlockState getStateFromMeta(int meta) {
        EnumMachineState machineState = EnumMachineState.fromMeta(meta);
        return getDefaultState().withProperty(FACING, machineState);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).meta;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
