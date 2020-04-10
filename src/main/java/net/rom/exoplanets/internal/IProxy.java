package net.rom.exoplanets.internal;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    void preInit(StellarRegistry registry, FMLPreInitializationEvent event);

    void init(StellarRegistry registry, FMLInitializationEvent event);

    void postInit(StellarRegistry registry, FMLPostInitializationEvent event);

    @Nullable EntityPlayer getClientPlayer();
}
