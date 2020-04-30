package net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.YzCetiDBiomes;

public class BiomeGenYzCetiD extends YzCetiDBiomes {

	public BiomeGenYzCetiD(BiomeProperties properties) {
		super(properties);
        this.topBlock = YzCetiBlocks.CetiD.D_SEDIMENTARYROCK.getDefaultState();
        this.fillerBlock = YzCetiBlocks.CetiD.D_METAMORPHIC.getDefaultState();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}

	@Override
	public void registerTypes(Biome b) {
            BiomeDictionary.addTypes(b, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);

	}
}
