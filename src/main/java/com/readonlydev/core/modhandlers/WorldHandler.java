package com.readonlydev.core.modhandlers;

import com.readonlydev.ExoInfo;
import com.readonlydev.api.registry.IInitialize;
import com.readonlydev.api.world.ExoBiomes;
import com.readonlydev.api.world.ExoDimensions;
import com.readonlydev.common.config.ConfigPlanets;
import com.readonlydev.common.world.BiomeObserverMountains;
import com.readonlydev.space.kepler1649.b.biomes.BiomeKepler1649B;
import com.readonlydev.space.kepler1649.b.biomes.BiomeKepler1649BCliffPlateau;
import com.readonlydev.space.kepler1649.b.biomes.BiomeKepler1649BCliffSwamp;
import com.readonlydev.space.wolf1061.b.worldgen.biome.BiomeGenWolfBCliffs;
import com.readonlydev.space.wolf1061.b.worldgen.biome.BiomeWolfBHills;
import com.readonlydev.space.wolf1061.b.worldgen.biome.BiomeWolfBPlains;
import com.readonlydev.space.wolf1061.b.worldgen.biome.BiomeWolfBSea;
import com.readonlydev.space.wolf1061.c.worldgen.BiomeGenWolf1061C;
import com.readonlydev.space.wolf1061.d.gen.BiomeGenWolf1061D;
import com.readonlydev.space.yzceti.b.worldgen.biomes.BiomeCetiBHills;
import com.readonlydev.space.yzceti.b.worldgen.biomes.BiomeCetiBPlains;
import com.readonlydev.space.yzceti.c.worldgen.biomes.BiomeGenYzCetiC;
import com.readonlydev.space.yzceti.c.worldgen.biomes.BiomeGenYzCetiCHills;
import com.readonlydev.space.yzceti.d.worldgen.biomes.BiomeCetiDCliffs;
import com.readonlydev.space.yzceti.d.worldgen.biomes.BiomeCetiDSea;
import com.readonlydev.space.yzceti.d.worldgen.biomes.BiomeGenYzCetiD;
import com.readonlydev.space.yzceti.d.worldgen.biomes.BiomeGenYzCetiDHills;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WorldHandler implements IInitialize {

	public static final Type EXOPLANET = Type.getType("EXOPLANET");

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
		ExoBiomes.ASTRONOMIC_MOUNTAINS = new BiomeObserverMountains();
		ExoBiomes.YZCETIB_PLAINS = new BiomeCetiBPlains();
		ExoBiomes.YZCETIB_HIGHPLAINS = new BiomeCetiBHills();
		ExoBiomes.YZCETIC_DUNES = new BiomeGenYzCetiCHills();
		ExoBiomes.YZCETIC_HIGHLANDS = new BiomeGenYzCetiC();
		ExoBiomes.YZCETID_PLAINS = new BiomeGenYzCetiD();
		ExoBiomes.YZCETID_HILLS = new BiomeGenYzCetiDHills();
		ExoBiomes.YZCETID_CLIFFS = new BiomeCetiDCliffs();
		ExoBiomes.YZCETID_SEA = new BiomeCetiDSea();
		ExoBiomes.WOLF1061B_PLAINS = new BiomeWolfBPlains();
		ExoBiomes.WOLF1061B_SEA = new BiomeWolfBSea();
		ExoBiomes.WOLF1061B_HILLS = new BiomeWolfBHills();
		ExoBiomes.WOLF1061B_CLIFFS = new BiomeGenWolfBCliffs();
		ExoBiomes.WOLF1061C_PLAINS = new BiomeGenWolf1061C();
		ExoBiomes.WOLF1061D_ATMOSPHERE = new BiomeGenWolf1061D();
		ExoBiomes.KEPLER1649B_MAIN = new BiomeKepler1649B();
		ExoBiomes.KEPLER1649B_CLIFF = new BiomeKepler1649BCliffPlateau();
		ExoBiomes.KEPLER1649B_BOTTOM = new BiomeKepler1649BCliffSwamp();
		
		buildOverworld(ExoBiomes.ASTRONOMIC_MOUNTAINS, Type.MOUNTAIN);
		build(ExoBiomes.YZCETIB_PLAINS, EXOPLANET, Type.PLAINS);
		build(ExoBiomes.YZCETIB_HIGHPLAINS, EXOPLANET, Type.PLAINS, Type.HILLS);
		build(ExoBiomes.YZCETIC_DUNES, EXOPLANET, Type.WASTELAND);
		build(ExoBiomes.YZCETIC_HIGHLANDS, EXOPLANET, Type.PLAINS, Type.HILLS);
		build(ExoBiomes.YZCETID_PLAINS, EXOPLANET, Type.PLAINS);
		build(ExoBiomes.YZCETID_HILLS, EXOPLANET, Type.WASTELAND);
		build(ExoBiomes.YZCETID_CLIFFS, EXOPLANET, Type.WASTELAND);
		build(ExoBiomes.YZCETID_SEA, EXOPLANET, Type.OCEAN);
		build(ExoBiomes.WOLF1061B_PLAINS, EXOPLANET, Type.PLAINS);
		build(ExoBiomes.WOLF1061B_SEA, EXOPLANET, Type.OCEAN);
		build(ExoBiomes.WOLF1061B_HILLS, EXOPLANET, Type.PLAINS, Type.HILLS);
		build(ExoBiomes.WOLF1061B_CLIFFS, EXOPLANET, Type.WASTELAND);
		build(ExoBiomes.WOLF1061C_PLAINS, EXOPLANET, Type.PLAINS, Type.HILLS);
		build(ExoBiomes.WOLF1061D_ATMOSPHERE, EXOPLANET, Type.VOID);
		build(ExoBiomes.KEPLER1649B_MAIN, EXOPLANET, Type.PLAINS);
		build(ExoBiomes.KEPLER1649B_CLIFF, EXOPLANET, Type.WASTELAND, Type.HILLS);
		build(ExoBiomes.KEPLER1649B_BOTTOM, EXOPLANET, Type.SWAMP);
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

		ExoDimensions.YZCETIB = WorldUtil.getDimensionTypeById(ConfigPlanets.id_yz_b);
		ExoDimensions.YZCETIC = WorldUtil.getDimensionTypeById(ConfigPlanets.id_yz_c);
		ExoDimensions.YZCETID = WorldUtil.getDimensionTypeById(ConfigPlanets.id_yz_d);

		ExoDimensions.WOLF1061_1B = WorldUtil.getDimensionTypeById(ConfigPlanets.id_wolf_b);
		ExoDimensions.WOLF1061_1C = WorldUtil.getDimensionTypeById(ConfigPlanets.id_wolf_c);
		ExoDimensions.WOLF1061_1D = WorldUtil.getDimensionTypeById(ConfigPlanets.id_wolf_d);

		ExoDimensions.TRAPPIST_1B = WorldUtil.getDimensionTypeById(ConfigPlanets.id_trap_b);
		ExoDimensions.TRAPPIST_1C = WorldUtil.getDimensionTypeById(ConfigPlanets.id_trap_c);
		ExoDimensions.TRAPPIST_1D = WorldUtil.getDimensionTypeById(ConfigPlanets.id_trap_d);
		ExoDimensions.TRAPPIST_1E = WorldUtil.getDimensionTypeById(ConfigPlanets.id_trap_e);
		ExoDimensions.TRAPPIST_1F = WorldUtil.getDimensionTypeById(ConfigPlanets.id_trap_f);
		ExoDimensions.TRAPPIST_1G = WorldUtil.getDimensionTypeById(ConfigPlanets.id_trap_g);
		ExoDimensions.TRAPPIST_1H = WorldUtil.getDimensionTypeById(ConfigPlanets.id_trap_h);

		ExoDimensions.KEPLER1649_B = WorldUtil.getDimensionTypeById(ConfigPlanets.id_kepler_b);
		ExoDimensions.KEPLER1649_C = WorldUtil.getDimensionTypeById(ConfigPlanets.id_kepler_c);
	}

	public void build(Biome biome, Type... biomeTypes) {
		ExoHandler.INSTANCE.BIOMESTYPES.put(biome, biomeTypes);
		ExoHandler.INSTANCE.BIOMES.add(biome);
	}
	
	public void buildOverworld(Biome biome, Type... biomeTypes) {
		ExoHandler.INSTANCE.BIOMESTYPES.put(biome, biomeTypes);
		ExoHandler.INSTANCE.BIOMES.add(biome.setRegistryName(new ResourceLocation(ExoInfo.MODID, "rocky_plateau")));
	}
	
	

}
