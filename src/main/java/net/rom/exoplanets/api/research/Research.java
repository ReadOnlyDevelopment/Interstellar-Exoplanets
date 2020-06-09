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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.rom.exoplanets.research.utils.JsonUtil;

public abstract class Research implements IResearch {
    protected String title;
    protected int xpReward;
    protected List<IResearchReward> researchRewards;
	public Random random;

    public Research(String title, JsonObject jsonObject) {
        this.title = title;
        xpReward = JsonUtil.getInt(jsonObject, "xp", 0);
        researchRewards = new ArrayList<>();
    }

    public Research(String title, int xpReward) {
        this.title = title;
        this.xpReward = xpReward;
        this.researchRewards = new ArrayList<>();
    }

    public static IResearch getResearchFromName(String name) {
    	//TODO
        return null;
    }

    @Override
    public String getName() {
        return title;
    }

    public String getTitle(ResearchStack researchStack) {
        return title;
    }

    public String getTitle(ResearchStack researchStack, EntityPlayer entityPlayer) {
        return getTitle(researchStack);
    }

    public Research addResearchRewards(IResearchReward... researchRewards) {
        Collections.addAll(this.researchRewards, researchRewards);
        return this;
    }

    public Research addResearchRewards(List<IResearchReward> rewards) {
        this.researchRewards.addAll(rewards);
        return this;
    }

    @Override
    public void setCompleted(ResearchStack researchStack, EntityPlayer entityPlayer) {
        researchStack.completed = true;
    }

	public abstract IResearch getResearchByName(String researchName);
}
