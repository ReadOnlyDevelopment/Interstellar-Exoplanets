package net.romvoid95.common.world.testing;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeTesting extends WorldType {
	
	private static WorldTypeTesting INSTANCE;

	private WorldTypeTesting() {
		super("EXOPLANETSTEST");
		this.enableInfoNotice();
	}
	
    public static WorldTypeTesting getInstance() {
        if (INSTANCE == null) {
            init();
        }
        return INSTANCE;
    }

    public static void init() {
        INSTANCE = new WorldTypeTesting();
    }

	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(Biomes.PLAINS);
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorTesting(world);
	}
	
    @Override
    public boolean isCustomizable()
    {
        return true;
    }
}
