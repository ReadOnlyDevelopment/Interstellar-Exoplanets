package net.romvoid95.api.registry;

import net.minecraftforge.fml.common.event.*;

public interface IInitialize {
	
	public interface IHandler {
		
	}

	void preInit (FMLPreInitializationEvent event);

	void init (FMLInitializationEvent event);

	void postInit (FMLPostInitializationEvent event);

}
