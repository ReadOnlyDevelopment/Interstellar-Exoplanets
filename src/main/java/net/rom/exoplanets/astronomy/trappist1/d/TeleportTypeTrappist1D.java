package net.rom.exoplanets.astronomy.trappist1.d;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import micdoodle8.mods.galacticraft.planets.venus.entities.EntityEntryPodVenus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleportTypeTrappist1D implements ITeleportType {

	private Random rand;

	@Override
	public boolean useParachute () {
		return false;
	}

	@Override
	public Vector3 getEntitySpawnLocation (WorldServer world, Entity player) {
		return new Vector3(0.0, 900.0, 0.0);
	}

	@Override
	public Vector3 getPlayerSpawnLocation (WorldServer world, EntityPlayerMP player) {
		return this.getEntitySpawnLocation(world, player);
	}

	@Override
	public Vector3 getParaChestSpawnLocation (WorldServer world, EntityPlayerMP player, Random rand) {
		return new Vector3(0.0, 90.0, 0.0D);
	}

	@Override
	public void onSpaceDimensionChanged (World newWorld, EntityPlayerMP player, boolean ridingAutoRocket) {
		if (!ridingAutoRocket && player != null) {
			GCPlayerStats stats = GCPlayerStats.get(player);

			if (stats.getTeleportCooldown() <= 0) {
				if (player.capabilities.isFlying) {
					player.capabilities.isFlying = false;
				}

				if (!newWorld.isRemote) {
					EntityEntryPodVenus entryPod = new EntityEntryPodVenus(player);

					boolean previous = CompatibilityManager.forceLoadChunks((WorldServer) newWorld);
					entryPod.forceSpawn = true;
					newWorld.spawnEntity(entryPod);
					CompatibilityManager.forceLoadChunksEnd((WorldServer) newWorld, previous);
				}

				stats.setTeleportCooldown(10);
			}
		}

	}

	@Override
	public void setupAdventureSpawn (EntityPlayerMP player) {

	}

}
