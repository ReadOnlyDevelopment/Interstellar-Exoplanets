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


import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeEventFactory;
import net.rom.exoplanets.internal.EnumUtils;
import net.rom.exoplanets.internal.inerf.IFuelBurner;

public class FurnaceFuelBurner implements IFuelBurner {
    private final BurnCondition burnCondition;
    @Getter private int timeRemaining;
    @Getter private int currentItemMaxTime;

    public FurnaceFuelBurner(BurnCondition burnCondition) {
        this.burnCondition = burnCondition;
    }

    @Override
    public boolean feedFuel(ItemStack stack) {
        int value = ForgeEventFactory.getItemBurnTime(stack);
        if (value > 0) {
            timeRemaining = currentItemMaxTime = value;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasFuel() {
        return timeRemaining > 0;
    }

    @Override
    public boolean tickFuel(boolean isProcessing) {
        if (burnCondition.shouldTick(this, isProcessing)) {
            --timeRemaining;
            return true;
        }
        return false;
    }

    private static FurnaceFuelBurner readFromNBT(NBTTagCompound tags) {
        BurnCondition condition = EnumUtils.fromString(BurnCondition.class, tags.getString("Condition"))
                .orElse(BurnCondition.DEFAULT);

        FurnaceFuelBurner result = new FurnaceFuelBurner(condition);
        result.timeRemaining = tags.getInteger("Time");
        result.currentItemMaxTime = tags.getInteger("MaxTime");

        return result;
    }

    private static void writeToNBT(NBTTagCompound tags, FurnaceFuelBurner burner) {
        if (burner.burnCondition != BurnCondition.DEFAULT)
            tags.setString("Condition", burner.burnCondition.name());
        tags.setInteger("Time", burner.timeRemaining);
        tags.setInteger("MaxTime", burner.currentItemMaxTime);
    }

    static {
        SyncVariable.Helper.registerSerializer(FurnaceFuelBurner.class,
                FurnaceFuelBurner::readFromNBT,
                FurnaceFuelBurner::writeToNBT);
    }

    public enum BurnCondition {
        STANDARD {
            @Override
            boolean shouldTick(FurnaceFuelBurner burner, boolean processing) {
                return burner.timeRemaining > 0;
            }
        },
        ONLY_WHEN_PROCESSING {
            @Override
            boolean shouldTick(FurnaceFuelBurner burner, boolean processing) {
                return processing;
            }
        },
        ALWAYS {
            @Override
            boolean shouldTick(FurnaceFuelBurner burner, boolean processing) {
                return true;
            }
        };

        abstract boolean shouldTick(FurnaceFuelBurner burner, boolean processing);

        private static final BurnCondition DEFAULT = STANDARD;
    }
}
