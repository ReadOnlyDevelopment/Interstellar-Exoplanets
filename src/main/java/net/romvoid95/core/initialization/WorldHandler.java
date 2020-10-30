package net.romvoid95.core.initialization;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.romvoid95.api.registry.IInitialize;
import net.romvoid95.api.world.ExoBiomes;
import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.common.config.ConfigPlanets;
import net.romvoid95.common.world.BiomeRockyPlateau;
import net.romvoid95.core.ExoInfo;
import net.romvoid95.space.wolf1061.b.worldgen.biome.BiomeGenWolfBCliffs;
import net.romvoid95.space.wolf1061.b.worldgen.biome.BiomeGenWolfBHills;
import net.romvoid95.space.wolf1061.b.worldgen.biome.BiomeGenWolfBPlains;
import net.romvoid95.space.wolf1061.b.worldgen.biome.BiomeGenWolfBSea;
import net.romvoid95.space.wolf1061.c.worldgen.BiomeGenWolf1061C;
import net.romvoid95.space.wolf1061.d.gen.BiomeGenWolf1061D;
import net.romvoid95.space.yzceti.b.worldgen.biomes.BiomeGenYzCetiB;
import net.romvoid95.space.yzceti.b.worldgen.biomes.BiomeGenYzCetiBHills;
import net.romvoid95.space.yzceti.c.worldgen.biomes.BiomeGenFrozenSea;
import net.romvoid95.space.yzceti.c.worldgen.biomes.BiomeGenYzCetiC;
import net.romvoid95.space.yzceti.d.worldgen.biomes.BiomeGenCragCliffs;
import net.romvoid95.space.yzceti.d.worldgen.biomes.BiomeGenYzCetiD;
import net.romvoid95.space.yzceti.d.worldgen.biomes.BiomeGenYzCetiDHills;
import net.romvoid95.space.yzceti.d.worldgen.biomes.BiomeGenYzCetiDSea;

public class WorldHandler implements IInitialize {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ExoBiomes.ASTRONOMIC_MOUNTAINS = new BiomeRockyPlateau(new BiomeProperties("Rocky Plateau"));
		
		ExoBiomes.YZCETID_PLAINS = new BiomeGenYzCetiD("YzCeti D Plains", 0.125F, 0.05F);
		ExoBiomes.YZCETID_SEA = new BiomeGenYzCetiDSea("YzCeti D Sea", -1.0F, 0.1F);
		ExoBiomes.YZCETID_HILLS = new BiomeGenYzCetiDHills("YzCeti D Hills", 1.1F, 0.5F);
		ExoBiomes.YZCETID_CLIFFS = new BiomeGenCragCliffs("YzCeti D Cliffs", 1.0F, 0.5F);
		ExoBiomes.YZCETIB_PLAINS = new BiomeGenYzCetiB("YzCeti B", 0.125F, 0.05F);
		ExoBiomes.YZCETIB_HIGHPLAINS = new BiomeGenYzCetiBHills("YzCeti B Hills", 1.1F, 0.5F);
		ExoBiomes.YZCETIC_DUNES = new BiomeGenYzCetiC("YzCeti C Plains", 1.5F, 0.6F);
		ExoBiomes.YZCETIC_HIGHLANDS = new BiomeGenFrozenSea("YzCeti C Sea", -0.6F, 0);
		ExoBiomes.WOLF1061D_ATMOSPHERE = new BiomeGenWolf1061D("Wolf1061 D Sky");
		ExoBiomes.WOLF1061B_PLAINS = new BiomeGenWolfBPlains("Wolf1061 B Plains", 0.1F, 0.02F);
		ExoBiomes.WOLF1061B_SEA = new BiomeGenWolfBSea("Wolf1061 B Sea", -0.6F, 0.1F);
		ExoBiomes.WOLF1061B_HILLS = new BiomeGenWolfBHills("Wolf1061 B Hills", 0.9F, 0.5F);
		ExoBiomes.WOLF1061B_CLIFFS = new BiomeGenWolfBCliffs("Wolf1061 B Cliffs", 0.5F, 0.2F);
		ExoBiomes.WOLF1061C_PLAINS = new BiomeGenWolf1061C("Wolf1061 C Plains", 2.5F, 0.4F);
		
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.ASTRONOMIC_MOUNTAINS.setRegistryName(new ResourceLocation(ExoInfo.MODID, "rocky_plateau")));
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETID_PLAINS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETID_HILLS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETID_CLIFFS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETID_SEA);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETIB_PLAINS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETIB_HIGHPLAINS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETIC_DUNES);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.YZCETIC_HIGHLANDS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.WOLF1061D_ATMOSPHERE);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.WOLF1061B_PLAINS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.WOLF1061B_SEA);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.WOLF1061B_HILLS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.WOLF1061B_CLIFFS);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.WOLF1061C_PLAINS);
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

}
