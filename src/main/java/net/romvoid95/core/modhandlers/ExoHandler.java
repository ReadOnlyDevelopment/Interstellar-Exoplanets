package net.romvoid95.core.modhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.romvoid95.ExoplanetsMod;
import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.api.registry.IInitialize;
import net.romvoid95.api.world.ExoBiomes;
import net.romvoid95.common.ExoplanetSounds;
import net.romvoid95.common.world.testing.WorldTypeTesting;
import net.romvoid95.core.ExoBlocks;
import net.romvoid95.core.ExoEntities;
import net.romvoid95.core.ExoItems;

public class ExoHandler implements IInitialize {
	
	public static final ExoHandler INSTANCE = new ExoHandler();
	
	final List<Biome> BIOMES = new ArrayList<>();
	final Map<Biome, Type[]> BIOMESTYPES = new HashMap<Biome, Type[]>();
	private ExoRegistry registry = ExoplanetsMod.REGISTRY;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		registry.addRegistrationHandler(ExoEntities::registerAll, EntityEntry.class);
		registry.addRegistrationHandler(ExoBlocks::registerAll, Block.class);
		registry.addRegistrationHandler(ExoItems::registerAll, Item.class);
		registry.addRegistrationHandler(ExoplanetSounds::registerAll, SoundEvent.class);
		
		WorldTypeTesting.init();
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
		
		BIOMESTYPES.forEach((b,t) ->  {
			BiomeDictionary.addTypes(b, t);
		});
		
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ExoBiomes.ASTRONOMIC_MOUNTAINS, 10));

	}

}
