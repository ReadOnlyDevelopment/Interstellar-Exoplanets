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

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.api.research.IResearch;
import net.rom.exoplanets.api.research.ResearchStack;

public class ResearchFactory {
	
    public static final String COMPLETED = "\u25a0";
    public static final String UNCOMPLETE = "\u25a1";
    
    public ResearchStack generateResearchStack(Random random, IResearch research) {
        ResearchStack researchStack = new ResearchStack(research);
        research.initResearchStack(random, researchStack);
        return researchStack;
    }

    @SideOnly(Side.CLIENT)
    public String getFormattedResearchObjective(EntityPlayer entityPlayer, ResearchStack researchStack, int objectiveInex) {
        boolean isCompleted = researchStack.isObjectiveCompleted(entityPlayer, objectiveInex);
        if (isCompleted) {
            //completed
            return TextFormatting.GREEN + COMPLETED + " " + researchStack.getObjective(entityPlayer, objectiveInex);
        } else {
            //not completed
            return TextFormatting.DARK_GREEN + UNCOMPLETE + " " + researchStack.getObjective(entityPlayer, objectiveInex);
        }
    }

    @SideOnly(Side.CLIENT)
    public List<String> getFormattedResearchObjective(EntityPlayer entityPlayer, ResearchStack researchStack, int objectiveInex, int length) {
        return getFormattedResearchObjective(entityPlayer, researchStack, objectiveInex, length, TextFormatting.DARK_GREEN.toString(), TextFormatting.GREEN.toString());
    }

    @SideOnly(Side.CLIENT)
    public List<String> getFormattedResearchObjective(EntityPlayer entityPlayer, ResearchStack researchStack, int objectiveInex, int length, String uncompletedPrefix, String completedPrefix) {
        List<String> objectiveLines = Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(researchStack.getObjective(entityPlayer, objectiveInex), length);
        boolean isObjectiveComplete = researchStack.isObjectiveCompleted(Minecraft.getMinecraft().player, objectiveInex);
        for (int o = 0; o < objectiveLines.size(); o++) {
            String line = "";
            if (isObjectiveComplete) {
                line += completedPrefix;
                if (o == 0) {
                    line += COMPLETED + " ";
                }
            } else {
                line += uncompletedPrefix;
                if (o == 0) {
                    line += UNCOMPLETE + " ";
                }
            }

            line += objectiveLines.get(o);
            objectiveLines.set(o, line);
        }
        return objectiveLines;
    }

//    public ResearchStack generateResearchStack(String name) {
//        IResearch research = ExoplanetsMod.RESEARCH.getResearchByName(name);
//        if (research != null) {
//            ResearchStack researchStack = new ResearchStack(research);
//            research.initResearchStack(ExoplanetsMod.RESEARCH.random, researchStack);
//            return researchStack;
//        }
//        return null;
//    }
}
