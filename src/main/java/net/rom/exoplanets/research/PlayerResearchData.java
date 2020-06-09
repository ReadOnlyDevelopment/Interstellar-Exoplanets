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

package net.rom.exoplanets.research;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.api.research.ResearchStack;
import net.rom.exoplanets.api.research.ResearchState;
import net.rom.exoplanets.capability.ResearchCapability;

public class PlayerResearchData {
    final List<ResearchStack> activeResearch;
    final List<ResearchStack> completedResearch;
    final ResearchCapability researchCapability;

    public PlayerResearchData(ResearchCapability extendedProperties) {
        activeResearch = new ArrayList<>();
        completedResearch = new ArrayList<>();
        this.researchCapability = extendedProperties;
    }

    public void writeToNBT(NBTTagCompound tagCompound, EnumSet<DataType> dataTypes) {
        if (dataTypes.contains(DataType.COMPLETED_RESEARCH)) {
            if (completedResearch.size() > 0) {
                NBTTagList activeResearchTagList = new NBTTagList();
                for (ResearchStack researchStack : completedResearch) {
                    NBTTagCompound researchStackNBT = new NBTTagCompound();
                    researchStack.writeToNBT(researchStackNBT);
                    activeResearchTagList.appendTag(researchStackNBT);
                }
                tagCompound.setTag("CompletedResearch", activeResearchTagList);
            }
        }
        if (dataTypes.contains(DataType.ACTIVE_RESEARCH)) {
            if (activeResearch.size() > 0) {
                NBTTagList activeResearchTagList = new NBTTagList();
                for (ResearchStack researchStack : activeResearch) {
                    NBTTagCompound researchStackNBT = new NBTTagCompound();
                    researchStack.writeToNBT(researchStackNBT);
                    activeResearchTagList.appendTag(researchStackNBT);
                }
                tagCompound.setTag("ActiveResearch", activeResearchTagList);
            }
        }
    }

    public void readFromNBT(NBTTagCompound tagCompound, EnumSet<DataType> dataTypes) {
        if (dataTypes.contains(DataType.COMPLETED_RESEARCH)) {
            completedResearch.clear();
            try {
                if (tagCompound.hasKey("CompletedResearch", Constants.NBT.TAG_LIST)) {
                    NBTTagList activeResearchTagList = tagCompound.getTagList("CompletedResearch", Constants.NBT.TAG_COMPOUND);
                    for (int i = 0; i < activeResearchTagList.tagCount(); i++) {
                        completedResearch.add(ResearchStack.loadFromNBT(activeResearchTagList.getCompoundTagAt(i)));
                    }
                }
            } catch (Exception e) {
            	ExoplanetsMod.logger.formatted_Error("There was a problem while loading Completed Research", e);
            }
        }
        if (dataTypes.contains(DataType.ACTIVE_RESEARCH)) {
            activeResearch.clear();
            try {
                if (tagCompound.hasKey("ActiveResearch", Constants.NBT.TAG_LIST)) {
                    NBTTagList activeResearchTagList = tagCompound.getTagList("ActiveResearch", Constants.NBT.TAG_COMPOUND);
                    for (int i = 0; i < activeResearchTagList.tagCount(); i++) {
                        activeResearch.add(ResearchStack.loadFromNBT(activeResearchTagList.getCompoundTagAt(i)));
                    }
                }
            } catch (Exception e) {
            	ExoplanetsMod.logger.formatted_Error("There was a problem while loading Active Research", e);
            }
        }
    }

    public void manageResearchCompletion() {
        int i = 0;
        while (i < activeResearch.size()) {
            if (activeResearch.get(i).isCompleted()) {
                ResearchStack researchStack = activeResearch.remove(i);
                researchCapability.onResearchCompleted(researchStack, i);
            } else {
                i++;
            }
        }
    }

    public boolean hasCompletedResearch(ResearchStack research) {
        for (ResearchStack q : completedResearch) {
            if (q.getResearch().areResearchStacksEqual(q, research)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasResearch(ResearchStack researchStack) {
        for (ResearchStack q : activeResearch) {
            if (q.getResearch().areResearchStacksEqual(q, researchStack)) {
                return true;
            }
        }
        return false;
    }

    public ResearchStack addResearch(ResearchStack researchStack) {
        if (researchStack.getResearch() != null && activeResearch.add(researchStack)) {
            return researchStack;
        }
        return null;
    }

    public void addResearchToCompleted(ResearchStack researchStack) {
        if (researchStack.getResearch() != null && !completedResearch.contains(researchStack)) {
            completedResearch.add(researchStack);
        }
    }

    public void onEvent(Event event) {
        if (researchCapability != null && researchCapability.getPlayer() != null) {
            for (int i = 0; i < activeResearch.size(); i++) {
                if (activeResearch.get(i).getResearch() != null) {
                    ResearchState researchState = activeResearch.get(i).getResearch().onEvent(activeResearch.get(i), event, researchCapability.getPlayer());
                    if (researchState != null) {
                        if (researchCapability.getPlayer() instanceof EntityPlayerMP) {
                        	//TODO Finish this
                        }
                    }
                }
            }
        }
    }

    public void clearActiveResearch() {
        activeResearch.clear();
    }

    public void clearCompletedResearch() {
        completedResearch.clear();
    }

    public void removeResearch(ResearchStack researchStack) {
        activeResearch.remove(researchStack);
    }

    public ResearchStack removeResearch(int id) {
        return activeResearch.remove(id);
    }

    public List<ResearchStack> getActiveResearch() {
        return activeResearch;
    }

    public List<ResearchStack> getCompletedResearch() {
        return completedResearch;
    }

    public enum DataType {
        ACTIVE_RESEARCH, COMPLETED_RESEARCH
    }
}
