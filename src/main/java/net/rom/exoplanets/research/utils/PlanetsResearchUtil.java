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

package net.rom.exoplanets.research.utils;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import net.rom.api.research.ResearchStack;

public class PlanetsResearchUtil {
    public static BlockPos getPosition(ResearchStack researchStack) {
        if (researchStack.getTagCompound() != null) {
            if (researchStack.getTagCompound().hasKey("pos", Constants.NBT.TAG_LONG)) {
                return BlockPos.fromLong(researchStack.getTagCompound().getLong("pos"));
            } else if (researchStack.getTagCompound().hasKey("pos", Constants.NBT.TAG_INT_ARRAY) && researchStack.getTagCompound().getIntArray("pos").length >= 3) {
                int[] s = researchStack.getTagCompound().getIntArray("pos");
                return new BlockPos(s[0], s[1], s[2]);
            }
        }
        return null;
    }
}
