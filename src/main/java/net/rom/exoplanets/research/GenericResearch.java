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

import java.util.List;
import java.util.Random;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.rom.api.research.IResearch;
import net.rom.api.research.IResearchLogic;
import net.rom.api.research.IResearchReward;
import net.rom.api.research.Research;
import net.rom.api.research.ResearchLogicState;
import net.rom.api.research.ResearchStack;
import net.rom.api.research.ResearchState;
import net.rom.exoplanets.ExoplanetsMod;

public class GenericResearch extends Research {
	protected IResearchLogic researchLogic;

	public GenericResearch(String title, JsonObject researchObj, IResearchLogic researchLogic) {
		super(title, researchObj);
		this.researchLogic = researchLogic;
	}

	public GenericResearch(IResearchLogic researchLogic, String title, int xpReward) {
		super(title, xpReward);
		this.researchLogic = researchLogic;
	}

	@Override
	public boolean canBeAccepted(ResearchStack researchStack, EntityPlayer entityPlayer) {
		// TODO Insert completed Capability caller here

		// if (Capability != null) {
		// return researchLogic.canAccept(researchStack, entityPlayer) &&
		// !Capability.hasCompletedResearch(researchStack) &&
		// !Capability.hasResearch(researchStack);
		// }
		return false;
	}

	@Override
	public String getTitle(ResearchStack researchStack) {
		return researchLogic.modifyTitle(researchStack, ExoplanetsMod.translate.translate("research." + title + ".title"));
	}

	@Override
	public String getTitle(ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyTitle(researchStack, replaceVariables(ExoplanetsMod.translate.translate("research." + title + ".title"), entityPlayer));
	}

	@Override
	public String getInfo(ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyInfo(researchStack, replaceVariables(ExoplanetsMod.translate.translate("research." + title + ".info"), entityPlayer));
	}

	@Override
	public String getObjective(ResearchStack researchStack, EntityPlayer entityPlayer, int objectiveIndex) {
		return researchLogic.modifyObjective(researchStack, entityPlayer, replaceVariables(ExoplanetsMod.translate.translate("research." + title + ".objective." + objectiveIndex),
				entityPlayer), objectiveIndex);
	}

	@Override
	public int getObjectivesCount(ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyObjectiveCount(researchStack, entityPlayer, 1);
	}

	@Override
	public boolean isObjectiveCompleted(ResearchStack researchStack, EntityPlayer entityPlayer, int objectiveIndex) {
		return researchLogic.isObjectiveCompleted(researchStack, entityPlayer, objectiveIndex);
	}

	@Override
	public boolean areResearchStacksEqual(ResearchStack researchStackOne, ResearchStack researchStackTwo) {
		if (researchStackOne.getResearch() instanceof GenericResearch && researchStackTwo.getResearch() instanceof GenericResearch) {
			if (((GenericResearch) researchStackOne.getResearch()).getResearchLogic() == ((GenericResearch) researchStackTwo.getResearch()).getResearchLogic()) {
				return ((GenericResearch) researchStackTwo.getResearch()).getResearchLogic().areResearchStacksEqual(researchStackOne, researchStackTwo);
			}
		}
		return false;
	}

	@Override
	public void initResearchStack(Random random, ResearchStack researchStack) {
		researchLogic.initResearchStack(random, researchStack);
	}

	@Override
	public void initResearchStack(Random random, ResearchStack researchStack, EntityPlayer entityPlayer) {

	}

	@Override
	public ResearchState onEvent(ResearchStack researchStack, Event event, EntityPlayer entityPlayer) {
		ResearchLogicState state = researchLogic.onEvent(researchStack, event, entityPlayer);
		if (state == null) {
			return null;
		}
		return new ResearchState(state.getType(), new int[] { 0 }, state.isShowOnHud());
	}

	@Override
	public void onCompleted(ResearchStack researchStack, EntityPlayer entityPlayer) {
		researchLogic.onResearchCompleted(researchStack, entityPlayer);
	}

	@Override
	public int getXpReward(ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyXP(researchStack, entityPlayer, xpReward);
	}

	@Override
	public void addToRewards(ResearchStack researchStack, EntityPlayer entityPlayer, List<IResearchReward> rewards) {
		rewards.addAll(researchRewards);
		researchLogic.modifyRewards(researchStack, entityPlayer, rewards);
	}

	public String replaceVariables(String text, EntityPlayer entityPlayer) {
		if (entityPlayer != null) {
			return text.replace("$player", entityPlayer.getDisplayName().getFormattedText());
		}
		return text;
	}

	public IResearchLogic getResearchLogic() {
		return researchLogic;
	}

	public void setResearchLogic(IResearchLogic researchLogic) {
		this.researchLogic = researchLogic;
	}

	@Override
	public IResearch getResearchByName(String researchName) {
		//TODO
		return null;
	}
}
