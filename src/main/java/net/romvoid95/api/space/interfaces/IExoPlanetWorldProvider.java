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
package net.romvoid95.api.space.interfaces;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;

import net.romvoid95.api.space.prefab.ExoPlanet;

public interface IExoPlanetWorldProvider extends IGalacticraftWorldProvider {

	public ExoPlanet getExoPlanet ();

	public default Vector3 getFogColor () {
		return new Vector3();
	}

	public default Vector3 getSkyColor () {
		return new Vector3();
	}

	@Override
	public default int getDungeonSpacing () {
		return 0;
	}

	@Override
	public default ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public default List<Block> getSurfaceBlocks () {
		return null;
	}
}
