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
package com.readonlydev.space.wolf1061.d;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleportTypeWolf1061D implements ITeleportType {
	@Override
	public boolean useParachute() {
		return false;
	}

	@Override
	public Vector3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player) {
		if (player != null) {
			GCPlayerStats stats = GCPlayerStats.get(player);
			return new Vector3(stats.getCoordsTeleportedFromX(), 103.0, stats.getCoordsTeleportedFromZ());
		}
		return null;
	}

	@Override
	public Vector3 getEntitySpawnLocation(WorldServer world, Entity entity) {
		return new Vector3(entity.posX, 103.0, entity.posZ);
	}

	@Override
	public Vector3 getParaChestSpawnLocation(WorldServer world, EntityPlayerMP player, Random rand) {
		final double x = (rand.nextDouble() * 2 - 1.0D) * 5.0D;
		final double z = (rand.nextDouble() * 2 - 1.0D) * 5.0D;

		return new Vector3(player.posX + x, 230.0D, player.posZ + z);

	}

	@Override
	public void onSpaceDimensionChanged(World newWorld, EntityPlayerMP player, boolean ridingAutoRocket) {
		BlockPos playerPos = player.getPosition();
		int X = playerPos.getX();
		int Z = playerPos.getZ();
		if (newWorld.isAirBlock(new BlockPos(X, 100, Z))) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					newWorld.setBlockState(new BlockPos(X + i, 100, Z + j), Blocks.STONE.getDefaultState());
				}
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					newWorld.setBlockState(new BlockPos(X - i, 100, Z + j), Blocks.STONE.getDefaultState());
				}
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					newWorld.setBlockState(new BlockPos(X + i, 100, Z - j), Blocks.STONE.getDefaultState());
				}
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					newWorld.setBlockState(new BlockPos(X - i, 100, Z - j), Blocks.STONE.getDefaultState());
				}
			}
		}
	}

	@Override
	public void setupAdventureSpawn(EntityPlayerMP player) {}
}
