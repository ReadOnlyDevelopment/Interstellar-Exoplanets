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

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.api.research.IResearch;
import net.rom.api.research.ResearchStack;

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
