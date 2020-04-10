package net.rom.stellar.astronomy.biomes.yzceti;

import net.rom.stellar.astronomy.enums.EnumBiomeType;
import net.rom.stellar.init.BlocksRegister;

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
		this.topBlock = BlocksRegister.yzc_sedimentary.getDefaultState();
		this.fillerBlock = BlocksRegister.yzc_metamorphic.getDefaultState();
		this.stoneBlock = BlocksRegister.yzc_ingneous;
	}
}