package net.rom.stellar.astronomy.biomes.yzceti;

import net.rom.stellar.astronomy.enums.EnumBiomeType;
import net.rom.stellar.init.ExoplanetsBlocks;


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
		this.topBlock = ExoplanetsBlocks.yzb_sedimentary.getDefaultState();
		this.fillerBlock = ExoplanetsBlocks.yzb_metamorphic.getDefaultState();
		this.stoneBlock = ExoplanetsBlocks.yzb_ingneous;
	}
	
	@Override
	public float getSpawningChance() {
		return 0.01F;
	}
}