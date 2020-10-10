package net.romvoid95.core.initialization;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.BiomeProperties;

import net.minecraftforge.fml.common.event.*;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;

import net.romvoid95.api.registry.IInitialize;
import net.romvoid95.api.world.ExoBiomes;
import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.common.astronomy.yzceti.b.worldgen.biome.BiomeYzCetiB;
import net.romvoid95.common.astronomy.yzceti.b.worldgen.biome.BiomeYzCetiBDirty;
import net.romvoid95.common.astronomy.yzceti.c.worldgen.biomes.BiomeYzCetiC;
import net.romvoid95.common.astronomy.yzceti.c.worldgen.biomes.BiomeYzCetiCUnknown;
import net.romvoid95.common.astronomy.yzceti.d.worldgen.biomes.*;
import net.romvoid95.common.config.PlanetCoreConfig;
import net.romvoid95.common.world.biome.exo.BiomeRockyPlateau;
import net.romvoid95.core.ExoInfo;

public class WorldHandler implements IInitialize {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ExoBiomes.exoOverworld = new BiomeRockyPlateau(new BiomeProperties("Rocky Plateau"));
		
		ExoBiomes.yzCeti_d_plains = new BiomeGenYzCetiD("YzCeti D Plains", 0.125F, 0.05F);
		ExoBiomes.yzCeti_d_sea = new BiomeGenYzCetiDSea("YzCeti D Sea", -1.0F, 0.1F);
		ExoBiomes.yzCeti_d_hills = new BiomeGenYzCetiDHills("YzCeti D Hills", 1.1F, 0.5F);
		ExoBiomes.yzCeti_d_cliffs = new BiomeGenCragCliffs("YzCeti D Cliffs", 1.0F, 0.5F);

		ExoBiomes.yzCeti_b_base = new BiomeYzCetiB(new BiomeProperties("Yz Ceti B"));
		ExoBiomes.yzCeti_b_dirty = new BiomeYzCetiBDirty(new BiomeProperties("Yz Ceti B Dirty"));

		ExoBiomes.yzCeti_c_base = new BiomeYzCetiC(new BiomeProperties("Yz Ceti C"));
		ExoBiomes.yzCeti_c_unknown = new BiomeYzCetiCUnknown(new BiomeProperties("Yz Ceti Unknown"));


		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.exoOverworld.setRegistryName(new ResourceLocation(ExoInfo.MODID, "rocky_plateau")));
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_plains);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_hills);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_cliffs);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_d_sea);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_b_base);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_b_dirty);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_c_base);
		ExoHandler.INSTANCE.BIOMES.add(ExoBiomes.yzCeti_c_unknown);
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
