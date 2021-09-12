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
package com.readonlydev.space.trappist1.d;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class Trappist1DTeleportType implements ITeleportType {

	@Override
	public boolean useParachute () {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector3 getPlayerSpawnLocation (WorldServer world, EntityPlayerMP player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3 getEntitySpawnLocation (WorldServer world, Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3 getParaChestSpawnLocation (WorldServer world, EntityPlayerMP player, Random rand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onSpaceDimensionChanged (World newWorld, EntityPlayerMP player, boolean ridingAutoRocket) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupAdventureSpawn (EntityPlayerMP player) {
		// TODO Auto-generated method stub

	}

}
