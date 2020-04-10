package net.rom.stellar.astronomy.biomes.yzceti;

import net.rom.stellar.astronomy.enums.EnumBiomeType;
import net.rom.stellar.init.BlocksRegister;


public class BiomeYzCetiC extends BiomeYzCetiBBase {
	
	public BiomeYzCetiC(BiomeProperties props) {
		super("yzc", props);
		props.setRainDisabled();
		props.setBaseHeight(1.0F);
		props.setHeightVariation(0.8F);
		props.setTemperature(5.0F);
		this.setTemp(5F);
		this.setBiomeHeight(72);
		this.setBiomeType(EnumBiomeType.DARK);
		this.topBlock = BlocksRegister.yzc_sedimentary.getDefaultState();
		this.fillerBlock = BlocksRegister.yzc_metamorphic.getDefaultState();
		this.stoneBlock = BlocksRegister.yzc_ingneous;
	}
}