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
