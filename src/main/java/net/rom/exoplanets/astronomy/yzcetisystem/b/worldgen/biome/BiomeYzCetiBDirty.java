package net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome;

import net.rom.api.enums.EnumBiomeType;
import net.rom.exoplanets.init.BlocksRegister;

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
		this.topBlock = BlocksRegister.YZB_METAMORPHIC.getDefaultState();
		this.fillerBlock = BlocksRegister.YZB_METAMORPHIC.getDefaultState();
		this.stoneBlock = BlocksRegister.YZB_INGNEOUS;
	}
}