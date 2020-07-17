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
