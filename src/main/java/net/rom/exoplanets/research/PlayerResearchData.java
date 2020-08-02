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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.rom.api.research.ResearchStack;
import net.rom.api.research.ResearchState;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.capability.ResearchCapability;

public class PlayerResearchData {
	final List<ResearchStack> activeResearch;
	final List<ResearchStack> completedResearch;
	final ResearchCapability  researchCapability;

	public PlayerResearchData(ResearchCapability extendedProperties) {
		activeResearch          = new ArrayList<>();
		completedResearch       = new ArrayList<>();
		this.researchCapability = extendedProperties;
	}

	public void writeToNBT (NBTTagCompound tagCompound, EnumSet<DataType> dataTypes) {
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

	public void readFromNBT (NBTTagCompound tagCompound, EnumSet<DataType> dataTypes) {
		if (dataTypes.contains(DataType.COMPLETED_RESEARCH)) {
			completedResearch.clear();
			try {
				if (tagCompound.hasKey("CompletedResearch", Constants.NBT.TAG_LIST)) {
					NBTTagList activeResearchTagList = tagCompound
							.getTagList("CompletedResearch", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < activeResearchTagList.tagCount(); i++) {
						completedResearch.add(ResearchStack.loadFromNBT(activeResearchTagList.getCompoundTagAt(i)));
					}
				}
			}
			catch (Exception e) {
				ExoplanetsMod.logger.error("There was a problem while loading Completed Research", e);
			}
		}
		if (dataTypes.contains(DataType.ACTIVE_RESEARCH)) {
			activeResearch.clear();
			try {
				if (tagCompound.hasKey("ActiveResearch", Constants.NBT.TAG_LIST)) {
					NBTTagList activeResearchTagList = tagCompound
							.getTagList("ActiveResearch", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < activeResearchTagList.tagCount(); i++) {
						activeResearch.add(ResearchStack.loadFromNBT(activeResearchTagList.getCompoundTagAt(i)));
					}
				}
			}
			catch (Exception e) {
				ExoplanetsMod.logger.error("There was a problem while loading Active Research", e);
			}
		}
	}

	public void manageResearchCompletion () {
		int i = 0;
		while (i < activeResearch.size()) {
			if (activeResearch.get(i).isCompleted()) {
				ResearchStack researchStack = activeResearch.remove(i);
				researchCapability.onResearchCompleted(researchStack, i);
			}
			else {
				i++;
			}
		}
	}

	public boolean hasCompletedResearch (ResearchStack research) {
		for (ResearchStack q : completedResearch) {
			if (q.getResearch().areResearchStacksEqual(q, research)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasResearch (ResearchStack researchStack) {
		for (ResearchStack q : activeResearch) {
			if (q.getResearch().areResearchStacksEqual(q, researchStack)) {
				return true;
			}
		}
		return false;
	}

	public ResearchStack addResearch (ResearchStack researchStack) {
		if (researchStack.getResearch() != null && activeResearch.add(researchStack)) {
			return researchStack;
		}
		return null;
	}

	public void addResearchToCompleted (ResearchStack researchStack) {
		if (researchStack.getResearch() != null && !completedResearch.contains(researchStack)) {
			completedResearch.add(researchStack);
		}
	}

	public void onEvent (Event event) {
		if (researchCapability != null && researchCapability.getPlayer() != null) {
			for (int i = 0; i < activeResearch.size(); i++) {
				if (activeResearch.get(i).getResearch() != null) {
					ResearchState researchState = activeResearch.get(i).getResearch()
							.onEvent(activeResearch.get(i), event, researchCapability.getPlayer());
					if (researchState != null) {
						if (researchCapability.getPlayer() instanceof EntityPlayerMP) {
							//TODO Finish this
						}
					}
				}
			}
		}
	}

	public void clearActiveResearch () {
		activeResearch.clear();
	}

	public void clearCompletedResearch () {
		completedResearch.clear();
	}

	public void removeResearch (ResearchStack researchStack) {
		activeResearch.remove(researchStack);
	}

	public ResearchStack removeResearch (int id) {
		return activeResearch.remove(id);
	}

	public List<ResearchStack> getActiveResearch () {
		return activeResearch;
	}

	public List<ResearchStack> getCompletedResearch () {
		return completedResearch;
	}

	public enum DataType {
		ACTIVE_RESEARCH,
		COMPLETED_RESEARCH
	}
}
