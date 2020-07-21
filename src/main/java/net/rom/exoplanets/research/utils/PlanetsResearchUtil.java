/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
