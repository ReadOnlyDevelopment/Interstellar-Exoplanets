package net.rom.exoplanets.client.handlers;

import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.yzcetisystem.b.SkyProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzcetisystem.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzcetisystem.c.WorldProviderYzCetiC;
import net.rom.exoplanets.astronomy.yzcetisystem.d.WorldProviderYzCetiD;
import net.rom.exoplanets.util.MCUtil;

public class SkyProviders {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        final Minecraft minecraft = MCUtil.getClient();
        final WorldClient world = minecraft.world;
        final EntityPlayerSP player = minecraft.player;

        if (event.phase == Phase.START && player != null && world != null) {
            // Planets
            if (world.provider instanceof WorldProviderYzCetiB) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderYzCetiB(WorldProviderYzCetiB.instance().getSolarSize()));
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            if (world.provider instanceof WorldProviderYzCetiC) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderYzCetiB(WorldProviderYzCetiC.instance().getSolarSize()));
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
            if (world.provider instanceof WorldProviderYzCetiD) {
                if (world.provider.getSkyRenderer() == null) {
                    world.provider.setSkyRenderer(new SkyProviderYzCetiB(WorldProviderYzCetiD.instance().getSolarSize()));
                }

                if (world.provider.getCloudRenderer() == null) {
                    world.provider.setCloudRenderer(new CloudRenderer());
                }
            }
        }
    }
}
