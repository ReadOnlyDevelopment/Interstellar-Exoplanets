package net.romvoid95.common.astronomy.trappist1.d;

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
