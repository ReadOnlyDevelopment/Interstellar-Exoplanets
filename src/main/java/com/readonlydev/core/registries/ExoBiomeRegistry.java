package com.readonlydev.core.registries;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.readonlydev.common.world.BiomeObserverMountains;
import com.readonlydev.lib.world.biome.ExoplanetBiome;
import com.readonlydev.space.generation.biome.ExoBiome;
import com.readonlydev.space.kepler1649.b.biomes.BiomeKepler1649B;
import com.readonlydev.space.kepler1649.b.biomes.BiomeKepler1649BCliffPlateau;
import com.readonlydev.space.kepler1649.b.biomes.BiomeKepler1649BCliffSwamp;
import com.readonlydev.space.kepler1649.c.biomes.BiomeGenKepler1649C;
import com.readonlydev.space.kepler1649.c.biomes.BiomeGenKepler1649CHills;
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

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ExoBiomeRegistry {

	public static final Type EXOPLANET = Type.getType("EXOPLANET");

	// ============== Yz Ceti B ==============
	public static final Biome YZCETIB_PLAINS = new BiomeCetiBPlains() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.PLAINS);
		};
	};
	
	public static final Biome YZCETIB_HIGHPLAINS = new BiomeCetiBHills() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.HILLS);
		};
	};

	// ============== Yz Ceti C ==============
	public static final Biome YZCETIC_DUNES = new BiomeGenYzCetiCHills() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.HILLS);
		};
	};
	
	public static final Biome YZCETIC_HIGHLANDS = new BiomeGenYzCetiC() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.PLAINS, Type.HILLS);
		};
	};

	// ============== Yz Ceti D ==============
	public static final Biome YZCETID_PLAINS = new BiomeGenYzCetiD() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.PLAINS);
		};
	};
	
	public static final Biome YZCETID_HILLS = new BiomeGenYzCetiDHills() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.WASTELAND, Type.HILLS);
		};
	};
	
	public static final Biome YZCETID_CLIFFS = new BiomeCetiDCliffs() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET,Type.WASTELAND, Type.HILLS);
		};
	};
	
	public static final Biome YZCETID_SEA = new BiomeCetiDSea() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.OCEAN, Type.HILLS);
		};
	};

	// ============== Wolf 1061 B ==============
	public static final Biome WOLF1061B_PLAINS = new BiomeWolfBPlains() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.PLAINS);
		};
	};
	
	public static final Biome WOLF1061B_SEA = new BiomeWolfBSea() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.OCEAN);
		};
	};
	
	public static final Biome WOLF1061B_HILLS = new BiomeWolfBHills() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.HILLS);
		};
	};
	
	public static final Biome WOLF1061B_CLIFFS = new BiomeGenWolfBCliffs() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.MESA);
		};
	};

	// ============== Wolf 1061 C ==============
	public static final Biome WOLF1061C_PLAINS = new BiomeGenWolf1061C() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.PLAINS);
		};
	};

	// ============== Wolf 1061 D ==============
	public static final Biome WOLF1061D_ATMOSPHERE = new BiomeGenWolf1061D() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.VOID);
		};
	};

	// ============== Kepler 1649 B ==============
	public static final Biome KEPLER1649B_MAIN = new BiomeKepler1649B() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.PLAINS);
		};
	};
	
	public static final Biome KEPLER1649B_CLIFF = new BiomeKepler1649BCliffPlateau() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.HILLS);
		};
	};
	
	public static final Biome KEPLER1649B_BOTTOM = new BiomeKepler1649BCliffSwamp() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.SWAMP);
		};
	};
	
	// ============== Kepler 1649 C ==============
	public static final Biome KEPLER1649C_MAIN = new BiomeGenKepler1649C() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.PLAINS);
		};
	};
	
	public static final Biome KEPLER1649C_CLIFFS = new BiomeGenKepler1649CHills() {
		@Override
		public void addTypes() {
			BiomeDictionary.addTypes(this, EXOPLANET, Type.HILLS);
		};
	};

	public static final List<Biome> REGISTERED_BIOMES = new ArrayList<Biome>();

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        final IForgeRegistry<Biome> registry = event.getRegistry();
        try {
            for (Field f : ExoBiomeRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof ExoplanetBiome) {
                	ExoplanetBiome biome = (ExoplanetBiome) obj;
                    registry.register(biome);
                    biome.addTypes();
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
