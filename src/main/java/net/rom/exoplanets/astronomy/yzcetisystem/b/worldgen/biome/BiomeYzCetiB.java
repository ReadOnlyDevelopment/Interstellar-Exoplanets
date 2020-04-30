package net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome;

import net.rom.api.stellar.enums.EnumBiomeType;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;


public class BiomeYzCetiB extends BiomeYzCetiBBase {
	
	public BiomeYzCetiB(BiomeProperties props) {
		super("yzb", props);
		props.setRainDisabled();
		props.setBaseHeight(1.0F);
		props.setHeightVariation(0.8F);
		props.setTemperature(5.0F);
		this.setTemp(5F);
		this.setBiomeHeight(72);
		this.setBiomeType(EnumBiomeType.DARK);
		this.topBlock = YzCetiBlocks.CetiB.B_DARK_LOOSE_SEDIMENT.getDefaultState();
		this.fillerBlock = YzCetiBlocks.CetiB.B_METAMORPHIC.getDefaultState();
		this.stoneBlock = YzCetiBlocks.CetiB.B_IGNEOUS;
	}
}