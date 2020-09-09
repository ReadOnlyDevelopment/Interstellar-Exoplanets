package net.romvoid95.api.registry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IInitialize {

	default void preInit (ExoRegistry registry, FMLPreInitializationEvent event) {}

	default void init (ExoRegistry registry, FMLInitializationEvent event) {}

	default void postInit (ExoRegistry registry, FMLPostInitializationEvent event) {}

}
