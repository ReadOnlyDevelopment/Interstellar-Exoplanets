package net.rom.exoplanets.events;

import java.util.HashMap;
import java.util.Map.Entry;

import asmodeuscore.core.utils.ACCompatibilityManager;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.server.permission.PermissionAPI;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.netlib.SimplePacket;
import net.rom.exoplanets.netlib.SimplePacket.EnumExoSimplePacket;

public class ExoPlanetEvent {

	//	@SubscribeEvent
	//	public void onPlayerLogIn (PlayerLoggedInEvent event) {
	//		if (!event.player.world.isRemote && event.player instanceof EntityPlayerMP)
	//			ExoplanetsMod.pipeline.sendTo(new SimplePacket(EnumExoSimplePacket.C_UPDATE_WORLD, GCCoreUtil
	//					.getDimensionID(event.player.getEntityWorld())), (EntityPlayerMP) event.player);
	//
	//	}
	//
	//	@SubscribeEvent
	//	public void onPlayerJoinWorld (EntityJoinWorldEvent event) {
	//		if (!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayerMP)
	//			ExoplanetsMod.pipeline.sendTo(new SimplePacket(EnumExoSimplePacket.C_UPDATE_WORLD, GCCoreUtil
	//					.getDimensionID(event.getEntity().getEntityWorld())), (EntityPlayerMP) event.getEntity());
	//
	//	}
	//
	//	@SubscribeEvent
	//	public void onPlayerChangeDim (PlayerChangedDimensionEvent event) {
	//		if (!event.player.world.isRemote && event.player instanceof EntityPlayerMP)
	//			ExoplanetsMod.pipeline.sendTo(new SimplePacket(EnumExoSimplePacket.C_UPDATE_WORLD, GCCoreUtil
	//					.getDimensionID(event.player.world)), (EntityPlayerMP) event.player);
	//	}

	@SubscribeEvent
	public void onEntityUpdate (LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		World            world  = living.world;
		if (living instanceof EntityPlayerMP) {
			if (!ACCompatibilityManager.isGalacticraftLoaded())
				return;

			EntityPlayerMP      player   = (EntityPlayerMP) living;
			final GCPlayerStats GCPlayer = GCPlayerStats.get(player);

			if (GCPlayer.isUsingPlanetSelectionGui()) {

				this.sendPlanetList(player, GCPlayer);
			}
		}

	}

	protected void sendPlanetList (EntityPlayerMP player, GCPlayerStats playerStats) {
		HashMap<String, Integer> map;
		if (player.ticksExisted % 50 == 0)
			map = WorldUtil.getArrayOfPossibleDimensions(playerStats.getSpaceshipTier(), player);
		else
			map = WorldUtil.getArrayOfPossibleDimensionsAgain(playerStats.getSpaceshipTier(), player);

		String temp  = "";
		int    count = 0;

		for (Entry<String, Integer> entry : map.entrySet()) {
			temp = temp.concat(entry.getKey() + (count < map.entrySet().size() - 1 ? "?" : ""));
			count++;
		}

		if (!temp.equals(playerStats.getSavedPlanetList()) || (player.ticksExisted % 1 == 0)) {

			boolean canCreateStations = PermissionAPI.hasPermission(player, Constants.PERMISSION_CREATE_STATION);
			ExoplanetsMod.pipeline.sendTo(new SimplePacket(EnumExoSimplePacket.C_UPDATE_DIMENSION_LIST, GCCoreUtil
					.getDimensionID(player.world), new Object[] { player.getGameProfile().getName(), temp,
							canCreateStations, playerStats.getSpaceshipTier(), playerStats.getFuelLevel() }), player);

			playerStats.setSavedPlanetList(temp);
		}
	}

}
