package net.rom.exoplanets.internal;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * A singleton that has preInit, init, and postInit methods. If registered in the StellarRegistry, these are called
 * automatically.
 *
 */
public interface IPhaseInit {
	
    default void preInit(StellarRegistry registry, FMLPreInitializationEvent event) {
    }

    default void init(StellarRegistry registry, FMLInitializationEvent event) {
    }

    default void postInit(StellarRegistry registry, FMLPostInitializationEvent event) {
    }

}
