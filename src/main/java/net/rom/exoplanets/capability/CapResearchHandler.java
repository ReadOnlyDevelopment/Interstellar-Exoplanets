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

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.rom.exoplanets.ExoInfo;

public class CapResearchHandler {
	@CapabilityInject(IResearchCapability.class)
	public static Capability<IResearchCapability> RESEARCH_CAPABILITY = null;

	public static final ResourceLocation EXO_PLAYER = new ResourceLocation(ExoInfo.MODID, "player_stats");

	public static void register () {
		CapabilityManager.INSTANCE.register(IResearchCapability.class, new Capability.IStorage<IResearchCapability>() {
			@Override
			public NBTBase writeNBT (Capability<IResearchCapability> capability, IResearchCapability instance, EnumFacing side) {
				return null;
			}

			@Override
			public void readNBT (Capability<IResearchCapability> capability, IResearchCapability instance, EnumFacing side, NBTBase nbt) {}
		}, ResearchCapability::new);
	}
}
