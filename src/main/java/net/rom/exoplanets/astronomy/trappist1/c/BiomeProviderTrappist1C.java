package net.rom.exoplanets.astronomy.trappist1.c;

import asmodeuscore.core.astronomy.dimension.world.gen.ACBiome;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;
import net.minecraft.world.biome.Biome;

public class BiomeProviderTrappist1C extends BiomeProviderSpace {
	@Override
	public Biome getBiome() {
		return ACBiome.ACSpace;
	}

}
