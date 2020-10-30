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
package net.romvoid95.space.trappist1.b;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;

import net.romvoid95.api.space.prefab.WorldProviderExoPlanet;
import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.core.initialization.Planets;

public class WorldProviderTrappistB extends WorldProviderExoPlanet {

	@Override
	public double getSolarEnergyMultiplier() {
		return 0;
	}

	@Override
	public double getYCoordinateToTeleport() {
		return 0;
	}

	@Override
	public float getFallDamageModifier() {
		return 0;
	}

	@Override
	public CelestialBody getCelestialBody() {
		return Planets.TRAPPIST1B;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public Vector3 getSkyColor() {
		return null;
	}

	@Override
	public boolean hasSunset() {
		return false;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return null;
	}

	@Override
	public DimensionType getDimensionType() {
		return ExoDimensions.TRAPPIST_1B;
	}

}
