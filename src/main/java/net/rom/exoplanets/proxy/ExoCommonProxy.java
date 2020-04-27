package net.rom.exoplanets.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rom.exoplanets.internal.IProxy;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoCommonProxy implements IProxy {
	

	@Override
	public void preInit(StellarRegistry registry, FMLPreInitializationEvent event) {
		registry.preInit(event);		
	}

	@Override
	public void init(StellarRegistry registry, FMLInitializationEvent event) {
		registry.init(event);
		
	}

	@Override
	public void postInit(StellarRegistry registry, FMLPostInitializationEvent event) {
		registry.postInit(event);
		
	}
	
    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }
}
