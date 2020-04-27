package net.rom.exoplanets.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rom.exoplanets.client.handlers.GuiScreenHandler;
import net.rom.exoplanets.init.ExoFluids;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoClientProxy extends ExoCommonProxy {
	

	@Override
	public void preInit(StellarRegistry registry, FMLPreInitializationEvent event) {
		super.preInit(registry, event);
		
		ExoFluids.bakeModels();
		MinecraftForge.EVENT_BUS.register(new GuiScreenHandler());
		registry.clientPreInit(event);
		
	}

	@Override
	public void init(StellarRegistry registry, FMLInitializationEvent event) {
		super.init(registry, event);
		
		registry.clientInit(event);
	}

	@Override
	public void postInit(StellarRegistry registry, FMLPostInitializationEvent event) {
		super.postInit(registry, event);
		
		registry.clientPostInit(event);
	}

	public World getWorld() {
		return Minecraft.getMinecraft().world;
	}
	
    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }
}
