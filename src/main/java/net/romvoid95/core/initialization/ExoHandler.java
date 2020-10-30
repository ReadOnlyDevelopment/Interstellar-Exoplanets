package net.romvoid95.core.initialization;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
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
import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.api.registry.IInitialize;
import net.romvoid95.api.world.ExoBiomes;
import net.romvoid95.common.ExoplanetSounds;
import net.romvoid95.core.ExoplanetsMod;

public class ExoHandler implements IInitialize {
	
	public static final ExoHandler INSTANCE = new ExoHandler();
	
	final List<Biome> BIOMES = new ArrayList<>();
	private ExoRegistry registry = ExoplanetsMod.REGISTRY;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		registry.addRegistrationHandler(ExoEntities::registerAll, EntityEntry.class);
		registry.addRegistrationHandler(ExoBlocks::registerAll, Block.class);
		registry.addRegistrationHandler(ExoItems::registerAll, Item.class);
		registry.addRegistrationHandler(ExoplanetSounds::registerAll, SoundEvent.class);
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
		
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ExoBiomes.ASTRONOMIC_MOUNTAINS, 10));
		BiomeManager.addVillageBiome(ExoBiomes.ASTRONOMIC_MOUNTAINS, true);
		
		for(Biome biome : BIOMES) {
			if(!(biome.getBiomeName() == "Rocky Plateau")) {
				BiomeDictionary.addTypes(biome, COLD, DEAD);
			}
		}
		
		BiomeDictionary.addTypes(ExoBiomes.ASTRONOMIC_MOUNTAINS, COLD, MOUNTAIN);

	}

}
