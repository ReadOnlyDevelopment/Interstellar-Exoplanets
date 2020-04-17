package net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome;

import net.rom.api.enums.EnumBiomeType;
import net.rom.exoplanets.init.BlocksRegister;


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
		this.topBlock = BlocksRegister.YZB_SEDIMENTARY.getDefaultState();
		this.fillerBlock = BlocksRegister.YZB_METAMORPHIC.getDefaultState();
		this.stoneBlock = BlocksRegister.YZB_INGNEOUS;
	}
}