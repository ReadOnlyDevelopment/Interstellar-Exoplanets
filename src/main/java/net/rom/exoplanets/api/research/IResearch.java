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

package net.rom.exoplanets.api.research;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public interface IResearch {

    String getName();

    String getTitle(ResearchStack researchStack);

    boolean canBeAccepted(ResearchStack researchStack, EntityPlayer entityPlayer);

    String getTitle(ResearchStack researchStack, EntityPlayer entityPlayer);

    boolean areResearchStacksEqual(ResearchStack researchStackOne, ResearchStack researchStackTwo);

    String getInfo(ResearchStack researchStack, EntityPlayer entityPlayer);

    String getObjective(ResearchStack researchStack, EntityPlayer entityPlayer, int objectiveIndex);

    int getObjectivesCount(ResearchStack researchStack, EntityPlayer entityPlayer);

    boolean isObjectiveCompleted(ResearchStack researchStack, EntityPlayer entityPlayer, int objectiveIndex);

    void initResearchStack(Random random, ResearchStack researchStack);

    void initResearchStack(Random random, ResearchStack researchStack, EntityPlayer entityPlayer);

    ResearchState onEvent(ResearchStack researchStack, Event event, EntityPlayer entityPlayer);

    void onCompleted(ResearchStack researchStack, EntityPlayer entityPlayer);

    int getXpReward(ResearchStack researchStack, EntityPlayer entityPlayer);

    void addToRewards(ResearchStack researchStack, EntityPlayer entityPlayer, List<IResearchReward> rewards);

    void setCompleted(ResearchStack researchStack, EntityPlayer entityPlayer);
}
