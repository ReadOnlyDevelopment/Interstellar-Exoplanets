package net.rom.exoplanets.astronomy.yzcetisystem.c.worldgen.biomes;

import net.rom.api.stellar.enums.EnumBiomeType;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome.BiomeYzCetiBBase;

public class BiomeYzCetiCUnknown extends BiomeYzCetiBBase {

	public static int grassFoilageColorMultiplier = 0x000000;

	public BiomeYzCetiCUnknown(BiomeProperties props) {
		super("unknown", props);
		props.setRainDisabled();
		props.setBaseHeight(6.0F);
		props.setHeightVariation(3.6F);
		props.setTemperature(2.0F);
		this.setTemp(2F);
		this.setBiomeHeight(82);
		this.setBiomeType(EnumBiomeType.ABANDONED);
		this.topBlock = YzCetiBlocks.CetiC.C_IGNEOUS.getDefaultState();
		this.fillerBlock = YzCetiBlocks.CetiC.C_GRAVEL.getDefaultState();
		this.stoneBlock = YzCetiBlocks.CetiC.C_METAMORPHIC;
	}
}