package net.romvoid95.common.astronomy.wolf1061.d;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;

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
