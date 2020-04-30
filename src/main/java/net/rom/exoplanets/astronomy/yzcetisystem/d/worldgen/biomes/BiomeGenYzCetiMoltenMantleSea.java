package net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.biomes;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.YzCetiDBiomes;

import com.google.common.collect.Lists;

public class BiomeGenYzCetiMoltenMantleSea extends YzCetiDBiomes {

	public BiomeGenYzCetiMoltenMantleSea(BiomeProperties properties) {
		super(properties);
		this.topBlock = YzCetiBlocks.CetiD.D_SEDIMENTARYROCK.getDefaultState();
		this.fillerBlock = YzCetiBlocks.CetiD.D_IGNEOUS.getDefaultState();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}

	@Override
	public List<Biome.SpawnListEntry> getSpawnableList(EnumCreatureType creatureType) {
		return Lists.<Biome.SpawnListEntry> newArrayList();
	}

	@Override
	public void registerTypes(Biome b) {
            BiomeDictionary.addTypes(b, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.OCEAN);
	}
}
