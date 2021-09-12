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
package com.readonlydev.space.wolf1061.c;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.entities.player.GCCapabilities;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleportTypeEris implements ITeleportType {
	@Override
	public boolean useParachute() {
		return false;
	}

	@Override
	public Vector3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player) {
		if (player != null) {
			GCPlayerStats stats = player.getCapability(GCCapabilities.GC_STATS_CAPABILITY, null);
			return new Vector3(stats.getCoordsTeleportedFromX(), 350, stats.getCoordsTeleportedFromZ());
		}

		return null;
	}

	@Override
	public Vector3 getEntitySpawnLocation(WorldServer world, Entity entity) {
		return new Vector3(entity.posX, 350, entity.posZ);
	}

	@Override
	public Vector3 getParaChestSpawnLocation(WorldServer world, EntityPlayerMP player, Random rand) {
		return null;
	}

	@Override
	public void onSpaceDimensionChanged(World newWorld, EntityPlayerMP player, boolean ridingAutoRocket) {
//		if (!ridingAutoRocket && player != null) {
//			GCPlayerStats stats = GCPlayerStats.get(player);
//
//			if (stats.getTeleportCooldown() <= 0) {
//				if (player.capabilities.isFlying) {
//					player.capabilities.isFlying = false;
//				}
//
//				EntityGeneralLander lander = new EntityGeneralLander(player);
//				lander.setPosition(player.posX, player.posY, player.posZ);
//
//				if (!newWorld.isRemote) {
//					boolean previous = CompatibilityManager.forceLoadChunks((WorldServer) newWorld);
//					lander.forceSpawn = true;
//					newWorld.spawnEntity(lander);
//					lander.setWorld(newWorld);
//					newWorld.updateEntityWithOptionalForce(lander, true);
//					player.startRiding(lander);
//					CompatibilityManager.forceLoadChunksEnd((WorldServer) newWorld, previous);
//					MessageUtilities.debugMessageToLog(Constants.modName, "Entering lander at : " + player.posX + "," + player.posZ + " lander spawn at: " + lander.posX + "," + lander.posZ);
//				}
//
//				stats.setTeleportCooldown(10);
//			}
//		}
	}

	@Override
	public void setupAdventureSpawn(EntityPlayerMP player) {

	}
}
