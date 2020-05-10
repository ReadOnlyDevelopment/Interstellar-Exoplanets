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

package net.rom.api.research;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class ResearchStack {
    boolean completed;
    private NBTTagCompound tagCompound;
    private UUID giverUniqueID;
    private Entity giver;
    private IResearch research;

    ResearchStack() {
    }

    public ResearchStack(IResearch research, Entity giver) {
        this.research = research;
        if (giver != null) {
            this.giverUniqueID = giver.getUniqueID();
        }
        this.giver = giver;
    }

    public ResearchStack(IResearch research) {
        this.research = research;
    }

    public static ResearchStack loadFromNBT(NBTTagCompound tagCompound) {
        if (tagCompound != null) {
            ResearchStack researchStack = new ResearchStack();
            researchStack.readFromNBT(tagCompound);
            return researchStack;
        }
        return null;
    }

    public static boolean canComplete(EntityPlayer entityPlayer, ResearchStack researchStack) {
        for (int i = 0; i < researchStack.getObjectivesCount(entityPlayer); i++) {
            if (!researchStack.isObjectiveCompleted(entityPlayer, i)) {
                return false;
            }
        }
        return true;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        if (this.tagCompound != null) {
            tagCompound.setTag("Data", this.tagCompound);
        }
        if (giverUniqueID != null) {
            tagCompound.setLong("giveIdLow", giverUniqueID.getLeastSignificantBits());
            tagCompound.setLong("giveIdHigh", giverUniqueID.getMostSignificantBits());
        }
        tagCompound.setShort("Research", (short) 0/*ExoplanetsMod.RESEARCH.getResearchID(research)*/);
        tagCompound.setBoolean("Completed", completed);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        if (tagCompound.hasKey("Data", Constants.NBT.TAG_COMPOUND)) {
            this.tagCompound = tagCompound.getCompoundTag("Data");
        }
        if (tagCompound.hasKey("giveIdLow", Constants.NBT.TAG_LONG) && tagCompound.hasKey("giveIdHigh", Constants.NBT.TAG_LONG)) {
            giverUniqueID = new UUID(tagCompound.getLong("giveIdLow"), tagCompound.getLong("giveIdHigh"));
        }
        if (tagCompound.hasKey("Research", Constants.NBT.TAG_SHORT)) {
            //research = ExoplanetsMod.RESEARCH.getResearchWithID(tagCompound.getShort("Research"));
        }
        completed = tagCompound.getBoolean("Completed");
    }

    public String getTitle() {
        return research.getTitle(this);
    }

    public int getXP(EntityPlayer entityPlayer) {
        return research.getXpReward(this, entityPlayer);
    }

    public String getTitle(EntityPlayer entityPlayer) {
        return research.getTitle(this, entityPlayer);
    }

    public String getInfo(EntityPlayer entityPlayer) {
        return research.getInfo(this, entityPlayer);
    }

    public String getObjective(EntityPlayer entityPlayer, int objectiveIndex) {
        return research.getObjective(this, entityPlayer, objectiveIndex);
    }

    public int getObjectivesCount(EntityPlayer entityPlayer) {
        return research.getObjectivesCount(this, entityPlayer);
    }

    public boolean isObjectiveCompleted(EntityPlayer entityPlayer, int objectiveID) {
        return research.isObjectiveCompleted(this, entityPlayer, objectiveID);
    }

    public Entity getGiver() {
        return giver;
    }

    public void setGiver(Entity entity) {
        this.giver = entity;
        this.giverUniqueID = giver.getUniqueID();
    }

    public boolean isGiver(Entity entity) {
        if (giver != null && giver == entity) {
            return true;
        }
        return giverUniqueID != null && entity.getUniqueID().equals(giverUniqueID);
    }

    public boolean hasGiver() {
        if (getGiver() != null) {
            return true;
        }
        return giverUniqueID != null;
    }

    public void addRewards(List<IResearchReward> rewards, EntityPlayer entityPlayer) {
        research.addToRewards(this, entityPlayer, rewards);
    }

    public IResearch getResearch() {
        return research;
    }

    public NBTTagCompound getTagCompound() {
        return tagCompound;
    }

    public void setTagCompound(NBTTagCompound tagCompound) {
        this.tagCompound = tagCompound;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markComplited(EntityPlayer entityPlayer, boolean force) {
        if (force) {
            this.completed = true;
        } else {
            this.research.setCompleted(this, entityPlayer);
        }
    }

    public ResearchStack copy() {
        ResearchStack researchStack = new ResearchStack(this.research);
        researchStack.giverUniqueID = giverUniqueID;
        researchStack.giver = giver;
        if (getTagCompound() != null) {
            researchStack.setTagCompound((NBTTagCompound) getTagCompound().copy());
        }
        return researchStack;
    }

    public ItemStack getResearchPaper() {
        ItemStack researchPaper = null;
        NBTTagCompound researchTag = new NBTTagCompound();
        writeToNBT(researchTag);
        researchPaper.setTagCompound(researchTag);
        return researchPaper;
    }

    public boolean canAccept(EntityPlayer entityPlayer, ResearchStack researchStack) {
        return research.canBeAccepted(researchStack, entityPlayer);
    }
}
