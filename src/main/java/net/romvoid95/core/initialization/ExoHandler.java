package net.romvoid95.core.initialization;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.common.*;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.romvoid95.api.registry.IInitialize;
import net.romvoid95.api.world.ExoBiomes;

public class ExoHandler implements IInitialize {
	
	public static final ExoHandler INSTANCE = new ExoHandler();
	
	final List<Biome> BIOMES = new ArrayList<>();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
	}
	
	@SubscribeEvent
	public void registerBiomes(RegistryEvent.Register<Biome> event){
		event.getRegistry().registerAll(BIOMES.toArray(new Biome[0]));
		
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ExoBiomes.exoOverworld, 10));
		BiomeManager.addVillageBiome(ExoBiomes.exoOverworld, true);
		
		BiomeDictionary.addTypes(ExoBiomes.exoOverworld, COLD, MOUNTAIN);
		BiomeDictionary.addTypes(ExoBiomes.yzCeti_d_plains, COLD, PLAINS);
		BiomeDictionary.addTypes(ExoBiomes.yzCeti_d_hills, COLD, HILLS, MOUNTAIN);
		BiomeDictionary.addTypes(ExoBiomes.yzCeti_d_cliffs, COLD, HILLS);
		BiomeDictionary.addTypes(ExoBiomes.yzCeti_d_sea, COLD, OCEAN);
	}

}
