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

package net.rom.exoplanets.internal.tile;

import java.util.Collection;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.rom.exoplanets.internal.MathUtils;
import net.rom.exoplanets.internal.inerf.IFuelBurner;
import net.rom.exoplanets.internal.inerf.InventoryUtils;

public abstract class TileEntityItemProcessor extends TileSidedInventoryExo implements ITickable {
    private static final int LONG_UPDATE_DELAY = 20;

    @SyncVariable(name = "Progress")
    private int progress = 0;
    @SyncVariable(name = "Burner")
    @Nullable
    private IFuelBurner burner;

    // Store the different slot types in separate list. Bit harder to manage, but eliminates some object creation.
    private NonNullList<ItemStack> inputs;
    private NonNullList<ItemStack> fuels;
    private NonNullList<ItemStack> outputs;

    /**
     * Output overflow, because verifying whether or not items can fit in the output slots could be
     * expensive, since multiple items could come from a single operation. This variable is
     * reassigned whenever all outputs cannot fit.
     */
    private Collection<ItemStack> overflow = ImmutableList.of();
    private int timer = MathUtils.nextInt(LONG_UPDATE_DELAY);


    public TileEntityItemProcessor(int inputCount, int fuelCount, int outputCount) {
        this(inputCount, fuelCount, outputCount, new FurnaceFuelBurner(FurnaceFuelBurner.BurnCondition.STANDARD));
    }

    public TileEntityItemProcessor(int inputCount, int fuelCount, int outputCount, @Nullable IFuelBurner burner) {
        if (inputCount < 1)
            throw new IllegalArgumentException("Input slot count must be greater than zero");
        if (outputCount < 1)
            throw new IllegalArgumentException("Output slot count must be greater than zero");

        this.inputs = NonNullList.withSize(inputCount, ItemStack.EMPTY);
        this.fuels = NonNullList.withSize(fuelCount, ItemStack.EMPTY);
        this.outputs = NonNullList.withSize(outputCount, ItemStack.EMPTY);
        this.burner = burner;
    }

    //region abstract methods

    protected abstract int getWorkTime(Collection<ItemStack> inputs);

    protected abstract boolean canWork(Collection<ItemStack> inputs);

    protected abstract Collection<ItemStack> getWorkResult(Collection<ItemStack> inputs);

    protected abstract void consumeIngredients(Collection<ItemStack> inputs);

    protected abstract boolean isInputItem(ItemStack stack);

    protected abstract boolean isFuelItem(ItemStack stack);

    //endregion

    private boolean hasSpaceForOutputs() {
        // Just rely on the overflow buffer.
        return overflow.isEmpty();
    }

    private ItemStack getFuelStack() {
        int start = getInputSlotCount();
        return getStackFromSlotRange(start, start + getFuelSlotCount());
    }

    @Deprecated
    private ItemStack getStackFromSlotRange(int startInclusive, int endExclusive) {
        for (int i = startInclusive; i < endExclusive; ++i) {
            ItemStack stack = getStackInSlot(i);
            if (!stack.isEmpty()) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    protected boolean doWork() {
        boolean requiresClientSync = false;
        boolean processing = false;

        if (!inputs.isEmpty() && canWork(inputs) && hasSpaceForOutputs() && (burner == null || burner.hasFuel())) {
            final int workTime = getWorkTime(inputs);

            if (progress < workTime) {
                ++progress;
                requiresClientSync = true;
                processing = true;
            }

            if (progress >= workTime) {
                Collection<ItemStack> result = getWorkResult(inputs);
                int slotStart = getInputSlotCount() + getFuelSlotCount();
                result = InventoryUtils.mergeItems(this, slotStart, slotStart + getOutputSlotCount(), result);
                if (!result.isEmpty()) {
                    overflow = result;
                }

                progress = 0;
                requiresClientSync = true;
            }
        }

        if (burner != null && burner.tickFuel(processing) && !burner.hasFuel()) {
            ItemStack fuel = getFuelStack();
            if (!fuel.isEmpty()) {
                burner.feedFuel(fuel);
                fuel.shrink(1);
            }
        }

        return requiresClientSync;
    }

    @Override
    public void update() {
        ++timer;

        if (!world.isRemote) {
            boolean requiresClientSync = doWork();

            if (timer % LONG_UPDATE_DELAY == 0 && !overflow.isEmpty()) {
                int oldCount = overflow.size();
                // Try to move overflow to output slots
                int start = getInputSlotCount() + getFuelSlotCount();
                overflow = InventoryUtils.mergeItems(this, start, start + getOutputSlotCount(), overflow);
                requiresClientSync |= oldCount != overflow.size();
            }

            if (requiresClientSync) {
                IBlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state, 3);
            }
        }
    }

    private int getInputSlotCount() {
        return inputs.size();
    }

    private int getFuelSlotCount() {
        return fuels.size();
    }

    private int getOutputSlotCount() {
        return outputs.size();
    }

    //region Inventory management overrides

    @Override
    public int getSizeInventory() {
        return getInputSlotCount() + getFuelSlotCount() + getOutputSlotCount();
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (index >= getSizeInventory())
            return ItemStack.EMPTY;

        if (index >= getInputSlotCount() + getFuelSlotCount())
            return ItemStackHelper.getAndSplit(outputs, index - (getInputSlotCount() + getFuelSlotCount()), count);
        if (index >= getInputSlotCount() && getFuelSlotCount() > 0)
            return ItemStackHelper.getAndSplit(fuels, index - getInputSlotCount(), count);
        return ItemStackHelper.getAndSplit(inputs, index, count);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index >= getSizeInventory())
            return ItemStack.EMPTY;

        if (index >= getInputSlotCount() + getFuelSlotCount())
            return outputs.get(index - (getInputSlotCount() + getFuelSlotCount()));
        if (index >= getInputSlotCount() && getFuelSlotCount() > 0)
            return fuels.get(index - getInputSlotCount());
        return inputs.get(index);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index >= getInputSlotCount() + getFuelSlotCount())
            return false;
        if (index >= getInputSlotCount() && getFuelSlotCount() > 0)
            return isFuelItem(stack);
        return isInputItem(stack);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index >= getSizeInventory())
            return ItemStack.EMPTY;

        if (index >= getInputSlotCount() + getFuelSlotCount())
            return ItemStackHelper.getAndRemove(outputs, index - (getInputSlotCount() + getFuelSlotCount()));
        if (index >= getInputSlotCount() && getFuelSlotCount() > 0)
            return ItemStackHelper.getAndRemove(fuels, index - getInputSlotCount());
        return ItemStackHelper.getAndRemove(inputs, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index >= getSizeInventory())
            return;

        if (index >= getInputSlotCount() + getFuelSlotCount())
            outputs.set(index - (getInputSlotCount() + getFuelSlotCount()), stack);
        else if (index >= getInputSlotCount() && getFuelSlotCount() > 0)
            fuels.set(index - getInputSlotCount(), stack);
        else
            inputs.set(index, stack);
    }

    //endregion
}
