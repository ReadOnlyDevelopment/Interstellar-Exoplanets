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

package net.rom.exoplanets.capability;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.rom.exoplanets.api.research.ResearchStack;
import net.rom.exoplanets.research.PlayerResearchData;

public class ResearchCapability implements IResearchCapability {
	
    private EntityPlayerMP player;
    private PlayerResearchData researchData;

	@Override
	public void saveNBTData(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyFrom(IResearchCapability oldData, boolean keepInv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityPlayerMP getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(EntityPlayerMP player) {

		
	}

	@Override
	public void addResearch(ResearchStack researchStack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasCompletedResearch(ResearchStack researchStack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasResearch(ResearchStack researchStack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeResearch(ResearchStack researchStack, int index) {	
	}
	
    public boolean isServer() {
        return player != null && !player.world.isRemote;
    }

    public PlayerResearchData getResearchData() {
        return researchData;
    }

    public void onEvent(Event event) {
        researchData.onEvent(event);
    }

	public void onResearchCompleted(ResearchStack researchStack, int i) {
		
	}
}
