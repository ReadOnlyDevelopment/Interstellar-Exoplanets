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

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.api.research.IResearchLogic;
import net.rom.exoplanets.api.research.ResearchStack;

public class RandomResearchText extends GenericResearch {
	int variationsCount;

	public RandomResearchText(IResearchLogic researchLogic, String title, int variationsCount, int xpReward) {
		super(researchLogic, title, xpReward);
		this.variationsCount = variationsCount;
	}

	@Override
	public void initResearchStack(Random random, ResearchStack researchStack, EntityPlayer entityPlayer) {
		super.initResearchStack(random, researchStack, entityPlayer);
		NBTTagCompound data = researchStack.getTagCompound();
		if (data == null) {
			data = new NBTTagCompound();
			researchStack.setTagCompound(data);
		}
		data.setShort("Variation", (short) random.nextInt(variationsCount));
	}

	@Override
	public boolean areResearchStacksEqual(ResearchStack researchStackOne, ResearchStack researchStackTwo) {
		if (researchStackOne == null && researchStackTwo == null) {
			return true;
		} else {
			if (researchStackOne.getTagCompound() == null && researchStackTwo.getTagCompound() == null) {
				return super.areResearchStacksEqual(researchStackOne, researchStackTwo);
			} else if (researchStackOne.getTagCompound() != null && researchStackTwo.getTagCompound() != null) {
				return super.areResearchStacksEqual(researchStackOne, researchStackTwo) && researchStackOne.getTagCompound().getShort("Variation") == researchStackTwo
						.getTagCompound().getShort("Variation");
			} else {
				return false;
			}
		}
	}

	public int getVariation(ResearchStack researchStack) {
		if (researchStack.getTagCompound() != null) {
			return researchStack.getTagCompound().getShort("Variation");
		}
		return 0;
	}

	@Override
	public String getTitle(ResearchStack researchStack) {
		return researchLogic.modifyTitle(researchStack, ExoplanetsMod.translate.translate("research." + title + "." + getVariation(researchStack) + ".title"));
	}

	@Override
	public String getTitle(ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyTitle(researchStack, replaceVariables(ExoplanetsMod.translate.translate("research." + title + "." + getVariation(researchStack) + ".title"),
				entityPlayer));
	}

	@Override
	public String getInfo(ResearchStack researchStack, EntityPlayer entityPlayer) {
		return researchLogic.modifyInfo(researchStack, replaceVariables(ExoplanetsMod.translate.translate("research." + title + "." + getVariation(researchStack) + ".info"),
				entityPlayer));
	}

	@Override
	public String getObjective(ResearchStack researchStack, EntityPlayer entityPlayer, int objectiveIndex) {
		return researchLogic.modifyObjective(researchStack, entityPlayer, replaceVariables(ExoplanetsMod.translate.translate("research." + title + "." + getVariation(researchStack)
				+ ".objective." + objectiveIndex), entityPlayer), objectiveIndex);
	}
}
