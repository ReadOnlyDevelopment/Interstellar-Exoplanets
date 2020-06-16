package net.rom.exoplanets.capability;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Compat {
	
	@SubscribeEvent
	public void GCSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		
		if(event.getEntity() instanceof EntityPlayer) {
			GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) event.getEntity());
			if(stats != null)
				stats.setLastOxygenSetupValid(true);
		}

		event.setCanceled(true);
	}
}
