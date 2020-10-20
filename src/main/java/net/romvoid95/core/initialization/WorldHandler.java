package net.romvoid95.core.initialization;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.BiomeProperties;

import net.minecraftforge.fml.common.event.*;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;

import net.romvoid95.api.registry.IInitialize;
import net.romvoid95.api.world.ExoBiomes;
import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.common.astronomy.wolf1061.d.gen.BiomeGenWolf1061D;
import net.romvoid95.common.astronomy.yzceti.b.worldgen.biomes.BiomeGenYzCetiB;
import net.romvoid95.common.astronomy.yzceti.b.worldgen.biomes.BiomeGenYzCetiBHills;
import net.romvoid95.common.astronomy.yzceti.c.worldgen.biomes.BiomeGenFrozenSea;
import net.romvoid95.common.astronomy.yzceti.c.worldgen.biomes.BiomeGenYzCetiC;
import net.romvoid95.common.astronomy.yzceti.d.worldgen.biomes.*;
import net.romvoid95.common.config.PlanetCoreConfig;
import net.romvoid95.common.world.BiomeRockyPlateau;
import net.romvoid95.core.ExoInfo;

public class WorldHandler implements IInitialize {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ExoBiomes.exoOverworld = new BiomeRockyPlateau(new BiomeProperties("Rocky Plateau"));
		
		ExoBiomes.yzCeti_d_plains = new BiomeGenYzCetiD("YzCeti D Plains", 0.125F, 0.05F);
		ExoBiomes.yzCeti_d_sea = new BiomeGenYzCetiDSea("YzCeti D Sea", -1.0F, 0.1F);
		ExoBiomes.yzCeti_d_hills = new BiomeGenYzCetiDHills("YzCeti D Hills", 1.1F, 0.5F);
		ExoBiomes.yzCeti_d_cliffs = new BiomeGenCragCliffs("YzCeti D Cliffs", 1.0F, 0.5F);

		ExoBiomes.yzCeti_b_base = new BiomeGenYzCetiB("Yz Ceti B", 0.125F, 0.05F);
		ExoBiomes.yzCeti_b_dirty = new BiomeGenYzCetiBHills("Yz Ceti B Hills", 1.1F, 0.5F);

		ExoBiomes.yzCeti_c_base = new BiomeGenYzCetiC("Yz Ceti C Plains", 1.5F, 0.6F);
		ExoBiomes.yzCeti_c_unknown = new BiomeGenFrozenSea("Yz Ceti C Sea", -0.6F, 0);
		
		ExoBiomes.wolf1061_d_main = new BiomeGenWolf1061D("Wolf 1061 D Sky");


		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.exoOverworld.setRegistryName(new ResourceLocation(ExoInfo.MODID, "rocky_plateau")));
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_plains);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_hills);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_cliffs);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_sea);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_b_base);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_b_dirty);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_c_base);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_c_unknown);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.wolf1061_d_main);
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
		ExoDimensions.YZCETIB = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_yz_b);
		ExoDimensions.YZCETIC = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_yz_c);
		ExoDimensions.YZCETID = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_yz_d);

		ExoDimensions.WOLF1061_1B = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_wolf_b);
		ExoDimensions.WOLF1061_1C = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_wolf_c);
		ExoDimensions.WOLF1061_1D = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_wolf_d);

		ExoDimensions.TRAPPIST_1B = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_trap_b);
		ExoDimensions.TRAPPIST_1C = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_trap_c);
		ExoDimensions.TRAPPIST_1D = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_trap_d);
		ExoDimensions.TRAPPIST_1E = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_trap_e);
		ExoDimensions.TRAPPIST_1F = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_trap_f);
		ExoDimensions.TRAPPIST_1G = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_trap_g);
		ExoDimensions.TRAPPIST_1H = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_trap_h);

		ExoDimensions.KEPLER1649_B = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_kepler_b);
		ExoDimensions.KEPLER1649_C = WorldUtil.getDimensionTypeById(PlanetCoreConfig.id_kepler_c);
	}

}
