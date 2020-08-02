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

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.rom.api.research.IResearchLogic;
import net.rom.api.research.ResearchStack;
import net.rom.exoplanets.ExoplanetsMod;

public class RandomResearchText extends GenericResearch {
	int variationsCount;

	public RandomResearchText(IResearchLogic researchLogic, String title, int variationsCount, int xpReward) {
		super(researchLogic, title, xpReward);
		this.variationsCount = variationsCount;
	}

	@Override
	public void initResearchStack (Random random, ResearchStack researchStack, EntityPlayer entityPlayer) {
		super.initResearchStack(random, researchStack, entityPlayer);
		NBTTagCompound data = researchStack.getTagCompound();
		if (data == null) {
			data = new NBTTagCompound();
			researchStack.setTagCompound(data);
		}
		data.setShort("Variation", (short) random.nextInt(variationsCount));
	}

	@Override
	public boolean areResearchStacksEqual (ResearchStack researchStackOne, ResearchStack researchStackTwo) {
		if (researchStackOne == null && researchStackTwo == null) {
			return true;
		}
		else {
			if (researchStackOne.getTagCompound() == null && researchStackTwo.getTagCompound() == null) {
				return super.areResearchStacksEqual(researchStackOne, researchStackTwo);
			}
			else if (researchStackOne.getTagCompound() != null && researchStackTwo.getTagCompound() != null) {
				return super.areResearchStacksEqual(researchStackOne, researchStackTwo)
						&& researchStackOne.getTagCompound().getShort("Variation") == researchStackTwo.getTagCompound()
								.getShort("Variation");
			}
			else {
				return false;
			}
		}
	}

	public int getVariation (ResearchStack researchStack) {
		if (researchStack.getTagCompound() != null) {
			return researchStack.getTagCompound().getShort("Variation");
		}
		return 0;
	}

	@Override
	public String getTitle (ResearchStack researchStack) {
		return researchLogic.modifyTitle(researchStack, ExoplanetsMod.translate
				.translate("research." + title + "." + getVariation(researchStack) + ".title"));
	}

	@Override
	public String getTitle (ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyTitle(researchStack, replaceVariables(ExoplanetsMod.translate
				.translate("research." + title + "." + getVariation(researchStack) + ".title"), entityPlayer));
	}

	@Override
	public String getInfo (ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyInfo(researchStack, replaceVariables(ExoplanetsMod.translate
				.translate("research." + title + "." + getVariation(researchStack) + ".info"), entityPlayer));
	}

	@Override
	public String getObjective (ResearchStack researchStack, EntityPlayer entityPlayer, int objectiveIndex) {
		return researchLogic.modifyObjective(researchStack, entityPlayer, replaceVariables(ExoplanetsMod.translate
				.translate("research." + title + "." + getVariation(researchStack) + ".objective."
						+ objectiveIndex), entityPlayer), objectiveIndex);
	}
}
