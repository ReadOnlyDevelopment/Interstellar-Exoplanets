package net.rom.exoplanets.astronomy.biomes.yzceti;

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
		this.topBlock = BlocksRegister.yzb_sedimentary.getDefaultState();
		this.fillerBlock = BlocksRegister.yzb_metamorphic.getDefaultState();
		this.stoneBlock = BlocksRegister.yzb_ingneous;
	}
}