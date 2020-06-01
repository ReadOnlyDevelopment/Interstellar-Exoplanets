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

package net.rom.exoplanets.content;

import javax.annotation.Nonnull;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum EnumMachineState implements IStringSerializable {
    NORTH_OFF(2, "north_off", false),
    SOUTH_OFF(3, "south_off", false),
    WEST_OFF(4, "west_off", false),
    EAST_OFF(5, "east_off", false),
    NORTH_ON(10, "north_on", true),
    SOUTH_ON(11, "south_on", true),
    WEST_ON(12, "west_on", true),
    EAST_ON(13, "east_on", true);

    public final int meta;
    public final String name;
    public final boolean isOn;

    EnumMachineState(int meta, String name, boolean isOn) {
        this.meta = meta;
        this.name = name;
        this.isOn = isOn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    public static EnumMachineState fromEnumFacing(EnumFacing facing) {
        switch (facing) {
            case EAST:
                return EAST_OFF;
            case NORTH:
                return NORTH_OFF;
            case SOUTH:
                return SOUTH_OFF;
            case WEST:
                return WEST_OFF;
            default:
                return SOUTH_OFF;
        }
    }

    public static EnumMachineState fromMeta(int meta) {
        for (EnumMachineState state : values()) {
            if (state.meta == meta) {
                return state;
            }
        }
        return SOUTH_OFF;
    }
}
