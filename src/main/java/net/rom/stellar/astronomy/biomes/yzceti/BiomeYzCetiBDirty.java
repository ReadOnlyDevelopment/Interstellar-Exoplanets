package net.rom.stellar.astronomy.biomes.yzceti;

import net.rom.stellar.astronomy.enums.EnumBiomeType;
import net.rom.stellar.init.BlocksRegister;

public class BiomeYzCetiBDirty extends BiomeYzCetiBBase {
	
	public static int grassFoilageColorMultiplier = 0x000000;
	
	public BiomeYzCetiBDirty(BiomeProperties props) {
		super("dirty", props);
		props.setRainDisabled();
		props.setBaseHeight(2.0F);
		props.setHeightVariation(0.6F);
		props.setTemperature(2.0F);
		this.setTemp(2F);
		this.setBiomeHeight(82);
		this.setBiomeType(EnumBiomeType.ABANDONED);
		this.topBlock = BlocksRegister.dirt.getDefaultState();
		this.fillerBlock = BlocksRegister.yzb_metamorphic.getDefaultState();
		this.stoneBlock = BlocksRegister.yzb_ingneous;
	}
}