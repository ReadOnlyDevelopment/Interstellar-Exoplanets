package net.romvoid95.common.astronomy.trappist1.d.client;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.world.weather.IClimateProvider;
import net.romvoid95.common.ExoplanetSounds;

@SideOnly(Side.CLIENT)
public class TickHandlerClientTrappistD {
	private Map<BlockPos, Integer> lightning = Maps.newHashMap();

	@SubscribeEvent
	public void renderLightning (ClientProxyCore.EventSpecialRender event) {
		final Minecraft      minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player    = minecraft.player;
		if (player != null) {
			if (player.lastTickPosY < player.world.provider.getCloudHeight()) {
				Iterator<Map.Entry<BlockPos, Integer>> it = lightning.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<BlockPos, Integer> entry = it.next();
					long                         seed  = entry.getValue() / 10 + entry.getKey().getX()
							+ entry.getKey().getZ();
					Lightning.renderBolt(seed, entry.getKey().getX() - ClientProxyCore.playerPosX, entry.getKey().getY()
							- ClientProxyCore.playerPosY, entry.getKey().getZ() - ClientProxyCore.playerPosZ);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerTick (TickEvent.PlayerTickEvent event) {
		final Minecraft      minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player    = minecraft.player;

		if (player == event.player) {
			Iterator<Map.Entry<BlockPos, Integer>> it = lightning.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<BlockPos, Integer> entry = it.next();
				int                          val   = entry.getValue();
				if (val - 1 <= 0) {
					it.remove();
				}
				else {
					entry.setValue(val - 1);
				}
			}

			if (player.getRNG().nextInt(300 + (int) (800F * minecraft.world.rainingStrength)) < 8
					&& minecraft.world.provider instanceof IClimateProvider) {
				double freq = player.getRNG().nextDouble() * 25.0F;
				double dist = 100.0F;
				double dX   = dist * Math.cos(freq);
				double dZ   = dist * Math.sin(freq);
				double posX = player.posX + dX;
				double posY = 95;
				double posZ = player.posZ + dZ;
				minecraft.world
						.playSound(player, posX, posY, posZ, ExoplanetSounds.CUSTOM_THUNDER, SoundCategory.WEATHER, 350.0F
								+ player.getRNG().nextFloat() * 100F, 1.0F + player.getRNG().nextFloat() * 0.2F);
				lightning.put(new BlockPos(posX, posY, posZ), 20);
			}
		}
	}
}
